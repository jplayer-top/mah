package com.modiwu.mah.ui.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.modiwu.mah.R;
import com.modiwu.mah.base.BaseCommonActivity;
import com.modiwu.mah.mvp.model.ManagerModel;
import com.modiwu.mah.ui.adapter.ManagerClient2Adapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Obl on 2018/7/30.
 * com.modiwu.mah.ui.activity
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */

public class ManagerClient2Activity extends BaseCommonActivity {


    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.tvCount1)
    TextView mTvCount1;
    private Unbinder mUnbinder;
    private ManagerClient2Adapter mAdapter;
    private ManagerModel mModel;

    @Override
    public int setBaseLayout() {
        return R.layout.activity_manager_client2;
    }

    @Override
    public void initBaseData() {
        mUnbinder = ButterKnife.bind(this, addRootView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mAdapter = new ManagerClient2Adapter(null);
        mModel = new ManagerModel();
        mModel.requestManager(mBundle.getString("uid")).subscribe(bean -> {
            mTvCount1.setText(String.valueOf(bean.lv1));
            mAdapter.setNewData(bean.profiles);
        }, throwable -> {
        });
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
    }

}
