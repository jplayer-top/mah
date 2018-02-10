package com.modiwu.mah.mvp.model;

import com.modiwu.mah.mvp.MahServer;
import com.modiwu.mah.mvp.model.bean.LoginBean;
import com.modiwu.mah.mvp.model.bean.RegisterBean;

import java.util.Map;

import io.reactivex.Observable;
import top.jplayer.baseprolibrary.mvp.model.bean.BaseBean;
import top.jplayer.baseprolibrary.net.IoMainSchedule;
import top.jplayer.baseprolibrary.net.RetrofitManager;

/**
 * Created by Obl on 2018/1/30.
 * com.modiwu.mah.mvp.model
 */

public class LoginModel {

    public Observable<LoginBean> requestLogin(String phone, String password) {
        return RetrofitManager.init().create(MahServer.class)
                .getLoginBean(phone, password)
                .compose(new IoMainSchedule<>());
    }

    public Observable<BaseBean> requestSms(Map<String, String> map) {
        return RetrofitManager.init().create(MahServer.class)
                .getSmsBean(map)
                .compose(new IoMainSchedule<>());
    }


    public Observable<BaseBean> requestVerfiyCode(String phone, String smCode) {
        return RetrofitManager.init().create(MahServer.class)
                .verfiyCode(phone, smCode)
                .compose(new IoMainSchedule<>());
    }

    public Observable<RegisterBean> requestRegister(Map<String, String> map) {
        return RetrofitManager.init().create(MahServer.class)
                .register(map)
                .compose(new IoMainSchedule<>());
    }

    public Observable<RegisterBean> requestForget(Map<String, String> map) {
        return RetrofitManager.init().create(MahServer.class)
                .forget(map)
                .compose(new IoMainSchedule<>());
    }
}
