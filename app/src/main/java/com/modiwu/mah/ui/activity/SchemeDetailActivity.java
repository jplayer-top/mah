package com.modiwu.mah.ui.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.modiwu.mah.R;
import com.modiwu.mah.base.BaseCommonActivity;
import com.modiwu.mah.ui.adapter.AdapterPagerShcemDetail;

import java.util.ArrayList;

import top.jplayer.baseprolibrary.utils.LogUtil;

/**
 * Created by Obl on 2018/1/23.
 * com.modiwu.mah.ui.activity
 */

public class SchemeDetailActivity extends BaseCommonActivity {
    @Override
    public int setBaseLayout() {
        return R.layout.activity_scheme_detail;
    }

    @Override
    public void initBaseData() {
        TabLayout tabLayout = mBaseView.findViewById(R.id.tabLayout);
        ViewPager viewPager = mBaseView.findViewById(R.id.viewPager);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        tabLayout.setupWithViewPager(viewPager);
        ArrayList<String> strings = new ArrayList<>();
        strings.add("整装");
        strings.add("硬装");
        strings.add("软装");
        strings.add("楼盘");
        strings.add("单品");
        viewPager.setAdapter(new AdapterPagerShcemDetail(getSupportFragmentManager(), strings));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                LogUtil.e("onTabSelected" + tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                LogUtil.e("onTabUnselected" + tab.getPosition());

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                LogUtil.e("onTabReselected" + tab.getPosition());
            }
        });
    }
}
