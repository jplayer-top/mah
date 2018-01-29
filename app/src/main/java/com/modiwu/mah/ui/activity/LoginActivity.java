package com.modiwu.mah.ui.activity;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.florent37.viewanimator.ViewAnimator;
import com.modiwu.mah.R;
import com.modiwu.mah.base.BaseSpecialActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Obl on 2018/1/29.
 * com.modiwu.mah.ui.activity
 */

public class LoginActivity extends BaseSpecialActivity {
    @BindView(R.id.iv_logo)
    ImageView mIvLogo;
    @BindView(R.id.tvTitle)
    TextView mTvTitle;
    @BindView(R.id.btnToLogin)
    Button mBtnToLogin;
    @BindView(R.id.btnToRegister)
    Button mBtnToRegister;
    @BindView(R.id.llToSelect)
    LinearLayout mLlToSelect;
    @BindView(R.id.btnLogin)
    Button mBtnLogin;
    @BindView(R.id.llToLogin)
    LinearLayout mLlToLogin;
    @BindView(R.id.btnCode)
    Button mBtnCode;
    @BindView(R.id.btnNext)
    Button mBtnNext;
    @BindView(R.id.llToNext)
    LinearLayout mLlToNext;
    @BindView(R.id.btnRegister)
    Button mBtnRegister;
    @BindView(R.id.llFinishRegister)
    LinearLayout mLlFinishRegister;
    private int animIng = 0;
    private final int ANIM_ING = 1;
    private final int ANIM_END = 2;
    private final int ANIM_DURATION = 400;

    @Override
    public int setBaseLayout() {
        return R.layout.activity_login;
    }

    @Override
    public void initBaseData() {
        ButterKnife.bind(this, mBaseView);
        mBtnToLogin.setOnClickListener(v -> animNextSelect(mLlToLogin, mLlToSelect));
        mBtnToRegister.setOnClickListener(v -> animNextSelect(mLlToNext, mLlToSelect));
        mBtnNext.setOnClickListener(v -> animNextSelect(mLlFinishRegister, mLlToNext));
    }


    @Override
    public void onBackPressed() {
        if (animIng == ANIM_ING) {
            return;
        }
        if (mLlToLogin.getVisibility() == View.VISIBLE) {
            animBack2Select(mLlToLogin, mLlToSelect);
        } else if (mLlToNext.getVisibility() == View.VISIBLE) {
            animBack2Select(mLlToNext, mLlToSelect);
        } else if (mLlFinishRegister.getVisibility() == View.VISIBLE) {
            animBack2Select(mLlFinishRegister, mLlToNext);
        } else
            super.onBackPressed();

    }


    /**
     * 返回按钮触发
     */
    private void animBack2Select(View preView, View aftView) {
        ViewAnimator.animate(preView).dp().translationY(0, preView.getHeight()).duration(ANIM_DURATION).alpha(1f, 0f)
                .andAnimate(aftView).dp().translationY(aftView.getHeight(), 0).alpha(0f, 1f).duration(ANIM_DURATION)
                .onStart(() -> {
                    aftView.setVisibility(View.VISIBLE);
                    animIng = ANIM_ING;
                })
                .onStop(() -> {
                    preView.setVisibility(View.INVISIBLE);
                    animIng = ANIM_END;
                })
                .start();
    }

    /**
     * 下一步动画点击
     */
    private void animNextSelect(View preView, View aftView) {
        ViewAnimator.animate(aftView).dp().translationY(0, aftView.getHeight()).duration(ANIM_DURATION).alpha(1f, 0f)
                .andAnimate(preView).dp().translationY(preView.getHeight(), 0).alpha(0f, 1f).duration(ANIM_DURATION)
                .onStart(() -> {
                    preView.setVisibility(View.VISIBLE);
                    animIng = ANIM_ING;
                })
                .onStop(() -> {
                    aftView.setVisibility(View.INVISIBLE);
                    animIng = ANIM_END;
                })
                .start();
    }
}
