package top.jplayer.baseprolibrary.ui.activity;

import android.view.View;
import android.widget.FrameLayout;

import top.jplayer.baseprolibrary.R;
import top.jplayer.baseprolibrary.ui.SuperBaseActivity;

/**
 * Created by Obl on 2018/3/12.
 * top.jplayer.baseprolibrary.ui.activity
 */

public class ActivityDialog extends SuperBaseActivity {
    @Override
    public void initSuperData(FrameLayout mFlRootView) {
        mFlRootView.addView(View.inflate(this, R.layout.layout_test, null));
    }
}
