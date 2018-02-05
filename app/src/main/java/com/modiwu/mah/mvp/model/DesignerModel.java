package com.modiwu.mah.mvp.model;

import android.os.SystemClock;

import com.modiwu.mah.mvp.MahServer;
import com.modiwu.mah.mvp.model.bean.DesignBean;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
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
                .subscribeOn(Schedulers.io())
                .map(bean -> {
                    SystemClock.sleep(1000);
                    return bean;
                })
                .observeOn(AndroidSchedulers.mainThread());
    }
}
