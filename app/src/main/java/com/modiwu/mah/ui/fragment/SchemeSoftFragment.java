package com.modiwu.mah.ui.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.modiwu.mah.R;
import com.modiwu.mah.mvp.model.bean.SchemeDetailBean;
import com.modiwu.mah.ui.activity.SchemeDetailActivity;
import com.modiwu.mah.ui.adapter.SchemeSoftAdapter;

import java.util.List;

import top.jplayer.baseprolibrary.ui.Fragment.SuperBaseFragment;

/**
 * Created by Administrator on 2018/1/23.
 * 硬装，软装Fragment
 */

public class SchemeSoftFragment extends SuperBaseFragment {



    SchemeDetailActivity mActivity;
    List<SchemeDetailBean.RuanBean> ruanList;

    @Override
    protected void initData(View rootView) {
        mActivity = (SchemeDetailActivity) getActivity();
        ruanList = mActivity.mSchemeDetailBean.ruan;

        RecyclerView recyclerView = rootView.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(new SchemeSoftAdapter(ruanList));
    }

    @Override
    public int initLayout() {
        return R.layout.fragment_scheme_hard;
    }

}
