package com.modiwu.mah.ui.activity;

import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.modiwu.mah.R;
import com.modiwu.mah.base.BaseCommonActivity;
import com.modiwu.mah.mvp.model.bean.MeFangAnBean;
import com.modiwu.mah.mvp.presenter.MeFangAnPresenter;
import com.modiwu.mah.ui.adapter.MeFanganAdapter;

import java.util.ArrayList;

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
            String order_no = mAdapter.getData().get(position).order_no;
            mPresenter.requestDelFangAnData(order_no);
            return false;
        });
        mRecyclerView.setAdapter(mAdapter);
        smartRefreshLayout.setOnRefreshListener(refresh -> mPresenter.requestFangAnData());
    }

    public void setMeFangAn(MeFangAnBean bean) {
        mAdapter.setNewData(bean.rows);
    }
}
