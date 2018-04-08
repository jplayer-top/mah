package com.modiwu.mah.mvp.presenter;

import com.modiwu.mah.mvp.constract.SchemeContract;
import com.modiwu.mah.mvp.model.SchemeModel;
import com.modiwu.mah.ui.fragment.SchemeFragment;

import java.util.Map;

import io.reactivex.disposables.Disposable;
import top.jplayer.baseprolibrary.mvp.contract.BasePresenter;

/**
 * Created by Obl on 2018/1/25.
 * com.modiwu.mah.mvp.presenter
 */

public class SchemePresenter extends BasePresenter<SchemeFragment> implements SchemeContract.ISchemePresenter {

    private final SchemeModel mModel;

    public SchemePresenter(SchemeFragment iView) {
        super(iView);
        mModel = new SchemeModel();
    }


    @Override
    public void requestSchemeData(String city_code) {
        Disposable disposable = mModel.requestSchemeBean(city_code).subscribe(schemeBean -> {
            if (schemeBean == null || schemeBean.records == null || schemeBean.records.size() < 1) {
                mIView.showEmpty();
            } else {
                mIView.smartRefreshLayout.finishRefresh(true);
                mIView.mMultipleStatusView.showContent();
                mIView.setSchemeData(schemeBean);
            }
        }, throwable -> mIView.showError());
        addSubscription(disposable);
    }

    public void requestSchemeData(Map<String,String> map) {
        Disposable disposable = mModel.requestSchemeBean(map).subscribe(schemeBean -> {
            if (schemeBean == null || schemeBean.records == null || schemeBean.records.size() < 1) {
                mIView.showEmpty();
            } else {
                mIView.smartRefreshLayout.finishRefresh(true);
                mIView.mMultipleStatusView.showContent();
                mIView.setSchemeData(schemeBean);
            }
        }, throwable -> mIView.showError());
        addSubscription(disposable);
    }
}
