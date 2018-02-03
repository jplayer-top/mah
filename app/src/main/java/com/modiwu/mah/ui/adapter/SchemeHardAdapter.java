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
 * Created by Administrator on 2018/1/23.
 * s
 */

public class SchemeHardAdapter extends BaseQuickAdapter<SchemeDetailBean.YingBean, BaseViewHolder> {
    public SchemeHardAdapter(List<SchemeDetailBean.YingBean> data) {
        super(R.layout.adapter_scheme_body, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, SchemeDetailBean.YingBean bean) {
        ImageView ivBodyPic = baseViewHolder.convertView.findViewById(R.id.ivBodyPic);
        Glide.with(mContext).load(bean.img).apply(GlideUtils.init().options()).into(ivBodyPic);
        baseViewHolder.addOnClickListener(R.id.llScheme_body)
                .setText(R.id.tvItemTitle, StringUtils.getInstance().isNullable(bean.title, "整个家"))
                .setText(R.id.tvItemBody, StringUtils.getInstance().isNullable(bean.subtitle, "整个家精心推荐"));
    }
}
