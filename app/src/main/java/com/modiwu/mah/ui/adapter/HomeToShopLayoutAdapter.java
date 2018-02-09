package com.modiwu.mah.ui.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.alibaba.android.vlayout.LayoutHelper;
import com.bumptech.glide.Glide;
import com.modiwu.mah.R;
import com.modiwu.mah.mvp.model.bean.HomeBean;

import java.util.List;

import top.jplayer.baseprolibrary.glide.GlideUtils;
import top.jplayer.baseprolibrary.ui.WebFullScreenActivity;
import top.jplayer.baseprolibrary.ui.adapter.VLayoutAdapter;
import top.jplayer.baseprolibrary.utils.ActivityUtils;

/**
 * Created by Obl on 2018/1/23.
 * com.modiwu.mah.ui.adapter
 */

public class HomeToShopLayoutAdapter extends VLayoutAdapter<RecyclerView.ViewHolder> {
    private List<HomeBean.ShiGongBean> mShiGong;
    private List<HomeBean.ShiDianBean> mShiDian;
    private boolean beanType = false;

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
        if (beanType) {
            HomeBean.ShiDianBean shiDianBean = mShiDian.get(position);
            Glide.with(context).load(shiDianBean.imgUrl).apply(GlideUtils.init().options()).into(imageView);
            imageView.setOnClickListener(v -> {
                Bundle bundle = new Bundle();
                bundle.putString("url", mShiDian.get(position).navValue);
                ActivityUtils.init().start(context, WebFullScreenActivity.class, context.getString(R.string
                                .app_name),
                        bundle);
            });
        } else {
            HomeBean.ShiGongBean shiGongBean = mShiGong.get(position);
            Glide.with(context).load(shiGongBean.imgUrl).apply(GlideUtils.init().options()).into(imageView);
            imageView.setOnClickListener(v -> {
                Bundle bundle = new Bundle();
                bundle.putString("url", shiGongBean.navValue);
                ActivityUtils.init().start(context, WebFullScreenActivity.class, context.getString(R.string
                                .app_name),
                        bundle);
            });
        }
    }

    public void setShiGong(List<HomeBean.ShiGongBean> shiGong) {
        beanType = false;
        mShiGong = shiGong;
    }

    public void setShiDian(List<HomeBean.ShiDianBean> shiDian) {
        beanType = true;
        mShiDian = shiDian;
    }
}
