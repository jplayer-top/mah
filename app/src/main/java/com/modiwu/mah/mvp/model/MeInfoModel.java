package com.modiwu.mah.mvp.model;

import com.modiwu.mah.mvp.MahServer;
import com.modiwu.mah.mvp.model.bean.LocalBean;

import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import top.jplayer.baseprolibrary.mvp.model.bean.BaseBean;
import top.jplayer.baseprolibrary.net.RetrofitManager;

/**
 * Created by Administrator on 2018/2/9.
 */

public class MeInfoModel {

    public Observable<BaseBean> requestInfoBean(String col, String value) {
        return RetrofitManager.init()
                .create(MahServer.class)
                .getMeInfoBean(col, value)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<BaseBean> requestGetInfo(String uid){
        return RetrofitManager.init()
                .create(MahServer.class)
                .getMeInfoStartBean(uid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

}
