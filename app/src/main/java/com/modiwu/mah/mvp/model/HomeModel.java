package com.modiwu.mah.mvp.model;

import android.os.SystemClock;

import com.modiwu.mah.mvp.MahServer;
import com.modiwu.mah.mvp.model.bean.HomeBean;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
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
//                .compose(new IoMainSchedule<HomeBean>())
                //模拟耗时网络请求
                .subscribeOn(Schedulers.io())
                .map(new Function<HomeBean, HomeBean>() {
                    @Override
                    public HomeBean apply(HomeBean homeBean) throws Exception {
                        SystemClock.sleep(1000);
                        return homeBean;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread());
    }
}
