package com.modiwu.mah.ui.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.modiwu.mah.R;
import com.modiwu.mah.base.BaseFragment;
import com.modiwu.mah.mvp.model.OrderListModel;
import com.modiwu.mah.mvp.model.bean.MeOrderBean;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import top.jplayer.baseprolibrary.utils.ToastUtils;

/**
 * Created by Obl on 2018/2/27.
 * com.modiwu.mah.ui.fragment
 */

public abstract class OrderBaseFragment extends BaseFragment {

    OrderListModel mModel;
    private RecyclerView mRecyclerView;

    @Override
    public int initLayout() {
        return R.layout.fragment_order_willpay;
    }

    @Override
    protected void initData(View rootView) {
        mMultipleStatusView = rootView.findViewById(R.id.multiplestatusview);
        smartRefreshLayout = rootView.findViewById(R.id.smartRefreshLayout);
        smartRefreshLayout.setOnRefreshListener(refresh -> requestDate());
        mRecyclerView = rootView.findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        mModel = new OrderListModel();
        showLoading();

    }

    public void requestDate() {
        mModel.requestOrderBean(getStatus())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(meOrderBean -> {
                    smartRefreshLayout.finishRefresh();

                    if (meOrderBean.records != null) {
                        getOrderList(meOrderBean);
                    } else {
                        this.showEmpty();
                    }
                }, throwable -> {
                    smartRefreshLayout.finishRefresh();
                    if (throwable.getMessage().contains("401")) {
                        ToastUtils.init().showErrorToast(getContext(), "请先登录");
                    } else {
                        this.showError();
                    }
                });
    }

    public abstract String getStatus();

    public abstract RecyclerView.Adapter bindAdapter(MeOrderBean meOrderBean);

    private void getOrderList(MeOrderBean meOrderBean) {
        if (meOrderBean.records.size() < 1) {
            showEmpty();
        } else {
            mMultipleStatusView.showContent();
            mRecyclerView.setAdapter(bindAdapter(meOrderBean));
        }

    }
}
