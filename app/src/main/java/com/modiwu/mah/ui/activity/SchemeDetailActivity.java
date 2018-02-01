package com.modiwu.mah.ui.activity;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.modiwu.mah.R;
import com.modiwu.mah.base.BaseSpecialActivity;
import com.modiwu.mah.mvp.model.ShopCartDaoUtil;
import com.modiwu.mah.mvp.model.bean.ShopCartBean;
import com.modiwu.mah.ui.adapter.AdapterPagerShcemDetail;

import java.util.ArrayList;

import top.jplayer.baseprolibrary.utils.LogUtil;

/**
 * Created by Obl on 2018/1/23.
 * com.modiwu.mah.ui.activity
 */
public class SchemeDetailActivity extends BaseSpecialActivity {

    private ImageView mIvCirRed;

    @Override
    public int setBaseLayout() {
        return R.layout.activity_scheme_detail;
    }

    private LinearLayout llSchemeDetailBottom;

    @Override
    public void initBaseData() {
        findToolBarView(contentView);
        tvBarTitle.setText("方案详情");
        customBarLeft();
        TabLayout tabLayout = contentView.findViewById(R.id.tabLayout);
        mIvCirRed = contentView.findViewById(R.id.ivCirRed);
        ViewPager viewPager = contentView.findViewById(R.id.viewPager);
        llSchemeDetailBottom = contentView.findViewById(R.id.rlSchemeDetailBottom);
        TextView tvToBuy = contentView.findViewById(R.id.tvToBuy);
        TextView tvToAdd = contentView.findViewById(R.id.tvToAdd);
        TextView tvToCard = contentView.findViewById(R.id.tvToCard);
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
            mIvCirRed.setVisibility(View.GONE);
            startActivity(new Intent(mBaseActivity, ShopCartActivity.class));
        });
        ShopCartDaoUtil daoUtil = new ShopCartDaoUtil(this);
        tvToAdd.setOnClickListener(v -> {
            ShopCartBean bean = new ShopCartBean(null, "造作远山沙发", "成都酣然设计", "1894.00", "1", "sdasd");
            boolean b = daoUtil.insertShopCart(bean);
            mIvCirRed.setVisibility(b ? View.VISIBLE : View.GONE);
        });
    }

}
