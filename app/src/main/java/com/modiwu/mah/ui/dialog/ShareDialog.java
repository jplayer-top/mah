package com.modiwu.mah.ui.dialog;


import android.content.Context;
import android.view.View;

import com.modiwu.mah.R;

import top.jplayer.baseprolibrary.utils.ScreenUtils;

/**
 * Created by PEO on 2017/2/24.
 * d
 */

public class ShareDialog extends BaseBottomFsDialog {


    public ShareDialog(Context context) {
        super(context);
    }

    @Override
    protected void initView(View view) {

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
