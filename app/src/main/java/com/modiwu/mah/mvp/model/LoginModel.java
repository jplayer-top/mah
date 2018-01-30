package com.modiwu.mah.mvp.model;

import com.modiwu.mah.mvp.MahServer;
import com.modiwu.mah.mvp.model.bean.LoginBean;

import io.reactivex.Observable;
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
}
