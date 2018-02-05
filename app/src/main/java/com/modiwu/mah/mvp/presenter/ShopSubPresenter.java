package com.modiwu.mah.mvp.presenter;

import com.modiwu.mah.mvp.constract.SubShopContract;
import com.modiwu.mah.mvp.model.SubListModel;
import com.modiwu.mah.ui.activity.ShopSubActivity;

import io.reactivex.disposables.Disposable;
import top.jplayer.baseprolibrary.mvp.contract.BasePresenter;

/**
 * Created by Obl on 2018/1/25.
 * com.modiwu.mah.mvp.presenter
 */

public class ShopSubPresenter extends BasePresenter<ShopSubActivity> implements SubShopContract.ISubShopPresenter {

    private final SubListModel mModel;

    public ShopSubPresenter(ShopSubActivity iView) {
        super(iView);
        mModel = new SubListModel();
    }


    @Override
    public void requestSubListData(String page_num, String cat_id) {

    }

    @Override
    public void requestSubTitleData(String cat_id) {
        Disposable disposable = mModel.requestSubTitleBean(cat_id).subscribe(bean -> {
            if (bean == null) {
                mIView.showEmpty();
            } else {
                mIView.mMultipleStatusView.showContent();
                mIView.setSubTitleData(bean);
            }
        }, throwable -> mIView.showError());
        addSubscription(disposable);
    }
}
