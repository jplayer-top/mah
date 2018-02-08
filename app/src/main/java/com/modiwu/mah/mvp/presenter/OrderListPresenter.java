package com.modiwu.mah.mvp.presenter;

import com.modiwu.mah.mvp.constract.OrderListContract;
import com.modiwu.mah.mvp.model.OrderListModel;
import com.modiwu.mah.mvp.model.bean.MeOrderBean;
import com.modiwu.mah.ui.activity.MeOrderActivity;

import io.reactivex.disposables.Disposable;
import top.jplayer.baseprolibrary.mvp.contract.BasePresenter;
import top.jplayer.baseprolibrary.mvp.model.bean.BaseBean;
import top.jplayer.baseprolibrary.net.SampleShowDialogObserver;

/**
 * Created by Obl on 2018/1/25.
 * com.modiwu.mah.mvp.presenter
 */

public class OrderListPresenter extends BasePresenter<MeOrderActivity> implements OrderListContract.IOrderListPresenter {

    private final OrderListModel mModel;

    public OrderListPresenter(MeOrderActivity iView) {
        super(iView);
        mModel = new OrderListModel();
    }


    private void successBean(MeOrderBean bean) {
        if (bean == null || bean.records == null || bean.records.size() < 1) {
            mIView.showEmpty();
        } else {
            mIView.smartRefreshLayout.finishRefresh();
            mIView.mMultipleStatusView.showContent();
            mIView.setOrderListData(bean);
        }
    }


//
//    @Override
//    public void requestShouCangDel(int fangan_id, int position) {
//        mModel.requestShouCangDel(String.valueOf(fangan_id))
//                .subscribe(new SampleShowDialogObserver<BaseBean>(mIView) {
//                    @Override
//                    protected void onSuccess(BaseBean baseBean) throws Exception {
//                        mIView.setDelShouCang(baseBean, position);
//                    }
//                });
//    }

    @Override
    public void requestOrderListData() {
        Disposable disposable = mModel.requestOrderBean().subscribe(this::successBean, throwable -> mIView.showError());
        addSubscription(disposable);
    }

    @Override
    public void requestOrderListDel(String orderId) {
        mModel.requestOrderDelBean(orderId, "z")
                .subscribe(new SampleShowDialogObserver<BaseBean>(mIView) {
                    @Override
                    protected void onSuccess(BaseBean baseBean) throws Exception {
                        requestOrderListData();
                    }
                });
    }
}
