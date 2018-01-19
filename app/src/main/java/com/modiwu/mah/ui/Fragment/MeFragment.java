package com.modiwu.mah.ui.Fragment;

import android.view.View;
import android.widget.TextView;

import com.modiwu.mah.R;
import com.modiwu.mah.base.BaseFragment;

import top.jplayer.baseprolibrary.utils.LogUtil;

/**
 * Created by Obl on 2018/1/19.
 * com.modiwu.mah.ui.Fragment
 */

public class MeFragment extends BaseFragment {
    @Override
    public int initLayout() {
        return R.layout.fragment_base;
    }

    @Override
    protected void initData(View rootView) {
        TextView text = rootView.findViewById(R.id.base_text);
        text.setText(getClass().getSimpleName());
        LogUtil.e(getClass().getSimpleName());

    }
}
