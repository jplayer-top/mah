package com.modiwu.mah.mvp.presenter;

import com.modiwu.mah.mvp.model.ShopDetailModel;
import com.modiwu.mah.mvp.model.bean.OrderCreateBean;
import com.modiwu.mah.ui.activity.ShopToBuyAvtivity;

import java.util.Map;

import io.reactivex.disposables.Disposable;
import top.jplayer.baseprolibrary.mvp.contract.BasePresenter;
import top.jplayer.baseprolibrary.mvp.contract.IContract;
import top.jplayer.baseprolibrary.net.SampleShowDialogObserver;

/**
 * Created by Obl on 2018/1/25.
 * com.modiwu.mah.mvp.presenter
 */

public class ShopToBuyPresenter extends BasePresenter<ShopToBuyAvtivity> implements IContract.IPresenter {

    private final ShopDetailModel mModel;

    public ShopToBuyPresenter(ShopToBuyAvtivity iView) {
        super(iView);
        mModel = new ShopDetailModel();
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
