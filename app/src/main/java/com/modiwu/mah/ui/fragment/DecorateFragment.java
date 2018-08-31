package com.modiwu.mah.ui.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.modiwu.mah.R;
import com.modiwu.mah.base.BaseFragment;
import com.modiwu.mah.ui.activity.DecorateAddProjectActivity;
import com.modiwu.mah.ui.activity.DecorateAllProjectActivity;
import com.modiwu.mah.ui.activity.DecorateSelectActivity;
import com.modiwu.mah.ui.activity.MessageActivity;
import com.modiwu.mah.ui.adapter.DecorateAdapter;
import com.modiwu.mah.ui.dialog.DialogChangeMan;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import top.jplayer.baseprolibrary.utils.ActivityUtils;
import top.jplayer.baseprolibrary.widgets.MultipleStatusView;

/**
 * Created by Obl on 2018/8/29.
 * com.modiwu.mah.ui.fragment
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */

public class DecorateFragment extends BaseFragment {
    @BindView(R.id.ivGoBack)
    ImageView mIvGoBack;
    @BindView(R.id.tvBarTitle)
    TextView mTvBarTitle;
    @BindView(R.id.ivBarSearch)
    ImageView mIvBarSearch;
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.multiplestatusview)
    MultipleStatusView mMultiplestatusview;
    @BindView(R.id.smartRefreshLayout)
    SmartRefreshLayout mSmartRefreshLayout;
    private Unbinder mUnbinder;
    private DecorateAdapter mAdapter;

    @Override
    public int initLayout() {
        return R.layout.fragment_decorate;
    }

    @Override
    protected void initData(View rootView) {
        super.initData(rootView);
        mUnbinder = ButterKnife.bind(this, rootView);
        mTvBarTitle.setText("我的家装");
        mIvGoBack.setVisibility(View.VISIBLE);
        mIvBarSearch.setVisibility(View.VISIBLE);
        mIvGoBack.setImageDrawable(getResources().getDrawable(R.drawable.decorate_yezhu));
        mIvBarSearch.setImageDrawable(getResources().getDrawable(R.drawable.decorate_notification));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        mAdapter = new DecorateAdapter(new ArrayList<>());
        mRecyclerView.setAdapter(mAdapter);
        mIvGoBack.setOnClickListener(v -> {
            ActivityUtils.init().start(this.getContext(), DecorateSelectActivity.class, "身份选择");
        });
        mIvBarSearch.setOnClickListener(v -> {
            ActivityUtils.init().start(this.getContext(), MessageActivity.class, "消息通知");
        });
        View header = View.inflate(this.getContext(), R.layout.layout_header_decorate, null);
        initHeader(header);
        mAdapter.addHeaderView(header);
    }

    private void initHeader(View header) {
        header.findViewById(R.id.tvAllPro).setOnClickListener(v -> {
            ActivityUtils.init().start(this.getContext(), DecorateAllProjectActivity.class, "所有项目");
        });
        header.findViewById(R.id.tvAddPro).setOnClickListener(v -> {
            ActivityUtils.init().start(this.getContext(), DecorateAddProjectActivity.class, "加入项目");
        });
        header.findViewById(R.id.tvChangeMan).setOnClickListener(v -> {
            new DialogChangeMan(this.getContext()).show();
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
    }
}
