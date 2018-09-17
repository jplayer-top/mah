package com.modiwu.mah.ui.adapter;

import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.modiwu.mah.R;
import com.modiwu.mah.mvp.model.bean.DecorateManBean;

import java.util.List;

import top.jplayer.baseprolibrary.glide.GlideUtils;

/**
 * Created by Obl on 2018/9/3.
 * com.modiwu.mah.ui.adapter
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */

public class DecorateProgressAdapter extends BaseQuickAdapter<DecorateManBean.TasksBean, BaseViewHolder> {
    public DecorateProgressAdapter(List<DecorateManBean.TasksBean> data) {
        super(R.layout.adapter_decorate_header_progress, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, DecorateManBean.TasksBean item) {
        helper.setText(R.id.tvTitle, item.seg_name)
                .setText(R.id.tvStatus, "0".equals(item.status) ? "未开始" : ("1".equals(item.status) ? "进行中" : "已完成"));
        ImageView ivStc = helper.itemView.findViewById(R.id.ivItemSrc);
        View clBgSel = helper.itemView.findViewById(R.id.clBgSel);
        if (item.isSel) {
            clBgSel.setBackgroundResource(R.drawable.decorate_select_progress);
        } else {
            clBgSel.setBackgroundColor(mContext.getResources().getColor(R.color.white));
        }
        Glide.with(mContext)
                .load(item.seg_icon)
                .apply(GlideUtils.init().options(R.drawable.decorate_diannuan))
                .into(ivStc);
    }
}
