package top.jplayer.baseprolibrary.ui;

import android.animation.Animator;
import android.annotation.SuppressLint;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.FrameLayout;

import com.google.gson.Gson;

import io.reactivex.functions.Function;
import top.jplayer.baseprolibrary.mvp.model.bean.LoginBean;
import top.jplayer.baseprolibrary.mvp.model.bean.SampleBean;
import top.jplayer.baseprolibrary.net.ApiService;
import top.jplayer.baseprolibrary.net.IoMainSchedule;
import top.jplayer.baseprolibrary.net.RetrofitManager;
import top.jplayer.baseprolibrary.net.SampleObserver;
import top.jplayer.baseprolibrary.utils.LogUtil;

/**
 * Created by Obl on 2018/1/18.
 * top.jplayer.baseprolibrary.ui
 */

public class TestActivity extends SuperBaseActivity {
    private View content;
    private int mX;
    private int mY;


    // 动画
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private Animator createRevealAnimator(boolean reversed, int x, int y) {
        float hypot = (float) Math.hypot(content.getHeight(), content.getWidth());
        float startRadius = reversed ? hypot : 0;
        float endRadius = reversed ? 0 : hypot;

        Animator animator = ViewAnimationUtils.createCircularReveal(
                content, x, y,
                startRadius,
                endRadius);
        animator.setDuration(800);
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        if (reversed)
            animator.addListener(animatorListener);
        return animator;
    }

    private Animator.AnimatorListener animatorListener = new Animator.AnimatorListener() {
        @Override
        public void onAnimationStart(Animator animation) {
        }

        @Override
        public void onAnimationEnd(Animator animation) {
            content.setVisibility(View.INVISIBLE);
            finish();
        }

        @Override
        public void onAnimationCancel(Animator animation) {
        }

        @Override
        public void onAnimationRepeat(Animator animation) {
        }
    };

    @Override
    public void onBackPressed() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Animator animator = createRevealAnimator(true, mX, mY);
            animator.start();
        } else {
            finish();
        }
    }

    @Override
    public void initSuperData(FrameLayout mFlRootView) {
        // 让根布局进行动画
        content.post(new Runnable() {
            @Override
            public void run() {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    mX = getIntent().getIntExtra("x", 0);
                    mY = getIntent().getIntExtra("y", 0);
                    Animator animator = createRevealAnimator(false, mX, mY);
                    animator.start();
                }
            }
        });
        content.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    Animator animator = createRevealAnimator(true, (int) event.getX(), (int) event.getY());
                    animator.start();
                } else {
                    finish();
                }
                return false;
            }
        });
    }
}