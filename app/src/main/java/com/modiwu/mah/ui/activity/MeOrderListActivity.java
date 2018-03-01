package com.modiwu.mah.ui.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.modiwu.mah.R;
import com.modiwu.mah.base.BaseSpecialActivity;
import com.modiwu.mah.mvp.model.event.PayOKStateEvent;
import com.modiwu.mah.ui.adapter.AdapterPagerOrderList;
import com.modiwu.mah.ui.fragment.OrderBaseFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import top.jplayer.baseprolibrary.ui.Fragment.SuperBaseFragment;

/**
 * Created by Obl on 2018/2/6.
 * com.modiwu.mah.ui.activity
 */


public class MeOrderListActivity extends BaseSpecialActivity {


    TabLayout mTabLayout;
    ViewPager mViewPager;
    AdapterPagerOrderList mAdpater;

    @Override
    public int setBaseLayout() {
        return R.layout.activity_me_order_list;
    }

    @Override
    public void initBaseData() {

        findToolBarView(contentView);
        customBarLeft();
        EventBus.getDefault().register(this);
        mViewPager = contentView.findViewById(R.id.viewPager);
        mTabLayout = contentView.findViewById(R.id.tabLayout);
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
        ArrayList<String> strings = new ArrayList<>();
        strings.add("待付款");
        strings.add("已付款");
        strings.add("已完成");
        mAdpater = new AdapterPagerOrderList(getSupportFragmentManager(), strings);
        mViewPager.setAdapter(mAdpater);
        mTabLayout.setupWithViewPager(mViewPager);
        mViewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                refreshDate(position);
            }
        });
        Observable.timer(500, TimeUnit.MILLISECONDS).subscribe(aLong -> refreshDate(0));
    }

    @Subscribe
    public void getPayStatusEvent(PayOKStateEvent event) {
        refreshDate(mViewPager.getCurrentItem());
    }

    private void refreshDate(int position) {
        List<SuperBaseFragment> list = mAdpater.mFragmentList;
        if (list != null && list.size() > 0) {
            OrderBaseFragment baseFragment = (OrderBaseFragment) list.get(position);
            baseFragment.requestDate();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    public void findToolBarView(View rootView) {
        mIvGoBack = contentView.findViewById(top.jplayer.baseprolibrary.R.id.ivGoBack);
        tvBarTitle = contentView.findViewById(top.jplayer.baseprolibrary.R.id.tvBarTitle);
        tvBarTitle.setText(getIntent().getStringExtra("title"));
    }
}