package com.modiwu.mah.ui.activity;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.modiwu.mah.R;
import com.modiwu.mah.base.BaseCommonActivity;
import com.modiwu.mah.mvp.presenter.DecorateBasePresenter;
import com.modiwu.mah.utils.StringUtils;

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

public class DecorateShiGongActivity extends BaseCommonActivity {
    @BindView(R.id.tvPhone)
    TextView mTvPhone;
    @BindView(R.id.tvTip01)
    TextView tvTip01;
    @BindView(R.id.tvEditCode)
    EditText mTvEditCode;
    @BindView(R.id.btnSendCode)
    Button mBtnSendCode;
    @BindView(R.id.btnSure)
    Button mBtnSure;
    private Unbinder mUnbinder;
    private DecorateBasePresenter mPresenter;
    private String mLoginPhone;
    private boolean mIsSV;
    private boolean mIsPm;

    @Override
    public int setBaseLayout() {
        return R.layout.activity_shigong_sel;
    }

    @Override
    public void initBaseData() {
        mUnbinder = ButterKnife.bind(this, mFlRootView);
        mBtnSure.setOnClickListener(v -> {
            if (StringUtils.getInstance().isNullObj(mTvEditCode)) {
                ToastUtils.init().showInfoToast(this, "请先获取验证码验证身份");
                return;
            }
            mPresenter.verSmCode(mLoginPhone, mTvEditCode.getText().toString());
        });
        mLoginPhone = (String) SharePreUtil.getData(this, "login_phone", "0");
        if (!"0".equals(mLoginPhone)) {
            mTvPhone.setText(mLoginPhone);
        } else {
            mTvPhone.setText("请先注册并绑定手机号");
        }
        mPresenter = new DecorateBasePresenter(this);
        mBtnSendCode.setOnClickListener(v -> {
            mPresenter.sendSmCode(mLoginPhone);
        });
        mIsSV = tvBarTitle.getText().toString().contains("监理");
        mIsPm = tvBarTitle.getText().toString().contains("项目经理");
        if (mIsSV) {
            tvTip01.setText("您还没有开启监理人员功能,确认开启请发送验证码验证");
        } else if (mIsPm) {
            tvTip01.setText("您还没有开启项目经理功能,确认开启请发送验证码验证");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
    }

    @Override
    public void sendCode() {
        StringUtils.getInstance().getSmCode(mBtnSendCode);
    }

    @Override
    public void verCode() {
        super.verCode();
        if (mIsSV) {
            ActivityUtils.init().start(this, DecorateShiGongRegisterActivity.class, "注册监理人员");
        } else if (mIsPm) {
            ActivityUtils.init().start(this, DecorateShiGongRegisterActivity.class, "注册项目经理");
        } else {
            ActivityUtils.init().start(this, DecorateShiGongRegisterActivity.class, "注册施工人员");
        }
        Observable.timer(1, TimeUnit.SECONDS).compose(new IoMainSchedule<>()).subscribe(aLong -> finish());
    }
}
