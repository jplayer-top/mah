package com.modiwu.mah.ui.activity;

import android.support.v7.widget.CardView;

import com.modiwu.mah.R;
import com.modiwu.mah.base.BaseCommonActivity;
import com.modiwu.mah.mvp.model.event.RegDecorateEvent;
import com.modiwu.mah.mvp.model.event.SelectDecorateEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Observable;
import top.jplayer.baseprolibrary.net.IoMainSchedule;
import top.jplayer.baseprolibrary.utils.ActivityUtils;
import top.jplayer.baseprolibrary.utils.SharePreUtil;
import top.jplayer.baseprolibrary.utils.ToastUtils;

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
        EventBus.getDefault().register(this);
        mCardShiGong.setOnClickListener(v -> {
            String isWorker = (String) SharePreUtil.getData(this, "decorate_worker", "0");
            if ("1".equals(isWorker)) {
                changeSelect("施工");
            } else {
                ActivityUtils.init().start(this, DecorateShiGongActivity.class, "我是施工人员");
            }
        });
        mCardYeZhu.setOnClickListener(v -> {
            changeSelect("业主");
        });
    }

    private void changeSelect(String select) {
        ToastUtils.init().showSuccessToast(this, "已您切换为" + select + "身份");
        EventBus.getDefault().post(new SelectDecorateEvent(select));
        SharePreUtil.saveData(this, "decorate_select", select);
        Observable.timer(500, TimeUnit.MILLISECONDS).compose(new IoMainSchedule<>()).subscribe(
                aLong -> finish()
        );
    }

    @Subscribe
    public void onEvent(RegDecorateEvent event) {
        EventBus.getDefault().post(new SelectDecorateEvent(event.type));
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
        EventBus.getDefault().unregister(this);
    }
}
