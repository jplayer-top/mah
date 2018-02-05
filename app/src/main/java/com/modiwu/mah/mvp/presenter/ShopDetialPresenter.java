package com.modiwu.mah.mvp.presenter;

import com.modiwu.mah.mvp.constract.ShopDetailContract;
import com.modiwu.mah.mvp.model.ShopDetailModel;
import com.modiwu.mah.ui.activity.ShopDetialActivity;

import io.reactivex.disposables.Disposable;
import top.jplayer.baseprolibrary.mvp.contract.BasePresenter;

/**
 * Created by Obl on 2018/1/25.
 * com.modiwu.mah.mvp.presenter
 */

public class ShopDetialPresenter extends BasePresenter<ShopDetialActivity> implements ShopDetailContract.IShopDetailPresenter {

    private final ShopDetailModel mModel;

    public ShopDetialPresenter(ShopDetialActivity iView) {
        super(iView);
        mModel = new ShopDetailModel();
    }


    @Override
    public void requestShopDetailData(String goods_id) {
        Disposable disposable = mModel.requestShopDetailBean(goods_id).subscribe(bean -> {
            if (bean == null) {
                mIView.showEmpty();
            } else {
                mIView.mMultipleStatusView.showContent();
                mIView.setShopDetailData(bean);
            }
        }, throwable -> mIView.showError());
        addSubscription(disposable);
    }
}
