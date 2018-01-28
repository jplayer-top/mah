package com.modiwu.mah.ui.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.modiwu.mah.R;
import com.modiwu.mah.base.BaseCommonActivity;
import com.modiwu.mah.greendao.ShopCartDaoUtil;
import com.modiwu.mah.mvp.model.bean.ShopCartBean;
import com.modiwu.mah.ui.adapter.ShopCartAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import top.jplayer.baseprolibrary.utils.LogUtil;
import top.jplayer.baseprolibrary.widgets.MultipleStatusView;

/**
 * Created by Administrator on 2018/1/28.
 */

public class ShopCartActivity extends BaseCommonActivity {
    protected RecyclerView mRecyclerView;
    protected MultipleStatusView multipleStatusView;
    protected SmartRefreshLayout smartRefreshLayout;

    @Override
    public int setBaseLayout() {
        return R.layout.activity_shop_cart;
    }

    @Override
    public void initBaseData() {
        findToolBarView(mBaseView);
        multipleStatusView = mBaseView.findViewById(R.id.multiplestatusview);
        smartRefreshLayout = mBaseView.findViewById(R.id.smartRefreshLayout);
        mRecyclerView = mBaseView.findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("1");
        list.add("1");
        list.add("1");
        mRecyclerView.setAdapter(new ShopCartAdapter(list));
        ShopCartDaoUtil daoUtil = new ShopCartDaoUtil(this);
        daoUtil.insertShopCart(new ShopCartBean(null, "sss", "ss2", "12", "1", "sadsd"));
        LogUtil.e(daoUtil.queryAllbean());
    }
}
