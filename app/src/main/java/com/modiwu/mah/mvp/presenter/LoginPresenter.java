package com.modiwu.mah.mvp.presenter;


import android.widget.TextView;

import com.modiwu.mah.mvp.constract.LoginAnimContract;
import com.modiwu.mah.mvp.model.LoginModel;
import com.modiwu.mah.ui.activity.LoginAnimActivity;

import java.util.Map;

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
        mModel.requestLogin(phone, password).subscribe(loginBean -> mIView.login());
    }

    @Override
    public void login() {

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
