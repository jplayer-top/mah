package com.modiwu.mah.ui.Fragment;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.GridLayoutHelper;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.modiwu.mah.R;
import com.modiwu.mah.base.BaseFragment;
import com.modiwu.mah.mvp.constract.HomeContract;
import com.modiwu.mah.mvp.model.bean.HomeBean;
import com.modiwu.mah.mvp.presenter.HomePresenter;
import com.modiwu.mah.ui.adapter.HomeAdvLayoutAdapter;
import com.modiwu.mah.ui.adapter.HomeHeardLayoutAdapter;
import com.modiwu.mah.ui.adapter.HomeRecommendLayoutAdapter;
import com.modiwu.mah.ui.adapter.HomeSectionLayoutAdapter;
import com.modiwu.mah.ui.adapter.HomeSingleVLayoutAdapter;
import com.modiwu.mah.ui.adapter.HomeToShopLayoutAdapter;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import io.reactivex.Observable;

/**
 * Created by Obl on 2018/1/19.
 * com.modiwu.mah.ui.Fragment
 */

public class HomeFragment extends BaseFragment implements HomeContract.HomeView {


    protected RecyclerView mRecyclerView;
    private HomePresenter mPresenter;
    private DelegateAdapter mDelegateAdapter;

    @Override
    public int initLayout() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initData(View rootView) {
        mMultipleStatusView = rootView.findViewById(R.id.multiplestatusview);
        smartRefreshLayout = rootView.findViewById(R.id.smartRefreshLayout);
        mRecyclerView = rootView.findViewById(R.id.recyclerView);
        VirtualLayoutManager manager = new VirtualLayoutManager(getContext());
        mRecyclerView.setLayoutManager(manager);
        RecyclerView.RecycledViewPool pool = new RecyclerView.RecycledViewPool();
        pool.setMaxRecycledViews(0, 10);
        mRecyclerView.setRecycledViewPool(pool);
        mDelegateAdapter = new DelegateAdapter(manager, true);
        smartRefreshLayout.setOnRefreshListener(refresh -> mPresenter.requestHomeData());
        mPresenter = new HomePresenter(this);
        mPresenter.requestHomeData();
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
        }

        /**
         * 施工标准
         */
        adapters.add(new HomeAdvLayoutAdapter(getContext(), new LinearLayoutHelper(), 1, HomeBean.BODY_ADV));

        adapters.add(getHomeSectionLayoutAdapter("颜值单品"));
        adapters.add(new HomeSingleVLayoutAdapter(getContext(), new GridLayoutHelper(2), 6, HomeBean.BODY_SINGLE));


        List<HomeBean.SjsBean> sjsBeans = homeBean.sjs;
        if (fanganBeans != null && fanganBeans.size() > 0) {
            List<HomeBean.SjsBean> realSjsBeans = new ArrayList<>();
            Observable.fromIterable(sjsBeans).filter(sjsBean -> TextUtils.equals("sjs", sjsBean.navType)).subscribe(realSjsBeans::add);
            if (realSjsBeans.size() > 0) {
                adapters.add(getHomeSectionLayoutAdapter("匠心"));
                HomeRecommendLayoutAdapter recommendLayoutAdapter = getHomeRecommendLayoutAdapter(realSjsBeans);
                recommendLayoutAdapter.setSjs(realSjsBeans);
                adapters.add(recommendLayoutAdapter);
            }
        }

        /**
         * 服务保障
         */
        adapters.add(new HomeAdvLayoutAdapter(getContext(), new LinearLayoutHelper(), 1, HomeBean.BODY_ADV));
        /**
         * 实体店
         */
        adapters.add(new HomeToShopLayoutAdapter(getContext(), new LinearLayoutHelper(), 1, HomeBean.BODY_TOSHOP));
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

    @Override
    public void versionUpData() {

    }

}
