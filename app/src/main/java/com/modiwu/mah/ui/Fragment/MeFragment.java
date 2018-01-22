package com.modiwu.mah.ui.Fragment;

import android.view.View;

import com.modiwu.mah.R;
import com.modiwu.mah.base.BaseFragment;

/**
 * Created by Obl on 2018/1/19.
 * com.modiwu.mah.ui.Fragment
 */

public class MeFragment extends BaseFragment {
    @Override
    public int initLayout() {
        return R.layout.fragment_me;
    }

    @Override
    protected void initData(View rootView) {
        super.initData(rootView);
        tvBarTitle.setText("我的");
    }
}
