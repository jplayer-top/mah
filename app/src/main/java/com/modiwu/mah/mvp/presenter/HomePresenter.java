package com.modiwu.mah.mvp.presenter;

import com.modiwu.mah.mvp.constract.HomeContract;
import com.modiwu.mah.mvp.model.HomeModel;
import com.modiwu.mah.ui.Fragment.HomeFragment;

import io.reactivex.disposables.Disposable;
import top.jplayer.baseprolibrary.mvp.contract.BasePresenter;

/**
 * Created by Obl on 2018/1/25.
 * com.modiwu.mah.mvp.presenter
 */

public class HomePresenter extends BasePresenter<HomeFragment> implements HomeContract.IHomePresenter {

    private final HomeModel mHomeModel;

    public HomePresenter(HomeFragment iView) {
        super(iView);
        mHomeModel = new HomeModel();
    }

    @Override
    public void requestHomeData() {
        Disposable disposable = mHomeModel.requestHomeBean().subscribe(homeBean -> {
            if (homeBean == null) {
                mIView.showEmpty();
            } else {
                mIView.smartRefreshLayout.finishRefresh(true);
                mIView.mMultipleStatusView.showContent();
                mIView.setHomeData(homeBean);
            }
        }, throwable -> mIView.showError());
        addSubscription(disposable);
    }

}
