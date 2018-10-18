package com.modiwu.mah.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.modiwu.mah.R;
import com.modiwu.mah.mvp.model.bean.SchemeDetailBean;
import com.modiwu.mah.ui.activity.SchemeDetailActivity;
import com.modiwu.mah.ui.adapter.SchemeAllAdapter;

import java.util.List;

import top.jplayer.baseprolibrary.ui.Fragment.SuperBaseFragment;
import top.jplayer.baseprolibrary.ui.WebFullScreenActivity;
import top.jplayer.baseprolibrary.utils.ActivityUtils;
import top.jplayer.baseprolibrary.widgets.MultipleStatusView;

/**
 * Created by Administrator on 2018/1/23.
 * 硬装，软装Fragment
 */

public class SchemeAllFragment extends SuperBaseFragment {

    SchemeDetailActivity mActivity;
    List<SchemeDetailBean.ZhengBean> zhengList;
    private SchemeAllAdapter mAdapter;

    @Override
    protected void initData(View rootView) {
        mActivity = (SchemeDetailActivity) getActivity();
        zhengList = mActivity.mSchemeDetailBean.zheng;

        RecyclerView recyclerView = rootView.findViewById(R.id.recyclerView);
        MultipleStatusView multiplestatusview = rootView.findViewById(R.id.multiplestatusview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

        if (zhengList != null && zhengList.size() > 0) {
            multiplestatusview.showContent();
            mAdapter = new SchemeAllAdapter(zhengList);
            mAdapter.setOnItemChildClickListener((adapter, view, position) -> {
                SchemeDetailBean.ZhengBean zhengBean = mAdapter.getData().get(position);
                if (zhengBean.link_url != null && !"".equals(zhengBean.link_url)) {
                    Bundle bundle = new Bundle();
                    bundle.putString("url", zhengBean.link_url);
                    ActivityUtils.init().start(this.mActivity, WebFullScreenActivity.class, "整个家", bundle);
                }
                return false;
            });
            recyclerView.setAdapter(mAdapter);
        } else {
            multiplestatusview.showEmpty();
        }
    }

    @Override
    public int initLayout() {
        return R.layout.fragment_scheme_hard;
    }

}
