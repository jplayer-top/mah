package com.modiwu.mah.ui.activity;

import com.modiwu.mah.R;
import com.modiwu.mah.base.BaseCommonActivity;

import top.jplayer.baseprolibrary.widgets.dialog.DialogFlowSure;

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
        addRootView.findViewById(R.id.btnSublime).setOnClickListener(v -> {
            final DialogFlowSure rxDialogSure = new DialogFlowSure(mBaseActivity);//提示弹窗
            rxDialogSure.getLogoView().setImageResource(R.mipmap.ic_launcher);
            rxDialogSure.getSureView().setOnClickListener(v1 -> rxDialogSure.cancel());
            rxDialogSure.show();
        });
    }
}
