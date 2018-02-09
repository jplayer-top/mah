package com.modiwu.mah.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.gson.Gson;
import com.modiwu.mah.R;
import com.modiwu.mah.base.BaseCommonActivity;
import com.modiwu.mah.mvp.model.bean.DefLocalBean;
import com.modiwu.mah.mvp.model.bean.LocalListBean;
import com.modiwu.mah.mvp.model.event.LocalEvent;
import com.modiwu.mah.mvp.presenter.LocalListPresenter;
import com.modiwu.mah.ui.adapter.LocalListAdapter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import top.jplayer.baseprolibrary.utils.ActivityUtils;
import top.jplayer.baseprolibrary.utils.ToastUtils;

/**
 * Created by Obl on 2018/2/7.
 * com.modiwu.mah.ui.activity
 */

public class LocalListActivity extends BaseCommonActivity {
    private RecyclerView mRecyclerView;
    private LocalListAdapter mAdapter;
    private LocalListPresenter mPresenter;

    @Override
    public int setBaseLayout() {
        return R.layout.activity_designer;
    }

    @Override
    public void initBaseData() {
        findToolBarView(addRootView);
        EventBus.getDefault().register(this);
        ivBarSearch.setVisibility(View.VISIBLE);
        ivBarSearch.setImageResource(R.drawable.local_create);
        ivBarSearch.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putString("isCreate", "1");
            ActivityUtils.init().start(this, LocalCreateActivity.class, "收货地址", bundle);
        });
        mMultipleStatusView = addRootView.findViewById(R.id.multiplestatusview);
        smartRefreshLayout = addRootView.findViewById(R.id.smartRefreshLayout);
        mRecyclerView = addRootView.findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        DividerItemDecoration decoration = new DividerItemDecoration(this, LinearLayoutManager.VERTICAL);
        decoration.setDrawable(getResources().getDrawable(R.drawable.shape_divider));
        mRecyclerView.addItemDecoration(decoration);
        mAdapter = new LocalListAdapter(new ArrayList<>());
        mRecyclerView.setAdapter(mAdapter);
        showLoading();
        mPresenter = new LocalListPresenter(this);
        mPresenter.requestLocalBean();
        smartRefreshLayout.setOnRefreshListener(refresh -> mPresenter.requestLocalBean());
        mAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            List<LocalListBean.RecordsBean> recordsBeans = mAdapter.getData();
            switch (view.getId()) {
                case R.id.localEdit:
                    Bundle bundle = new Bundle();
                    bundle.putString("isCreate", "0");
                    bundle.putParcelable("local", recordsBeans.get(position));
                    ActivityUtils.init().start(this, LocalCreateActivity.class,
                            "收货地址", bundle);
                    break;
                case R.id.localDel:
                    String rpid = recordsBeans.get(position).rp_id + "";
                    mPresenter.delLocal(rpid);
                    break;
                case R.id.checkbox:
                    if (recordsBeans.get(position).rp_default == 1) {
                        ToastUtils.init().showInfoToast(this, "必须有一个默认地址");
                        mAdapter.notifyItemChanged(position);
                        break;
                    }
                    String rp_id = recordsBeans.get(position).rp_id + "";
                    mPresenter.setDefLocal(rp_id);
                    break;
            }
            return false;
        });
        mAdapter.setOnItemClickListener((adapter, view, position) -> {
            LocalListBean.RecordsBean recordsBean = mAdapter.getData().get(position);
            Gson gson = new Gson();
            String json = gson.toJson(recordsBean);
            DefLocalBean.AddrBean localBean = gson.fromJson(json, DefLocalBean.AddrBean.class);
            EventBus.getDefault().post(localBean);
            finish();
        });
    }

    @Subscribe
    public void updateLocalList(LocalEvent event) {
        mPresenter.requestLocalBean();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    public void delLocal() {
        mPresenter.requestLocalBean();
    }

    public void setLocalListBean(LocalListBean listBean) {
        mAdapter.setNewData(listBean.records);
    }
}
