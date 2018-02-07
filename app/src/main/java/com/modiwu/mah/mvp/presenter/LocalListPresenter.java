package com.modiwu.mah.mvp.presenter;

import com.modiwu.mah.mvp.model.LocalModel;
import com.modiwu.mah.ui.activity.LocalListActivity;

import io.reactivex.disposables.Disposable;
import top.jplayer.baseprolibrary.mvp.contract.BasePresenter;
import top.jplayer.baseprolibrary.mvp.contract.IContract;
import top.jplayer.baseprolibrary.mvp.model.bean.BaseBean;
import top.jplayer.baseprolibrary.net.SampleShowDialogObserver;

/**
 * Created by Obl on 2018/1/25.
 * com.modiwu.mah.mvp.presenter
 */

public class LocalListPresenter extends BasePresenter<LocalListActivity> implements IContract.IPresenter {

    private final LocalModel mModel;

    public LocalListPresenter(LocalListActivity iView) {
        super(iView);
        mModel = new LocalModel();
    }

    public void requestLocalBean() {
        Disposable disposable = mModel.requestLocalListBean()
                .subscribe(bean -> {
                    if (bean == null || bean.records == null || bean.records.size() < 1) {
                        mIView.showEmpty();
                    } else {
                        mIView.smartRefreshLayout.finishRefresh();
                        mIView.mMultipleStatusView.showContent();
                        mIView.setLocalListBean(bean);
                    }
                }, throwable -> mIView.showError());
        addSubscription(disposable);
    }

    public void delLocal(String rpid) {
        mModel.requestLocalDelBean(rpid)
                .subscribe(new SampleShowDialogObserver<BaseBean>(mIView) {
                    @Override
                    protected void onSuccess(BaseBean bean) throws Exception {
                        mIView.delLocal();
                    }
                });
    }

    public void setDefLocal(String rp_id) {
        mModel.requestLocalDefBean(rp_id, "1")
                .subscribe(new SampleShowDialogObserver<BaseBean>(mIView) {
                    @Override
                    protected void onSuccess(BaseBean bean) throws Exception {
                        requestLocalBean();
                    }
                });
    }
}
