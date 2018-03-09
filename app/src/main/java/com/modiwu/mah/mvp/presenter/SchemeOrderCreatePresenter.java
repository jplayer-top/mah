package com.modiwu.mah.mvp.presenter;

import com.modiwu.mah.mvp.model.SchemeDetailModel;
import com.modiwu.mah.mvp.model.bean.OrderCreateBean;
import com.modiwu.mah.ui.activity.SchemeOrderCreateActivity;

import java.util.Map;

import io.reactivex.disposables.Disposable;
import top.jplayer.baseprolibrary.mvp.contract.BasePresenter;
import top.jplayer.baseprolibrary.mvp.contract.IContract;
import top.jplayer.baseprolibrary.net.SampleShowDialogObserver;
import top.jplayer.baseprolibrary.utils.LogUtil;

/**
 * Created by Obl on 2018/1/25.
 * com.modiwu.mah.mvp.presenter
 */

public class SchemeOrderCreatePresenter extends BasePresenter<SchemeOrderCreateActivity> implements IContract.IPresenter {

    private final SchemeDetailModel mModel;

    public SchemeOrderCreatePresenter(SchemeOrderCreateActivity iView) {
        super(iView);
        mModel = new SchemeDetailModel();
    }


    public void requestSchemeOrderCreateData(String fangan_id) {
        Disposable disposable = mModel.requestSchemeOrderCreateBean(fangan_id).subscribe(bean -> {
            if (bean == null) {
                mIView.showEmpty();
            } else {
                mIView.mMultipleStatusView.showContent();
                mIView.setOrderCreateBean(bean);
            }
        }, throwable -> {
            LogUtil.e(throwable.getMessage());
            mIView.showError();
        });
        addSubscription(disposable);
    }

    public void requestOrderCreateData(Map<String, String> map) {
        mModel.requestOrderCreateBean(map).subscribe(new SampleShowDialogObserver<OrderCreateBean>(mIView) {
            @Override
            protected void onSuccess(OrderCreateBean baseBean) throws Exception {
                mIView.setOrderCreate(baseBean);

            }
        });
    }

    public void requestOrderLocalData() {
        Disposable disposable = mModel.requestOrderLocalBean().subscribe(bean -> {
            if (bean == null) {
                mIView.showEmpty();
            } else {
                mIView.mMultipleStatusView.showContent();
                mIView.setOrderLocal(bean);
            }
        }, throwable -> mIView.showError());
        addSubscription(disposable);
    }
}
