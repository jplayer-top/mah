package com.modiwu.mah.ui.Fragment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.modiwu.mah.R;
import com.modiwu.mah.mvp.model.bean.SchemeDetailBean;
import com.modiwu.mah.ui.activity.SchemeDetailActivity;
import com.modiwu.mah.ui.adapter.SchemeSingleAdapter;

import java.util.List;

import top.jplayer.baseprolibrary.ui.Fragment.SuperBaseFragment;

/**
 * Created by Administrator on 2018/1/23.
 * 硬装，软装Fragment
 */

public class SchemeSingleFragment extends SuperBaseFragment {


    SchemeDetailActivity mActivity;
    List<SchemeDetailBean.GoodsBean> mGoodsBeans;

    @Override
    protected void initData(View rootView) {
        mActivity = (SchemeDetailActivity) getActivity();
        mGoodsBeans = mActivity.mSchemeDetailBean.goods;
        RecyclerView recyclerView = rootView.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        recyclerView.setAdapter(new SchemeSingleAdapter(mGoodsBeans));
    }

    @Override
    public int initLayout() {
        return R.layout.fragment_scheme_hard;
    }

}
