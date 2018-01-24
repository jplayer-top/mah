package com.modiwu.mah.ui.activity;

import com.modiwu.mah.R;
import com.modiwu.mah.base.BaseCommonActivity;

/**
 * Created by Obl on 2018/1/24.
 * com.modiwu.mah.ui.activity
 */

public class HouseSampleActivity extends BaseCommonActivity {
    @Override
    public int setBaseLayout() {
        return R.layout.activity_house_sample;
    }

    @Override
    public void initBaseData() {
        tvBarTitle.setText("样板间征集");
    }
}
