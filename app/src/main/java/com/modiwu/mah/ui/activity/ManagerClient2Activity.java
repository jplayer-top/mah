package com.modiwu.mah.ui.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.modiwu.mah.R;
import com.modiwu.mah.base.BaseCommonActivity;
import com.modiwu.mah.ui.adapter.ManagerClientAdapter;

import java.util.ArrayList;

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

public class ManagerClient2Activity extends BaseCommonActivity {


    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    private Unbinder mUnbinder;
    private ManagerClientAdapter mAdapter;

    @Override
    public int setBaseLayout() {
        return R.layout.activity_manager_client2;
    }

    @Override
    public void initBaseData() {
        mUnbinder = ButterKnife.bind(this, addRootView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        ArrayList<String> list = new ArrayList<>();
        list.add("1");
        list.add("1");
        list.add("1");
        list.add("1");
        mAdapter = new ManagerClientAdapter(list);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
    }
}
