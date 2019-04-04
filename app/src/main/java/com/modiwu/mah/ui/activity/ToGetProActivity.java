package com.modiwu.mah.ui.activity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.modiwu.mah.R;
import com.modiwu.mah.base.BaseCommonActivity;
import com.modiwu.mah.mvp.model.event.FinishAddEvent;
import com.modiwu.mah.utils.StringUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import top.jplayer.baseprolibrary.utils.ActivityUtils;
import top.jplayer.baseprolibrary.utils.ToastUtils;

/**
 * Created by Administrator on 2019/4/4.
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */

public class ToGetProActivity extends BaseCommonActivity {
    @BindView(R.id.editName)
    EditText editName;
    @BindView(R.id.editPhone)
    EditText editPhone;
    @BindView(R.id.btnAdd)
    TextView btnAdd;
    private Unbinder bind;

    @Override
    public int setBaseLayout() {
        return R.layout.activity_sjfa;
    }

    @Override
    public void initBaseData() {
        bind = ButterKnife.bind(this, addRootView);
        EventBus.getDefault().register(this);
        btnAdd.setOnClickListener(view -> {
            if (StringUtils.getInstance().isNullObj(editName)) {
                ToastUtils.init().showInfoToast(mBaseActivity,"请输入您的称呼");
                return;
            } if (StringUtils.getInstance().isNullObj(editPhone)) {
                ToastUtils.init().showInfoToast(mBaseActivity,"请输入您的联系方式");
                return;
            }
            Bundle bundle = new Bundle();
            bundle.putString("fangan_name", mBundle.getString("fangan_name"));
            bundle.putString("fangan_id", mBundle.getString("fangan_id"));
            bundle.putString("user_name", mBundle.getString("user_name"));
            bundle.putString("user_phone", mBundle.getString("user_phone"));
            ActivityUtils.init().start(mBaseActivity, FinishZHActivity.class, "", bundle);
        });
    }

    @Subscribe
    public void onEvnet(FinishAddEvent event) {
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bind.unbind();
        EventBus.getDefault().unregister(this);
    }
}
