package com.modiwu.mah.mvp.presenter;


import android.text.TextUtils;
import android.util.Log;
import android.widget.TextView;

import com.modiwu.mah.mvp.constract.LoginAnimContract;
import com.modiwu.mah.mvp.model.LoginModel;
import com.modiwu.mah.mvp.model.bean.LoginBean;
import com.modiwu.mah.mvp.model.bean.RegisterBean;
import com.modiwu.mah.ui.activity.LoginAnimActivity;

import java.util.Map;

import io.reactivex.disposables.Disposable;
import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;
import top.jplayer.baseprolibrary.mvp.contract.BasePresenter;
import top.jplayer.baseprolibrary.net.SampleShowDialogObserver;
import top.jplayer.baseprolibrary.utils.SharePreUtil;

import static com.modiwu.mah.base.BaseApplication.getCurProcessName;

/**
 * Created by Obl on 2018/1/29.
 * com.modiwu.mah.mvp.presenter
 */

public class LoginPresenter extends BasePresenter<LoginAnimActivity> implements LoginAnimContract.ILoginAnimPresenter {

    private final LoginModel mModel;

    public LoginPresenter(LoginAnimActivity iView) {
        super(iView);
        mModel = new LoginModel();
    }

    @Override
    public void login(String phone, String password) {
        mModel.requestLogin(phone, password).subscribe(new SampleShowDialogObserver<LoginBean>(mIView) {
            @Override
            protected void onSuccess(LoginBean loginBean) throws Exception {
                connectIm(loginBean);
                SharePreUtil.saveData(mIView, "login_phone", phone);
                mIView.login(loginBean);
            }
        });

    }

    private void connectIm(LoginBean loginBean) {
        String imtoken = loginBean.imtoken;
        if (imtoken == null) {
            String token = (String) SharePreUtil.getData(mIView, "token", "");
            if (token != null && !token.equals("")) {
                connect(token);
            }
        } else {
            connect(imtoken);
        }
    }

    @Override
    public void dotoLogin() {

    }

    @Override
    public void register(Map<String, String> map) {
        mModel.requestRegister(map).subscribe(new SampleShowDialogObserver<RegisterBean>(mIView) {
            @Override
            protected void onSuccess(RegisterBean baseBean) throws Exception {
                login(map.get("phone"), map.get("password"));
            }
        });
    }

    @Override
    public void forget(Map<String, String> map) {
        mModel.requestForget(map).subscribe(new SampleShowDialogObserver<RegisterBean>(mIView) {
            @Override
            protected void onSuccess(RegisterBean baseBean) throws Exception {
                mIView.forget();
            }
        });
    }

    @Override
    public void smsCode(Map<String, String> map, TextView mRtnCode) {
        Disposable disposable = mModel.requestSms(map)
                .subscribe(baseBean -> {
                            if (TextUtils.equals("000", baseBean.code)) {
                                mIView.smsSend(mRtnCode);
                            } else {
                                mIView.showSpecError(baseBean.msg);
                            }
                        },
                        throwable -> mIView.showError());
        addSubscription(disposable);
    }

    @Override
    public void verfiyCode(String phone, String smCode) {
        Disposable disposable = mModel.requestVerfiyCode(phone, smCode)
                .subscribe(baseBean -> {
                            if (TextUtils.equals("000", baseBean.code)) {
                                mIView.goNext();
                                mIView.showSpecError("");
                            } else {
                                mIView.showSpecError(baseBean.msg);
                            }
                        },
                        throwable -> mIView.showError());
        addSubscription(disposable);
    }

    @Override
    public void wxLogin(String token, String phone, String smCode) {
        mModel.requestWxTokenByLogin(token, phone, smCode)
                .subscribe(new SampleShowDialogObserver<LoginBean>(mIView) {
                    @Override
                    protected void onSuccess(LoginBean loginBean) throws Exception {
                        connectIm(loginBean);
                        SharePreUtil.saveData(mIView, "login_phone", phone);
                        mIView.login(loginBean);
                    }
                });

    }


    private void connect(String token) {

        if (mIView.getApplicationInfo().packageName.equals(getCurProcessName(mIView.getApplicationContext()))) {

            RongIM.connect(token, new RongIMClient.ConnectCallback() {

                @Override
                public void onTokenIncorrect() {

                }

                @Override
                public void onSuccess(String userid) {
                    Log.d("LoginActivity", "--onSuccess" + userid);

                }

                @Override
                public void onError(RongIMClient.ErrorCode errorCode) {

                }
            });
        }
    }

}
