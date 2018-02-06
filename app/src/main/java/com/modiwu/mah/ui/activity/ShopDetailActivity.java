package com.modiwu.mah.ui.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.modiwu.mah.R;
import com.modiwu.mah.base.BaseSpecialActivity;
import com.modiwu.mah.mvp.constract.ShopDetailContract;
import com.modiwu.mah.mvp.model.bean.ShopGoodsInfoBean;
import com.modiwu.mah.mvp.presenter.ShopDetailPresenter;
import com.modiwu.mah.ui.adapter.AdapterPagerShopDetial;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
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
        findToolBarView(contentView);
        customBarLeft();
        String goods_id = mBundle.getString("goods_id");
        mUnbinder = ButterKnife.bind(this, contentView);
        mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);


        mPresenter = new ShopDetailPresenter(this);
        showLoading();
        mPresenter.requestShopDetailData(goods_id);

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
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
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
