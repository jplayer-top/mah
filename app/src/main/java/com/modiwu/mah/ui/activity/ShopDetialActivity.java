package com.modiwu.mah.ui.activity;

import com.modiwu.mah.R;
import com.modiwu.mah.base.BaseCommonActivity;

import top.jplayer.baseprolibrary.utils.LogUtil;

/**
 * Created by Obl on 2018/2/5.
 * com.modiwu.mah.ui.activity
 */

public class ShopDetialActivity extends BaseCommonActivity {
    @Override
    public int setBaseLayout() {
        return R.layout.activity_shop_detail;
    }

    @Override
    public void initBaseData() {
        String cat_id = mBundle.getString("cat_id");
        LogUtil.e(cat_id);
    }
}
