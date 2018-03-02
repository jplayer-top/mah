package com.modiwu.mah.ui.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.modiwu.mah.R;
import com.modiwu.mah.base.BaseCommonActivity;
import com.modiwu.mah.mvp.model.bean.DefLocalBean;
import com.modiwu.mah.mvp.model.bean.OrderCreateBean;
import com.modiwu.mah.mvp.model.bean.ShopCartBean;
import com.modiwu.mah.mvp.model.event.PayOKStateEvent;
import com.modiwu.mah.mvp.presenter.ShopToBuyPresenter;
import com.modiwu.mah.ui.adapter.OrderCreateInfoAdapter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Observable;
import top.jplayer.baseprolibrary.utils.ActivityUtils;
import top.jplayer.baseprolibrary.utils.MoneyUtils;
import top.jplayer.baseprolibrary.utils.ToastUtils;

/**
 * Created by Administrator on 2018/2/7.
 * 商品订单-提交
 */

public class ShopToBuyAvtivity extends BaseCommonActivity {
    @BindView(R.id.tvLocal)
    TextView mTvLocal;
    @BindView(R.id.editName)
    EditText tvName;
    @BindView(R.id.editPhone)
    EditText tvPhone;
    @BindView(R.id.editRemark)
    EditText editRemark;
    @BindView(R.id.editEmail)
    EditText editEmail;
    @BindView(R.id.tvCountPrice)
    TextView tvCountPrice;
    @BindView(R.id.recycleItem)
    RecyclerView recycleItem;
    @BindView(R.id.btnSubmit)
    Button btnSubmit;
    @BindView(R.id.llPay)
    LinearLayout llPay;
    private Unbinder mUnbinder;
    DefLocalBean.AddrBean mAddrBean;
    ShopToBuyPresenter mPresenter;
    private Map<String, String> mMap;
    private String mFangan_id;

    @Override
    public int setBaseLayout() {
        return R.layout.activity_shop_to_pay;
    }

    float countPrice = 0;

    @Override
    public void initBaseData() {
        mUnbinder = ButterKnife.bind(this, addRootView);
        EventBus.getDefault().register(this);
        mMultipleStatusView = addRootView.findViewById(R.id.multiplestatusview);
        mPresenter = new ShopToBuyPresenter(this);
        mPresenter.requestOrderLocalData();
        mMap = new HashMap<>();
        String goods_num = mBundle.getString("goods_num");
        mFangan_id = mBundle.getString("fangan_id");

        if (mFangan_id != null) {
            tvPhone.setVisibility(View.VISIBLE);
            tvName.setVisibility(View.VISIBLE);
            editEmail.setVisibility(View.VISIBLE);
            mTvLocal.setVisibility(View.GONE);
            mMap.put("fangan_id", mFangan_id);

        }
        llPay.setEnabled(false);
        mMap.put("goods_num", goods_num);
        List<ShopCartBean> orderLists = new Gson().fromJson(mBundle.getString("json"), new TypeToken<List<ShopCartBean>>() {
        }.getType());
        Observable.fromIterable(orderLists).subscribe(shopCartBean ->
                {
                    String strPrice = shopCartBean.price;
                    String count = shopCartBean.count;
                    int parseIntF = MoneyUtils.parseIntF(strPrice) * Integer.parseInt(count);
                    countPrice += parseIntF;
                }
        );
        mTvLocal.setOnClickListener(v -> ActivityUtils.init().start(this, LocalListActivity.class, "地址列表"));
        recycleItem.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recycleItem.setAdapter(new OrderCreateInfoAdapter(orderLists));
        btnSubmit.setOnClickListener(v -> {
            if (!TextUtils.isEmpty(editRemark.getText())) {
                mMap.put("remark", editRemark.getText().toString());
            } else {
                if (mMap.containsKey("remark")) {
                    mMap.remove("remark");
                }
            }
            if (mFangan_id != null) {
                if (TextUtils.isEmpty(tvName.getText())) {
                    ToastUtils.init().showInfoToast(this, "请填写姓名");
                    return;
                }
                mMap.put("user_name", tvName.getText().toString());
                if (TextUtils.isEmpty(tvPhone.getText())) {
                    ToastUtils.init().showInfoToast(this, "请填写联系电话");
                    return;
                }
                mMap.put("user_phone", tvPhone.getText().toString());
                if (TextUtils.isEmpty(editEmail.getText())) {
                    ToastUtils.init().showInfoToast(this, "请填写电子邮箱");
                    return;
                }
                mMap.put("user_email", editEmail.getText().toString());
                mPresenter.requestSchemeCreateData(mMap);

            } else {
                if (TextUtils.isEmpty(mTvLocal.getText())) {
                    ToastUtils.init().showInfoToast(this, "请选择收货地址");
                    return;
                }
                mMap.put("rp_name", mAddrBean.rp_name);
                mMap.put("rp_mobile", mAddrBean.rp_phone);
                String local = String.format(Locale.CHINA, "%s%s%s%s", mAddrBean.rp_province, mAddrBean.rp_city, mAddrBean.rp_area,
                        mAddrBean.rp_addr);
                mMap.put("rp_addr", local);
                mPresenter.requestOrderCreateData(mMap);
            }
        });
        tvCountPrice.setText(String.format(Locale.CHINA, "￥%.2f", countPrice / 100f));

        mTvLocal.setOnClickListener(toAddLocal());
//        tvName.setOnClickListener(toAddLocal());
//        tvPhone.setOnClickListener(toAddLocal());
    }

    @NonNull
    private View.OnClickListener toAddLocal() {
        return v -> ActivityUtils.init().start(this, LocalListActivity.class, "地址列表");
    }

    @Subscribe
    public void getLocalEvent(DefLocalBean.AddrBean addr) {
        setAddrBean(addr);
    }

    @Subscribe
    public void getPayStatusEvent(PayOKStateEvent event) {
        finish();
    }


    public void setOrderCreate(OrderCreateBean bean, String type) {

        Bundle bundle = new Bundle();
        bundle.putString("totalPrice", String.format(Locale.CHINA, "￥%s", bean.totalPrice));
        bundle.putString("orderId", bean.orderId);
        bundle.putString("type", type);
        ActivityUtils.init().start(this, ShopPayActivity.class, "支付", bundle);
    }

    public void setNoOrderLocal() {

    }

    public void setOrderLocal(DefLocalBean localBean) {
        setAddrBean(localBean.addr);
        llPay.setEnabled(true);
    }

    private void setAddrBean(DefLocalBean.AddrBean addr) {
        mAddrBean = addr;
        if (mFangan_id != null) {
            tvName.setText(mAddrBean.rp_name);
            tvPhone.setText(mAddrBean.rp_phone);
        } else {
            String local = String.format(Locale.CHINA, "%s%s%s%s", mAddrBean.rp_province, mAddrBean.rp_city, mAddrBean.rp_area,
                    mAddrBean.rp_addr);
            mTvLocal.setText(local);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
        EventBus.getDefault().unregister(this);
    }


}