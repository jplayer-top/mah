package com.modiwu.mah.ui.dialog;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;

import com.modiwu.mah.R;
import com.modiwu.mah.mvp.model.bean.FilterBean;
import com.modiwu.mah.ui.adapter.DialogChangeManAdapter;

import java.util.ArrayList;
import java.util.List;

import top.jplayer.baseprolibrary.widgets.dialog.BaseCustomDialog;

/**
 * Created by Obl on 2018/8/30.
 * com.modiwu.mah.ui.dialog
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */

public class DialogChangeMan extends BaseCustomDialog {

    private RecyclerView mRecyclerView;
    private List<FilterBean> mBeans;
    private DialogChangeManAdapter mAdapter;

    public DialogChangeMan(Context context) {
        super(context);
    }

    @Override
    protected void initView(View view) {
        String[] filterName = new String[]{"拆除\n施工", "安装\n施工", "添加"};
        int[] filterPng = new int[]{
                R.color.white,
                R.color.white,
                R.color.white};
        mBeans = new ArrayList<>();
        for (int i = 0; i < filterName.length; i++) {
            mBeans.add(new FilterBean(filterName[i], filterPng[i], i == 0));
        }
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        mAdapter = new DialogChangeManAdapter(mBeans);
        recyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener((adapter1, view1, position) -> {
            List<FilterBean> beanList = mAdapter.getData();
            for (int i = 0; i < beanList.size(); i++) {
                beanList.get(i).isSel = (i == position);
            }
            mAdapter.notifyDataSetChanged();
        });
    }

    @Override
    public int initLayout() {
        return R.layout.dialog_change_man;
    }

    @Override
    public int setWidth(int i) {
        return super.setWidth(9);
    }

    @Override
    public int setAnim() {
        return R.style.AnimFade;
    }

    @Override
    public int setGravity() {
        return Gravity.CENTER;
    }
}
