package com.modiwu.mah.mvp.presenter;

import com.modiwu.mah.mvp.constract.SubShopContract;
import com.modiwu.mah.mvp.model.SubListModel;
import com.modiwu.mah.ui.fragment.ShopSubFragment;

import io.reactivex.disposables.Disposable;
import top.jplayer.baseprolibrary.mvp.contract.BasePresenter;

/**
 * Created by Obl on 2018/1/25.
 * com.modiwu.mah.mvp.presenter
 */

public class ShopSubFragmentPresenter extends BasePresenter<ShopSubFragment> implements SubShopContract.ISubShopPresenter {

    private final SubListModel mModel;

    public ShopSubFragmentPresenter(ShopSubFragment iView) {
        super(iView);
        mModel = new SubListModel();
    }


    @Override
    public void requestSubListData(String page_num, String cat_id) {
        Disposable disposable = mModel.requestSubListBean(page_num, cat_id).subscribe(bean -> {
            if (bean == null || bean.records == null || bean.records.size() < 1) {
                mIView.showEmpty();
            } else {
                mIView.smartRefreshLayout.finishRefresh();
                mIView.mMultipleStatusView.showContent();
                mIView.setSubListData(bean);
            }
        }, throwable -> mIView.showError());
        addSubscription(disposable);
    }

    @Override
    public void requestSubTitleData(String cat_id) {

    }
}
