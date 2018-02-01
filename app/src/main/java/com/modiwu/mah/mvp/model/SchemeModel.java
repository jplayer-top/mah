package com.modiwu.mah.mvp.model;

import android.os.SystemClock;

import com.modiwu.mah.mvp.MahServer;
import com.modiwu.mah.mvp.model.bean.SchemeBean;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import top.jplayer.baseprolibrary.net.RetrofitManager;

/**
 * Created by Obl on 2018/2/1.
 * com.modiwu.mah.mvp.model
 */

public class SchemeModel {
    public Observable<SchemeBean> requestSchemeBean() {
        return RetrofitManager.init()
                .create(MahServer.class)
                .getSchemeBean()
                .subscribeOn(Schedulers.io())
                .map(homeBean -> {
                    SystemClock.sleep(1000);
                    return homeBean;
                })
                .observeOn(AndroidSchedulers.mainThread());
    }
}
