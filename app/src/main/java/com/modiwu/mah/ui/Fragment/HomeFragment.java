package com.modiwu.mah.ui.Fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.modiwu.mah.R;
import com.modiwu.mah.base.BaseFragment;
import com.modiwu.mah.net.bean.HomeBean;
import com.modiwu.mah.ui.adapter.HomeAdapter;

import java.util.ArrayList;

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
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        ArrayList<HomeBean> homeBeans = new ArrayList<>();
        homeBeans.add(new HomeBean("BODY_RECOMMEND"));
        homeBeans.add(new HomeBean("BODY_ADV"));
        homeBeans.add(new HomeBean("BODY_TOSHOP"));
        mRecyclerView.setAdapter(new HomeAdapter(homeBeans));
    }
}
