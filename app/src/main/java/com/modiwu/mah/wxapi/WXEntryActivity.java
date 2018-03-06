package com.modiwu.mah.wxapi;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;

import com.modiwu.mah.base.BaseApplication;
import com.modiwu.mah.mvp.model.LoginModel;
import com.modiwu.mah.mvp.model.event.TokenEvent;
import com.modiwu.mah.mvp.model.event.WXLoginSuccessEvent;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.greenrobot.eventbus.EventBus;

import top.jplayer.baseprolibrary.utils.ToastUtils;


/**
 * Created by Obl on 2017/4/5.
 * com.ilanchuang.xiaoi.wxapi
 */

public class WXEntryActivity extends AppCompatActivity implements IWXAPIEventHandler {

    private IWXAPI mWxapi;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mWxapi = WXAPIFactory.createWXAPI(this, BaseApplication.APP_ID, false);
        mWxapi.handleIntent(getIntent(), this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        mWxapi.handleIntent(intent, this);
    }

    @Override
    public void onReq(BaseReq baseReq) {
        finish();
    }


    @Override
    public void onResp(BaseResp resp) {
        String result;
        switch (resp.errCode) {
            case BaseResp.ErrCode.ERR_OK:
                check(resp, "分享成功");
                finish();
                break;
            case BaseResp.ErrCode.ERR_USER_CANCEL:
                check(resp, "用户取消");
                finish();
                break;
            case BaseResp.ErrCode.ERR_AUTH_DENIED:
                finish();
                break;
            default:
                result = "发送请求被拒绝";
                ToastUtils.init().showInfoToast(this, result);
                finish();
                break;
        }
    }

    private void check(BaseResp resp, String msg) {
        if (resp instanceof SendMessageToWX.Resp) {
            ToastUtils.init().showSuccessToast(this, msg);
        } else {
            if (TextUtils.equals("分享成功", msg)) {
                wxLogin((SendAuth.Resp) resp);
            }
        }
    }

    private void wxLogin(SendAuth.Resp resp) {
        LoginModel model = new LoginModel();
        String token = resp.code;
        model.requestWxToken(token).subscribe(loginBean -> {
            if (loginBean != null) {
                if (loginBean.isWxLoginFail()) {
                    ToastUtils.init().showInfoToast(this, loginBean.msg);
                    EventBus.getDefault().post(new TokenEvent(loginBean.code, token));
                } else {
                    EventBus.getDefault().post(new WXLoginSuccessEvent(loginBean));
                }
            } else {
                loginError();
            }
            this.finish();
        }, throwable -> loginError());
    }

    private void loginError() {
        ToastUtils.init().showErrorToast(this, "请求失败");
    }
}
