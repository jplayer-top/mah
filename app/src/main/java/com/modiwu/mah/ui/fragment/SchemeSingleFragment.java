package com.modiwu.mah.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.modiwu.mah.R;
import com.modiwu.mah.mvp.model.bean.SchemeDetailBean;
import com.modiwu.mah.ui.activity.SchemeDetailActivity;
import com.modiwu.mah.ui.activity.ShopDetialActivity;
import com.modiwu.mah.ui.adapter.SchemeSingleAdapter;

import java.util.List;
import java.util.Locale;

import top.jplayer.baseprolibrary.ui.Fragment.SuperBaseFragment;
import top.jplayer.baseprolibrary.utils.ActivityUtils;

/**
 * Created by Administrator on 2018/1/23.
 * 硬装，软装Fragment
 */

public class SchemeSingleFragment extends SuperBaseFragment {


    SchemeDetailActivity mActivity;
    List<SchemeDetailBean.GoodsBean> mGoodsBeans;

    @Override
    protected void initData(View rootView) {
        mActivity = (SchemeDetailActivity) getActivity();
        mGoodsBeans = mActivity.mSchemeDetailBean.goods;
        RecyclerView recyclerView = rootView.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        SchemeSingleAdapter adapter = new SchemeSingleAdapter(mGoodsBeans);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener((adapter1, view, position) -> {
            SchemeDetailBean.GoodsBean goodsBean = (SchemeDetailBean.GoodsBean) adapter1.getData().get(position);
            Bundle bundle = new Bundle();
            bundle.putString("goods_id", String.format(Locale.CHINA, "%d", goodsBean.goods_id));
            ActivityUtils.init().start(getContext(), ShopDetialActivity.class, goodsBean.goods_title, bundle);
        });
    }

    @Override
    public int initLayout() {
        return R.layout.fragment_scheme_hard;
    }

}
