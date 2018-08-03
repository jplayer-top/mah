package com.modiwu.mah.ui.activity;

import com.modiwu.mah.R;
import com.modiwu.mah.base.BaseCommonActivity;

/**
 * Created by Obl on 2018/8/3.
 * com.modiwu.mah.ui.activity
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */

public class ConversationListActivity extends BaseCommonActivity {
    @Override
    public int setBaseLayout() {
        return R.layout.activity_conversation;
    }

    @Override
    public void initBaseData() {
        tvBarTitle.setText("客服列表");
    }
}
