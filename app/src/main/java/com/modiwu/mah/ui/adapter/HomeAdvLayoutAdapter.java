package com.modiwu.mah.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.alibaba.android.vlayout.LayoutHelper;
import com.bumptech.glide.Glide;
import com.modiwu.mah.R;
import com.modiwu.mah.mvp.model.bean.HomeBean;

import java.util.List;

import top.jplayer.baseprolibrary.glide.GlideUtils;
import top.jplayer.baseprolibrary.ui.adapter.VLayoutAdapter;

/**
 * Created by Obl on 2018/1/23.
 * com.modiwu.mah.ui.adapter
 */

public class HomeAdvLayoutAdapter extends VLayoutAdapter<RecyclerView.ViewHolder> {
    private List<HomeBean.ShouHouBean> mShouHou;

    public HomeAdvLayoutAdapter(Context context, LayoutHelper helper, int count, int itemType) {
        super(context, helper, count, itemType);
    }

    @Override
    protected int addHolderLayout(ViewGroup parent, int viewType) {
        return R.layout.adapter_home_body_adv;
    }

    @Override
    protected void onBindViewHolderWithOffset(RecyclerView.ViewHolder holder, int position, int offsetTotal) {
        ImageView ivShouHou = holder.itemView.findViewById(R.id.ivShouHou);
        Glide.with(context).load(mShouHou.get(position).imgUrl).apply(GlideUtils.init().options()).into(ivShouHou);

    }

    public void setShouHou(List<HomeBean.ShouHouBean> shouHou) {
        mShouHou = shouHou;
    }
}
