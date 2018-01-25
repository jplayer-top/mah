package com.modiwu.mah.ui.Fragment;

import android.support.v7.widget.RecyclerView;
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

import java.util.LinkedList;
import java.util.List;

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
        mRecyclerView = rootView.findViewById(R.id.recyclerView);
        VirtualLayoutManager manager = new VirtualLayoutManager(getContext());
        mRecyclerView.setLayoutManager(manager);
        RecyclerView.RecycledViewPool pool = new RecyclerView.RecycledViewPool();
        pool.setMaxRecycledViews(0, 10);
        mRecyclerView.setRecycledViewPool(pool);
        mDelegateAdapter = new DelegateAdapter(manager, true);
        mRecyclerView.setAdapter(mDelegateAdapter);


        mPresenter = new HomePresenter(this);
        mPresenter.requestHomeBean();
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
        mDelegateAdapter.setAdapters(adapters);
    }

    @Override
    public void versionUpData() {

    }

}
