package com.modiwu.mah.mvp.constract;

import android.widget.TextView;

import java.util.Map;

import top.jplayer.baseprolibrary.mvp.contract.IContract;

/**
 * Created by Obl on 2018/1/25.
 * com.modiwu.mah.mvp.constract
 */

public interface LoginAnimContract {
    interface ILoginAnimPresenter extends IContract.IPresenter {
        void login(String phone, String password);

        void dotoLogin();

        void register(Map<String, String> map);

        void forget(Map<String, String> map);

        void smsCode(Map<String, String> map, TextView mRtnCode);

        void verfiyCode(String phone, String smCode);

        void wxLogin(String token, String phone, String smCode);
    }

    interface ILoginAnimView extends IContract.IView {

        void login();

        void register();

        void forget();

        void smsCode(Map<String, String> map, TextView mRtnCode);

        void verfiyCode();

    }
}
