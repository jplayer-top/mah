package com.modiwu.mah.ui.activity;

import android.net.Uri;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;

import com.modiwu.mah.R;
import com.modiwu.mah.base.BaseCommonActivity;

import io.rong.imkit.fragment.ConversationFragment;
import top.jplayer.baseprolibrary.utils.KeyBoardUtils;

/**
 * Created by PEO on 2017/2/22.
 * 单聊界面
 */

public class ConversationOneActivity extends BaseCommonActivity {
    FrameLayout mFlShowChat;


    @Override
    protected void onPause() {
        KeyBoardUtils.closeInput(this, mFlShowChat);
        super.onPause();
    }

    @Override
    public int setBaseLayout() {
        return R.layout.conversation;
    }

    @Override
    public void initBaseData() {
        mFlShowChat = addRootView.findViewById(R.id.flShowChat);
        ConversationFragment fragment = new ConversationFragment();
        Uri uri = getIntent().getData();
        fragment.setUri(uri);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.flShowChat, fragment);
        transaction.commit();
    }
}
