package com.modiwu.mah.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.google.gson.Gson;
import com.modiwu.mah.R;
import com.modiwu.mah.base.BaseCommonActivity;
import com.modiwu.mah.mvp.model.bean.DefLocalBean;
import com.modiwu.mah.mvp.model.bean.OrderCreateBean;
import com.modiwu.mah.mvp.model.bean.SchemeOrderCreateBean;
import com.modiwu.mah.mvp.model.bean.ShopCartBean;
import com.modiwu.mah.mvp.model.event.MoneyChangeEvent;
import com.modiwu.mah.mvp.presenter.SchemeOrderCreatePresenter;
import com.modiwu.mah.ui.adapter.SchemeOrderItemAdapter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import io.reactivex.Observable;
import top.jplayer.baseprolibrary.utils.ActivityUtils;
import top.jplayer.baseprolibrary.utils.LogUtil;
import top.jplayer.baseprolibrary.utils.ToastUtils;

/**
 * Created by Obl on 2018/2/8.
 * com.modiwu.mah.ui.activity
 */

public class SchemeOrderCreateActivity extends BaseCommonActivity {

    private RecyclerView mRecyclerView;
    private DelegateAdapter mDelegateAdapter;
    SchemeOrderCreatePresenter mPresenter;
    TextView tvMoney;
    TextView tvToBuy;
    private SchemeOrderCreateBean mBean;
    private Map<String, String> mMap;

    @Override
    public int setBaseLayout() {
        return R.layout.activity_scheme_order_create;
    }

    @Override
    public void initBaseData() {
        EventBus.getDefault().register(this);
        mMultipleStatusView = addRootView.findViewById(R.id.multiplestatusview);
        mRecyclerView = addRootView.findViewById(R.id.recyclerView);
        tvMoney = addRootView.findViewById(R.id.tvMoney);
        tvToBuy = addRootView.findViewById(R.id.tvToBuy);
        VirtualLayoutManager manager = new VirtualLayoutManager(this);
        mRecyclerView.setLayoutManager(manager);
        RecyclerView.RecycledViewPool pool = new RecyclerView.RecycledViewPool();
        pool.setMaxRecycledViews(0, 10);
        mRecyclerView.setRecycledViewPool(pool);
        mDelegateAdapter = new DelegateAdapter(manager, true);
        mMap = new HashMap<>();
        mCartBeans = new ArrayList<>();
        goods_num = new StringBuilder("");
        showLoading();
        mPresenter = new SchemeOrderCreatePresenter(this);
        String fangan_id = mBundle.getString("fangan_id");
        mPresenter.requestSchemeOrderCreateData(fangan_id);
        tvToBuy.setOnClickListener(v -> {
            if (mBean == null) {
                ToastUtils.init().showErrorToast(this, "订单创建错误，请稍后重试");
                return;
            }
            Bundle bundle = new Bundle();
            String json = new Gson().toJson(mCartBeans);
            bundle.putString("json", json);
            bundle.putString("goods_num", goods_num.toString());
            bundle.putString("fangan_id", fangan_id);
            ActivityUtils.init().start(this, ShopToBuyAvtivity.class, "确认购买", bundle);
        });
    }

    public void setOrderCreateBean(SchemeOrderCreateBean bean) {
        List<DelegateAdapter.Adapter> adapters = new LinkedList<>();
        /**
         * 硬装
         */
        mBean = bean;
        moneySet();
        List<SchemeOrderCreateBean.SheBean> sheBeans = mBean.she;
        if (sheBeans != null && sheBeans.size() > 0) {

            SchemeOrderItemAdapter schemeOrderItemAdapter = new SchemeOrderItemAdapter(this, new
                    LinearLayoutHelper(), sheBeans.size(), SchemeOrderCreateBean.BODY_ITEM);
            adapters.add(schemeOrderItemAdapter);
            schemeOrderItemAdapter.setShe(sheBeans);
        } /**
         * 硬装
         */
        List<SchemeOrderCreateBean.YingBean> yings = bean.ying;
        if (yings != null && yings.size() > 0) {

            SchemeOrderItemAdapter schemeOrderItemAdapter = new SchemeOrderItemAdapter(this, new
                    LinearLayoutHelper(), yings.size(), SchemeOrderCreateBean.BODY_ITEM);
            adapters.add(schemeOrderItemAdapter);
            schemeOrderItemAdapter.setYing(yings);
        }
        /**
         * 软装
         */
        List<SchemeOrderCreateBean.RuanBean> ruans = bean.ruan;
        if (ruans != null && ruans.size() > 0) {
            SchemeOrderItemAdapter schemeOrderItemAdapter = new SchemeOrderItemAdapter(this, new
                    LinearLayoutHelper(), ruans.size(), SchemeOrderCreateBean.BODY_ITEM);
            adapters.add(schemeOrderItemAdapter);
            schemeOrderItemAdapter.setRuan(ruans);
        }
        mDelegateAdapter.clear();
        mDelegateAdapter.setAdapters(adapters);
        mRecyclerView.setAdapter(mDelegateAdapter);

    }

    int countPrice = 0;
    List<ShopCartBean> mCartBeans;
    private StringBuilder goods_num;

    @Subscribe

    public void moneyChange(MoneyChangeEvent event) {
        moneySet();
    }

    private void moneySet() {
        countPrice = 0;
        if (mCartBeans != null) {
            mCartBeans.clear();
        }
        goods_num.delete(0, goods_num.length());

        Observable.fromIterable(mBean.she).subscribe(sheBean -> {
            countPrice += (sheBean.goods_price * sheBean.goods_num);
            ShopCartBean bean = new ShopCartBean(null, sheBean.goods_title, String.valueOf(sheBean.goods_num), sheBean
                    .goods_price_yuan, String.valueOf(sheBean.goods_num),
                    sheBean.goods_thumb, String.valueOf(sheBean.goods_attr_id));
            mCartBeans.add(bean);
            goods_num.append(sheBean.goods_attr_id);
            goods_num.append(",");
            goods_num.append(String.valueOf(sheBean.goods_num));
            goods_num.append("|");
        });
        Observable.fromIterable(mBean.ruan).filter(ruanBean -> ruanBean.isCheck)
                .subscribe(ruanBean -> {
                    countPrice += (ruanBean.goods_price * ruanBean.goods_num);
                    ShopCartBean bean = new ShopCartBean(null, ruanBean.goods_title, String.valueOf(ruanBean.goods_num), ruanBean
                            .goods_price_yuan,
                            String.valueOf(ruanBean.goods_num), ruanBean.goods_thumb, String.valueOf(ruanBean.goods_attr_id));
                    mCartBeans.add(bean);
                    goods_num.append(ruanBean.goods_attr_id);
                    goods_num.append(",");
                    goods_num.append(String.valueOf(ruanBean.goods_num));
                    goods_num.append("|");
                });
        Observable.fromIterable(mBean.ying)
                .subscribe(yingBean -> {
                    countPrice += (yingBean.goods_price * yingBean.goods_num);
                    ShopCartBean bean = new ShopCartBean(null, yingBean.goods_title, String.valueOf(yingBean.goods_num), yingBean.goods_price_yuan,
                            String.valueOf(yingBean.goods_num), yingBean.goods_thumb, String.valueOf(yingBean.goods_attr_id));
                    mCartBeans.add(bean);
                    goods_num.append(yingBean.goods_attr_id);
                    goods_num.append(",");
                    goods_num.append(String.valueOf(yingBean.goods_num));
                    goods_num.append("|");
                });
        goods_num.deleteCharAt(goods_num.lastIndexOf("|"));
        LogUtil.e(goods_num.toString());
        tvMoney.setText(String.format(Locale.CHINA, "￥%.2f", (countPrice / 100f)));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    public void setOrderCreate(OrderCreateBean bean) {

    }

    DefLocalBean.AddrBean mAddrBean;

    public void setOrderLocal(DefLocalBean localBean) {
        this.mAddrBean = localBean.addr;
        mMap.put("rp_name", mAddrBean.rp_name);
        mMap.put("rp_mobile", mAddrBean.rp_phone);
        String local = String.format(Locale.CHINA, "%s%s%s%s", mAddrBean.rp_province, mAddrBean.rp_city, mAddrBean.rp_area,
                mAddrBean.rp_addr);
        mMap.put("rp_addr", local);
    }
}
