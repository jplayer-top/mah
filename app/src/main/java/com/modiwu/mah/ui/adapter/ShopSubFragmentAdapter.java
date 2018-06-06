package com.modiwu.mah.ui.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.modiwu.mah.R;
import com.modiwu.mah.mvp.model.bean.ShopSubListBean;
import com.modiwu.mah.utils.StringUtils;

import java.util.List;
import java.util.Locale;

import top.jplayer.baseprolibrary.glide.GlideUtils;
import top.jplayer.baseprolibrary.utils.ScreenUtils;

/**
 * Created by Obl on 2018/2/5.
 * com.modiwu.mah.ui.adapter
 */

public class ShopSubFragmentAdapter extends BaseQuickAdapter<ShopSubListBean.RecordsBean, BaseViewHolder> {
    private ImageView mIvBodyPic;

    public ShopSubFragmentAdapter(List<ShopSubListBean.RecordsBean> data) {
        super(R.layout.adapter_home_item_single, data);
    }

    @Override
    protected BaseViewHolder onCreateDefViewHolder(ViewGroup parent, int viewType) {
        BaseViewHolder viewHolder = super.onCreateDefViewHolder(parent, viewType);
        View itemView = viewHolder.itemView;
        mIvBodyPic = itemView.findViewById(R.id.ivBodyPic);
        LinearLayout llTextView = itemView.findViewById(R.id.llTextView);
        int i = ScreenUtils.getWidthOfScreen(mContext, 2, 2);
        mIvBodyPic.getLayoutParams().width = i;
        llTextView.getLayoutParams().width = i;
        mIvBodyPic.getLayoutParams().height = i;
        return viewHolder;
    }

    @Override
    protected void convert(BaseViewHolder helper, ShopSubListBean.RecordsBean item) {
        ImageView ivBodyPic = helper.itemView.findViewById(R.id.ivBodyPic);
        Glide.with(mContext).load(item.goods_thumb)
                .apply(GlideUtils.init().options())
                .into(ivBodyPic);
        helper.setText(R.id.tvTitle, StringUtils.getInstance().isNullable(item.goods_title, ""))
                .setText(R.id.tvPrice, String.format(Locale.CHINA, "￥%s", item.goods_price_yuan))
                .setText(R.id.tvSubTitle, StringUtils.getInstance().isNullable(item.goods_subtitle, ""));
    }
}