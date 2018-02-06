package com.modiwu.mah.ui.activity;

import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.modiwu.mah.R;
import com.modiwu.mah.base.BaseCommonActivity;
import com.modiwu.mah.mvp.constract.OrderListContract;
import com.modiwu.mah.mvp.model.bean.MeOrderBean;
import com.modiwu.mah.mvp.presenter.OrderListPresenter;
import com.modiwu.mah.ui.adapter.MeOrderAdapter;

import java.util.ArrayList;

import top.jplayer.baseprolibrary.mvp.model.bean.BaseBean;

/**
 * Created by Obl on 2018/2/6.
 * com.modiwu.mah.ui.activity
 */


public class MeOrderActivity extends BaseCommonActivity implements OrderListContract.IOrderListView {

    private OrderListPresenter mPresenter;
    private MeOrderAdapter mAdapter;

    @Override
    public int setBaseLayout() {
        return R.layout.activity_me_order;
    }

    RecyclerView mRecyclerView;

    @Override
    public void initBaseData() {
        findToolBarView(addRootView);
        mMultipleStatusView = addRootView.findViewById(R.id.multiplestatusview);
        smartRefreshLayout = addRootView.findViewById(R.id.smartRefreshLayout);
        mRecyclerView = addRootView.findViewById(R.id.recyclerView);
        DividerItemDecoration decor = new DividerItemDecoration(this, LinearLayoutManager.VERTICAL);
        decor.setDrawable(getResources().getDrawable(R.drawable.shape_divider));
        mRecyclerView.addItemDecoration(decor);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,
                false));
        ArrayList<MeOrderBean> data = new ArrayList<>();

        mAdapter = new MeOrderAdapter(data);
        mRecyclerView.setAdapter(mAdapter);
        mPresenter = new OrderListPresenter(this);
        showLoading();
        mPresenter.requestOrderListData();
        smartRefreshLayout.setOnRefreshListener(refresh -> mPresenter.requestOrderListData());
    }

    @Override
    public void setOrderListData(MeOrderBean bean) {
//        mAdapter.setNewData(bean.records);
    }

    @Override
    public void setOrderListDel(BaseBean baseBean, int position) {

    }
}