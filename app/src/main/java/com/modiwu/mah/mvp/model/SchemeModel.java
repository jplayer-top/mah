package com.modiwu.mah.mvp.model;

import com.modiwu.mah.mvp.MahServer;
import com.modiwu.mah.mvp.model.bean.MeFangAnBean;
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
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<MeFangAnBean> requestMeFangAnBean() {
        return RetrofitManager.init()
                .create(MahServer.class)
                .getMeSchemeListBean()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
