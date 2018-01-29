package com.modiwu.mah.ui.activity;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.modiwu.mah.R;
import com.modiwu.mah.base.BaseSpecialActivity;
import com.modiwu.mah.ui.adapter.AdapterPagerShcemDetail;

import java.util.ArrayList;

import top.jplayer.baseprolibrary.utils.LogUtil;

/**
 * Created by Obl on 2018/1/23.
 * com.modiwu.mah.ui.activity
 */
public class SchemeDetailActivity extends BaseSpecialActivity {
    @Override
    public int setBaseLayout() {
        return R.layout.activity_scheme_detail;
    }

    private LinearLayout llSchemeDetailBottom;

    @Override
    public void initBaseData() {
        findToolBarView(mBaseView);
        tvBarTitle.setText("方案详情");
        customBarLeft();
        TabLayout tabLayout = mBaseView.findViewById(R.id.tabLayout);
        ViewPager viewPager = mBaseView.findViewById(R.id.viewPager);
        llSchemeDetailBottom = mBaseView.findViewById(R.id.rlSchemeDetailBottom);
        TextView tvToBuy = mBaseView.findViewById(R.id.tvToBuy);
        TextView tvToCard = mBaseView.findViewById(R.id.tvToCard);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        tabLayout.setupWithViewPager(viewPager);
        ArrayList<String> strings = new ArrayList<>();
        strings.add("整装");
        strings.add("硬装");
        strings.add("软装");
        strings.add("楼盘");
        strings.add("单品");
        AdapterPagerShcemDetail adapter = new AdapterPagerShcemDetail(getSupportFragmentManager(), strings);
        viewPager.setAdapter(adapter);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                LogUtil.e("onTabSelected" + tab.getPosition());
                if (tab.getPosition() == 3 || tab.getPosition() == 4) {
                    llSchemeDetailBottom.setVisibility(View.GONE);
                } else
                    llSchemeDetailBottom.setVisibility(View.VISIBLE);
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
        tvToCard.setOnClickListener(view -> {
            startActivity(new Intent(mBaseActivity, ShopCartActivity.class));
        });
    }

}
