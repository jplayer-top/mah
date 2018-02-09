package com.modiwu.mah.mvp.model;

import com.modiwu.mah.mvp.MahServer;
import com.modiwu.mah.mvp.model.bean.MeShouCangBean;

import io.reactivex.Observable;
import top.jplayer.baseprolibrary.mvp.model.bean.BaseBean;
import top.jplayer.baseprolibrary.net.IoMainSchedule;
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
                .compose(new IoMainSchedule<>());
    }

    public Observable<BaseBean> requestShouCangDel(String fangan_id) {
        return RetrofitManager.init()
                .create(MahServer.class)
                .getShouCangDel(fangan_id)
                .compose(new IoMainSchedule<>());
    }

}
