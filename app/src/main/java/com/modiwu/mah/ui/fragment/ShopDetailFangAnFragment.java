package com.modiwu.mah.ui.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.modiwu.mah.R;
import com.modiwu.mah.base.BaseFragment;
import com.modiwu.mah.mvp.model.bean.ShopGoodsInfoBean;
import com.modiwu.mah.ui.activity.ShopDetailActivity;
import com.modiwu.mah.ui.adapter.ShopDetailAdpater;

import java.util.List;

/**
 * Created by Administrator on 2018/2/6.
 * 商品详情方案
 */

public class ShopDetailFangAnFragment extends BaseFragment {
    @Override
    public int initLayout() {
        return R.layout.fragment_shop_detail_fangan;
    }

    ShopDetailActivity activity;

    @Override
    protected void initData(View rootView) {
        activity = (ShopDetailActivity) getActivity();
        ShopGoodsInfoBean bean = activity.bean;
        if (bean != null && bean.fangans != null && bean.fangans.size() > 0) {

            List<ShopGoodsInfoBean.FangansBean> fangans = bean.fangans;
            RecyclerView recyclerView = rootView.findViewById(R.id.recyclerView);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
            recyclerView.setAdapter(new ShopDetailAdpater(fangans));
        }
    }
}
