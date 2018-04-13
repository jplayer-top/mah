package com.modiwu.mah.ui.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.modiwu.mah.R;
import com.modiwu.mah.mvp.model.bean.SchemeDetailBean;
import com.modiwu.mah.ui.activity.SchemeDetailActivity;
import com.modiwu.mah.ui.adapter.SchemeAllAdapter;

import java.util.List;

import top.jplayer.baseprolibrary.ui.Fragment.SuperBaseFragment;
import top.jplayer.baseprolibrary.widgets.MultipleStatusView;

/**
 * Created by Administrator on 2018/1/23.
 * 硬装，软装Fragment
 */

public class SchemeAllFragment extends SuperBaseFragment {

    SchemeDetailActivity mActivity;
    List<SchemeDetailBean.ZhengBean> zhengList;

    @Override
    protected void initData(View rootView) {
        mActivity = (SchemeDetailActivity) getActivity();
        zhengList = mActivity.mSchemeDetailBean.zheng;

        RecyclerView recyclerView = rootView.findViewById(R.id.recyclerView);
        MultipleStatusView multiplestatusview = rootView.findViewById(R.id.multiplestatusview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

        if (zhengList != null && zhengList.size() > 0) {
            multiplestatusview.showContent();
            recyclerView.setAdapter(new SchemeAllAdapter(zhengList));
        } else {
            multiplestatusview.showEmpty();
        }
    }

    @Override
    public int initLayout() {
        return R.layout.fragment_scheme_hard;
    }

}
