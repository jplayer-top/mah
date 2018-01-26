package com.modiwu.mah.ui.activity;

import android.content.Intent;
import android.view.View;

import com.github.florent37.viewanimator.AnimationListener;
import com.github.florent37.viewanimator.ViewAnimator;
import com.modiwu.mah.MainActivity;
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
        ViewAnimator.animate(view).alpha(1, 0.8f).scale(1, 1.2f).duration(2000).onStop(new AnimationListener.Stop() {
            @Override
            public void onStop() {
                startActivity(new Intent(mBaseActivity, MainActivity.class));
                finish();
            }
        }).start();
    }

    private int back = 0;

    @Override
    public void onBackPressed() {
        if (back > 1) {
            super.onBackPressed();
        }
        back++;

    }
}
