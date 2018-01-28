package com.modiwu.mah.ui.Fragment;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.modiwu.mah.R;
import com.modiwu.mah.base.BaseFragment;
import com.modiwu.mah.ui.activity.SchemeDetailActivity;
import com.modiwu.mah.ui.activity.SchemeSearchActivity;
import com.modiwu.mah.ui.adapter.SchemeAdapter;

import java.util.ArrayList;

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
        super.initData(rootView);
        mMultipleStatusView = rootView.findViewById(R.id.multiplestatusview);
        mRecyclerView = rootView.findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        ArrayList<Integer> list = new ArrayList<>();
        list.add(R.drawable.pic_01);
        list.add(R.drawable.pic_02);
        list.add(R.drawable.pic_03);
        list.add(R.drawable.pic_04);
        list.add(R.drawable.pic_05);
        SchemeAdapter adapter = new SchemeAdapter(list);
        mRecyclerView.setAdapter(adapter);
        tvBarTitle.setText("方案");
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public boolean onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                startActivity(new Intent(getContext(), SchemeDetailActivity.class));
                return false;
            }
        });
        ivBarSearch = rootView.findViewById(R.id.ivBarSearch);
        ivBarSearch.setVisibility(View.VISIBLE);
        ivBarSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), SchemeSearchActivity.class));
            }
        });
    }
}
