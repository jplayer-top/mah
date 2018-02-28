package com.modiwu.mah.ui.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.vlayout.LayoutHelper;
import com.bumptech.glide.Glide;
import com.modiwu.mah.R;
import com.modiwu.mah.mvp.model.bean.HomeBean;
import com.modiwu.mah.ui.activity.DesignerActivity;
import com.modiwu.mah.ui.activity.SchemeDetailActivity;
import com.modiwu.mah.utils.StringUtils;

import java.util.List;

import top.jplayer.baseprolibrary.glide.GlideUtils;
import top.jplayer.baseprolibrary.ui.adapter.VLayoutAdapter;
import top.jplayer.baseprolibrary.utils.ActivityUtils;

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
        View itemView = holder.itemView;
        ImageView ivBodyPic = itemView.findViewById(R.id.ivBodyPic);
        TextView tvItemTitle = itemView.findViewById(R.id.tvItemTitle);
        TextView tvItemBody = itemView.findViewById(R.id.tvItemBody);
        if (beanType) {
            Bundle bundle = new Bundle();
            bundle.putString("designer_id", mSjs.get(position).navValue);
            HomeBean.SjsBean sjsBean = mSjs.get(position);
            Glide.with(context).load(sjsBean.imgUrl).apply(GlideUtils.init().options()).into(ivBodyPic);
            String designer = StringUtils.getInstance().isNullable(sjsBean.title, "设计师");
            tvItemTitle.setText(designer);
            tvItemBody.setText(StringUtils.getInstance().isNullable(sjsBean.subtitle, "设计师精心推荐"));
            itemView.setOnClickListener(v -> ActivityUtils.init().start(context, DesignerActivity.class, designer, bundle));
        } else {
            Bundle bundle = new Bundle();
            bundle.putString("fangan_id", mFangAn.get(position).navValue);
            itemView.setOnClickListener(v -> ActivityUtils.init().start(context, SchemeDetailActivity.class, mFangAn
                            .get(position).title,
                    bundle));
            HomeBean.FanganBean fanganBean = mFangAn.get(position);
            Glide.with(context).load(fanganBean.imgUrl).apply(GlideUtils.init().options()).into(ivBodyPic);
            tvItemTitle.setText(StringUtils.getInstance().isNullable(fanganBean.title, "整个家"));
            tvItemBody.setText(StringUtils.getInstance().isNullable(fanganBean.subtitle, "整个家精心推荐"));
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
