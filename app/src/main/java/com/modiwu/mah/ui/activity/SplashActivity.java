package com.modiwu.mah.ui.activity;

import android.content.Intent;
import android.view.View;

import com.github.florent37.viewanimator.ViewAnimator;
import com.modiwu.mah.R;
import com.modiwu.mah.base.BaseSpecialActivity;

/**
 * Created by PEO on 2017/3/11.
 * Splash界面
 */

public class SplashActivity extends BaseSpecialActivity {

    @Override
    public int setBaseLayout() {
        return R.layout.activity_splash;
    }

    @Override
    public void initBaseData() {
        View view = mBaseView.findViewById(R.id.splashBg);
        ViewAnimator.animate(view)
                .alpha(1, 0.8f)
                .scale(1f, 1.1f)
                .duration(2000)
                .onStop(() -> {
                    startActivity(new Intent(mBaseActivity, LoginAnimActivity.class));
                    finish();
                }).start();
    }
}
