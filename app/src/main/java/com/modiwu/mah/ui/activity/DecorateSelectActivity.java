package com.modiwu.mah.ui.activity;

import android.support.v7.widget.CardView;

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

public class DecorateSelectActivity extends BaseCommonActivity {
    @BindView(R.id.cardYeZhu)
    CardView mCardYeZhu;
    @BindView(R.id.cardJianLi)
    CardView mCardJianLi;
    @BindView(R.id.cardShiGong)
    CardView mCardShiGong;
    private Unbinder mUnbinder;

    @Override
    public int setBaseLayout() {
        return R.layout.activity_select_decorate;
    }

    @Override
    public void initBaseData() {
        mUnbinder = ButterKnife.bind(this, mFlRootView);
        mCardShiGong.setOnClickListener(v -> {
            ActivityUtils.init().start(this, DecorateShiGongActivity.class, "我是施工人员");
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
    }
}
