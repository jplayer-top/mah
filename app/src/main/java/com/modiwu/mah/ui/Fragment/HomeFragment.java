package com.modiwu.mah.ui.Fragment;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.modiwu.mah.R;
import com.modiwu.mah.base.BaseFragment;

import top.jplayer.baseprolibrary.ui.adapter.AdapterLinearLayout;

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
//        LinearLayoutManager manager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
//        ArrayList<HomeBean> homeBeans = new ArrayList<>();
//        homeBeans.add(new HomeBean("BODY_RECOMMEND"));
//        homeBeans.add(new HomeBean("BODY_SINGLE"));
//        homeBeans.add(new HomeBean("BODY_RECOMMEND"));
//        homeBeans.add(new HomeBean("BODY_ADV"));
//        homeBeans.add(new HomeBean("BODY_TOSHOP"));
//        mRecyclerView.setLayoutManager(manager);
//        mRecyclerView.setAdapter(new HomeAdapter(this, homeBeans));
        //取消嵌套自动滑动问题
//        recyclerview.setFocusableInTouchMode(false);
//        recyclerview.requestFocus();
        VirtualLayoutManager manager = new VirtualLayoutManager(getContext());
        mRecyclerView.setLayoutManager(manager);
        DelegateAdapter adapter = new DelegateAdapter(manager, true);
        adapter.addAdapter(new AdapterLinearLayout(getContext(),new LinearLayoutHelper()));
        mRecyclerView.setAdapter(adapter);

    }
}
