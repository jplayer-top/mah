package com.modiwu.mah.base;

import android.annotation.SuppressLint;
import android.support.annotation.LayoutRes;
import android.view.View;
import android.widget.FrameLayout;

import top.jplayer.baseprolibrary.ui.SuperBaseActivity;

/**
 * Created by Obl on 2018/1/19.
 * com.modiwu.mah.base
 */

@SuppressLint("Registered")
public abstract class BaseSpecialActivity extends SuperBaseActivity {

    @Override
    public View setDefView() {
        mBaseActivity = this;
        contentView = View.inflate(this, setBaseLayout(), null);
        initBaseData();
        return contentView;
    }

    public abstract @LayoutRes
    int setBaseLayout();

    public abstract void initBaseData();

    /**
     * 特殊的ToolBar 不实现该方法
     *
     * @param mFlRootView 根布局下的FrameLayout
     */
    @Override
    public void initSuperData(FrameLayout mFlRootView) {

    }
}
