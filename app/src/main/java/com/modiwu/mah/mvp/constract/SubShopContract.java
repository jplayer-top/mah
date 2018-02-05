package com.modiwu.mah.mvp.constract;

import com.modiwu.mah.mvp.model.bean.ShopSubListBean;
import com.modiwu.mah.mvp.model.bean.SubTitleBean;

import top.jplayer.baseprolibrary.mvp.contract.IContract;

/**
 * Created by Obl on 2018/1/25.
 * com.modiwu.mah.mvp.constract
 */

public interface SubShopContract {
    interface ISubShopPresenter extends IContract.IPresenter {
        void requestSubListData(String page_num, String cat_id);

        void requestSubTitleData(String cat_id);

    }

    interface ISubShopView extends IContract.IView {
        void setSubListData(ShopSubListBean bean);

        void setSubTitleData(SubTitleBean bean);

    }
}
