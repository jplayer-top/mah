package com.modiwu.mah.ui.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.modiwu.mah.R;
import com.modiwu.mah.mvp.model.bean.MeOrderBean;
import com.modiwu.mah.mvp.model.bean.ShopCartBean;

import java.util.List;
import java.util.Locale;

import top.jplayer.baseprolibrary.glide.GlideUtils;

/**
 * Created by Obl on 2018/2/8.
 * com.modiwu.mah.ui.adapter
 */

public class MeOrderInfoAdapter extends BaseQuickAdapter<MeOrderBean.RecordsBean.DetailBean, BaseViewHolder> {
    public MeOrderInfoAdapter(List<MeOrderBean.RecordsBean.DetailBean> orderLists) {
        super(R.layout.item_shop_order_me, orderLists);
    }

    @Override
    protected void convert(BaseViewHolder helper, MeOrderBean.RecordsBean.DetailBean item) {
        ImageView ivPic = helper.itemView.findViewById(R.id.ivShopPic);
        Glide.with(mContext).load(item.goods_thumb).apply(GlideUtils.init().options()).into(ivPic);
        helper.setText(R.id.tvTitle, item.goods_title)
                .setVisible(R.id.tvMoney, false)
                .setText(R.id.tvSubTitle, String.format(Locale.CHINA, "X %d", item.amount));
    }
}
