package com.modiwu.mah.mvp.presenter;

import com.modiwu.mah.mvp.constract.ShouCangContract;
import com.modiwu.mah.mvp.model.ShouCangModel;
import com.modiwu.mah.mvp.model.bean.MeShouCangBean;
import com.modiwu.mah.ui.activity.MeShouCangActivity;

import io.reactivex.disposables.Disposable;
import top.jplayer.baseprolibrary.mvp.contract.BasePresenter;
import top.jplayer.baseprolibrary.mvp.model.bean.BaseBean;
import top.jplayer.baseprolibrary.net.SampleShowDialogObserver;

/**
 * Created by Obl on 2018/1/25.
 * com.modiwu.mah.mvp.presenter
 */

public class ShouCangPresenter extends BasePresenter<MeShouCangActivity> implements ShouCangContract.IShouCangPresenter {

    private final ShouCangModel mModel;

    public ShouCangPresenter(MeShouCangActivity iView) {
        super(iView);
        mModel = new ShouCangModel();
    }


    @Override
    public void requestShouCangData() {
        Disposable disposable = mModel.requestShouCangBean().subscribe(this::successBean, throwable -> mIView.showError());
        addSubscription(disposable);
    }

    private void successBean(MeShouCangBean bean) {
        if (bean == null || bean.rows == null || bean.rows.size() < 1) {
            mIView.showEmpty();
        } else {
            mIView.smartRefreshLayout.finishRefresh();
            mIView.mMultipleStatusView.showContent();
            mIView.setShouCangData(bean);
        }
    }

    public void requestShouCangData(boolean isUpDate) {
        mModel.requestShouCangBean()
                .subscribe(new SampleShowDialogObserver<MeShouCangBean>(mIView) {
                    @Override
                    protected void onSuccess(MeShouCangBean bean) throws Exception {
                        successBean(bean);
                    }

                    @Override
                    protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {
                        super.onFailure(e, isNetWorkError);
                        mIView.showError();
                    }
                });
    }

    @Override
    public void requestShouCangDel(int fangan_id, int position) {
        mModel.requestShouCangDel(String.valueOf(fangan_id))
                .subscribe(new SampleShowDialogObserver<BaseBean>(mIView) {
                    @Override
                    protected void onSuccess(BaseBean baseBean) throws Exception {
                        mIView.setDelShouCang(baseBean, position);
                    }
                });
    }
}
