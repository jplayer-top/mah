package com.modiwu.mah.mvp.model;

import com.modiwu.mah.mvp.MahServer;
import com.modiwu.mah.mvp.model.bean.CarpenterBean;
import com.modiwu.mah.mvp.model.bean.DockerBean;

import io.reactivex.Observable;
import top.jplayer.baseprolibrary.net.IoMainSchedule;
import top.jplayer.baseprolibrary.net.RetrofitManager;

/**
 * Created by Obl on 2018/1/25.
 * com.modiwu.mah.mvp.model
 */

public class CarpenterModel {
    public Observable<CarpenterBean> requestCarpenterBean() {
        return RetrofitManager.init()
                .create(MahServer.class)
                .getCarpenterBean()
                .compose(new IoMainSchedule<>());
    }
    public Observable<DockerBean> requestDockerBean() {
        return RetrofitManager.init()
                .create(MahServer.class)
                .getDockerBean()
                .compose(new IoMainSchedule<>());
    }
}
