package com.modiwu.mah.base;

import android.annotation.SuppressLint;
import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.modiwu.mah.mvp.constract.DecorateContract;
import com.modiwu.mah.mvp.model.bean.DecorateAllProBean;
import com.modiwu.mah.mvp.model.bean.FlowSelBean;
import com.modiwu.mah.mvp.model.bean.MsgHasBean;
import com.modiwu.mah.mvp.model.bean.MsgListBean;
import com.modiwu.mah.mvp.model.bean.ProInfoBean;
import com.modiwu.mah.mvp.model.bean.SelWorkerBean;
import com.modiwu.mah.mvp.model.bean.SelectWorkerTypeBean;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import top.jplayer.baseprolibrary.mvp.contract.IContract;
import top.jplayer.baseprolibrary.ui.SuperBaseActivity;
import top.jplayer.baseprolibrary.widgets.MultipleStatusView;


/**
 * Created by Obl on 2018/1/9.
 * top.jplayer.zhenggejia.base
 */

@SuppressLint("Registered")
public abstract class BaseCommonActivity extends SuperBaseActivity implements IContract.IView, DecorateContract.IDecorateView {
    public View addRootView;
    public MultipleStatusView mMultipleStatusView;
    public SmartRefreshLayout smartRefreshLayout;

    @Override
    public void initSuperData(FrameLayout mFlRootView) {
        mBaseActivity = this;
        mFlRootView.removeAllViews();
        mFlRootView.addView(setBaseInflate(mFlRootView));
        initBaseData();
    }

    private View setBaseInflate(FrameLayout mFlRootView) {
        addRootView = LayoutInflater.from(this).inflate(setBaseLayout(), mFlRootView, false);
        return addRootView;
    }

    public abstract @LayoutRes
    int setBaseLayout();

    public abstract void initBaseData();

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

    @Override
    public void sendCode() {

    }

    @Override
    public void verCode() {
    }

    @Override
    public void regSuperView() {

    }

    @Override
    public void regWorker() {

    }

    @Override
    public void getProInfo(ProInfoBean bean) {

    }

    @Override
    public void sendPush() {

    }

    @Override
    public void addMan() {

    }

    @Override
    public void addSuperView() {

    }

    @Override
    public void addWorker() {

    }

    @Override
    public void getMsgHasInfo(MsgHasBean bean) {

    }

    @Override
    public void getMsgList(MsgListBean bean) {

    }

    @Override
    public void invAgree() {

    }

    @Override
    public void invCancel() {

    }

    @Override
    public void getAllProList(DecorateAllProBean bean) {

    }

    @Override
    public void getWorkerAllProList(DecorateAllProBean bean) {

    }

    @Override
    public void getFlowSel(FlowSelBean bean) {

    }

    @Override
    public void getSelWorker(SelWorkerBean bean) {

    }

    @Override
    public void selectWorkerType(SelectWorkerTypeBean bean) {

    }
}
