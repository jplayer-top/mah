package com.modiwu.mah.ui.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.modiwu.mah.R;
import com.modiwu.mah.base.BaseCommonActivity;
import com.modiwu.mah.ui.adapter.AddProjectAdapter;

import java.util.ArrayList;

/**
 * Created by Obl on 2018/8/31.
 * com.modiwu.mah.ui.activity
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */

public class DecorateAddProjectActivity extends BaseCommonActivity {
    private RecyclerView mRecyclerView;
    private AddProjectAdapter mAdapter;

    @Override
    public int setBaseLayout() {
        return R.layout.activity_add_pro;
    }

    @Override
    public void initBaseData() {
        mRecyclerView = mFlRootView.findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        ArrayList<String> strings = new ArrayList<>();
        strings.add("1");
        mAdapter = new AddProjectAdapter(strings);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.addHeaderView(View.inflate(this, R.layout.layout_header_add_pro, null));
        mAdapter.setOnItemClickListener((adapter, view, position) -> {
        });
    }
}
