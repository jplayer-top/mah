package com.modiwu.mah.mvp.model;

import com.modiwu.mah.mvp.MahServer;
import com.modiwu.mah.mvp.model.bean.CollectionHaseBean;
import com.modiwu.mah.mvp.model.bean.DefLocalBean;
import com.modiwu.mah.mvp.model.bean.OrderCreateBean;
import com.modiwu.mah.mvp.model.bean.SchemeDetailBean;
import com.modiwu.mah.mvp.model.bean.SchemeOrderCreateBean;

import java.util.Map;

import io.reactivex.Completable;
import io.reactivex.Observable;
import top.jplayer.baseprolibrary.mvp.model.bean.BaseBean;
import top.jplayer.baseprolibrary.net.IoMainSchedule;
import top.jplayer.baseprolibrary.net.RetrofitManager;

/**
 * Created by Obl on 2018/1/25.
 * com.modiwu.mah.mvp.model
 */

public class SchemeDetailModel {
    public Observable<SchemeDetailBean> requestSchemeDetailBean(String fangan_id) {
        return RetrofitManager.init()
                .create(MahServer.class)
                .getSchemeDetailBean(fangan_id)
                .compose(new IoMainSchedule<>());
    }

    public Observable<SchemeDetailBean> requestSchemePDDetailBean(String fangan_id) {
        return RetrofitManager.init()
                .create(MahServer.class)
                .getSchemePDDetailBean(fangan_id)
                .compose(new IoMainSchedule<>());
    }

    public Observable<SchemeOrderCreateBean> requestSchemeOrderCreateBean(String fangan_id) {
        return RetrofitManager.init()
                .create(MahServer.class)
                .getSchemeOrderCreateBean(fangan_id)
                .compose(new IoMainSchedule<>());
    }

    public Observable<OrderCreateBean> requestOrderCreateBean(Map<String, String> map) {
        return RetrofitManager.init()
                .create(MahServer.class)
                .getOrderCreateBean(map)
                .compose(new IoMainSchedule<>());
    }

    public Observable<DefLocalBean> requestOrderLocalBean() {
        return RetrofitManager.init()
                .create(MahServer.class)
                .getOrderLocalBean()
                .compose(new IoMainSchedule<>());
    }

    public Observable<BaseBean> requestSchemeCollectionBean(String fangan_id) {
        return RetrofitManager.init()
                .create(MahServer.class)
                .getSchemeCollectionBean(fangan_id)
                .compose(new IoMainSchedule<>());
    }

    public Observable<CollectionHaseBean> requestSchemeHasCollectionBean(String fangan_id) {
        return RetrofitManager.init()
                .create(MahServer.class)
                .getSchemeHasCollectionBean(fangan_id)
                .compose(new IoMainSchedule<>());
    }
}
