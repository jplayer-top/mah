package com.modiwu.mah.base;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.modiwu.mah.R;

import top.jplayer.baseprolibrary.ui.Fragment.SuperBaseFragment;
import top.jplayer.baseprolibrary.widgets.MultipleStatusView;

/**
 * Created by Obl on 2018/1/19.
 * com.modiwu.mah.base
 */

public abstract class BaseFragment extends SuperBaseFragment {
    public MultipleStatusView mMultipleStatusView;
    public TextView tvBarTitle;
    public ImageView ivBarSearch;

    @Override
    protected void initData(View rootView) {
        tvBarTitle = rootView.findViewById(R.id.tvBarTitle);
        ivBarSearch = rootView.findViewById(R.id.ivBarSearch);
    }

    @Override
    public abstract int initLayout();
}
