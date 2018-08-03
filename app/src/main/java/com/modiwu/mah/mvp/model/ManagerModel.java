package com.modiwu.mah.mvp.model;

import com.modiwu.mah.mvp.MahServer;
import com.modiwu.mah.mvp.model.bean.ManagerClientBean;

import io.reactivex.Observable;
import top.jplayer.baseprolibrary.net.IoMainSchedule;
import top.jplayer.baseprolibrary.net.RetrofitManager;

/**
 * Created by Obl on 2018/8/2.
 * com.modiwu.mah.mvp.model
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */

public class ManagerModel {

    public Observable<ManagerClientBean> requestManager() {
        return RetrofitManager.init().create(MahServer.class)
                .getKHSBean()
                .compose(new IoMainSchedule<>());
    }

    public Observable<ManagerClientBean> requestManager(String uid) {
        return RetrofitManager.init().create(MahServer.class)
                .getKHSBean(uid)
                .compose(new IoMainSchedule<>());
    }
}
