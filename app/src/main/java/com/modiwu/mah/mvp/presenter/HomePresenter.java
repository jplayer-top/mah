package com.modiwu.mah.mvp.presenter;

import com.modiwu.mah.mvp.constract.HomeContract;
import com.modiwu.mah.mvp.model.HomeModel;
import com.modiwu.mah.mvp.model.bean.CityCodeBean;
import com.modiwu.mah.mvp.model.bean.HomeBean;
import com.modiwu.mah.ui.fragment.HomeFragment;

import io.reactivex.disposables.Disposable;
import top.jplayer.baseprolibrary.mvp.contract.BasePresenter;
import top.jplayer.baseprolibrary.net.SampleShowDialogObserver;

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

    public void requestHomeData(String city_code, String city) {
        mHomeModel.requestHomeBean(city_code).subscribe(new SampleShowDialogObserver<HomeBean>(mIView.getContext()) {
            @Override
            protected void onSuccess(HomeBean homeBean) throws Exception {
                mIView.setHomeData(homeBean, city);
            }
        });
    }

    public void requestVersion() {
        Disposable disposable = mHomeModel.requestVersion().subscribe(versionBean -> mIView.versionUpData(versionBean), throwable -> {
        });
        addSubscription(disposable);
    }

    public void requestCityCode(String city) {
        mHomeModel.requestCodeData(city).subscribe(new SampleShowDialogObserver<CityCodeBean>(mIView.getContext()) {
            @Override
            protected void onSuccess(CityCodeBean codeBean) throws Exception {
                requestHomeData(codeBean.city_code, city);
            }
        });
    }
}
