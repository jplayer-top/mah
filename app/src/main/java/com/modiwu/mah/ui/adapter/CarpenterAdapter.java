package com.modiwu.mah.ui.adapter;

import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.modiwu.mah.R;
import com.modiwu.mah.mvp.model.bean.CarpenterBean;

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
        TextView tvItemTitle = baseViewHolder.convertView.findViewById(R.id.tvItemTitle);
        TextView tvItemBody = baseViewHolder.convertView.findViewById(R.id.tvItemBody);
        Glide.with(mContext).load(bean.designer_url).apply(GlideUtils.init().options()).into(ivBodyPic);
        tvItemTitle.setText(R.string.carpenter_title);
        tvItemBody.setText(R.string.carpenter_body);
    }
}