package com.modiwu.mah.mvp.model;

import android.os.SystemClock;

import com.modiwu.mah.mvp.MahServer;
import com.modiwu.mah.mvp.model.bean.MeShouCangBean;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import top.jplayer.baseprolibrary.mvp.model.bean.BaseBean;
import top.jplayer.baseprolibrary.net.RetrofitManager;

/**
 * Created by Obl on 2018/1/25.
 * com.modiwu.mah.mvp.model
 */

public class ShouCangModel {
    public Observable<MeShouCangBean> requestShouCangBean() {
        return RetrofitManager.init()
                .create(MahServer.class)
                .getShouCangBean()
                .subscribeOn(Schedulers.io())
                .map(bean -> {
                    SystemClock.sleep(500);
                    return bean;
                })
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<BaseBean> requestShouCangDel(String fangan_id) {
        return RetrofitManager.init()
                .create(MahServer.class)
                .getShouCangDel(fangan_id)
                .subscribeOn(Schedulers.io())
                .map(bean -> {
                    SystemClock.sleep(500);
                    return bean;
                })
                .observeOn(AndroidSchedulers.mainThread());
    }

}
