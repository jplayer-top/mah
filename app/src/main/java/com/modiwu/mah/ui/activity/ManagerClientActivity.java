package com.modiwu.mah.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.modiwu.mah.R;
import com.modiwu.mah.base.BaseCommonActivity;
import com.modiwu.mah.mvp.model.ManagerModel;
import com.modiwu.mah.mvp.model.bean.ManagerClientBean;
import com.modiwu.mah.ui.adapter.ManagerClientAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import top.jplayer.baseprolibrary.utils.ActivityUtils;

/**
 * Created by Obl on 2018/7/30.
 * com.modiwu.mah.ui.activity
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */

public class ManagerClientActivity extends BaseCommonActivity {


    @BindView(R.id.tvCount1)
    TextView mTvCount1;
    @BindView(R.id.tvCount2)
    TextView mTvCount2;
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    private Unbinder mUnbinder;
    private ManagerClientAdapter mAdapter;
    private ManagerModel mModel;

    @Override
    public int setBaseLayout() {
        return R.layout.activity_manager_client;
    }

    @Override
    public void initBaseData() {
        mUnbinder = ButterKnife.bind(this, addRootView);
        mModel = new ManagerModel();
        mModel.requestManager().subscribe(bean -> {
            mTvCount1.setText(String.valueOf(bean.lv1));
            mTvCount2.setText(String.valueOf(bean.lv2));
            mAdapter.setNewData(bean.profiles);
        }, throwable -> {
        });
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mAdapter = new ManagerClientAdapter(null);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener((adapter, view, position) -> {
            Bundle bundle = new Bundle();
            ManagerClientBean.ProfileBean bean = mAdapter.getData().get(position);
            bundle.putString("uid", bean.user_id + "");
            ActivityUtils.init().start(this,
                    ManagerClient2Activity.class, bean.user_name + "的客户", bundle);
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
    }
}
