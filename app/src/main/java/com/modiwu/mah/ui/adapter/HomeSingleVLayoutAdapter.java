package com.modiwu.mah.ui.adapter;

import android.content.Context;
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
import com.modiwu.mah.utils.StringUtils;

import java.util.List;

import top.jplayer.baseprolibrary.glide.GlideUtils;
import top.jplayer.baseprolibrary.ui.adapter.VLayoutAdapter;
import top.jplayer.baseprolibrary.utils.ScreenUtils;

/**
 * Created by Obl on 2018/1/23.
 * com.modiwu.mah.ui.adapter
 */

public class HomeSingleVLayoutAdapter extends VLayoutAdapter<RecyclerView.ViewHolder> {

    private List<HomeBean.GoodBean> mGoods;
    private ImageView mIvBodyPic;
    private TextView mTvTitle;
    private TextView mTvSubTitle;
    private TextView mTvPrice;

    public HomeSingleVLayoutAdapter(Context context, LayoutHelper helper, int count, int itemType) {
        super(context, helper, count, itemType);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = super.onCreateViewHolder(parent, viewType);
        View itemView = viewHolder.itemView;
        mIvBodyPic = itemView.findViewById(R.id.ivBodyPic);
        mTvTitle = itemView.findViewById(R.id.tvTitle);
        mTvSubTitle = itemView.findViewById(R.id.tvSubTitle);
        mTvPrice = itemView.findViewById(R.id.tvPrice);
        LinearLayout llTextView = itemView.findViewById(R.id.llTextView);
        int i = ScreenUtils.getWidthOfScreen(context, 10, 2);
        mIvBodyPic.getLayoutParams().width = i;
        llTextView.getLayoutParams().width = i;
        mIvBodyPic.getLayoutParams().height = i;
        return viewHolder;
    }

    @Override
    protected int addHolderLayout(ViewGroup parent, int viewType) {
        return R.layout.adapter_home_item_single;
    }

    @Override
    protected void onBindViewHolderWithOffset(RecyclerView.ViewHolder holder, int position, int offsetTotal) {
        HomeBean.GoodBean goodBean = mGoods.get(position);
        if (position == 1) {
            Glide.with(context).load(goodBean.imgUrl).apply(GlideUtils.init().options(R.drawable.home_item_single01)).into(mIvBodyPic);
        } else {
            Glide.with(context).load(goodBean.imgUrl).apply(GlideUtils.init().options()).into(mIvBodyPic);

        }
        mTvTitle.setText(StringUtils.getInstance().isNullable(goodBean.title, "整个家"));
        mTvSubTitle.setText(StringUtils.getInstance().isNullable(goodBean.subtitle, "整个家精心推荐"));
        mTvPrice.setText(StringUtils.getInstance().isNullable(goodBean.price, "￥0.00"));
    }

    public void setGoods(List<HomeBean.GoodBean> goods) {
        mGoods = goods;
    }
}
