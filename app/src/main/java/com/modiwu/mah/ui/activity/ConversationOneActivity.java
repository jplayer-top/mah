package com.modiwu.mah.ui.activity;

import com.modiwu.mah.R;
import com.modiwu.mah.base.BaseCommonActivity;

/**
 * Created by PEO on 2017/2/22.
 * 单聊界面
 */

public class ConversationOneActivity extends BaseCommonActivity {


    @Override
    public int setBaseLayout() {
        return R.layout.conversation;
    }

    @Override
    public void initBaseData() {
        tvBarTitle.setText("客服");
    }
}
