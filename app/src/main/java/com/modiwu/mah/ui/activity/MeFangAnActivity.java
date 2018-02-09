package com.modiwu.mah.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.modiwu.mah.R;
import com.modiwu.mah.base.BaseCommonActivity;
import com.modiwu.mah.mvp.model.bean.MeFangAnBean;
import com.modiwu.mah.mvp.presenter.MeFangAnPresenter;
import com.modiwu.mah.ui.adapter.MeFanganAdapter;

import java.util.ArrayList;
import java.util.Locale;

import top.jplayer.baseprolibrary.utils.ActivityUtils;

/**
 * Created by Obl on 2018/2/6.
 * com.modiwu.mah.ui.activity
 */

public class MeFangAnActivity extends BaseCommonActivity {

    private MeFanganAdapter mAdapter;
    private MeFangAnPresenter mPresenter;

    @Override
    public int setBaseLayout() {
        return R.layout.activity_me_fangan;
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
        ArrayList<MeFangAnBean.RowsBean> data = new ArrayList<>();
        mPresenter = new MeFangAnPresenter(this);
        mPresenter.requestFangAnData();
        mAdapter = new MeFanganAdapter(data);
        mAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            MeFangAnBean.RowsBean bean = mAdapter.getData().get(position);
            switch (view.getId()) {
                case R.id.tvOrderToCancel:
                    mPresenter.requestDelFangAnData(bean.order_no);
                    break;
                case R.id.tvOrderToPay:
                    Bundle bundle = new Bundle();
                    bundle.putString("totalPrice", String.format(Locale.CHINA, "￥%s", bean.order_fee_yuan));
                    bundle.putString("orderId", bean.order_no);
                    bundle.putString("type", "方案");
                    ActivityUtils.init().start(this, ShopPayActivity.class, "支付", bundle);
                    break;
            }

            return false;
        });
        mRecyclerView.setAdapter(mAdapter);
        smartRefreshLayout.setOnRefreshListener(refresh -> mPresenter.requestFangAnData());
    }

    public void setMeFangAn(MeFangAnBean bean) {
        mAdapter.setNewData(bean.rows);
    }
}
