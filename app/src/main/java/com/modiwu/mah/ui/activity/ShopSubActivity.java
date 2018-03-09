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
import com.modiwu.mah.ui.fragment.ShopSubFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
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
    private List<String> mCat_ids;
    private AdapterPagerShopSub mAdapter;

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
        mCat_ids = new ArrayList<>();
        Observable.fromIterable(bean.records)
                .subscribe(recordsBean -> {
                    titleList.add(recordsBean.cat_name);
                    mCat_ids.add(recordsBean.cat_id);
                });
        mAdapter = new AdapterPagerShopSub(getSupportFragmentManager(), titleList);
        mViewPager.setAdapter(mAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
        mViewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                refreshData(position);
            }
        });
        Observable.timer(500, TimeUnit.MILLISECONDS).subscribe(aLong -> refreshData(0));

    }

    private void refreshData(int position) {
        mCat_id = mCat_ids.get(position);
        ShopSubFragment fragment = mAdapter.mFragmentList.get(position);
        fragment.requestData(mCat_id);
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
