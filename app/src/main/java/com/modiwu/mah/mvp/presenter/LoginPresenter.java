package com.modiwu.mah.mvp.presenter;


import android.text.TextUtils;
import android.widget.TextView;

import com.modiwu.mah.mvp.constract.LoginAnimContract;
import com.modiwu.mah.mvp.model.LoginModel;
import com.modiwu.mah.ui.activity.LoginAnimActivity;

import java.util.Map;

import io.reactivex.disposables.Disposable;
import top.jplayer.baseprolibrary.mvp.contract.BasePresenter;

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
        Disposable disposable = mModel.requestLogin(phone, password)
                .subscribe(loginBean -> {
                            if (TextUtils.equals("000", loginBean.code)) {
                                mIView.login();
                            } else {
                                mIView.showSpecError(loginBean.msg);
                            }
                        },
                        throwable -> mIView.showError());
        addSubscription(disposable);

    }

    @Override
    public void dotoLogin() {

    }

    @Override
    public void register(Map<String, String> map) {

    }

    @Override
    public void forget(Map<String, String> map) {

    }

    @Override
    public void smsCode(Map<String, String> map, TextView mRtnCode) {

    }

    @Override
    public void verfiyCode(String phone, String smCode) {

    }

    @Override
    public void wxLogin(String token, String phone, String smCode) {

    }
}
