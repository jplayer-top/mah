package com.modiwu.mah.mvp.presenter;

import com.modiwu.mah.mvp.model.SchemeModel;
import com.modiwu.mah.mvp.model.bean.MeFangAnBean;
import com.modiwu.mah.ui.activity.MeFangAnActivity;

import io.reactivex.disposables.Disposable;
import top.jplayer.baseprolibrary.mvp.contract.BasePresenter;
import top.jplayer.baseprolibrary.mvp.contract.IContract;

/**
 * Created by Obl on 2018/1/25.
 * com.modiwu.mah.mvp.presenter
 */

public class MeFangAnPresenter extends BasePresenter<MeFangAnActivity> implements IContract.IPresenter {

    private final SchemeModel mModel;

    public MeFangAnPresenter(MeFangAnActivity iView) {
        super(iView);
        mModel = new SchemeModel();
    }


    public void requestFangAnData() {
        Disposable disposable = mModel.requestMeFangAnBean().subscribe(this::successBean, throwable -> mIView
                .showError());
        addSubscription(disposable);
    }

    private void successBean(MeFangAnBean bean) {
        if (bean == null || bean.rows == null || bean.rows.size() < 1) {
            mIView.showEmpty();
        } else {
            mIView.smartRefreshLayout.finishRefresh();
            mIView.mMultipleStatusView.showContent();
            mIView.setMeFangAn(bean);
        }
    }

}
