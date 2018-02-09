package com.modiwu.mah.mvp.model;

import com.modiwu.mah.mvp.MahServer;
import com.modiwu.mah.mvp.model.bean.DesignBean;

import io.reactivex.Observable;
import top.jplayer.baseprolibrary.net.IoMainSchedule;
import top.jplayer.baseprolibrary.net.RetrofitManager;

/**
 * Created by Obl on 2018/1/25.
 * com.modiwu.mah.mvp.model
 */

public class DesignerModel {
    public Observable<DesignBean> requestDesignerBean(String designer_id) {
        return RetrofitManager.init()
                .create(MahServer.class)
                .getDesignBean(designer_id)
                .compose(new IoMainSchedule<>());
    }
}
