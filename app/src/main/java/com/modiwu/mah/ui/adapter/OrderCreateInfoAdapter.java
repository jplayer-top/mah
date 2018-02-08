package com.modiwu.mah.ui.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.modiwu.mah.R;
import com.modiwu.mah.mvp.model.bean.ShopCartBean;

import java.util.List;
import java.util.Locale;

import top.jplayer.baseprolibrary.glide.GlideUtils;

/**
 * Created by Obl on 2018/2/8.
 * com.modiwu.mah.ui.adapter
 */

public class OrderCreateInfoAdapter extends BaseQuickAdapter<ShopCartBean, BaseViewHolder> {
    public OrderCreateInfoAdapter(List<ShopCartBean> orderLists) {
        super(R.layout.item_shop_order, orderLists);
    }

    @Override
    protected void convert(BaseViewHolder helper, ShopCartBean item) {
        ImageView ivPic = helper.itemView.findViewById(R.id.ivShopPic);
        Glide.with(mContext).load(item.pic_url).apply(GlideUtils.init().options()).into(ivPic);
        helper.setText(R.id.tvTitle, item.title)
                .setText(R.id.tvMoney, String.format(Locale.CHINA, "￥%s", item.price))
                .setText(R.id.tvSubTitle, String.format(Locale.CHINA, "X %s", item.count));
    }
}
