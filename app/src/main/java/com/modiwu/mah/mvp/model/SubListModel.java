package com.modiwu.mah.mvp.model;

import android.os.SystemClock;

import com.modiwu.mah.mvp.MahServer;
import com.modiwu.mah.mvp.model.bean.ShopSubListBean;
import com.modiwu.mah.mvp.model.bean.SubTitleBean;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import top.jplayer.baseprolibrary.net.RetrofitManager;

/**
 * Created by Obl on 2018/1/25.
 * com.modiwu.mah.mvp.model
 */

public class SubListModel {
    public Observable<ShopSubListBean> requestSubListBean(String page_num, String cat_id) {
        return RetrofitManager.init()
                .create(MahServer.class)
                .getSubListBean(page_num, cat_id)
                .subscribeOn(Schedulers.io())
                .map(bean -> {
                    SystemClock.sleep(1000);
                    return bean;
                })
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<SubTitleBean> requestSubTitleBean(String cat_id) {
        return RetrofitManager.init()
                .create(MahServer.class)
                .getSubTitleBean(cat_id)
                .subscribeOn(Schedulers.io())
                .map(bean -> {
                    SystemClock.sleep(1000);
                    return bean;
                })
                .observeOn(AndroidSchedulers.mainThread());
    }
}
