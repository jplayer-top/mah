package com.modiwu.mah.ui.activity;

import android.widget.TextView;

import com.modiwu.mah.BuildConfig;
import com.modiwu.mah.R;
import com.modiwu.mah.base.BaseCommonActivity;

import java.util.Locale;

import top.jplayer.baseprolibrary.listener.observer.IntegerObserver;
import top.jplayer.baseprolibrary.listener.observer.IntegerObservable;

/**
 * Created by Obl on 2018/2/2.
 * com.modiwu.mah.ui.activity
 */

public class AboutMahActivity extends BaseCommonActivity {

    private int mInt;
    private IntegerObserver mOberver;
    private IntegerObservable mObservable;

    @Override
    public int setBaseLayout() {
        return R.layout.activity_about;
    }

    @Override
    public void initBaseData() {
        findToolBarView(addRootView);
        TextView tvVersion = addRootView.findViewById(R.id.tvVersion);
        tvVersion.setText(String.format(Locale.CHINA, "版本号：%s", BuildConfig.VERSION_NAME));
        mOberver = new IntegerObserver(this);
        mObservable = new IntegerObservable();
        mObservable.addObserver(mOberver);
        mInt = 10;
        tvVersion.setOnClickListener(v -> {
            mInt--;
            mObservable.change(this, mInt);
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mObservable.deleteObservers();
    }
}
