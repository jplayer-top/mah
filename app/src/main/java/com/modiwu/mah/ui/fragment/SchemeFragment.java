package com.modiwu.mah.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.modiwu.mah.R;
import com.modiwu.mah.base.BaseFragment;
import com.modiwu.mah.mvp.constract.SchemeContract;
import com.modiwu.mah.mvp.model.bean.SchemeBean;
import com.modiwu.mah.mvp.model.event.SchemeSelectEvent;
import com.modiwu.mah.mvp.presenter.SchemePresenter;
import com.modiwu.mah.ui.activity.SchemeDetailActivity;
import com.modiwu.mah.ui.activity.SchemeSearchActivity;
import com.modiwu.mah.ui.adapter.SchemeAdapter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import top.jplayer.baseprolibrary.utils.ActivityUtils;
import top.jplayer.baseprolibrary.utils.SharePreUtil;

/**
 * Created by Obl on 2018/1/19.
 * com.modiwu.mah.ui.Fragment
 */

public class SchemeFragment extends BaseFragment implements SchemeContract.ISchemeView {

    protected RecyclerView mRecyclerView;
    private SchemePresenter mPresenter;
    private SchemeAdapter mAdapter;
    private SchemeAdapter mAdapter1;
    private String mCity_code;
    private TabLayout mTabLayout;
    protected RecyclerView mRecyclerView1;

    @Override
    public int initLayout() {
        return R.layout.fragment_scheme;
    }

    @Override
    protected void initData(View rootView) {
        super.initData(rootView);
        EventBus.getDefault().register(this);
        mMultipleStatusView = rootView.findViewById(R.id.multiplestatusview);
        smartRefreshLayout = rootView.findViewById(R.id.smartRefreshLayout);
        mRecyclerView = rootView.findViewById(R.id.recyclerView);
        mRecyclerView1 = rootView.findViewById(R.id.recyclerView1);
        mTabLayout = rootView.findViewById(R.id.tabLayout);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        mRecyclerView1.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        mPresenter = new SchemePresenter(this);
        showLoading();
        mCity_code = (String) SharePreUtil.getData(getContext(), "sel_city_code", "");
        click1();
        smartRefreshLayout.setOnRefreshListener(refresh -> {
            if (mTabLayout.getSelectedTabPosition() == 0) {
                mPresenter.requestAnLiData(mCity_code);
            } else {
                mPresenter.requestSchemeData(mCity_code);
            }
            mRecyclerView.setVisibility(View.GONE);
            mRecyclerView1.setVisibility(View.GONE);
        });

        ArrayList<SchemeBean.RecordsBean> recordsBeans = new ArrayList<>();
        ArrayList<SchemeBean.RecordsBean> recordsAnLiBeans = new ArrayList<>();
        mAdapter = new SchemeAdapter(recordsBeans);
        mAdapter1 = new SchemeAdapter(recordsAnLiBeans);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView1.setAdapter(mAdapter1);
        mAdapter.setOnItemChildClickListener((adapter1, view, position) -> {
            List<SchemeBean.RecordsBean> recordsBean = mAdapter.getData();
            Bundle bundle = new Bundle();
            bundle.putString("fangan_id", String.format(Locale.CHINA, "%d", recordsBean.get(position).fangan_id));
            bundle.putBoolean("ttype",false);//产品
            ActivityUtils.init().start(getContext(), SchemeDetailActivity.class, recordsBean.get(position).fangan_name, bundle);
            return false;
        });
        mAdapter1.setOnItemChildClickListener((adapter1, view, position) -> {
            List<SchemeBean.RecordsBean> recordsBean = mAdapter1.getData();
            Bundle bundle = new Bundle();
            bundle.putString("fangan_id", String.format(Locale.CHINA, "%d", recordsBean.get(position).fangan_id));
            bundle.putBoolean("ttype",true);//案例
            ActivityUtils.init().start(getContext(), SchemeDetailActivity.class, recordsBean.get(position).fangan_name, bundle);
            return false;
        });
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                showLoading();
                if (position == 0) {

                    click1();
                } else {

                    click2();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        tvBarTitle.setText("方案");
        ivBarSearch = rootView.findViewById(R.id.ivBarSearch);
        ivBarSearch.setVisibility(View.VISIBLE);
        ivBarSearch.setOnClickListener(v -> startActivity(new Intent(getContext(), SchemeSearchActivity.class)));
    }

    private void click2() {
        mPresenter.requestSchemeData(mCity_code);
    }

    private void click1() {
        mPresenter.requestAnLiData(mCity_code);
    }

    @Subscribe
    public void selectScheme(SchemeSelectEvent event) {
        Map<String, String> map = new HashMap<>();
        if (!TextUtils.equals("", event.building_id))
            map.put("building_id", event.building_id);
        if (!TextUtils.equals("", event.area_code))
            map.put("area_code", event.area_code);
        if (!TextUtils.equals("", event.city_code))
            map.put("city_code", event.city_code);
        if (!TextUtils.equals("", event.fangan_style))
            map.put("fangan_style", event.fangan_style);
        if (!TextUtils.equals("", event.huxing_type))
            map.put("huxing_type", event.huxing_type);
        showLoading();
        mPresenter.requestSchemeData(map);
        mPresenter.requestAnLiData(map);
    }

    @Override

    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void setSchemeData(SchemeBean schemeData) {
        mAdapter.setNewData(schemeData.records);
        mRecyclerView1.setVisibility(View.GONE);
        mRecyclerView.setVisibility(View.VISIBLE);
    }

    public void setAnLiData(SchemeBean schemeData) {
        mAdapter1.setNewData(schemeData.records);
        mRecyclerView1.setVisibility(View.VISIBLE);
        mRecyclerView.setVisibility(View.GONE);
    }
}
