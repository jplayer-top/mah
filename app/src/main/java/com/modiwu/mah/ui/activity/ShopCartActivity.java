package com.modiwu.mah.ui.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.modiwu.mah.R;
import com.modiwu.mah.base.BaseCommonActivity;
import com.modiwu.mah.mvp.constract.ShopCartContract;
import com.modiwu.mah.mvp.model.bean.ShopCartBean;
import com.modiwu.mah.mvp.presenter.ShopCartPresenter;
import com.modiwu.mah.ui.adapter.ShopCartAdapter;

import java.util.List;

/**
 * Created by Administrator on 2018/1/28.
 * 购物车
 */

public class ShopCartActivity extends BaseCommonActivity implements ShopCartContract.IShopCartView {
    protected RecyclerView mRecyclerView;

    private ShopCartAdapter mAdapter;
    private ShopCartPresenter mPresenter;

    @Override
    public int setBaseLayout() {
        return R.layout.activity_shop_cart;
    }

    @Override
    public void initBaseData() {
        findToolBarView(mBaseView);
        mMultipleStatusView = mBaseView.findViewById(R.id.multiplestatusview);
        smartRefreshLayout = mBaseView.findViewById(R.id.smartRefreshLayout);
        mRecyclerView = mBaseView.findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mPresenter = new ShopCartPresenter(this);
        showLoading();
        mPresenter.requestShopCartData();
        smartRefreshLayout.setOnRefreshListener(refresh -> mPresenter.requestShopCartData());
        mAdapter = new ShopCartAdapter(null);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void setShopCartData(List<ShopCartBean> shopCartBeans) {
        mAdapter.setNewData(shopCartBeans);
    }

    @Override
    public void delOneData() {

    }

    @Override
    public void upDataBean() {

    }
}
