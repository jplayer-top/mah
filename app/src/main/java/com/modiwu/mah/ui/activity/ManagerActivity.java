package com.modiwu.mah.ui.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.Button;
import android.widget.ImageView;

import com.modiwu.mah.R;
import com.modiwu.mah.base.BaseCommonActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import top.jplayer.baseprolibrary.utils.ActivityUtils;
import top.jplayer.baseprolibrary.utils.SizeUtils;

/**
 * Created by Obl on 2018/7/30.
 * com.modiwu.mah.ui.activity
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */

public class ManagerActivity extends BaseCommonActivity {

    private Bitmap mBitmap;
    private ImageView mIvQc;
    private Button btnNext;

    @Override
    public int setBaseLayout() {
        return R.layout.activity_manager_qc;
    }

    @Override
    public void initBaseData() {
        mIvQc = addRootView.findViewById(R.id.ivQc);
        btnNext = addRootView.findViewById(R.id.btnNext);
        int screen = SizeUtils.dp2px(200);
        mBitmap = CodeUtils.createImage("111", screen, screen, BitmapFactory.decodeResource(getResources(), R.mipmap
                .ic_launcher));
        mIvQc.setImageBitmap(mBitmap);
        btnNext.setOnClickListener(v -> {
            ActivityUtils.init().start(this, ManagerClientActivity.class, "我的客户");
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mBitmap != null && mBitmap.isRecycled()) {
            mBitmap.recycle();
            mBitmap = null;
        }
    }

}
