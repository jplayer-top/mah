package com.modiwu.mah.wxapi;

import com.modiwu.mah.base.BaseSpecialActivity;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import top.jplayer.baseprolibrary.utils.ToastUtils;

import static android.provider.UserDictionary.Words.APP_ID;


/**
 * Created by Obl on 2017/4/5.
 * com.ilanchuang.xiaoi.wxapi
 */

public class WXEntryActivity extends BaseSpecialActivity implements IWXAPIEventHandler {


    @Override
    public int setBaseLayout() {
        return top.jplayer.baseprolibrary.R.layout.fragment_base;
    }

    @Override
    public void initBaseData() {
        IWXAPI wxapi = WXAPIFactory.createWXAPI(this, APP_ID, false);
        wxapi.handleIntent(getIntent(), this);
        mBaseView.setAlpha(0);

    }

    @Override
    public void onReq(BaseReq baseReq) {
        finish();
    }


    @Override
    public void onResp(BaseResp resp) {
        String result;
//        WXLoginPresenter mPresenter = new WXLoginPresenter(this);
        switch (resp.errCode) {
            case BaseResp.ErrCode.ERR_OK:
                String code = ((SendAuth.Resp) resp).code;
//                mPresenter.wxLogin(code);
                break;
            case BaseResp.ErrCode.ERR_USER_CANCEL:
                finish();
                break;
            case BaseResp.ErrCode.ERR_AUTH_DENIED:
                finish();
                break;
            default:
                result = "认证失败，请用手机号注册";
                ToastUtils.init().showInfoToast(this, result);
                finish();
                break;
        }
    }

}
