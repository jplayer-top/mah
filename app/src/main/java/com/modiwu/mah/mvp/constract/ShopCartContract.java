package com.modiwu.mah.mvp.constract;

import com.modiwu.mah.mvp.model.bean.ShopCartBean;

import java.util.List;

import top.jplayer.baseprolibrary.mvp.contract.IContract;

/**
 * Created by Obl on 2018/1/25.
 * com.modiwu.mah.mvp.constract
 */

public interface ShopCartContract {
    interface IShopCartPresenter extends IContract.IPresenter {
        void requestShopCartData();
    }

    interface IShopCartView extends IContract.IView {
        void setShopCartData(List<ShopCartBean> shopCartBeans);

        void delOneData();

        void upDataBean();
    }
}
