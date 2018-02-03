package com.modiwu.mah.ui.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.modiwu.mah.R;
import com.modiwu.mah.mvp.model.bean.CarpenterBean;
import com.modiwu.mah.utils.StringUtils;

import java.util.List;

import top.jplayer.baseprolibrary.glide.GlideUtils;
import top.jplayer.baseprolibrary.ui.SuperBaseActivity;

/**
 * Created by Obl on 2018/1/22.
 * com.modiwu.mah.ui.adapter
 */

public class CarpenterAdapter extends BaseQuickAdapter<CarpenterBean.RecordsBean, BaseViewHolder> {
    private SuperBaseActivity activity;

    public CarpenterAdapter(List<CarpenterBean.RecordsBean> data) {
        super(R.layout.adapter_scheme_body, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, CarpenterBean.RecordsBean bean) {
        ImageView ivBodyPic = baseViewHolder.convertView.findViewById(R.id.ivBodyPic);
        Glide.with(mContext).load(bean.designer_url).apply(GlideUtils.init().options()).into(ivBodyPic);
        baseViewHolder.setText(R.id.tvItemTitle,StringUtils.getInstance().isNullable(bean.designer_name, "匠心"));
        baseViewHolder.setText(R.id.tvItemBody,StringUtils.getInstance().isNullable(bean.designer_desc, "匠心设计"));
    }
}