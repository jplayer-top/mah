package com.modiwu.mah.ui.Fragment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.modiwu.mah.MainActivity;
import com.modiwu.mah.R;
import com.modiwu.mah.base.BaseFragment;
import com.modiwu.mah.mvp.constract.CarpenterContract;
import com.modiwu.mah.mvp.model.bean.CarpenterBean;
import com.modiwu.mah.mvp.model.bean.DockerBean;
import com.modiwu.mah.mvp.presenter.CarpenterPresenter;
import com.modiwu.mah.ui.adapter.CarpenterAdapter;
import com.modiwu.mah.ui.adapter.DockerAdapter;

import java.util.ArrayList;
import java.util.List;

import devlight.io.library.ntb.NavigationTabBar;
import top.jplayer.baseprolibrary.listener.NetNavigationBarListener;

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

    @Override
    public int initLayout() {
        return R.layout.fragment_carpenter;
    }

    @Override
    protected void initData(View rootView) {
        mMultipleStatusView = rootView.findViewById(R.id.multiplestatusview);
        smartRefreshLayout = rootView.findViewById(R.id.smartRefreshLayout);
        mNavigationTabBar = rootView.findViewById(R.id.ntb);
        mRecyclerView1 = rootView.findViewById(R.id.recyclerView1);
        mRecyclerView2 = rootView.findViewById(R.id.recyclerView2);
        bottomBar(mNavigationTabBar);

        initRecyclerView1(new ArrayList<>());
        initRecyclerView2(new ArrayList<>());

        mPresenter = new CarpenterPresenter(this);
        showLoading();

        int type = ((MainActivity) getActivity()).carFragmentType;
        setShowTypeByClickMore(type);
    }


    private void initRecyclerView2(ArrayList<DockerBean.RecordsBean> list) {
        mRecyclerView2.setLayoutManager(new GridLayoutManager(getContext(), 3));
        mAdapter2 = new DockerAdapter(list);
        mAdapter2.addHeaderView(View.inflate(getContext(), R.layout.adapter_home_body_toshop, null));
        mRecyclerView2.setAdapter(mAdapter2);
    }

    private void initRecyclerView1(List<CarpenterBean.RecordsBean> list) {
        mRecyclerView1.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        mAdapter1 = new CarpenterAdapter(list);
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
                    getResources().getColor(top.jplayer.baseprolibrary.R.color.black))
                    .title(titleArrs[i]).build());
        }
        navigationTabBar.setModels(models);
        navigationTabBar.setOnTabBarSelectedIndexListener(new NetNavigationBarListener() {
            @Override
            public void onceSelected(NavigationTabBar.Model model, int index) {
                if (index == 0) {
                    mRecyclerView1.setVisibility(View.VISIBLE);
                    mRecyclerView2.setVisibility(View.GONE);
                } else {
                    mRecyclerView1.setVisibility(View.GONE);
                    mRecyclerView2.setVisibility(View.VISIBLE);
                    mPresenter.requestDockerData();
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
        if (type == 0) {
            if (mCarpenterBean != null) {
                setCarpenterData(mCarpenterBean);
            } else {
                mPresenter.requestCarpenterData();
            }
        } else {
            if (mDockerBean != null) {
                setDockerData(mDockerBean);
            } else {
                mPresenter.requestDockerData();
            }
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

        mAdapter2.setNewData(bean.records);
        mMultipleStatusView.showContent(R.id.recyclerView2);
    }
}
