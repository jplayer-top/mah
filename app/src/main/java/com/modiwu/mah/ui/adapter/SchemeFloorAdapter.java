package com.modiwu.mah.ui.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.modiwu.mah.R;
import com.modiwu.mah.mvp.model.bean.SchemeDetailBean;
import com.modiwu.mah.utils.StringUtils;

import java.util.List;

import top.jplayer.baseprolibrary.glide.GlideUtils;

/**
 * Created by Obl on 2018/2/2.
 * com.modiwu.mah.ui.adapter
 */

public class SchemeFloorAdapter extends BaseQuickAdapter<SchemeDetailBean.LoupanBean, BaseViewHolder> {
    public SchemeFloorAdapter(List<SchemeDetailBean.LoupanBean> data) {
        super(R.layout.adapter_scheme_body, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, SchemeDetailBean.LoupanBean bean) {
        ImageView ivBodyPic = baseViewHolder.convertView.findViewById(R.id.ivBodyPic);
        Glide.with(mContext).load(bean.building_imgs).apply(GlideUtils.init().options()).into(ivBodyPic);
        baseViewHolder.addOnClickListener(R.id.llScheme_body)
                .setText(R.id.tvItemTitle, StringUtils.getInstance().isNullable(bean.building_name, ""));
    }
}
