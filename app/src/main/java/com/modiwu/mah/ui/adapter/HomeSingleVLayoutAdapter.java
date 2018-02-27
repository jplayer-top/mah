package com.modiwu.mah.ui.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.vlayout.LayoutHelper;
import com.bumptech.glide.Glide;
import com.modiwu.mah.R;
import com.modiwu.mah.mvp.model.bean.HomeBean;
import com.modiwu.mah.ui.activity.ShopDetailActivity;
import com.modiwu.mah.utils.StringUtils;

import java.util.List;
import java.util.Locale;

import top.jplayer.baseprolibrary.glide.GlideUtils;
import top.jplayer.baseprolibrary.ui.adapter.VLayoutAdapter;
import top.jplayer.baseprolibrary.utils.ActivityUtils;
import top.jplayer.baseprolibrary.utils.ScreenUtils;

/**
 * Created by Obl on 2018/1/23.
 * com.modiwu.mah.ui.adapter
 */

public class HomeSingleVLayoutAdapter extends VLayoutAdapter<RecyclerView.ViewHolder> {

    private List<HomeBean.GoodBean> mGoods;

    public HomeSingleVLayoutAdapter(Context context, LayoutHelper helper, int count, int itemType) {
        super(context, helper, count, itemType);
    }

    @Override
    protected int addHolderLayout(ViewGroup parent, int viewType) {
        return R.layout.adapter_home_item_single;
    }

    @Override
    protected void onBindViewHolderWithOffset(RecyclerView.ViewHolder holder, int position, int offsetTotal) {
        View mItemView = holder.itemView;
        LinearLayout llTextView = mItemView.findViewById(R.id.llTextView);
        ImageView  mIvBodyPic = mItemView.findViewById(R.id.ivBodyPic);
        TextView mTvTitle = mItemView.findViewById(R.id.tvTitle);
        TextView mTvSubTitle = mItemView.findViewById(R.id.tvSubTitle);
        TextView mTvPrice = mItemView.findViewById(R.id.tvPrice);

        int i = ScreenUtils.getWidthOfScreen(context, 1, 2);
        mIvBodyPic.getLayoutParams().width = i;
        mIvBodyPic.getLayoutParams().height = i;
        llTextView.getLayoutParams().width = i;

        HomeBean.GoodBean goodBean = mGoods.get(position);
        Glide.with(context).load(goodBean.imgUrl).apply(GlideUtils.init().options()).into(mIvBodyPic);
        mItemView.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putString("goods_id", String.format(Locale.CHINA, "%s", goodBean.navValue));
            ActivityUtils.init().start(context, ShopDetailActivity.class, goodBean.title, bundle);
        });
        mTvTitle.setText(StringUtils.getInstance().isNullable(goodBean.title, "整个家"));
        mTvSubTitle.setText(StringUtils.getInstance().isNullable(goodBean.subtitle, "整个家精心推荐"));
        mTvPrice.setText(String.format(Locale.CHINA, "￥%s", StringUtils.getInstance().isNullable(goodBean.price,
                "0.00")));

    }

    public void setGoods(List<HomeBean.GoodBean> goods) {
        mGoods = goods;
    }
}
