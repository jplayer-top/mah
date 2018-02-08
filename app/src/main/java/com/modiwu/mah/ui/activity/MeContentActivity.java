package com.modiwu.mah.ui.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.modiwu.mah.R;
import com.modiwu.mah.base.BaseCommonActivity;
import com.modiwu.mah.mvp.model.event.MessageEvent;
import com.modiwu.mah.mvp.presenter.MeInfoPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import top.jplayer.baseprolibrary.mvp.model.bean.BaseBean;
import top.jplayer.baseprolibrary.utils.ActivityUtils;

/**
 * Created by Obl on 2018/2/6.
 * com.modiwu.mah.ui.activity
 */

public class MeContentActivity extends BaseCommonActivity {

    @BindView(R.id.tvAvatar)
    TextView tvAvatar;
    @BindView(R.id.tvName)
    TextView tvName;
    @BindView(R.id.tvPhone)
    TextView tvPhone;
    @BindView(R.id.tvEmail)
    TextView tvEmail;
    @BindView(R.id.tvFloor)
    TextView tvFloor;
    @BindView(R.id.tvType)
    TextView tvType;
    @BindView(R.id.tvArea)
    TextView tvArea;
    @BindView(R.id.tvPoint)
    TextView tvPoint;
    @BindView(R.id.btnLogout)
    Button btnLogout;
    private Unbinder bind;
    private MeInfoPresenter presenter;

    @Override
    public int setBaseLayout() {
        return R.layout.activity_me;
    }

    @Override
    public void initBaseData() {
        EventBus.getDefault().register(this);
        bind = ButterKnife.bind(this, addRootView);
        tvName.setOnClickListener(view -> messageChange("姓名", tvName));
        tvType.setOnClickListener(view -> messageChange("户型", tvType));
        tvArea.setOnClickListener(view -> messageChange("面积", tvArea));
        tvFloor.setOnClickListener(view -> messageChange("小区", tvFloor));
        tvEmail.setOnClickListener(view -> messageChange("邮箱", tvEmail));
        presenter = new MeInfoPresenter(this);

        presenter.getMeInfo("0");
    }

    private void messageChange(String key, TextView view) {
        Bundle bundle = new Bundle();
        bundle.putString(key, view.getText().toString().trim());
        ActivityUtils.init().start(this,
                MessageChangeActivity.class, key, bundle);
    }

    @Subscribe
    public void messageChange(MessageEvent event) {
        switch (event.key) {
            case "姓名":
                presenter.setMeInfo("user_name", event.value);
                break;
            case "邮箱":
                presenter.setMeInfo("user_email", event.value);
                break;
            case "小区":
                presenter.setMeInfo("user_building", event.value);
                break;
            case "户型":
                presenter.setMeInfo("user_huxing", event.value);
                break;
            case "面积":
                presenter.setMeInfo("user_mian", event.value);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bind.unbind();
        EventBus.getDefault().unregister(this);
    }

    public void success(String col, String value) {
        switch (col) {
            case "user_name":
                tvName.setText(value);
                break;
            case "user_email":
                tvEmail.setText(value);
                break;
            case "user_building":
                tvFloor.setText(value);
                break;
            case "user_huxing":
                tvType.setText(value);
                break;
            case "user_mian":
                tvArea.setText(value);
                break;
        }
    }

    public void successGet(BaseBean baseBean) {

    }
}
