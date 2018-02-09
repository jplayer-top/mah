package com.modiwu.mah.ui.activity;

import android.widget.TextView;

import com.modiwu.mah.BuildConfig;
import com.modiwu.mah.R;
import com.modiwu.mah.base.BaseCommonActivity;

import java.util.Locale;

/**
 * Created by Obl on 2018/2/2.
 * com.modiwu.mah.ui.activity
 */

public class AboutMahActivity extends BaseCommonActivity {
    @Override
    public int setBaseLayout() {
        return R.layout.activity_about;
    }

    @Override
    public void initBaseData() {
        findToolBarView(addRootView);
        TextView tvVersion = addRootView.findViewById(R.id.tvVersion);
        tvVersion.setText(String.format(Locale.CHINA, "版本号：%s", BuildConfig.VERSION_NAME));
    }
}
