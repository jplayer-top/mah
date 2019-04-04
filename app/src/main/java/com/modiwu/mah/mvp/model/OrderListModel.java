package com.modiwu.mah.mvp.model;

import com.modiwu.mah.mvp.MahServer;
import com.modiwu.mah.mvp.model.bean.MeOrderBean;

import io.reactivex.Observable;
import retrofit2.http.Query;
import top.jplayer.baseprolibrary.mvp.model.bean.BaseBean;
import top.jplayer.baseprolibrary.net.IoMainSchedule;
import top.jplayer.baseprolibrary.net.RetrofitManager;

/**
 * Created by Obl on 2018/1/25.
 * com.modiwu.mah.mvp.model
 */

public class OrderListModel {
    public Observable<MeOrderBean> requestOrderBean() {
        return RetrofitManager.init()
                .create(MahServer.class)
                .getOrderListBean()
                .compose(new IoMainSchedule<>());
    }

    public Observable<MeOrderBean> requestOrderBean(String status) {
        return RetrofitManager.init()
                .create(MahServer.class)
                .getOrderListBean(status)
                .compose(new IoMainSchedule<>());
    }

    public Observable<BaseBean> sjfaAdd(String fangan_name,
                                        String user_name,
                                        String user_phone,
                                        String xiaoqu_name,
                                        String zhxsj,
                                        String fwzk,
                                        String remark,
                                        String fangan_id) {
        return RetrofitManager.init()
                .create(MahServer.class)
                .sjfaAdd(fangan_name, user_name, user_phone, xiaoqu_name, zhxsj, fwzk, remark, fangan_id)
                .compose(new IoMainSchedule<>());
    }


    public Observable<BaseBean> requestOrderDelBean(String order_id, String reason) {
        return RetrofitManager.init()
                .create(MahServer.class)
                .getOrderDelBean(order_id, reason)
                .compose(new IoMainSchedule<>());

    }

    public Observable<BaseBean> requestOrderOkBean(String order_id) {
        return RetrofitManager.init()
                .create(MahServer.class)
                .getOrderOkBean(order_id)
                .compose(new IoMainSchedule<>());

    }
}
