package com.modiwu.mah.mvp.presenter;

import com.modiwu.mah.mvp.constract.DesignerContract;
import com.modiwu.mah.mvp.model.DesignerModel;
import com.modiwu.mah.ui.activity.DesignerActivity;

import io.reactivex.disposables.Disposable;
import top.jplayer.baseprolibrary.mvp.contract.BasePresenter;

/**
 * Created by Obl on 2018/1/25.
 * com.modiwu.mah.mvp.presenter
 */

public class DesignerPresenter extends BasePresenter<DesignerActivity> implements DesignerContract.IDesignerPresenter {

    private final DesignerModel mModel;

    public DesignerPresenter(DesignerActivity iView) {
        super(iView);
        mModel = new DesignerModel();
    }

    @Override
    public void requestDesignerData(String designer_id) {
        Disposable disposable = mModel.requestDesignerBean(designer_id).subscribe(bean -> {
            if (bean == null) {
                mIView.showEmpty();
            } else {
                mIView.smartRefreshLayout.finishRefresh(true);
                mIView.mMultipleStatusView.showContent();
                mIView.setDesignerData(bean);
            }
        }, throwable -> mIView.showError());
        addSubscription(disposable);
    }
}
