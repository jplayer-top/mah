package com.modiwu.mah.ui.activity;

import android.graphics.Bitmap;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.modiwu.mah.R;
import com.modiwu.mah.base.BaseCommonActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import top.jplayer.baseprolibrary.utils.ActivityUtils;
import top.jplayer.baseprolibrary.utils.SizeUtils;

/**
 * Created by Obl on 2018/7/30.
 * com.modiwu.mah.ui.activity
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */

public class ManagerActivity extends BaseCommonActivity {

    @BindView(R.id.tvName)
    TextView mTvName;
    @BindView(R.id.ivQc)
    ImageView mIvQc;
    @BindView(R.id.ivAvatar)
    ImageView mIvAvatar;
    @BindView(R.id.btnNext)
    Button mBtnNext;
    private Bitmap mBitmap;
    private Unbinder mUnbinder;

    @Override
    public int setBaseLayout() {
        return R.layout.activity_manager_qc;
    }

    @Override
    public void initBaseData() {
        mUnbinder = ButterKnife.bind(this, addRootView);
        int screen = SizeUtils.dp2px(150);
        int uid = mBundle.getInt("uid");
        mTvName.setText(String.format(Locale.CHINA, "用户： %s", mBundle.getString("name")));
        mBitmap = CodeUtils.createImage("https://app.modiwu.com/app/download?" + uid, screen, screen, null);
        mIvQc.setImageBitmap(mBitmap);
        Glide.with(this).load(mBundle.getString("avatar")).into(mIvAvatar);
        mBtnNext.setOnClickListener(v -> {
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
        mUnbinder.unbind();
    }

}
