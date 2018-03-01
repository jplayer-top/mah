package com.modiwu.mah.ui.activity;

import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alipay.sdk.app.PayTask;
import com.modiwu.mah.R;
import com.modiwu.mah.base.BaseCommonActivity;
import com.modiwu.mah.mvp.model.ShopDetailModel;
import com.modiwu.mah.mvp.model.bean.AliPayInfoBean;
import com.modiwu.mah.mvp.model.bean.WxPayInfoBean;
import com.modiwu.mah.mvp.model.event.PayOKStateEvent;
import com.modiwu.mah.wxapi.WXPayEntryActivity;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import top.jplayer.baseprolibrary.net.SampleShowDialogObserver;
import top.jplayer.baseprolibrary.utils.ToastUtils;

/**
 * Created by Obl on 2018/2/9.
 * com.modiwu.mah.ui.activity
 */

public class ShopPayActivity extends BaseCommonActivity {
    @BindView(R.id.tvPayMoney)
    TextView mTvPayMoney;
    @BindView(R.id.tvOrderId)
    TextView tvOrderId;
    @BindView(R.id.checkbox1)
    CheckBox mCheckbox1;
    @BindView(R.id.checkbox2)
    CheckBox mCheckbox2;
    @BindView(R.id.tv2Pay)
    TextView tv2Pay;
    @BindView(R.id.llPayOk)
    LinearLayout llPayOk;
    private Unbinder mUnbinder;
    private ShopDetailModel mModel;

    @Override
    public int setBaseLayout() {
        return R.layout.activity_shop_pay;
    }

    @Override
    public void initBaseData() {
        mUnbinder = ButterKnife.bind(this, addRootView);
        EventBus.getDefault().register(this);
        final String orderId = mBundle.getString("orderId");
        String totalPrice = mBundle.getString("totalPrice");
        llPayOk.setVisibility(View.GONE);
        mTvPayMoney.setText(String.format(Locale.CHINA, "付款总额：%s", totalPrice));
        tvOrderId.setText(String.format(Locale.CHINA, "订单编号：%s", orderId));
        mCheckbox1.setOnCheckedChangeListener((buttonView, isChecked) -> {
            mCheckbox1.setChecked(isChecked);
            mCheckbox2.setChecked(!isChecked);
        });
        mCheckbox2.setOnCheckedChangeListener((buttonView, isChecked) -> {
            mCheckbox2.setChecked(isChecked);
            mCheckbox1.setChecked(!isChecked);
        });
        mModel = new ShopDetailModel();
        boolean type = "方案".equals(mBundle.getString("type"));

        tv2Pay.setOnClickListener(v -> {
            boolean checked = mCheckbox1.isChecked();
            if (checked) {
                if (type) {
                    mModel.requestWXPrePayFangAn(orderId, "2").subscribe(new SampleShowDialogObserver<WxPayInfoBean>
                            (mBaseActivity) {
                        @Override
                        protected void onSuccess(WxPayInfoBean wxPayInfoBean) throws Exception {
                            wxPay(wxPayInfoBean);
                        }
                    });
                } else {
                    mModel.requestWXPrePay(orderId, "2").subscribe(new SampleShowDialogObserver<WxPayInfoBean>(mBaseActivity) {
                        @Override
                        protected void onSuccess(WxPayInfoBean wxPayInfoBean) throws Exception {
                            wxPay(wxPayInfoBean);
                        }
                    });
                }
            } else {
                if (type) {
                    mModel.requestAliPrePayFangAn(orderId, "1").subscribe(new SampleShowDialogObserver<AliPayInfoBean>
                            (mBaseActivity) {
                        @Override
                        protected void onSuccess(AliPayInfoBean aliPayInfoBean) throws Exception {
                            aliPay(aliPayInfoBean);
                        }
                    });
                } else {
                    mModel.requestAliPrePay(orderId, "1").subscribe(new SampleShowDialogObserver<AliPayInfoBean>(mBaseActivity) {
                        @Override
                        protected void onSuccess(AliPayInfoBean aliPayInfoBean) throws Exception {
                            aliPay(aliPayInfoBean);
                        }
                    });
                }

            }
        });
    }

    IWXAPI api;

    // 检查微信是否安装
    private boolean checkWX() {
        boolean bErr = false;
        try {
            if (!api.isWXAppInstalled() || !api.isWXAppSupportAPI()) {
                bErr = true;
            }
        } catch (Exception e) {
            bErr = true;
            e.printStackTrace();
        }
        if (!bErr) {
            return true;
        }
        return false;
    }

    private void wxPay(WxPayInfoBean response) {
        WxPayInfoBean.OrderStrBean orderStrBean = response.orderStr;
        if (api == null) {
            api = WXAPIFactory.createWXAPI(this, response.orderStr.appid, true);
        }
        if (!checkWX()) {
            ToastUtils.init().showInfoToast(this, "您手机尚未安装微信，请安装后再登录");
            return;
        }
        api.registerApp(response.orderStr.appid);
        PayReq request = new PayReq();
        request.appId = orderStrBean.appid;
        request.partnerId = orderStrBean.partnerid;
        request.prepayId = orderStrBean.prepayid;
        request.packageValue = orderStrBean.packageX;
        request.nonceStr = orderStrBean.noncestr;
        request.timeStamp = orderStrBean.timestamp;
        request.sign = orderStrBean.sign;
        api.sendReq(request);
    }

    private void aliPay(final AliPayInfoBean response) {
        Observable.just(response).subscribeOn(Schedulers.io())
                .map(aliPayInfoBean -> {
                    PayTask alipay = new PayTask(mBaseActivity);
                    return alipay.payV2(response.orderStr, true);
                }).observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> {
                    if (result.get("resultStatus").equals("9000")) {
                        //支付成功
                        payAliOk();
                    } else if (result.get("resultStatus").equals("8000")) {
                        //支付处理中
                        ToastUtils.init().showErrorToast(mBaseActivity, "支付处理中，请稍后");
                    } else {
                        ToastUtils.init().showErrorToast(mBaseActivity, "订单支付失败");
                    }
                });
    }

    private void payAliOk() {
        next4PayOk();
    }

    private void next4PayOk() {
        llPayOk.setVisibility(View.VISIBLE);
        EventBus.getDefault().post(new PayOKStateEvent());
    }

    @Subscribe
    public void payWxOk(WXPayEntryActivity.WxPayEvent event) {
        next4PayOk();
    }

    @Override

    protected void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
        EventBus.getDefault().unregister(this);
    }
}
