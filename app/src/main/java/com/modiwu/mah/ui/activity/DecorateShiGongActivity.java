package com.modiwu.mah.ui.activity;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.modiwu.mah.R;
import com.modiwu.mah.base.BaseCommonActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import top.jplayer.baseprolibrary.utils.ActivityUtils;

/**
 * Created by Obl on 2018/8/31.
 * com.modiwu.mah.ui.activity
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */

public class DecorateShiGongActivity extends BaseCommonActivity {
    @BindView(R.id.tvPhone)
    TextView mTvPhone;
    @BindView(R.id.tvEditCode)
    EditText mTvEditCode;
    @BindView(R.id.btnSendCode)
    Button mBtnSendCode;
    @BindView(R.id.btnSure)
    Button mBtnSure;
    private Unbinder mUnbinder;

    @Override
    public int setBaseLayout() {
        return R.layout.activity_shigong_sel;
    }

    @Override
    public void initBaseData() {
        mUnbinder = ButterKnife.bind(this, mFlRootView);
        mBtnSure.setOnClickListener(v -> {
            ActivityUtils.init().start(this, DecorateShiGongRegisterActivity.class, "注册施工人员");
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
    }
}
