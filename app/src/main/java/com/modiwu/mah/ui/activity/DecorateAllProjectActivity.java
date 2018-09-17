package com.modiwu.mah.ui.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.modiwu.mah.R;
import com.modiwu.mah.base.BaseCommonActivity;
import com.modiwu.mah.mvp.model.bean.DecorateAllProBean;
import com.modiwu.mah.mvp.model.event.SelProIdDecorateEvent;
import com.modiwu.mah.mvp.presenter.DecorateBasePresenter;
import com.modiwu.mah.ui.adapter.AddProjectAdapter;
import com.modiwu.mah.ui.adapter.AllProjectAdapter;

import org.greenrobot.eventbus.EventBus;

import top.jplayer.baseprolibrary.utils.SharePreUtil;

/**
 * Created by Obl on 2018/8/30.
 * com.modiwu.mah.ui.activity
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */

public class DecorateAllProjectActivity extends BaseCommonActivity {
    private RecyclerView mRecyclerView;
    private AllProjectAdapter mWorkerAdapter;
    private AddProjectAdapter mSvAdapter;
    private DecorateBasePresenter mPresenter;
    private String mSelect;
    private boolean mEquals;

    @Override
    public int setBaseLayout() {
        return R.layout.activity_app_project;
    }

    @Override
    public void initBaseData() {
        mMultipleStatusView = addRootView.findViewById(R.id.multiplestatusview);
        smartRefreshLayout = addRootView.findViewById(R.id.smartRefreshLayout);
        mRecyclerView = mFlRootView.findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mSelect = (String) SharePreUtil.getData(this, "decorate_select", "监理");
        mEquals = "监理".equals(mSelect);
        mPresenter = new DecorateBasePresenter(this);
        if (mEquals) {
            mSvAdapter = new AddProjectAdapter(null);
            mRecyclerView.setAdapter(mSvAdapter);
            mPresenter.getAllProList();
            mSvAdapter.setOnItemClickListener((adapter, view, position) -> {
                EventBus.getDefault().post(new SelProIdDecorateEvent(mSvAdapter.getData().get(position).project_id, "监理"));
                finish();
            });
        } else {
            mWorkerAdapter = new AllProjectAdapter(null);
            mRecyclerView.setAdapter(mWorkerAdapter);
            mPresenter.getWorkerAllProList();
            mWorkerAdapter.setOnItemClickListener((adapter, view, position) -> {
                EventBus.getDefault().post(new SelProIdDecorateEvent(mWorkerAdapter.getData().get(position)
                        .project_id, "施工"));
                finish();
            });
        }
        showLoading();
        smartRefreshLayout.setOnRefreshListener(refresh -> {
            if (mEquals) {
                mPresenter.getAllProList();
            } else {
                mPresenter.getWorkerAllProList();
            }
        });
    }

    @Override
    public void getWorkerAllProList(DecorateAllProBean bean) {
        smartRefreshLayout.finishRefresh();
        mMultipleStatusView.showContent();
        mWorkerAdapter.setNewData(bean.projects);
    }

    @Override
    public void getAllProList(DecorateAllProBean bean) {
        super.getAllProList(bean);
        smartRefreshLayout.finishRefresh();
        mMultipleStatusView.showContent();
        mSvAdapter.setNewData(bean.projects);
    }
}
