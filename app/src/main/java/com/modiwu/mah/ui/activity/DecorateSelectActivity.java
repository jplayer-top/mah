package com.modiwu.mah.ui.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.widget.ImageView;
import android.widget.TextView;

import com.modiwu.mah.R;
import com.modiwu.mah.base.BaseCommonActivity;
import com.modiwu.mah.mvp.model.DecorateModel;
import com.modiwu.mah.mvp.model.bean.DecorateManBean;
import com.modiwu.mah.mvp.model.bean.DecorateWorkerBean;
import com.modiwu.mah.mvp.model.event.SelectDecorateEvent;

import org.greenrobot.eventbus.EventBus;

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
    @BindView(R.id.tvOW)
    TextView tvOW;
    @BindView(R.id.tvSV)
    TextView tvSV;
    @BindView(R.id.tvPM)
    TextView tvPM;
    @BindView(R.id.cardProJL)
    CardView cardProJL;
    @BindView(R.id.tvWM)
    TextView tvWM;
    @BindView(R.id.ivOW)
    ImageView ivOW;
    @BindView(R.id.ivSV)
    ImageView ivSV;
    @BindView(R.id.ivPM)
    ImageView ivPM;
    @BindView(R.id.ivWM)
    ImageView ivWM;
    private Unbinder mUnbinder;
    private DecorateModel mModel;

    @Override
    public int setBaseLayout() {
        return R.layout.activity_select_decorate;
    }

    @Override
    public void initBaseData() {
        mModel = new DecorateModel();

        mModel.getDecorateStatus().subscribe(bean -> {
            setColor("1".equals(bean.isow), tvOW, ivOW);
            setColor("1".equals(bean.issv), tvSV, ivSV);
            setColor("1".equals(bean.ispm), tvPM, ivPM);
            setColor("1".equals(bean.iswm), tvWM, ivWM);
        }, throwable -> {
        });

        mUnbinder = ButterKnife.bind(this, mFlRootView);
        mCardShiGong.setOnClickListener(v -> {
            requestWorkerPro("");

        });
        mCardYeZhu.setOnClickListener(v -> {
            changeSelect("业主");
        });
        mCardJianLi.setOnClickListener(v -> {
            requestSVPro("");

        });
        cardProJL.setOnClickListener(view -> {
            requestPmPro("");
        });
    }

    public void setColor(boolean is, TextView view, ImageView iv) {
        iv.setImageResource(is ? R.drawable.decorate_isreg : R.drawable.decorate_isnoreg);
        view.setText(is ? "已注册" : "未注册");
        view.setTextColor(is ? Color.parseColor("#C9A672") : Color.parseColor("#999999"));
    }

    private void changeSelect(String select) {
        ToastUtils.init().showSuccessToast(this, "已您切换为" + select + "身份");
        SharePreUtil.saveData(this, "decorate_select", select);
        EventBus.getDefault().post(new SelectDecorateEvent(select));
        Observable.timer(1, TimeUnit.SECONDS).compose(new IoMainSchedule<>()).subscribe(
                aLong -> finish()
        );
    }

    private void responseWorker(DecorateWorkerBean bean) {
        if ("1".equals(bean.iswm)) {
            changeSelect("施工");
        } else {
            ActivityUtils.init().start(this, DecorateShiGongActivity.class, "我是施工人员");
            Observable.timer(1, TimeUnit.SECONDS).compose(new IoMainSchedule<>()).subscribe(
                    aLong -> finish()
            );
        }
    }

    private void responseSv(DecorateManBean bean) {
        if ("1".equals(bean.issv)) {
            changeSelect("监理");
        } else {
            ActivityUtils.init().start(this, DecorateShiGongActivity.class, "我是监理人员");
            Observable.timer(500, TimeUnit.MILLISECONDS).compose(new IoMainSchedule<>()).subscribe(
                    aLong -> finish()
            );
        }
    }

    private void responsePm(DecorateManBean bean) {
        if ("1".equals(bean.issv)) {
            changeSelect("经理");
        } else {
            ActivityUtils.init().start(this, DecorateShiGongActivity.class, "我是项目经理");
            Observable.timer(500, TimeUnit.MILLISECONDS).compose(new IoMainSchedule<>()).subscribe(
                    aLong -> finish()
            );
        }
    }


    public void requestWorkerPro(String id) {
        mModel.requestWorkerPro(id)
                .subscribe(this::responseWorker, throwable -> {
                    if (throwable.getMessage().contains("401")) {
                        ToastUtils.init().showErrorToast(this, "请先登录");
                    }
                });
    }


    public void requestSVPro(String id) {
        mModel.requestSVPro(id)
                .subscribe(this::responseSv, throwable -> {
                    if (throwable.getMessage().contains("401")) {
                        ToastUtils.init().showErrorToast(this, "请先登录");
                    }

                });
    }

    public void requestPmPro(String id) {
        mModel.requestPmPro(id)
                .subscribe(this::responsePm, throwable -> {
                    if (throwable.getMessage().contains("401")) {
                        ToastUtils.init().showErrorToast(this, "请先登录");
                    }

                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
    }

}
