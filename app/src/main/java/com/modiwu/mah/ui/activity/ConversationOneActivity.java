package com.modiwu.mah.ui.activity;

import com.modiwu.mah.R;
import com.modiwu.mah.base.BaseCommonActivity;
import com.modiwu.mah.message.CustomizeBPMessage;

import io.rong.imkit.RongIM;
import io.rong.imlib.IRongCallback;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.Message;

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
