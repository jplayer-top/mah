package com.modiwu.mah.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.modiwu.mah.R;
import com.modiwu.mah.mvp.model.bean.SchemeBean;
import com.modiwu.mah.mvp.model.bean.ShopGoodsInfoBean;
import com.modiwu.mah.utils.StringUtils;

import java.util.List;

import top.jplayer.baseprolibrary.glide.GlideUtils;
import top.jplayer.baseprolibrary.ui.SuperBaseActivity;

/**
 * Created by Administrator on 2018/2/6.
 */

public class ShopDetailAdpater extends BaseQuickAdapter<ShopGoodsInfoBean.FangansBean, BaseViewHolder> {
    private SuperBaseActivity activity;

    public ShopDetailAdpater(List<ShopGoodsInfoBean.FangansBean> data) {
        super(R.layout.adapter_scheme_body, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, ShopGoodsInfoBean.FangansBean bean) {
        ImageView ivBodyPic = baseViewHolder.convertView.findViewById(R.id.ivBodyPic);
        Glide.with(mContext).load(bean.fangan_avatar).apply(GlideUtils.init().options()).into(ivBodyPic);
        baseViewHolder.addOnClickListener(R.id.llScheme_body)
                .setText(R.id.tvItemTitle, StringUtils.getInstance().isNullable(bean.fangan_name, "整个家"))
                .setText(R.id.tvItemBody, StringUtils.getInstance().isNullable(bean.fangan_desc, "整个家精心推荐"));
    }
}
