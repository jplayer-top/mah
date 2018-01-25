package com.modiwu.mah.mvp.presenter;

import android.os.SystemClock;

import com.modiwu.mah.mvp.model.HomeModel;
import com.modiwu.mah.mvp.model.bean.HomeBean;
import com.modiwu.mah.ui.Fragment.HomeFragment;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import top.jplayer.baseprolibrary.mvp.contract.BasePresenter;
import top.jplayer.baseprolibrary.mvp.contract.IContract;

/**
 * Created by Obl on 2018/1/25.
 * com.modiwu.mah.mvp.presenter
 */

public class HomePresenter extends BasePresenter<HomeFragment> implements IContract.IPresenter<HomeFragment> {

    private final HomeModel mHomeModel;

    public HomePresenter(HomeFragment iView) {
        super(iView);
        mHomeModel = new HomeModel();
    }


    public void requestHomeBean() {
        mIView.showLoading();
        Disposable disposable = mHomeModel.requestHomeBean().subscribe(new Consumer<HomeBean>() {
            @Override
            public void accept(final HomeBean homeBean) throws Exception {
                if (homeBean == null) {
                    mIView.showEmpty();
                } else {
                    mIView.mMultipleStatusView.showContent();
                    mIView.setHomeData(homeBean);
                }
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                mIView.showError();
            }
        });
        addSubscription(disposable);
    }
}
