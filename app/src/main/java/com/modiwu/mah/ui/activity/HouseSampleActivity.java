package com.modiwu.mah.ui.activity;

import android.view.View;

import com.modiwu.mah.R;
import com.modiwu.mah.base.BaseCommonActivity;

import top.jplayer.baseprolibrary.widgets.DialogFlowSure;

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
        final DialogFlowSure rxDialogSure = new DialogFlowSure(mBaseActivity);//提示弹窗
        rxDialogSure.getLogoView().setImageResource(R.mipmap.ic_launcher);
        rxDialogSure.getSureView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rxDialogSure.cancel();
            }
        });
        rxDialogSure.show();
    }
}
