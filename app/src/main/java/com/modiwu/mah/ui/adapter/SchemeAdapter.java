package com.modiwu.mah.ui.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.modiwu.mah.R;
import com.modiwu.mah.mvp.model.bean.SchemeBean;
import com.modiwu.mah.utils.StringUtils;

import java.util.List;

import top.jplayer.baseprolibrary.glide.GlideUtils;
import top.jplayer.baseprolibrary.ui.SuperBaseActivity;

/**
 * Created by Administrator on 2018/1/21.
 * SchemeAdapter 方案页面适配器
 */

public class SchemeAdapter extends BaseQuickAdapter<SchemeBean.RecordsBean, BaseViewHolder> {
    private SuperBaseActivity activity;

    public SchemeAdapter(List<SchemeBean.RecordsBean> data) {
        super(R.layout.adapter_scheme_item, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, SchemeBean.RecordsBean bean) {
        ImageView ivBodyPic = baseViewHolder.convertView.findViewById(R.id.ivBodyPic);
        Glide.with(mContext).load(bean.fangan_avatar).apply(GlideUtils.init().options()).into(ivBodyPic);
        baseViewHolder.addOnClickListener(R.id.llScheme_body)
                .setText(R.id.tvItemTitle, StringUtils.getInstance().isNullable(bean.fangan_name, ""))
                .setText(R.id.tvItemBody, StringUtils.getInstance().isNullable(bean.fangan_desc, ""));
    }
}
