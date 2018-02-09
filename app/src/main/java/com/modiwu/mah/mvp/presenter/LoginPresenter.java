package com.modiwu.mah.mvp.presenter;


import android.text.TextUtils;
import android.widget.TextView;

import com.modiwu.mah.mvp.constract.LoginAnimContract;
import com.modiwu.mah.mvp.model.LoginModel;
import com.modiwu.mah.mvp.model.bean.LoginBean;
import com.modiwu.mah.ui.activity.LoginAnimActivity;

import java.util.Map;

import io.reactivex.disposables.Disposable;
import top.jplayer.baseprolibrary.mvp.contract.BasePresenter;
import top.jplayer.baseprolibrary.mvp.model.bean.BaseBean;
import top.jplayer.baseprolibrary.net.SampleShowDialogObserver;

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
                mIView.login(loginBean);
            }
        });

    }

    @Override
    public void dotoLogin() {

    }

    @Override
    public void register(Map<String, String> map) {
        mModel.requestRegister(map).subscribe(new SampleShowDialogObserver<BaseBean>(mIView) {
            @Override
            protected void onSuccess(BaseBean baseBean) throws Exception {
                mIView.register();
            }
        });
    }

    @Override
    public void forget(Map<String, String> map) {

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

    }
}
