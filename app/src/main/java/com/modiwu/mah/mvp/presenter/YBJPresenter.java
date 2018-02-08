package com.modiwu.mah.mvp.presenter;

import com.modiwu.mah.mvp.model.YBJModel;
import com.modiwu.mah.ui.activity.HouseSampleActivity;

import java.util.Map;

import io.reactivex.disposables.Disposable;
import top.jplayer.baseprolibrary.mvp.contract.BasePresenter;
import top.jplayer.baseprolibrary.mvp.contract.IContract;
import top.jplayer.baseprolibrary.mvp.model.bean.BaseBean;
import top.jplayer.baseprolibrary.net.SampleShowDialogObserver;

/**
 * Created by Obl on 2018/1/25.
 * com.modiwu.mah.mvp.presenter
 */

public class YBJPresenter extends BasePresenter<HouseSampleActivity> implements IContract.IPresenter {

    private final YBJModel mModel;

    public YBJPresenter(HouseSampleActivity iView) {
        super(iView);
        mModel = new YBJModel();
    }

    public void requestInfoBean() {
        Disposable disposable = mModel.requestYBJInfoBean()
                .subscribe(bean -> {
                    if (bean == null) {
                        mIView.showEmpty();
                    } else {
                        mIView.mMultipleStatusView.showContent();
                        mIView.setYBJInfoBean(bean);
                    }
                }, throwable -> mIView.showError());
        addSubscription(disposable);
    }


    public void setDefLocal(String rp_id) {
//        mModel.requestLocalDefBean(rp_id, "1")
//                .subscribe(new SampleShowDialogObserver<BaseBean>(mIView) {
//                    @Override
//                    protected void onSuccess(BaseBean bean) throws Exception {
//                        requestInfoBean();
//                    }
//                });
    }

    public void requestSubmit(Map<String, String> map) {
        mModel.requestSubmitBean(map)
                .subscribe(new SampleShowDialogObserver<BaseBean>(mIView) {
                    @Override
                    protected void onSuccess(BaseBean bean) throws Exception {
                        mIView.setSubmitBean(bean);
                    }
                });
    }

    public void requestYYSubmit(Map<String, String> map) {
        mModel.requestYYSubmitBean(map)
                .subscribe(new SampleShowDialogObserver<BaseBean>(mIView) {
                    @Override
                    protected void onSuccess(BaseBean bean) throws Exception {
                        mIView.setYYSubmitBean(bean);
                    }
                });
    }
}
