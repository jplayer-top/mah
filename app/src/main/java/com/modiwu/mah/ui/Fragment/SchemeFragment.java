package com.modiwu.mah.ui.Fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.modiwu.mah.R;
import com.modiwu.mah.base.BaseFragment;
import com.modiwu.mah.ui.adapter.SchemeAdapter;

import java.util.ArrayList;

import top.jplayer.baseprolibrary.utils.LogUtil;

/**
 * Created by Obl on 2018/1/19.
 * com.modiwu.mah.ui.Fragment
 */

public class SchemeFragment extends BaseFragment {

    protected RecyclerView mRecyclerView;

    @Override
    public int initLayout() {
        return R.layout.fragment_scheme;
    }

    @Override
    protected void initData(View rootView) {
        mMultipleStatusView = rootView.findViewById(R.id.multiplestatusview);
        mRecyclerView = rootView.findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        ArrayList<Integer> list = new ArrayList<>();
        list.add(R.drawable.pic_01);
        list.add(R.drawable.pic_02);
        list.add(R.drawable.pic_03);
        list.add(R.drawable.pic_04);
        list.add(R.drawable.pic_05);
        mRecyclerView.setAdapter(new SchemeAdapter(list));
        tvBarTitle.setText("方案");
        ivBarSearch.setVisibility(View.VISIBLE);
    }
}
