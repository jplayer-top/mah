package com.modiwu.mah.ui.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.modiwu.mah.R;
import com.modiwu.mah.base.BaseFragment;
import com.modiwu.mah.mvp.constract.SchemeContract;
import com.modiwu.mah.mvp.model.bean.SchemeBean;
import com.modiwu.mah.mvp.presenter.SchemePresenter;
import com.modiwu.mah.ui.activity.SchemeDetailActivity;
import com.modiwu.mah.ui.activity.SchemeSearchActivity;
import com.modiwu.mah.ui.adapter.SchemeAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import top.jplayer.baseprolibrary.utils.ActivityUtils;

/**
 * Created by Obl on 2018/1/19.
 * com.modiwu.mah.ui.Fragment
 */

public class SchemeFragment extends BaseFragment implements SchemeContract.ISchemeView {

    protected RecyclerView mRecyclerView;
    private SchemePresenter mPresenter;
    private SchemeAdapter mAdapter;

    @Override
    public int initLayout() {
        return R.layout.fragment_scheme;
    }

    @Override
    protected void initData(View rootView) {
        super.initData(rootView);
        mMultipleStatusView = rootView.findViewById(R.id.multiplestatusview);
        smartRefreshLayout = rootView.findViewById(R.id.smartRefreshLayout);
        mRecyclerView = rootView.findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        mPresenter = new SchemePresenter(this);
        showLoading();
        mPresenter.requestSchemeData();
        smartRefreshLayout.setOnRefreshListener(refresh -> mPresenter.requestSchemeData());

        ArrayList<SchemeBean.RecordsBean> recordsBeans = new ArrayList<>();
        mAdapter = new SchemeAdapter(recordsBeans);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemChildClickListener((adapter1, view, position) -> {
            List<SchemeBean.RecordsBean> recordsBean = adapter1.getData();
            Bundle bundle = new Bundle();
            bundle.putString("fangan_id", String.format(Locale.CHINA, "%d", recordsBean.get(position).fangan_id));
            ActivityUtils.init().start(getContext(), SchemeDetailActivity.class, "方案详情", bundle);
            return false;
        });

        tvBarTitle.setText("方案");
        ivBarSearch = rootView.findViewById(R.id.ivBarSearch);
        ivBarSearch.setVisibility(View.VISIBLE);
        ivBarSearch.setOnClickListener(v -> startActivity(new Intent(getContext(), SchemeSearchActivity.class)));
    }

    @Override
    public void setSchemeData(SchemeBean schemeData) {
        mAdapter.setNewData(schemeData.records);
    }
}
