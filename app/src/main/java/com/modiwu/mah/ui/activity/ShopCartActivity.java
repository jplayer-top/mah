package com.modiwu.mah.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.modiwu.mah.R;
import com.modiwu.mah.base.BaseCommonActivity;
import com.modiwu.mah.mvp.constract.ShopCartContract;
import com.modiwu.mah.mvp.model.bean.ShopCartBean;
import com.modiwu.mah.mvp.model.event.PayOKStateEvent;
import com.modiwu.mah.mvp.presenter.ShopCartPresenter;
import com.modiwu.mah.ui.adapter.ShopCartAdapter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Observable;
import top.jplayer.baseprolibrary.net.IoMainSchedule;
import top.jplayer.baseprolibrary.utils.ActivityUtils;
import top.jplayer.baseprolibrary.utils.LogUtil;
import top.jplayer.baseprolibrary.utils.MoneyUtils;
import top.jplayer.baseprolibrary.utils.ToastUtils;

/**
 * Created by Administrator on 2018/1/28.
 * 购物车
 */

public class ShopCartActivity extends BaseCommonActivity implements ShopCartContract.IShopCartView {
    @BindView(R.id.tv2Collection)
    TextView mTv2Collection;
    @BindView(R.id.tv2Del)
    TextView mTv2Del;
    @BindView(R.id.tvMoney)
    TextView tvMoney;
    @BindView(R.id.tvToBuy)
    TextView tvToBuy;
    @BindView(R.id.llEditToDel)
    LinearLayout mLlEditToDel;
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    private ShopCartAdapter mAdapter;
    private ShopCartPresenter mPresenter;
    private Unbinder mUnbinder;

    @Override
    public int setBaseLayout() {
        return R.layout.activity_shop_cart;
    }

    @Override
    public void initBaseData() {
        findToolBarView(addRootView);
        mUnbinder = ButterKnife.bind(this, addRootView);
        EventBus.getDefault().register(this);
        goods_attr_id = new StringBuilder("");
        sublimCartBeans = new ArrayList<>();
        mMultipleStatusView = addRootView.findViewById(R.id.multiplestatusview);
        smartRefreshLayout = addRootView.findViewById(R.id.smartRefreshLayout);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setBackgroundColor(getResources().getColor(R.color.whitesmoke));
        mPresenter = new ShopCartPresenter(this);
        showLoading();
        mPresenter.requestShopCartData();
        smartRefreshLayout.setOnRefreshListener(refresh -> mPresenter.requestShopCartData());
        shopCartBeans = new ArrayList<>();
        mAdapter = new ShopCartAdapter(shopCartBeans);
        mRecyclerView.setAdapter(mAdapter);
        ivBarSearch.setVisibility(View.GONE);
        ivBarSearch.setImageResource(R.drawable.shop_cart_edit);

        mAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            int i = Integer.parseInt(shopCartBeans.get(position).count);
            switch (view.getId()) {
                case R.id.tvAdd:
                    if (i >= 999) {
                        ToastUtils.init().showInfoToast(mBaseActivity, "客官，分批买吧");
                        break;
                    }
                    countChange(i, position, 1);
                    break;
                case R.id.tvRemove:
                    if (i <= 1) {
                        ToastUtils.init().showInfoToast(mBaseActivity, "客官，不能再少了");

                        break;
                    }
                    countChange(i, position, -1);
                    break;
                case R.id.checkbox:
                    ShopCartBean shopCartBean = shopCartBeans.get(position);
                    shopCartBean.isCheck = !shopCartBean.isCheck;
                    moneyChange();
                    break;
                case R.id.tv_delete:
                    mPresenter.delData(mAdapter.getData().get(position));
                    break;
            }
            return false;
        });

        ivBarSearch.setOnClickListener(v -> {
            if (shopCartBeans != null) {
                mLlEditToDel.setVisibility(mLlEditToDel.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);
                Observable.just(shopCartBeans)
                        .map(shopCartBeans -> {
                            for (ShopCartBean shopCartBean : shopCartBeans) {
                                shopCartBean.isEdit = !shopCartBean.isEdit;
                            }
                            return shopCartBeans;
                        }).compose(new IoMainSchedule<>())
                        .subscribe(this::setShopCartData);
            }
        });

        mTv2Del.setOnClickListener(v -> {
            List<ShopCartBean> list = new ArrayList<>();
            Observable.fromIterable(shopCartBeans)
                    .filter(shopCartBean -> shopCartBean.isCheck)
                    .subscribe(list::add);
            if (list.size() > 0) {
                mPresenter.delData(list);
            } else {
                ToastUtils.init().showInfoToast(this, "请先选择要删除的物品");
            }
        });
        mTv2Collection.setOnClickListener(v -> {
            List<ShopCartBean> list = new ArrayList<>();
            Observable.fromIterable(shopCartBeans)
                    .filter(shopCartBean -> shopCartBean.isCheck)
                    .subscribe(list::add);
            mPresenter.delData(list);
        });
        tvToBuy.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            String json = new Gson().toJson(sublimCartBeans);
            bundle.putString("json", json);
            bundle.putString("goods_num", goods_attr_id.toString());
            ActivityUtils.init().start(this, ShopToBuyAvtivity.class, "确认购买", bundle);
        });
    }

    StringBuilder goods_attr_id;

    @Override
    public void showEmpty() {
        super.showEmpty();
        mLlEditToDel.setVisibility(View.GONE);
    }

    @Subscribe
    public void getPayStatusEvent(PayOKStateEvent event) {
        if (sublimCartBeans != null && sublimCartBeans.size() > 0) {
            mPresenter.delData(sublimCartBeans);
        }
    }

    private void countChange(int i, int position, int mov) {
        i = i + mov;
        ShopCartBean shopCartBean = shopCartBeans.get(position);
        shopCartBean.count = String.valueOf(i);
        mAdapter.notifyItemChanged(position);
        mPresenter.updataBean(position, shopCartBean);
        moneyChange();
    }

    List<ShopCartBean> shopCartBeans;
    List<ShopCartBean> sublimCartBeans;

    @Override
    public void setShopCartData(List<ShopCartBean> shopCartBeans) {
        this.shopCartBeans = shopCartBeans;
        moneyChange();
        mAdapter.setNewData(shopCartBeans);
    }

    float countMoney = 0;

    private void moneyChange() {
        countMoney = 0;
        if (sublimCartBeans != null) {
            sublimCartBeans.clear();
        } else {
            sublimCartBeans = new ArrayList<>();
        }
        if (goods_attr_id != null) {
            goods_attr_id.delete(0, goods_attr_id.length());
        }
        Observable.fromIterable(shopCartBeans)
                .filter(shopCartBean -> shopCartBean.isCheck)
                .subscribe(shopCartBean -> {
                    String strPrice = shopCartBean.price;
                    String count = shopCartBean.count;
                    int parseIntF = MoneyUtils.parseIntF(strPrice) * Integer.parseInt(count);
                    countMoney += parseIntF;
                    goods_attr_id.append(shopCartBean.getGoods_attr_id());
                    goods_attr_id.append(",");
                    goods_attr_id.append(shopCartBean.count);
                    goods_attr_id.append("|");
                    sublimCartBeans.add(shopCartBean);
                });
        LogUtil.e(goods_attr_id.toString());
        if (goods_attr_id != null && !goods_attr_id.toString().equals("")) {
            goods_attr_id.deleteCharAt(goods_attr_id.lastIndexOf("|"));
        }
        tvMoney.setText(String.format(Locale.CHINA, "￥%.2f", countMoney / 100f));
    }

    @Override
    public void delOneData() {
        mPresenter.requestShopCartData();
    }

    @Override
    public void upDataBean() {
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
        EventBus.getDefault().unregister(this);
    }

}
