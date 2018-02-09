package com.modiwu.mah.mvp.model;

import com.modiwu.mah.mvp.MahServer;
import com.modiwu.mah.mvp.model.bean.ShopSubListBean;
import com.modiwu.mah.mvp.model.bean.SubTitleBean;

import io.reactivex.Observable;
import top.jplayer.baseprolibrary.net.IoMainSchedule;
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
                .compose(new IoMainSchedule<>());
    }

    public Observable<SubTitleBean> requestSubTitleBean(String cat_id) {
        return RetrofitManager.init()
                .create(MahServer.class)
                .getSubTitleBean(cat_id)
                .compose(new IoMainSchedule<>());
    }
}
