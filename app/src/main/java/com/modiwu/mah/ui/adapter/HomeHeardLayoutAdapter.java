package com.modiwu.mah.ui.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.alibaba.android.vlayout.LayoutHelper;
import com.bumptech.glide.Glide;
import com.modiwu.mah.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cn.bingoogolapple.bgabanner.BGABanner;
import top.jplayer.baseprolibrary.ui.adapter.VLayoutAdapter;

/**
 * Created by Obl on 2018/1/23.
 * com.modiwu.mah.ui.adapter
 */

public class HomeHeardLayoutAdapter extends VLayoutAdapter<RecyclerView.ViewHolder> {

    private final List<Integer> mIntegers;

    public HomeHeardLayoutAdapter(Context context, LayoutHelper helper, int count, int itemType) {
        super(context, helper, count, itemType);
        mIntegers = new ArrayList<>();
        mIntegers.add(R.drawable.pic_01);
        mIntegers.add(R.drawable.pic_02);
        mIntegers.add(R.drawable.pic_03);
        mIntegers.add(R.drawable.pic_04);
        mIntegers.add(R.drawable.pic_05);
    }

    @Override
    protected int addHolderLayout(ViewGroup parent, int viewType) {
        return R.layout.adapter_home_body_heard;
    }

    @Override
    protected void onBindViewHolderWithOffset(RecyclerView.ViewHolder holder, int position, int offsetTotal) {
        BGABanner bgaBanner = holder.itemView.findViewById(R.id.bgaBanner);
        bgaBanner.setAdapter(new BGABanner.Adapter<ImageView, Integer>() {
            @Override
            public void fillBannerItem(BGABanner banner, ImageView itemView, @Nullable Integer model, int position) {
                Glide.with(context).load(model).into(itemView);
            }

        });
        bgaBanner.setData(mIntegers, Arrays.asList("", "", "", "", ""));
    }
}
