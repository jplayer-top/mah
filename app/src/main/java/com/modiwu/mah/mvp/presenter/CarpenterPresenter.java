package com.modiwu.mah.mvp.presenter;

import com.modiwu.mah.mvp.constract.CarpenterContract;
import com.modiwu.mah.mvp.model.CarpenterModel;
import com.modiwu.mah.ui.Fragment.CarpenterFragment;

import io.reactivex.disposables.Disposable;
import top.jplayer.baseprolibrary.mvp.contract.BasePresenter;

/**
 * Created by Obl on 2018/1/25.
 * com.modiwu.mah.mvp.presenter
 */

public class CarpenterPresenter extends BasePresenter<CarpenterFragment> implements CarpenterContract.ICarpenterPresenter {

    private final CarpenterModel mModel;

    public CarpenterPresenter(CarpenterFragment iView) {
        super(iView);
        mModel = new CarpenterModel();
    }


    @Override
    public void requestCarpenterData() {
        Disposable disposable = mModel.requestCarpenterBean().subscribe(bean -> {
            if (bean == null || bean.records == null || bean.records.size() < 1) {
                mIView.showEmpty();
            } else {
                mIView.smartRefreshLayout.finishRefresh(true);
                mIView.setCarpenterData(bean);
            }
        }, throwable -> mIView.showError());
        addSubscription(disposable);
    }

    @Override
    public void requestDockerData() {
        Disposable disposable = mModel.requestDockerBean().subscribe(bean -> {
            if (bean == null || bean.records == null || bean.records.size() < 1) {
                mIView.showEmpty();
            } else {
                mIView.smartRefreshLayout.finishRefresh(true);
                mIView.setDockerData(bean);
            }
        }, throwable -> mIView.showError());
        addSubscription(disposable);
    }
}
