package com.modiwu.mah.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.modiwu.mah.R;
import com.modiwu.mah.base.BaseFragment;
import com.modiwu.mah.mvp.model.bean.ShopGoodsInfoBean;
import com.modiwu.mah.ui.activity.SchemeDetailActivity;
import com.modiwu.mah.ui.activity.ShopDetailActivity;
import com.modiwu.mah.ui.adapter.ShopDetailAdpater;

import java.util.List;

import top.jplayer.baseprolibrary.utils.ActivityUtils;
import top.jplayer.baseprolibrary.widgets.MultipleStatusView;

/**
 * Created by Administrator on 2018/2/6.
 * 商品详情方案
 */

public class ShopDetailFangAnFragment extends BaseFragment {

    private ShopDetailAdpater mAdapter;

    @Override
    public int initLayout() {
        return R.layout.fragment_shop_detail_fangan;
    }

    ShopDetailActivity activity;

    @Override
    protected void initData(View rootView) {
        activity = (ShopDetailActivity) getActivity();
        ShopGoodsInfoBean bean = activity.bean;
        MultipleStatusView multipleStatusView = rootView.findViewById(R.id.multiplestatusview);
        if (bean != null && bean.fangans != null && bean.fangans.size() > 0) {
            List<ShopGoodsInfoBean.FangansBean> fangans = bean.fangans;
            RecyclerView recyclerView = rootView.findViewById(R.id.recyclerView);
            multipleStatusView.showContent();
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
            mAdapter = new ShopDetailAdpater(fangans);
            recyclerView.setAdapter(mAdapter);
            mAdapter.setOnItemChildClickListener((adapter1, view, position) -> {
                ShopGoodsInfoBean.FangansBean fangansBean = mAdapter.getData().get(position);
                int fangan_id = fangansBean.fangan_id;
                Bundle bundle = new Bundle();
                bundle.putString("fangan_id", fangan_id + "");
                ActivityUtils.init().start(getContext(), SchemeDetailActivity.class, fangansBean.fangan_name, bundle);
                return false;
            });
        } else {
            multipleStatusView.showEmpty();
        }
    }
}
