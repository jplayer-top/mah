package com.modiwu.mah.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.alibaba.android.vlayout.LayoutHelper;
import com.bumptech.glide.Glide;
import com.modiwu.mah.R;

import top.jplayer.baseprolibrary.glide.GlideUtils;
import top.jplayer.baseprolibrary.ui.adapter.VLayoutAdapter;

/**
 * Created by Obl on 2018/1/23.
 * com.modiwu.mah.ui.adapter
 */

public class HomeToShopLayoutAdapter extends VLayoutAdapter<RecyclerView.ViewHolder> {
    public HomeToShopLayoutAdapter(Context context, LayoutHelper helper, int count, int itemType) {
        super(context, helper, count, itemType);
    }

    @Override
    protected int addHolderLayout(ViewGroup parent, int viewType) {
        return R.layout.adapter_home_body_toshop;
    }

    @Override
    protected void onBindViewHolderWithOffset(RecyclerView.ViewHolder holder, int position, int offsetTotal) {
        ImageView imageView = holder.itemView.findViewById(R.id.ivHeard);
        Glide.with(context).load(R.drawable.home_toshop).apply(GlideUtils.init().options()).into(imageView);
    }
}
