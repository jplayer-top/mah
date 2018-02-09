package com.modiwu.mah.mvp.presenter;

import com.modiwu.mah.mvp.model.ShopDetailModel;
import com.modiwu.mah.mvp.model.bean.DefLocalBean;
import com.modiwu.mah.mvp.model.bean.OrderCreateBean;
import com.modiwu.mah.ui.activity.ShopToBuyAvtivity;

import java.util.Map;

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
                mIView.setOrderCreate(baseBean,"订单");
            }
        });
    }

    public void requestOrderLocalData() {
        mModel.requestOrderLocalBean().subscribe(new SampleShowDialogObserver<DefLocalBean>(mIView) {
            @Override
            protected void onSuccess(DefLocalBean defLocalBean) throws Exception {
                if (defLocalBean != null && defLocalBean.addr != null) {
                    mIView.setOrderLocal(defLocalBean);
                }else {
                    mIView.setNoOrderLocal();
                }

            }
        });
    }

    public void requestSchemeCreateData(Map<String, String> map) {
        mModel.requestSchemeCreateBean(map).subscribe(new SampleShowDialogObserver<OrderCreateBean>(mIView) {
            @Override
            protected void onSuccess(OrderCreateBean baseBean) throws Exception {
                mIView.setOrderCreate(baseBean,"方案");
            }
        });
    }
}
