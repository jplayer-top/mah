package top.jplayer.baseprolibrary.ui;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import top.jplayer.baseprolibrary.R;
import top.jplayer.baseprolibrary.mvp.contract.SampleContract;
import top.jplayer.baseprolibrary.mvp.model.bean.SampleBean;
import top.jplayer.baseprolibrary.mvp.presenter.SamplePresenter;
import top.jplayer.baseprolibrary.ui.adapter.SampleAdapter;

/**
 * Created by Administrator on 2018/1/27.
 * 样例
 */

public class SampleActivity extends SuperBaseActivity implements SampleContract.ISampleView {

    private SamplePresenter presenter;
    private SampleAdapter adapter;
    private SmartRefreshLayout refreshLayout;

    @Override
    public void initSuperData(FrameLayout mFlRootView) {
        mFlRootView.addView(View.inflate(this, R.layout.activity_sample, null));
        presenter = new SamplePresenter(this);
        presenter.requestHBList();
        refreshLayout = mFlRootView.findViewById(R.id.smartRefreshLayout);
        RecyclerView recyclerView = mFlRootView.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        ArrayList<SampleBean.DataBean.ListBean> sampleBeans = new ArrayList<>();
        adapter = new SampleAdapter(sampleBeans);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemChildClickListener((adapter1, view, position) ->
        {
            List<SampleBean.DataBean.ListBean> datas = adapter1.getData();
            for (SampleBean.DataBean.ListBean listBean : datas) {
                presenter.requestGrad(listBean.id, "2017082407616512");
                presenter.requestGrad(listBean.id, "2017091307758112");
            }
            return false;
        });
        refreshLayout.setOnRefreshListener(refresh -> presenter.requestHBList());
    }

    @Override
    public void setHBList(SampleBean sampleBean) {
        SampleBean.DataBean data = sampleBean.data;
        refreshLayout.finishRefresh();
        adapter.setNewData(data.list);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.detachView();
        }
    }

    @Override
    public void showError() {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void showEmpty() {

    }


}
