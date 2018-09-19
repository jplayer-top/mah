package com.modiwu.mah.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.modiwu.mah.R;
import com.modiwu.mah.base.BaseCommonActivity;
import com.modiwu.mah.mvp.model.bean.InvListBean;
import com.modiwu.mah.mvp.model.bean.MsgListBean;
import com.modiwu.mah.mvp.model.event.DecorateMessageSelEvent;
import com.modiwu.mah.mvp.presenter.DecorateBasePresenter;
import com.modiwu.mah.ui.adapter.MessageInvAdapter;
import com.modiwu.mah.ui.adapter.MessageMsgAdapter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import top.jplayer.baseprolibrary.utils.ActivityUtils;

/**
 * Created by Obl on 2018/8/29.
 * com.modiwu.mah.ui.activity
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */

public class DecorateMessageListActivity extends BaseCommonActivity {

    private RecyclerView mRecyclerView;
    private MessageInvAdapter mAdapter;
    private DecorateBasePresenter mPresenter;
    private MessageMsgAdapter mMsgAdapter;

    @Override
    public int setBaseLayout() {
        return R.layout.activity_decorate_message;
    }

    @Override
    public void initBaseData() {
        EventBus.getDefault().register(this);
        mRecyclerView = mFlRootView.findViewById(R.id.recyclerView);
        mMultipleStatusView = addRootView.findViewById(R.id.multiplestatusview);
        smartRefreshLayout = addRootView.findViewById(R.id.smartRefreshLayout);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mPresenter = new DecorateBasePresenter(this);
        if ("邀请详情".equals(tvBarTitle.getText())) {
            mPresenter.getInvList();
            mAdapter = new MessageInvAdapter(null);
            mRecyclerView.setAdapter(mAdapter);
            mAdapter.setOnItemClickListener((adapter, view, position) -> {
                Bundle bundle = new Bundle();
                bundle.putParcelable("bean", mAdapter.getData().get(position));
                ActivityUtils.init().start(this, MessageDetailActivity.class, "邀请详情", bundle);
            });
            mAdapter.setEmptyView(View.inflate(this, R.layout.layout_empty_view, null));
        } else {
            mPresenter.getMsgList();
            mMsgAdapter = new MessageMsgAdapter(null);
            mRecyclerView.setAdapter(mMsgAdapter);
            mMsgAdapter.setEmptyView(View.inflate(this, R.layout.layout_empty_view, null));
        }
        showLoading();
        smartRefreshLayout.setOnRefreshListener(refresh -> {
            if ("邀请详情".equals(tvBarTitle.getText())) {
                mPresenter.getInvList();
            } else {
                mPresenter.getMsgList();
            }
        });
    }

    @Override
    public void getMsgList(MsgListBean bean) {
        smartRefreshLayout.finishRefresh();
        mMultipleStatusView.showContent();
        mMsgAdapter.setNewData(bean.wmsgs);
    }

    @Override
    public void getInvList(InvListBean bean) {
        smartRefreshLayout.finishRefresh();
        mMultipleStatusView.showContent();
        mAdapter.setNewData(bean.invts);
    }

    @Subscribe
    public void onEvent(DecorateMessageSelEvent event) {
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
