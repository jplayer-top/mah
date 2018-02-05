package com.modiwu.mah.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.vlayout.LayoutHelper;
import com.bumptech.glide.Glide;
import com.modiwu.mah.R;
import com.modiwu.mah.mvp.model.bean.DesignBean;
import com.modiwu.mah.utils.StringUtils;

import java.util.List;

import top.jplayer.baseprolibrary.glide.GlideUtils;
import top.jplayer.baseprolibrary.ui.adapter.VLayoutAdapter;

/**
 * Created by Obl on 2018/1/23.
 * com.modiwu.mah.ui.adapter
 */

public class DesignerDetialsAdapter extends VLayoutAdapter<RecyclerView.ViewHolder> {

    private List<DesignBean.DetailsBean> mDetailsBeans;

    public DesignerDetialsAdapter(Context context, LayoutHelper helper, int count, int itemType) {
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

        DesignBean.DetailsBean detailsBean = mDetailsBeans.get(position);

        Glide.with(context).load(detailsBean.img).apply(GlideUtils.init().options()).into(ivBodyPic);
        tvItemTitle.setText(StringUtils.getInstance().isNullable(detailsBean.title, "设计师"));
        tvItemBody.setText(StringUtils.getInstance().isNullable(detailsBean.subtitle, "设计师精心设计"));
    }

    public void setDetials(List<DesignBean.DetailsBean> fangAn) {
        mDetailsBeans = fangAn;
    }

}
