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

public class HomeRecommendLayoutAdapter extends VLayoutAdapter<RecyclerView.ViewHolder> {

    private List<HomeBean.FanganBean> mFangAn;
    private List<HomeBean.SjsBean> mSjs;
    private boolean beanType = false;

    public HomeRecommendLayoutAdapter(Context context, LayoutHelper helper, int count, int itemType) {
        super(context, helper, count, itemType);
    }

    @Override
    protected int addHolderLayout(ViewGroup parent, int viewType) {
        return R.layout.adapter_home_body_recommend;
    }

    @Override
    protected void onBindViewHolderWithOffset(RecyclerView.ViewHolder holder, int position, int offsetTotal) {
        ImageView ivBodyPic = holder.itemView.findViewById(R.id.ivBodyPic);
        if (beanType) {
            Glide.with(context).load(mSjs.get(position)).apply(GlideUtils.init().options()).into(ivBodyPic);
        } else {
            Glide.with(context).load(mFangAn.get(position)).apply(GlideUtils.init().options()).into(ivBodyPic);
        }
    }

    public void setFangAn(List<HomeBean.FanganBean> fangAn) {
        beanType = false;
        mFangAn = fangAn;
    }

    public void setSjs(List<HomeBean.SjsBean> sjs) {
        beanType = true;
        mSjs = sjs;
    }
}
