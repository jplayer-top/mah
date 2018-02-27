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
 * Created by Administrator on 2018/1/28.
 * 购物车界面
 */

public class ShopCartAdapter extends BaseQuickAdapter<ShopCartBean, BaseViewHolder> {
    public ShopCartAdapter(List<ShopCartBean> data) {
        super(R.layout.adapter_shop_cart, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ShopCartBean item) {
        ImageView ivPic = helper.itemView.findViewById(R.id.ivShopDel);
        Glide.with(mContext).load(item.getPic_url()).apply(GlideUtils.init().options()).into(ivPic);
        helper.setVisible(R.id.llContent, !item.isEdit)
                .addOnClickListener(R.id.checkbox)
                .setText(R.id.tvTitle, item.title)
                .setText(R.id.tvSubTitle, String.format(Locale.CHINA, "￥%s", item.price))
                .setText(R.id.tvEditNum, item.count)
                .addOnClickListener(R.id.tvAdd)
                .addOnClickListener(R.id.tvRemove)
                .setText(R.id.tvNum, String.format(Locale.CHINA, "x%s", item.count))
                .setChecked(R.id.checkbox, item.isCheck)
                .setVisible(R.id.llEditNum, item.isEdit);

    }
}
