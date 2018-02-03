package com.modiwu.mah.ui.activity;

import com.modiwu.mah.R;
import com.modiwu.mah.base.BaseCommonActivity;

/**
 * Created by Obl on 2018/2/3.
 * com.modiwu.mah.ui.activity
 */

public class DesignerActivity extends BaseCommonActivity {
    @Override
    public int setBaseLayout() {
        return R.layout.activity_designer;
    }

    @Override
    public void initBaseData() {
        findToolBarView(addRootView);
    }
}
