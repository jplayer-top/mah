package com.modiwu.mah.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.modiwu.mah.R;
import com.modiwu.mah.base.BaseFragment;
import com.modiwu.mah.mvp.constract.SubShopContract;
import com.modiwu.mah.mvp.model.bean.ShopSubListBean;
import com.modiwu.mah.mvp.model.bean.SubTitleBean;
import com.modiwu.mah.mvp.presenter.ShopSubFragmentPresenter;
import com.modiwu.mah.ui.activity.ShopDetailActivity;
import com.modiwu.mah.ui.activity.ShopSubActivity;
import com.modiwu.mah.ui.adapter.ShopSubFragmentAdapter;

import java.util.ArrayList;
import java.util.Locale;

import top.jplayer.baseprolibrary.utils.ActivityUtils;

/**
 * Created by Administrator on 2018/1/23.
 * 硬装，软装Fragment
 */

public class ShopSubFragment extends BaseFragment implements SubShopContract.ISubShopView {

    ShopSubFragmentPresenter mPresenter;
    ShopSubActivity mActivity;
    private ShopSubFragmentAdapter mAdapter;

    @Override
    protected void initData(View rootView) {
        mActivity = (ShopSubActivity) getActivity();
        RecyclerView recyclerView = rootView.findViewById(R.id.recyclerView);
        mMultipleStatusView = rootView.findViewById(R.id.multiplestatusview);
        smartRefreshLayout = rootView.findViewById(R.id.smartRefreshLayout);
        mPresenter = new ShopSubFragmentPresenter(this);

        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        mAdapter = new ShopSubFragmentAdapter(new ArrayList<>());
        recyclerView.setAdapter(mAdapter);
        showLoading();

        mAdapter.setOnItemClickListener((adapter1, view, position) -> {
            ShopSubListBean.RecordsBean goodsBean = (ShopSubListBean.RecordsBean) adapter1.getData().get(position);
            Bundle bundle = new Bundle();
            bundle.putString("goods_id", String.format(Locale.CHINA, "%d", goodsBean.goods_id));
            ActivityUtils.init().start(getContext(), ShopDetailActivity.class, goodsBean.goods_title, bundle);
        });
    }

    @Override
    public int initLayout() {
        return R.layout.fragment_shop_sub_hard;
    }

    @Override
    public void setSubListData(ShopSubListBean bean) {
        mAdapter.setNewData(bean.records);
    }

    @Override
    public void setSubTitleData(SubTitleBean bean) {

    }

    public void requestData(String cat_id) {
        mPresenter.requestSubListData("0", cat_id);
    }
}
