package com.modiwu.mah.ui.activity;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.modiwu.mah.R;
import com.modiwu.mah.base.BaseCommonActivity;
import com.modiwu.mah.mvp.constract.DesignerContract;
import com.modiwu.mah.mvp.model.bean.DesignBean;
import com.modiwu.mah.mvp.presenter.DesignerPresenter;
import com.modiwu.mah.ui.adapter.DesignerDetialsAdapter;
import com.modiwu.mah.ui.adapter.DesignerFangAnAdapter;

import java.util.LinkedList;
import java.util.List;

import top.jplayer.baseprolibrary.utils.LogUtil;

/**
 * Created by Obl on 2018/2/3.
 * com.modiwu.mah.ui.activity
 */

public class DesignerActivity extends BaseCommonActivity implements DesignerContract.IDesignerView {

    private RecyclerView mRecyclerView;
    private DesignerPresenter mPresenter;
    private DelegateAdapter mDelegateAdapter;

    @Override
    public int setBaseLayout() {
        return R.layout.activity_designer;
    }

    @Override
    public void initBaseData() {
        findToolBarView(addRootView);
        mMultipleStatusView = addRootView.findViewById(R.id.multiplestatusview);
        smartRefreshLayout = addRootView.findViewById(R.id.smartRefreshLayout);
        mRecyclerView = addRootView.findViewById(R.id.recyclerView);
        String designer_id = mBundle.getString("designer_id");

        VirtualLayoutManager manager = new VirtualLayoutManager(this);
        mRecyclerView.setLayoutManager(manager);
        RecyclerView.RecycledViewPool pool = new RecyclerView.RecycledViewPool();
        pool.setMaxRecycledViews(0, 10);
        mRecyclerView.setRecycledViewPool(pool);
        mDelegateAdapter = new DelegateAdapter(manager, true);

        mPresenter = new DesignerPresenter(this);
        showLoading();
        mPresenter.requestDesignerData(designer_id);
        smartRefreshLayout.setOnRefreshListener(refresh -> mPresenter.requestDesignerData(designer_id));
    }

    @Override
    public void setDesignerData(DesignBean bean) {
        LogUtil.e(bean);
        List<DelegateAdapter.Adapter> adapters = new LinkedList<>();
        /**
         * 设计列表
         */
        List<DesignBean.DetailsBean> detailsBeans = bean.details;
        if (detailsBeans != null && detailsBeans.size() > 0) {
            DesignerDetialsAdapter recommendLayoutAdapter = getLayoutAdapter(detailsBeans);
            recommendLayoutAdapter.setDetials(detailsBeans);
            adapters.add(recommendLayoutAdapter);
        } /**
         * 设计列表
         */
        List<DesignBean.FanganBean> fanganBeans = bean.fangan;
        if (fanganBeans != null && fanganBeans.size() > 0) {
            DesignerFangAnAdapter fangAnAdapter = getFangAnLayoutAdapter(fanganBeans);
            fangAnAdapter.setFangAn(fanganBeans);
            adapters.add(fangAnAdapter);
        }
        mDelegateAdapter.clear();
        mDelegateAdapter.setAdapters(adapters);
        mRecyclerView.setAdapter(mDelegateAdapter);
    }

    @NonNull
    private DesignerDetialsAdapter getLayoutAdapter(List beans) {
        return new DesignerDetialsAdapter(this, new LinearLayoutHelper(), beans.size(), DesignBean.BODY_DETIALS);
    }

    @NonNull
    private DesignerFangAnAdapter getFangAnLayoutAdapter(List beans) {
        return new DesignerFangAnAdapter(this, new LinearLayoutHelper(), beans.size(), DesignBean.BODY_FANGAN);
    }

}
