package com.modiwu.mah.ui.activity;

import android.content.Intent;
import android.util.Log;
import android.view.View;

import com.github.florent37.viewanimator.ViewAnimator;
import com.modiwu.mah.MainActivity;
import com.modiwu.mah.R;
import com.modiwu.mah.base.BaseSpecialActivity;

import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;
import top.jplayer.baseprolibrary.utils.SharePreUtil;

import static com.modiwu.mah.base.BaseApplication.getCurProcessName;

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
        View view = contentView.findViewById(R.id.ivBackSplash);
        ViewAnimator.animate(view)
                .alpha(1, 0.8f)
                .scale(1f, 1.1f)

                .duration(2000)
                .onStart(() -> {
                    String token = (String) SharePreUtil.getData(this, "token", "");
                    if (token != null && !token.equals("")) {
                        connect(token);
                    }
                })
                .onStop(() -> {
                    startActivity(new Intent(mBaseActivity, MainActivity.class));
                    finish();
                }).start();
    }

    private void connect(String token) {

        if (getApplicationInfo().packageName.equals(getCurProcessName(getApplicationContext()))) {

            RongIM.connect(token, new RongIMClient.ConnectCallback() {

                @Override
                public void onTokenIncorrect() {

                }

                @Override
                public void onSuccess(String userid) {
                    Log.d("LoginActivity", "--onSuccess" + userid);

                }

                @Override
                public void onError(RongIMClient.ErrorCode errorCode) {

                }
            });
        }
    }
}
