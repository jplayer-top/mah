package com.modiwu.mah.ui.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.modiwu.mah.R;
import com.modiwu.mah.base.BaseCommonActivity;
import com.modiwu.mah.mvp.model.bean.DefLocalBean;
import com.modiwu.mah.mvp.model.bean.OrderCreateBean;
import com.modiwu.mah.mvp.model.bean.ShopCartBean;
import com.modiwu.mah.mvp.presenter.ShopToBuyPresenter;
import com.modiwu.mah.ui.adapter.OrderCreateInfoAdapter;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Observable;
import top.jplayer.baseprolibrary.utils.ActivityUtils;

/**
 * Created by Administrator on 2018/2/7.
 * 商品订单-提交
 */

public class ShopToBuyAvtivity extends BaseCommonActivity {
    @BindView(R.id.tvLocal)
    TextView mTvLocal;
    @BindView(R.id.tvCountPrice)
    TextView tvCountPrice;
    @BindView(R.id.recycleItem)
    RecyclerView recycleItem;
    @BindView(R.id.btnSubmit)
    Button btnSubmit;

    private Unbinder mUnbinder;
    DefLocalBean.AddrBean mAddrBean;
    ShopToBuyPresenter mPresenter;
    private Map<String, String> mMap;

    @Override
    public int setBaseLayout() {
        return R.layout.activity_shop_to_pay;
    }

    float countPrice = 0;

    @Override
    public void initBaseData() {
        mUnbinder = ButterKnife.bind(this, addRootView);
        mMultipleStatusView = addRootView.findViewById(R.id.multiplestatusview);
        mPresenter = new ShopToBuyPresenter(this);
        mPresenter.requestOrderLocalData();
        mMap = new HashMap<>();
        String goods_num = mBundle.getString("goods_num");
        mMap.put("goods_num", goods_num);
        List<ShopCartBean> orderLists = new Gson().fromJson(mBundle.getString("json"), new TypeToken<List<ShopCartBean>>() {
        }.getType());
        Observable.fromIterable(orderLists).subscribe(shopCartBean -> countPrice += Float.parseFloat(shopCartBean.getPrice()));
        mTvLocal.setOnClickListener(v -> ActivityUtils.init().start(this, LocalListActivity.class, "地址列表"));
        recycleItem.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recycleItem.setAdapter(new OrderCreateInfoAdapter(orderLists));
        btnSubmit.setOnClickListener(v -> mPresenter.requestOrderCreateData(mMap));
        tvCountPrice.setText(String.format(Locale.CHINA, "￥%.2f", countPrice));
    }

    public void setOrderCreate(OrderCreateBean bean) {

    }

    public void setOrderLocal(DefLocalBean localBean) {
        mAddrBean = localBean.addr;
        mMap.put("rp_name", mAddrBean.rp_name);
        mMap.put("rp_mobile", mAddrBean.rp_phone);
        String local = String.format(Locale.CHINA, "%s%s%s%s", mAddrBean.rp_province, mAddrBean.rp_city, mAddrBean.rp_area,
                mAddrBean.rp_addr);
        mMap.put("rp_addr", local);
        mTvLocal.setText(local);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
    }
}
