package com.modiwu.mah.ui.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.modiwu.mah.R;
import com.modiwu.mah.base.BaseSpecialActivity;
import com.modiwu.mah.mvp.constract.ShopDetailContract;
import com.modiwu.mah.mvp.model.ShopCartDaoUtil;
import com.modiwu.mah.mvp.model.bean.ShopCartBean;
import com.modiwu.mah.mvp.model.bean.ShopGoodsInfoBean;
import com.modiwu.mah.mvp.presenter.ShopDetailPresenter;
import com.modiwu.mah.ui.adapter.AdapterPagerShopDetial;
import com.modiwu.mah.ui.dialog.FsShopDetialDialog;
import com.modiwu.mah.utils.StringUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import top.jplayer.baseprolibrary.utils.ActivityUtils;
import top.jplayer.baseprolibrary.utils.ToastUtils;
import top.jplayer.baseprolibrary.widgets.MultipleStatusView;

/**
 * Created by Obl on 2018/2/5.
 * com.modiwu.mah.ui.activity
 */

public class ShopDetailActivity extends BaseSpecialActivity implements ShopDetailContract.IShopDetailView {
    @BindView(R.id.tabLayout)
    TabLayout mTabLayout;
    @BindView(R.id.viewPager)
    ViewPager mViewPager;
    @BindView(R.id.tvServer)
    TextView tvServer;
    @BindView(R.id.tvToCard)
    TextView tvToCard;
    @BindView(R.id.tvToBuy)
    TextView tvToBuy;
    @BindView(R.id.tvToAdd)
    TextView tvToAdd;
    @BindView(R.id.ivCirRed)
    ImageView ivCirRed;
    private Unbinder mUnbinder;
    private ShopDetailPresenter mPresenter;
    @BindView(R.id.multiplestatusview)
    public MultipleStatusView mMultipleStatusView;
    @BindView(R.id.smartRefreshLayout)
    public SmartRefreshLayout smartRefreshLayout;

    @Override
    public int setBaseLayout() {
        return R.layout.activity_shop_detail;
    }

    @Override
    public void initBaseData() {
        EventBus.getDefault().register(this);
        findToolBarView(contentView);
        customBarLeft();
        String goods_id = mBundle.getString("goods_id");
        mUnbinder = ButterKnife.bind(this, contentView);
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);


        mPresenter = new ShopDetailPresenter(this);
        showLoading();
        mPresenter.requestShopDetailData(goods_id);
        tvToCard.setText("加入购物车");
        tvToCard.setOnClickListener(view -> {
//            ActivityUtils.init().start(this, ShopCartActivity.class, "购物车");
//            ivCirRed.setVisibility(View.GONE);
            if (StringUtils.getInstance().assert2Login(this)) {
                typeClick = false;
                callListener();
            }

        });
        tvToBuy.setOnClickListener(view -> {
            if (StringUtils.getInstance().assert2Login(this)) {
                typeClick = true;
                callListener();
            }
        });
        tvBarTitle.setMaxEms(6);
        tvBarTitle.setEllipsize(TextUtils.TruncateAt.END);
        tvBarTitle.setLines(1);

        tvToAdd.setOnClickListener(view -> {
            if (StringUtils.getInstance().assert2Login(this)) {
                typeClick = false;
                callListener();
            }
        });
    }

    boolean typeClick = false;

    public void callListener() {
        if (bean == null || bean.goods == null) {
            return;
        }
        FsShopDetialDialog fsShopDetialDialog = new FsShopDetialDialog();
        Bundle bundle = new Bundle();
        bundle.putParcelable("type", bean);
        bundle.putString("num", String.format(Locale.CHINA, "%d", bean.goods.goods_stocks));
        bundle.putString("price", bean.goods.goods_price_yuan);
        fsShopDetialDialog.setArguments(bundle);
        fsShopDetialDialog.show(getSupportFragmentManager(), "fs");
    }

    @Subscribe
    public void clickDialog(FsShopDetialDialog.ShodeDetialOKEvent event) {
        ShopCartDaoUtil daoUtil = new ShopCartDaoUtil(this);
        ShopGoodsInfoBean.GoodsBean goods = this.bean.goods;
        String goods_attr_id = "";
        for (ShopGoodsInfoBean.SpecsBean spec : bean.specs) {
            if (TextUtils.equals(event.attr_ids, spec.attr_id)) {
                goods_attr_id = String.format(Locale.CHINA, "%d", spec.goods_attr_id);
            }
        }
        ShopCartBean bean = new ShopCartBean(null, goods.goods_title, goods_attr_id, event.price, event.amount +
                "", goods.goods_thumb, event.attrs, goods_attr_id);
        if (typeClick) {
            Bundle bundle = new Bundle();
            List<ShopCartBean> list = new ArrayList<>();
            list.add(bean);
            String json = new Gson().toJson(list);
            bundle.putString("json", json);
            bundle.putString("goods_num", String.format(Locale.CHINA, "%s,%d", goods_attr_id, event.amount));
            ActivityUtils.init().start(this, ShopToBuyAvtivity.class, "确认购买", bundle);
        } else {
            boolean insertShopCart = daoUtil.insertShopCart(bean);
            if (insertShopCart) {
//                ivCirRed.setVisibility(View.VISIBLE);
                ToastUtils.init().showSuccessToast(this, "加入成功");
            }
        }
    }

    public ShopGoodsInfoBean bean;

    @Override
    public void setShopDetailData(ShopGoodsInfoBean bean) {
        this.bean = bean;
        List<String> list = new ArrayList<>();
        list.add("作品");
        list.add("参数");
        list.add("方案");
        mViewPager.setAdapter(new AdapterPagerShopDetial(getSupportFragmentManager(), list));
        mTabLayout.setupWithViewPager(mViewPager);
        tvServer.setOnClickListener(v -> {
            if (StringUtils.getInstance().assert2Login(this)) {
                ActivityUtils.init().startConversion(this, ConversationOneActivity.class,
                        bean.kfuid);
            }

        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
        EventBus.getDefault().unregister(this);
    }


    @Override
    public void showError() {
        if (mMultipleStatusView != null) {
            mMultipleStatusView.showError();
        }
        if (smartRefreshLayout != null && smartRefreshLayout.isRefreshing()) {
            smartRefreshLayout.finishRefresh();
        }
    }

    @Override
    public void showLoading() {
        if (mMultipleStatusView != null) {
            mMultipleStatusView.showLoading();
        }
    }

    @Override
    public void showEmpty() {
        if (mMultipleStatusView != null) {
            mMultipleStatusView.showEmpty();
        }
        if (smartRefreshLayout != null && smartRefreshLayout.isRefreshing()) {
            smartRefreshLayout.finishRefresh();
        }
    }

}
