package com.modiwu.mah.ui.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.modiwu.mah.R;
import com.modiwu.mah.base.BaseCommonActivity;
import com.modiwu.mah.ui.adapter.MessageAdapter;

import java.util.ArrayList;

import top.jplayer.baseprolibrary.utils.ActivityUtils;

/**
 * Created by Obl on 2018/8/29.
 * com.modiwu.mah.ui.activity
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */

public class MessageActivity extends BaseCommonActivity {

    private RecyclerView mRecyclerView;
    private MessageAdapter mAdapter;

    @Override
    public int setBaseLayout() {
        return R.layout.activity_decorate_message;
    }

    @Override
    public void initBaseData() {
        mRecyclerView = mFlRootView.findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        ArrayList<String> strings = new ArrayList<>();
        strings.add("1");
        strings.add("1");
        mAdapter = new MessageAdapter(strings);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener((adapter, view, position) -> {
            ActivityUtils.init().start(this, MessageDetailActivity.class, "邀请详情");
        });
    }
}
