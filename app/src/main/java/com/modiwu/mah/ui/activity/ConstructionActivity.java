package com.modiwu.mah.ui.activity;

import android.view.View;

import com.modiwu.mah.R;
import com.modiwu.mah.base.BaseCommonActivity;

import top.jplayer.baseprolibrary.utils.LogUtil;

/**
 * Created by Obl on 2018/1/29.
 * com.modiwu.mah.ui.activity
 */

public class ConstructionActivity extends BaseCommonActivity {
    @Override
    public int setBaseLayout() {
        return R.layout.activity_construction;
    }

    @Override
    public void initBaseData() {
        findToolBarView(mBaseView);
        tvBarTitle.setText("施工标准");
        ivBarSearch.setVisibility(View.VISIBLE);
        ivBarSearch.setImageResource(R.drawable.main_me);
        ivBarSearch.setOnClickListener(v -> LogUtil.e("客服"));
    }
}
