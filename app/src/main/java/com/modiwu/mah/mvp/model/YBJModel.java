package com.modiwu.mah.mvp.model;

import com.modiwu.mah.mvp.MahServer;
import com.modiwu.mah.mvp.model.bean.LocalBean;
import com.modiwu.mah.mvp.model.bean.LocalListBean;
import com.modiwu.mah.mvp.model.bean.YBJBean;

import java.util.Map;

import io.reactivex.Completable;
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

public class YBJModel {
    public Observable<YBJBean> requestYBJInfoBean() {
        return RetrofitManager.init()
                .create(MahServer.class)
                .getYBJBean()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<BaseBean> requestSubmitBean(Map<String,String> map) {
        return RetrofitManager.init()
                .create(MahServer.class)
                .getYBJSubmitBean(map)
                .compose(new IoMainSchedule<>());
    }


    public Observable<BaseBean> requestYYSubmitBean(Map<String, String> map) {
        return RetrofitManager.init()
                .create(MahServer.class)
                .getYYSubmitBean(map)
                .compose(new IoMainSchedule<>());
    }
}
