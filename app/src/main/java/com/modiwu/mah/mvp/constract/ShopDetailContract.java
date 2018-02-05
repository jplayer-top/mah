package com.modiwu.mah.mvp.constract;

import com.modiwu.mah.mvp.model.bean.ShopGoodsInfoBean;

import top.jplayer.baseprolibrary.mvp.contract.IContract;

/**
 * Created by Obl on 2018/1/25.
 * com.modiwu.mah.mvp.constract
 */

public interface ShopDetailContract {
    interface IShopDetailPresenter extends IContract.IPresenter {
        void requestShopDetailData(String goods_id);


    }

    interface IShopDetailView extends IContract.IView {
        void setShopDetailData(ShopGoodsInfoBean bean);


    }
}
