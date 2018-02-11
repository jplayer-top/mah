package com.modiwu.mah.ui.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.modiwu.mah.R;
import com.modiwu.mah.base.BaseSpecialActivity;
import com.modiwu.mah.mvp.constract.SubShopContract;
import com.modiwu.mah.mvp.model.bean.ShopSubListBean;
import com.modiwu.mah.mvp.model.bean.SubTitleBean;
import com.modiwu.mah.mvp.presenter.ShopSubPresenter;
import com.modiwu.mah.ui.adapter.AdapterPagerShopSub;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import top.jplayer.baseprolibrary.net.IoMainSchedule;
import top.jplayer.baseprolibrary.widgets.MultipleStatusView;

/**
 * Created by Obl on 2018/2/5.
 * com.modiwu.mah.ui.activity
 */

public class ShopSubActivity extends BaseSpecialActivity implements SubShopContract.ISubShopView {

    ShopSubPresenter mPresenter;
    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    public MultipleStatusView mMultipleStatusView;
    public String mCat_id;

    @Override
    public int setBaseLayout() {
        return R.layout.activity_shop_sub;
    }

    @Override
    public void initBaseData() {
        findToolBarView(contentView);
        customBarLeft();
        mCat_id = mBundle.getString("cat_id");
        mViewPager = contentView.findViewById(R.id.viewPager);
        mMultipleStatusView = contentView.findViewById(R.id.multiplestatusview);
        mTabLayout = contentView.findViewById(R.id.tabLayout);
        mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        showLoading();
        mPresenter = new ShopSubPresenter(this);
        mPresenter.requestSubTitleData(mCat_id);
    }

    @Override
    public void setSubListData(ShopSubListBean bean) {

    }

    @Override
    public void setSubTitleData(SubTitleBean bean) {
        List<String> titleList = new ArrayList<>();
        Observable.fromIterable(bean.records).map(recordsBean -> {
            titleList.add(recordsBean.cat_name);
            return titleList;
        }).compose(new IoMainSchedule<>())
                .subscribe(strings -> {
                    mViewPager.setAdapter(new AdapterPagerShopSub(getSupportFragmentManager(), strings));
                    mTabLayout.setupWithViewPager(mViewPager);
                });

    }

    @Override
    public void showError() {
        if (mMultipleStatusView != null) {
            mMultipleStatusView.showError();
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
    }
}
