package com.modiwu.mah.ui.Fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.modiwu.mah.R;
import com.modiwu.mah.base.BaseFragment;
import com.modiwu.mah.ui.adapter.SchemeHardAdapter;

import java.util.ArrayList;

import top.jplayer.baseprolibrary.ui.Fragment.SuperBaseFragment;

/**
 * Created by Administrator on 2018/1/23.
 * 硬装，软装Fragment
 */

public class SchemeHardFragment extends SuperBaseFragment {
    @Override
    protected void initData(View rootView) {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(R.drawable.pic_01);
        list.add(R.drawable.pic_02);
        list.add(R.drawable.pic_03);
        list.add(R.drawable.pic_04);
        list.add(R.drawable.pic_05);
        RecyclerView recyclerView = rootView.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(new SchemeHardAdapter(list));
    }

    @Override
    public int initLayout() {
        return R.layout.fragment_scheme_hard;
    }
}
