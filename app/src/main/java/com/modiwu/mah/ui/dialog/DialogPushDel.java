package com.modiwu.mah.ui.dialog;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.modiwu.mah.R;

import top.jplayer.baseprolibrary.widgets.dialog.BaseCustomDialog;

/**
 * Created by Obl on 2018/8/30.
 * com.modiwu.mah.ui.dialog
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */

public class DialogPushDel extends BaseCustomDialog {


    private TextView mTvTip;

    public DialogPushDel(Context context) {
        super(context);
    }


    @Override
    protected void initView(View view) {
        view.findViewById(R.id.tvCancel).setOnClickListener(v -> dismiss());
        mTvTip = view.findViewById(R.id.tvTipPushDel);
    }

    public void setTvTip(String tip) {
        mTvTip.setText(tip);
    }

    @Override
    public int initLayout() {
        return R.layout.dialog_push_del;
    }

    @Override
    public int setWidth(int i) {
        return super.setWidth(8);
    }

    @Override
    public int setAnim() {
        return R.style.AnimFade;
    }

    @Override
    public int setGravity() {
        return Gravity.CENTER;
    }
}
