package com.modiwu.mah.mvp.model;

import com.modiwu.mah.mvp.MahServer;
import com.modiwu.mah.mvp.model.bean.LocalBean;
import com.modiwu.mah.mvp.model.bean.LocalListBean;

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

public class LocalModel {
    public Observable<LocalBean> requestLocalBean() {
        return RetrofitManager.init()
                .create(MahServer.class)
                .getLocalBean()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<LocalListBean> requestLocalListBean() {
        return RetrofitManager.init()
                .create(MahServer.class)
                .getLocalListBean()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<BaseBean> requestLocalDelBean(String rpid) {
        return RetrofitManager.init()
                .create(MahServer.class)
                .getLocalDelBean(rpid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<BaseBean> requestLocalEditBean(Map<String, String> map) {
        return RetrofitManager.init()
                .create(MahServer.class)
                .getLocalEditBean(map)
                .compose(new IoMainSchedule<>());
    }


    public Observable<BaseBean> requestLocalSaveBean(Map<String, String> map) {
        return RetrofitManager.init()
                .create(MahServer.class)
                .getLocalSaveBean(map)
                .compose(new IoMainSchedule<>());
    }

    public Observable<BaseBean> requestLocalDefBean(String rpid, String def) {
        return RetrofitManager.init()
                .create(MahServer.class)
                .getLocalDefBean(rpid,def)
                .compose(new IoMainSchedule<>());
    }

}
