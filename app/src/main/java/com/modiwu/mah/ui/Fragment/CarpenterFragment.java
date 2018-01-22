package com.modiwu.mah.ui.Fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.modiwu.mah.R;
import com.modiwu.mah.base.BaseFragment;
import com.modiwu.mah.ui.adapter.CarpenterAdapter;

import java.util.ArrayList;

/**
 * Created by Obl on 2018/1/19.
 * com.modiwu.mah.ui.Fragment
 */

public class CarpenterFragment extends BaseFragment {
    protected RecyclerView mRecyclerView;

    @Override
    public int initLayout() {
        return R.layout.fragment_carpenter;
    }

    @Override
    protected void initData(View rootView) {
        mMultipleStatusView = rootView.findViewById(R.id.multiplestatusview);
        mRecyclerView = rootView.findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        ArrayList<Integer> list = new ArrayList<>();
        list.add(R.drawable.pic_06);
        list.add(R.drawable.pic_06);
        list.add(R.drawable.pic_06);
        list.add(R.drawable.pic_06);
        list.add(R.drawable.pic_06);
        mRecyclerView.setAdapter(new CarpenterAdapter(list));
    }
}
