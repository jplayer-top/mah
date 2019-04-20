package com.modiwu.mah.ui.fragment;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.GridLayoutHelper;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.modiwu.mah.BuildConfig;
import com.modiwu.mah.MainActivity;
import com.modiwu.mah.R;
import com.modiwu.mah.base.BaseFragment;
import com.modiwu.mah.mvp.constract.HomeContract;
import com.modiwu.mah.mvp.model.bean.AdvBean;
import com.modiwu.mah.mvp.model.bean.CameraBean;
import com.modiwu.mah.mvp.model.bean.HomeBean;
import com.modiwu.mah.mvp.model.bean.VersionBean;
import com.modiwu.mah.mvp.presenter.HomePresenter;
import com.modiwu.mah.ui.activity.ToGetProActivity;
import com.modiwu.mah.ui.adapter.HomeAdvLayoutAdapter;
import com.modiwu.mah.ui.adapter.HomeHeardLayoutAdapter;
import com.modiwu.mah.ui.adapter.HomeRecommendLayoutAdapter;
import com.modiwu.mah.ui.adapter.HomeSectionFooterLayoutAdapter;
import com.modiwu.mah.ui.adapter.HomeSectionLayoutAdapter;
import com.modiwu.mah.ui.adapter.HomeSingleVLayoutAdapter;
import com.modiwu.mah.ui.adapter.HomeToShopLayoutAdapter;
import com.modiwu.mah.ui.dialog.AdvDialog;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import io.reactivex.Observable;
import top.jplayer.baseprolibrary.BaseInitApplication;
import top.jplayer.baseprolibrary.ui.ContactActivity;
import top.jplayer.baseprolibrary.utils.ActivityUtils;

import static android.app.Activity.RESULT_FIRST_USER;

/**
 * Created by Obl on 2018/1/19.
 * com.modiwu.mah.ui.Fragment
 */

public class HomeFragment extends BaseFragment implements HomeContract.HomeView {


    protected RecyclerView mRecyclerView;
    protected TextView tvCurLocal;
    private HomePresenter mPresenter;
    private DelegateAdapter mDelegateAdapter;
    public MainActivity mMainActivity;

    @Override
    public int initLayout() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initData(View rootView) {
        mMultipleStatusView = rootView.findViewById(R.id.multiplestatusview);
        smartRefreshLayout = rootView.findViewById(R.id.smartRefreshLayout);
        mRecyclerView = rootView.findViewById(R.id.recyclerView);
        tvCurLocal = rootView.findViewById(R.id.tvCurLocal);
        ivBarSearch = rootView.findViewById(R.id.ivBarSearch);
        ivBarSearch.setImageResource(R.drawable.qcode);
        ivBarSearch.setVisibility(View.VISIBLE);
        ivBarSearch.setOnClickListener(v -> {
            EventBus.getDefault().post(new CameraBean());
        });
        mMainActivity = (MainActivity) getActivity();
        VirtualLayoutManager manager = new VirtualLayoutManager(getContext());
        mRecyclerView.setLayoutManager(manager);
        RecyclerView.RecycledViewPool pool = new RecyclerView.RecycledViewPool();
        pool.setMaxRecycledViews(0, 10);
        mRecyclerView.setRecycledViewPool(pool);
        mDelegateAdapter = new DelegateAdapter(manager, true);
        smartRefreshLayout.setOnRefreshListener(refresh -> mPresenter.requestHomeData());
        mPresenter = new HomePresenter(this);
        showLoading();
        mPresenter.requestHomeData();
        mPresenter.requestVersion();
        tvCurLocal.setVisibility(View.VISIBLE);
        tvCurLocal.setOnClickListener(v -> ActivityUtils.init().startFragmentForResult(this, ContactActivity.class,
                "定位城市", RESULT_FIRST_USER));
        mPresenter.requestAdvBean();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RESULT_FIRST_USER && resultCode == BaseInitApplication.DEF_RESULT) {
            String city = data.getStringExtra("city");
            mPresenter.requestCityCode(city);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }

    @Override
    public void setHomeData(HomeBean homeBean) {
        homeMethod(homeBean);
        tvCurLocal.setText("烟台");
    }

    public void setHomeData(HomeBean homeBean, String city) {
        homeMethod(homeBean);
        tvCurLocal.setText(city);
    }

    private void homeMethod(HomeBean homeBean) {
        List<DelegateAdapter.Adapter> adapters = new LinkedList<>();
        /**
         * banner 数据
         *
         */
        List<HomeBean.BannerBean> banner = homeBean.banner;
        if (banner != null && banner.size() > 0) {
            HomeHeardLayoutAdapter heardLayoutAdapter = new HomeHeardLayoutAdapter(getContext(), new LinearLayoutHelper(), 1, HomeBean.BODY_HEARD);
            heardLayoutAdapter.setBanner(banner);
            adapters.add(heardLayoutAdapter);
        }
        /**
         * 方案
         */
        List<HomeBean.FanganBean> fanganBeans = homeBean.fangan;
        if (fanganBeans != null && fanganBeans.size() > 0) {
            adapters.add(getHomeSectionLayoutAdapter("方案"));
            HomeRecommendLayoutAdapter recommendLayoutAdapter = getHomeRecommendLayoutAdapter(fanganBeans);
            recommendLayoutAdapter.setFangAn(fanganBeans);
            adapters.add(recommendLayoutAdapter);
            adapters.add(getHomeSectionFooterLayoutAdapter("方案"));

        }

        /**
         * 施工标准
         */
        List<HomeBean.ShiGongBean> shiGongBeans = homeBean.shigong;
        if (shiGongBeans != null && shiGongBeans.size() > 0) {
            HomeToShopLayoutAdapter standardLayoutAdapter = new HomeToShopLayoutAdapter(getContext(), new
                    LinearLayoutHelper(), shiGongBeans.size(), HomeBean.BODY_TOSHOP);
            adapters.add(standardLayoutAdapter);
            standardLayoutAdapter.setShiGong(shiGongBeans);
        }
        /**
         * 颜值单品
         */
        List<HomeBean.GoodBean> goodBeans = homeBean.goods;
        if (goodBeans != null && goodBeans.size() > 0) {
            adapters.add(getHomeSectionLayoutAdapter("颜值单品"));
            GridLayoutHelper gridLayoutHelper = new GridLayoutHelper(2, goodBeans.size());
            HomeSingleVLayoutAdapter singleVLayoutAdapter = new HomeSingleVLayoutAdapter(getContext(), gridLayoutHelper, goodBeans.size(), HomeBean.BODY_SINGLE);
            singleVLayoutAdapter.setGoods(goodBeans);
            adapters.add(singleVLayoutAdapter);
            adapters.add(getHomeSectionFooterLayoutAdapter("颜值单品"));
        }

        /**
         * 匠心
         */
        List<HomeBean.SjsBean> sjsBeans = homeBean.sjs;
        if (sjsBeans != null && sjsBeans.size() > 0) {
            List<HomeBean.SjsBean> realSjsBeans = new ArrayList<>();
            Observable.fromIterable(sjsBeans).filter(sjsBean -> TextUtils.equals("sjs", sjsBean.navType)).subscribe(realSjsBeans::add);
            if (realSjsBeans.size() > 0) {
                adapters.add(getHomeSectionLayoutAdapter("匠心"));
                HomeRecommendLayoutAdapter recommendLayoutAdapter = getHomeRecommendLayoutAdapter(realSjsBeans);
                recommendLayoutAdapter.setSjs(realSjsBeans);
                adapters.add(recommendLayoutAdapter);
                adapters.add(getHomeSectionFooterLayoutAdapter("匠心"));

            }
        }

        /**
         * 服务保障
         */
        List<HomeBean.ShouHouBean> shouHouBeans = homeBean.shouhou;
        if (shouHouBeans != null && shouHouBeans.size() > 0) {
            HomeAdvLayoutAdapter advLayoutAdapter = new HomeAdvLayoutAdapter(getContext(), new LinearLayoutHelper(), shouHouBeans.size(), HomeBean
                    .BODY_ADV);
            advLayoutAdapter.setShouHou(shouHouBeans);
            adapters.add(advLayoutAdapter);
        }

        /**
         * 实体店
         */
        List<HomeBean.ShiDianBean> shiDianBeans = homeBean.shidian;
        if (shiDianBeans != null && shiDianBeans.size() > 0) {
            HomeToShopLayoutAdapter standardLayoutAdapter = new HomeToShopLayoutAdapter(getContext(), new
                    LinearLayoutHelper(), shiDianBeans.size(), HomeBean.BODY_TOSHOP);
            adapters.add(standardLayoutAdapter);
            standardLayoutAdapter.setShiDian(shiDianBeans);
        }


        mDelegateAdapter.clear();
        mDelegateAdapter.setAdapters(adapters);
        mRecyclerView.setAdapter(mDelegateAdapter);
    }

    @NonNull
    private HomeRecommendLayoutAdapter getHomeRecommendLayoutAdapter(List beans) {
        return new HomeRecommendLayoutAdapter(getContext(), new LinearLayoutHelper(), beans.size(), HomeBean.BODY_RECOMMEND);
    }

    private HomeSectionLayoutAdapter getHomeSectionLayoutAdapter(String title) {
        HomeSectionLayoutAdapter sectionLayoutAdapter = new HomeSectionLayoutAdapter(getContext(), new LinearLayoutHelper(), 1, HomeBean.BODY_SECTION);
        sectionLayoutAdapter.setTitle(title);
        return sectionLayoutAdapter;
    }

    private HomeSectionFooterLayoutAdapter getHomeSectionFooterLayoutAdapter(String title) {
        HomeSectionFooterLayoutAdapter sectionLayoutAdapter = new HomeSectionFooterLayoutAdapter(getContext(), new
                LinearLayoutHelper(), 1, HomeBean.BODY_SECTION_FOOTER);
        sectionLayoutAdapter.setTitle(title);
        return sectionLayoutAdapter;
    }


    @Override
    public void versionUpData(VersionBean versionBean) {
        VersionBean.VerBean verBean = versionBean.ver;
        int curVerCode = BuildConfig.VERSION_CODE;// 当前的版本号
        int urlCode = verBean.build;
        if (urlCode > curVerCode) {
            mMainActivity.showNoticeDialog(verBean);
        }
    }


    public void setAdvBean(AdvBean bean) {
        if (bean != null && bean.banner != null && bean.banner.banner_img != null) {
            AdvBean.BannerBean banner = bean.banner;
            AdvDialog advDialog = new AdvDialog(mMainActivity);
            advDialog.setBean(bean);
            advDialog.setIvListener(() -> {
                Bundle bundle = new Bundle();
                bundle.putString("fangan_id", banner.banner_id + "");
                bundle.putString("fangan_name", banner.banner_title);
                ActivityUtils.init().start(mMainActivity, ToGetProActivity.class, "", bundle);
            });
            advDialog.show(R.id.ivDel, view -> {
                advDialog.dismiss();
            });
        }
    }
}
