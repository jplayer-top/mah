package com.modiwu.mah.ui.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.modiwu.mah.R;
import com.modiwu.mah.mvp.model.bean.MeShouCangBean;
import com.modiwu.mah.utils.StringUtils;

import java.util.List;

import top.jplayer.baseprolibrary.glide.GlideUtils;

/**
 * Created by Obl on 2018/2/6.
 * com.modiwu.mah.ui.adapter
 */

public class MeShouCangAdapter extends BaseQuickAdapter<MeShouCangBean.RowsBean, BaseViewHolder> {
    public MeShouCangAdapter(List<MeShouCangBean.RowsBean> data) {
        super(R.layout.adapter_me_fangan, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MeShouCangBean.RowsBean item) {
        ImageView ivPic = helper.itemView.findViewById(R.id.ivFangAnPic);
        Glide.with(mContext).load(item.fangan_avatar).apply(GlideUtils.init().options()).into(ivPic);
        helper.setText(R.id.tvFangAnName, StringUtils.getInstance().isNullable(item.fangan_name, "整个家"))
                .addOnClickListener(R.id.tvFangAnDel);
    }
}
