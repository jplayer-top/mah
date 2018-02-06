package com.modiwu.mah.mvp.model;

import android.os.SystemClock;

import com.modiwu.mah.mvp.MahServer;
import com.modiwu.mah.mvp.model.bean.MeOrderBean;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import top.jplayer.baseprolibrary.net.RetrofitManager;

/**
 * Created by Obl on 2018/1/25.
 * com.modiwu.mah.mvp.model
 */

public class OrderListModel {
    public Observable<MeOrderBean> requestOrderBean() {
        return RetrofitManager.init()
                .create(MahServer.class)
                .getOrderListBean()
                .subscribeOn(Schedulers.io())
                .map(bean -> {
                    SystemClock.sleep(500);
                    return bean;
                })
                .observeOn(AndroidSchedulers.mainThread());
    }


}
