package com.modiwu.mah.mvp.constract;

import com.modiwu.mah.mvp.model.bean.MeOrderBean;

import top.jplayer.baseprolibrary.mvp.contract.IContract;
import top.jplayer.baseprolibrary.mvp.model.bean.BaseBean;

/**
 * Created by Obl on 2018/1/25.
 * com.modiwu.mah.mvp.constract
 */

public interface OrderListContract {
    interface IOrderListPresenter extends IContract.IPresenter {
        void requestOrderListData();


        void requestOrderListDel(String uid);
    }

    interface IOrderListView extends IContract.IView {
        void setOrderListData(MeOrderBean bean);

        void setOrderListDel(BaseBean baseBean, int position);
    }
}
