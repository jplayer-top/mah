package com.modiwu.mah.base;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.modiwu.mah.R;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import top.jplayer.baseprolibrary.mvp.contract.IContract;
import top.jplayer.baseprolibrary.ui.Fragment.SuperBaseFragment;
import top.jplayer.baseprolibrary.widgets.MultipleStatusView;

/**
 * Created by Obl on 2018/1/19.
 * com.modiwu.mah.base
 */

public abstract class BaseFragment extends SuperBaseFragment implements IContract.IView {
    public MultipleStatusView mMultipleStatusView;
    public SmartRefreshLayout smartRefreshLayout;
    public TextView tvBarTitle;
    public ImageView ivBarSearch;

    @Override

    protected void initData(View rootView) {
        tvBarTitle = rootView.findViewById(R.id.tvBarTitle);
    }

    @Override
    public abstract int initLayout();


    @Override
    public void showError() {
        if (mMultipleStatusView != null) {
            mMultipleStatusView.showError();
        }
        if (smartRefreshLayout != null && smartRefreshLayout.isRefreshing()) {
            smartRefreshLayout.finishRefresh();
        }
    }

    @Override
    public void showLoading() {
        if (mMultipleStatusView != null) {
            mMultipleStatusView.showLoading();
        }
    }

    @Override
    public void showEmpty() {
        if (mMultipleStatusView != null) {
            mMultipleStatusView.showEmpty();
        }
        if (smartRefreshLayout != null && smartRefreshLayout.isRefreshing()) {
            smartRefreshLayout.finishRefresh();
        }
    }
}
