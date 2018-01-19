package com.modiwu.mah.base;

import android.annotation.SuppressLint;
import android.support.annotation.LayoutRes;
import android.view.View;
import android.widget.FrameLayout;

import top.jplayer.baseprolibrary.ui.SuperBaseActivity;


/**
 * Created by Obl on 2018/1/9.
 * top.jplayer.zhenggejia.base
 */

@SuppressLint("Registered")
public abstract class BaseCommonActivity extends SuperBaseActivity {
    public View mBaseView;
    @Override
    public void initSuperData(FrameLayout mFlRootView) {
        mBaseActivity = this;
        mFlRootView.removeAllViews();
        mFlRootView.addView(setBaseInflate());
        initBaseData();
    }

    private View setBaseInflate() {
        mBaseView = View.inflate(this, setBaseLayout(), null);
        return mBaseView;
    }

    public abstract @LayoutRes
    int setBaseLayout();

    public abstract void initBaseData();
}
