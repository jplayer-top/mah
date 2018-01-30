package com.modiwu.mah.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alibaba.android.vlayout.LayoutHelper;
import com.modiwu.mah.R;

import top.jplayer.baseprolibrary.ui.adapter.VLayoutAdapter;
import top.jplayer.baseprolibrary.utils.LogUtil;

/**
 * Created by Obl on 2018/1/23.
 * com.modiwu.mah.ui.adapter
 */

public class HomeSectionLayoutAdapter extends VLayoutAdapter<RecyclerView.ViewHolder> {
    private String mTitle = "";

    public HomeSectionLayoutAdapter(Context context, LayoutHelper helper, int count, int itemType) {
        super(context, helper, count, itemType);
    }

    @Override

    protected int addHolderLayout(ViewGroup parent, int viewType) {
        return R.layout.adapter_home_body_section;
    }

    @Override
    protected void onBindViewHolderWithOffset(RecyclerView.ViewHolder holder, int position, int offsetTotal) {
        TextView tvTitle = holder.itemView.findViewById(R.id.tvTitle);
        TextView tvToMore = holder.itemView.findViewById(R.id.tvToMore);
        tvToMore.setOnClickListener(v -> LogUtil.e(position));
        tvTitle.setText(mTitle);
    }

    public void setTitle(String title) {
        mTitle = title;
    }
}
