package com.modiwu.mah.base;

import android.view.View;

import com.modiwu.mah.R;

import top.jplayer.baseprolibrary.ui.Fragment.SuperBaseFragment;
import top.jplayer.baseprolibrary.widgets.MultipleStatusView;

/**
 * Created by Obl on 2018/1/19.
 * com.modiwu.mah.base
 */

public abstract class BaseFragment extends SuperBaseFragment {
    public MultipleStatusView mMultipleStatusView;

    @Override
    protected void initData(View rootView) {
    }

    @Override
    public abstract int initLayout();
}
