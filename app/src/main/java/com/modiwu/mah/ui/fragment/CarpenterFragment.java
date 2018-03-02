package com.modiwu.mah.ui.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.modiwu.mah.MainActivity;
import com.modiwu.mah.R;
import com.modiwu.mah.base.BaseFragment;
import com.modiwu.mah.mvp.constract.CarpenterContract;
import com.modiwu.mah.mvp.model.bean.CarpenterBean;
import com.modiwu.mah.mvp.model.bean.DockerBean;
import com.modiwu.mah.mvp.presenter.CarpenterPresenter;
import com.modiwu.mah.ui.activity.DesignerActivity;
import com.modiwu.mah.ui.activity.ShopSubActivity;
import com.modiwu.mah.ui.adapter.CarpenterAdapter;
import com.modiwu.mah.ui.adapter.DockerAdapter;
import com.modiwu.mah.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import devlight.io.library.ntb.NavigationTabBar;
import top.jplayer.baseprolibrary.glide.GlideUtils;
import top.jplayer.baseprolibrary.listener.NetNavigationBarListener;
import top.jplayer.baseprolibrary.ui.WebFullScreenActivity;
import top.jplayer.baseprolibrary.utils.ActivityUtils;
import top.jplayer.baseprolibrary.utils.SizeUtils;

/**
 * Created by Obl on 2018/1/19.
 * com.modiwu.mah.ui.Fragment
 */

public class CarpenterFragment extends BaseFragment implements CarpenterContract.ICarpenterView {
    protected RecyclerView mRecyclerView1;
    protected RecyclerView mRecyclerView2;
    private NavigationTabBar mNavigationTabBar;
    private CarpenterPresenter mPresenter;
    private CarpenterAdapter mAdapter1;
    private DockerAdapter mAdapter2;
    private ImageView mIvHeard;
    private TabLayout mTabLayout;

    @Override
    public int initLayout() {
        return R.layout.fragment_carpenter;
    }

    @Override
    protected void initData(View rootView) {
        super.initData(rootView);
        mMultipleStatusView = rootView.findViewById(R.id.multiplestatusview);
        smartRefreshLayout = rootView.findViewById(R.id.smartRefreshLayout);
        mNavigationTabBar = rootView.findViewById(R.id.ntb);
        mRecyclerView1 = rootView.findViewById(R.id.recyclerView1);
        mTabLayout = rootView.findViewById(R.id.tabLayout);
        mRecyclerView2 = rootView.findViewById(R.id.recyclerView2);
        bottomBar(mNavigationTabBar);
        tvBarTitle.setText("匠器");
        initRecyclerView1(new ArrayList<>());
        initRecyclerView2(new ArrayList<>());

        mPresenter = new CarpenterPresenter(this);
        int type = ((MainActivity) getActivity()).carFragmentType;
        setShowTypeByClickMore(type);
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                if (position == 0) {
                    mRecyclerView1.setVisibility(View.VISIBLE);
                    mRecyclerView2.setVisibility(View.GONE);
                    click1();
                } else {
                    mRecyclerView1.setVisibility(View.GONE);
                    mRecyclerView2.setVisibility(View.VISIBLE);
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
    }


    private void initRecyclerView2(ArrayList<DockerBean.RecordsBean> list) {
        mRecyclerView2.setLayoutManager(new GridLayoutManager(getContext(), 3));
        mAdapter2 = new DockerAdapter(list);
        View view = View.inflate(getContext(), R.layout.adapter_carpenter_body, null);
        mIvHeard = view.findViewById(R.id.ivHeard);
        mAdapter2.addHeaderView(view);
        mAdapter2.setOnItemClickListener((adapter, view1, position) ->
        {
            DockerBean.RecordsBean recordsBean = (DockerBean.RecordsBean) adapter.getData().get(position);
            Bundle bundle = new Bundle();
            bundle.putString("cat_id", recordsBean.cat_id);
            ActivityUtils.init().start(getContext(), ShopSubActivity.class, recordsBean.cat_name, bundle);
        });
        mRecyclerView2.setAdapter(mAdapter2);

    }

    private void initRecyclerView1(List<CarpenterBean.RecordsBean> list) {
        mRecyclerView1.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        mAdapter1 = new CarpenterAdapter(list);
        mAdapter1.setOnItemClickListener((adapter, view, position) -> {
            Bundle bundle = new Bundle();
            List<CarpenterBean.RecordsBean> data = mAdapter1.getData();
            bundle.putString("designer_id", String.format(Locale.CHINA, "%d", data.get(position).designer_id));
            String designer = StringUtils.getInstance().isNullable(data.get(position).designer_name, "设计师");
            ActivityUtils.init().start(getContext(), DesignerActivity.class, designer, bundle);
        });
        mRecyclerView1.setAdapter(mAdapter1);
    }

    /**
     * 设置底部栏
     */
    private void bottomBar(NavigationTabBar navigationTabBar) {

        final ArrayList<NavigationTabBar.Model> models = new ArrayList<>();
        String[] titleArrs = new String[]{"匠", "器"};
        int[] drawArrs = new int[]{R.drawable.main_home, R.drawable.main_scheme, R.drawable.main_charpenter, R.drawable.main_me};
        for (int i = 0; i < titleArrs.length; i++) {
            models.add(new NavigationTabBar.Model.Builder(
                    getResources().getDrawable(drawArrs[i]),
                    getResources().getColor(top.jplayer.baseprolibrary.R.color.trans))
                    .title(titleArrs[i]).build());
        }
        navigationTabBar.setModels(models);
        navigationTabBar.setOnTabBarSelectedIndexListener(new NetNavigationBarListener() {
            @Override
            public void onceSelected(NavigationTabBar.Model model, int index) {
                if (index == 0) {
                    mRecyclerView1.setVisibility(View.VISIBLE);
                    mRecyclerView2.setVisibility(View.GONE);
                    click1();
                } else {
                    mRecyclerView1.setVisibility(View.GONE);
                    mRecyclerView2.setVisibility(View.VISIBLE);
                    click2();
                }
            }
        });
    }

    /**
     * 根据首页点击更多，实现进入不同界面
     *
     * @param type
     */
    private void setShowTypeByClickMore(int type) {
        mNavigationTabBar.setModelIndex(type);
        mTabLayout.setScrollPosition(type, SizeUtils.getMeasuredWidth(mTabLayout) / 2f, true);
    }

    private void click2() {
        if (mDockerBean != null) {
            setDockerData(mDockerBean);
        } else {
            showLoading();
            mPresenter.requestDockerData();
        }
    }

    private void click1() {
        if (mCarpenterBean != null) {
            setCarpenterData(mCarpenterBean);
        } else {
            showLoading();
            mPresenter.requestCarpenterData();
        }
    }

    @Override
    protected void onShowFragment() {
        int type = ((MainActivity) getActivity()).carFragmentType;
        setShowTypeByClickMore(type);
    }

    private CarpenterBean mCarpenterBean;

    @Override
    public void setCarpenterData(CarpenterBean bean) {
        mCarpenterBean = bean;
        mAdapter1.setNewData(bean.records);
        mMultipleStatusView.showContent(R.id.recyclerView1);
    }

    private DockerBean mDockerBean;

    @Override
    public void setDockerData(DockerBean bean) {
        this.mDockerBean = bean;
        if (bean.top != null) {
            Glide.with(this).load(bean.top.imgUrl).apply(GlideUtils.init().options()).into(mIvHeard);
        }
        mAdapter2.setNewData(bean.records);
        mMultipleStatusView.showContent(R.id.recyclerView2);
        mIvHeard.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            if ("url".equals(bean.top.navType)) {
                bundle.putString("url", bean.top.navValue);
                ActivityUtils.init().start(getContext(), WebFullScreenActivity.class, getString(R.string.app_name), bundle);
            }
        });
    }
}
