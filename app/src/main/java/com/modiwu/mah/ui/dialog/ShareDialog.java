package com.modiwu.mah.ui.dialog;


import android.content.Context;
import android.view.View;

import com.modiwu.mah.R;
import com.modiwu.mah.mvp.model.event.ShareAllEvent;
import com.modiwu.mah.mvp.model.event.ShareOneEvent;

import org.greenrobot.eventbus.EventBus;

import top.jplayer.baseprolibrary.utils.ScreenUtils;
import top.jplayer.baseprolibrary.widgets.dialog.BaseBottomDialog;

/**
 * Created by PEO on 2017/2/24.
 * d
 */

public class ShareDialog extends BaseBottomDialog {


    public ShareDialog(Context context) {
        super(context);
    }

    @Override
    protected void initView(View view) {
        view.findViewById(R.id.tvOneShare).setOnClickListener(v -> {
            EventBus.getDefault().post(new ShareOneEvent());
            this.dismiss();
        });
        view.findViewById(R.id.tvAllShare).setOnClickListener(v -> {
            EventBus.getDefault().post(new ShareAllEvent());
            this.dismiss();
        });
        view.findViewById(R.id.tvClose).setOnClickListener(v -> this.dismiss());
    }

    @Override
    public int setWidth() {
        return ScreenUtils.getScreenWidth();
    }

    @Override
    public int initLayout() {
        return R.layout.dialog_share;
    }
}
