package com.modiwu.mah.mvp.model;

import android.os.SystemClock;

import com.modiwu.mah.mvp.MahServer;
import com.modiwu.mah.mvp.model.bean.AdvBean;
import com.modiwu.mah.mvp.model.bean.CityCodeBean;
import com.modiwu.mah.mvp.model.bean.HomeBean;
import com.modiwu.mah.mvp.model.bean.VersionBean;

import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import top.jplayer.baseprolibrary.mvp.model.bean.BaseBean;
import top.jplayer.baseprolibrary.net.IoMainSchedule;
import top.jplayer.baseprolibrary.net.RetrofitManager;

/**
 * Created by Obl on 2018/1/25.
 * com.modiwu.mah.mvp.model
 */

public class HomeModel {
    public Observable<HomeBean> requestHomeBean() {
        return RetrofitManager.init()
                .create(MahServer.class)
                .getHomeBean()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<AdvBean> requestAdvBean() {
        return RetrofitManager.init()
                .create(MahServer.class)
                .getAdvBean()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<BaseBean> sjFA(Map<String, String> map) {
        return RetrofitManager.init()
                .create(MahServer.class)
                .sjFA(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<CityCodeBean> requestCodeData(String city) {
        return RetrofitManager.init().create(MahServer.class)
                .getCityCodeBean(city)
                .compose(new IoMainSchedule<>());

    }

    public Observable<VersionBean> requestVersion() {
        return RetrofitManager.init()
                .create(MahServer.class)
                .getVersion()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<HomeBean> requestHomeBean(String city_code) {
        return RetrofitManager.init()
                .create(MahServer.class)
                .getHomeBean(city_code)
                .compose(new IoMainSchedule<>());

    }
}
