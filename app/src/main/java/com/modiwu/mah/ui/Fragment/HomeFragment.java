package com.modiwu.mah.ui.Fragment;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.GridLayoutHelper;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.modiwu.mah.R;
import com.modiwu.mah.base.BaseFragment;
import com.modiwu.mah.net.bean.HomeBean;
import com.modiwu.mah.ui.adapter.HomeAdvLayoutAdapter;
import com.modiwu.mah.ui.adapter.HomeHeardLayoutAdapter;
import com.modiwu.mah.ui.adapter.HomeRecommendLayoutAdapter;
import com.modiwu.mah.ui.adapter.HomeSectionLayoutAdapter;
import com.modiwu.mah.ui.adapter.HomeSingleVLayoutAdapter;
import com.modiwu.mah.ui.adapter.HomeToShopLayoutAdapter;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Obl on 2018/1/19.
 * com.modiwu.mah.ui.Fragment
 */

public class HomeFragment extends BaseFragment {


    protected RecyclerView mRecyclerView;

    @Override
    public int initLayout() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initData(View rootView) {
        mMultipleStatusView = rootView.findViewById(R.id.multiplestatusview);
        mRecyclerView = rootView.findViewById(R.id.recyclerView);
        ArrayList<HomeBean> homeBeans = new ArrayList<>();
        homeBeans.add(new HomeBean("BODY_RECOMMEND"));
        homeBeans.add(new HomeBean("BODY_SINGLE"));
        homeBeans.add(new HomeBean("BODY_RECOMMEND"));
        homeBeans.add(new HomeBean("BODY_ADV"));
        homeBeans.add(new HomeBean("BODY_TOSHOP"));
        VirtualLayoutManager manager = new VirtualLayoutManager(getContext());
        mRecyclerView.setLayoutManager(manager);
        RecyclerView.RecycledViewPool pool = new RecyclerView.RecycledViewPool();
        pool.setMaxRecycledViews(0, 10);
        mRecyclerView.setRecycledViewPool(pool);
        DelegateAdapter delegateAdapter = new DelegateAdapter(manager, true);
        mRecyclerView.setAdapter(delegateAdapter);
        List<DelegateAdapter.Adapter> adapters = new LinkedList<>();
        adapters.add(new HomeHeardLayoutAdapter(getContext(), new LinearLayoutHelper(), 1, HomeBean.BODY_HEARD));
        adapters.add(new HomeSectionLayoutAdapter(getContext(), new LinearLayoutHelper(), 1, HomeBean.BODY_SECTION));
        adapters.add(new HomeRecommendLayoutAdapter(getContext(), new LinearLayoutHelper(), 3, HomeBean.BODY_RECOMMEND));
        adapters.add(new HomeAdvLayoutAdapter(getContext(), new LinearLayoutHelper(), 1, HomeBean.BODY_ADV));
        adapters.add(new HomeSectionLayoutAdapter(getContext(), new LinearLayoutHelper(), 1, HomeBean.BODY_SECTION));
        adapters.add(new HomeSingleVLayoutAdapter(getContext(), new GridLayoutHelper(2), 6, HomeBean.BODY_SINGLE));
        adapters.add(new HomeSectionLayoutAdapter(getContext(), new LinearLayoutHelper(), 1, HomeBean.BODY_SECTION));
        adapters.add(new HomeRecommendLayoutAdapter(getContext(), new LinearLayoutHelper(), 1, HomeBean.BODY_RECOMMEND));
        adapters.add(new HomeAdvLayoutAdapter(getContext(), new LinearLayoutHelper(), 1, HomeBean.BODY_ADV));
        adapters.add(new HomeToShopLayoutAdapter(getContext(), new LinearLayoutHelper(), 1, HomeBean.BODY_TOSHOP));
        delegateAdapter.setAdapters(adapters);
    }
}
