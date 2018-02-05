package com.modiwu.mah.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.modiwu.mah.R;
import com.modiwu.mah.mvp.model.bean.SchemeDetailBean;
import com.modiwu.mah.utils.StringUtils;

import java.util.List;
import java.util.Locale;

import top.jplayer.baseprolibrary.glide.GlideUtils;
import top.jplayer.baseprolibrary.utils.ScreenUtils;

/**
 * Created by Obl on 2018/2/3.
 * com.modiwu.mah.ui.adapter
 */

public class SchemeSingleAdapter extends BaseQuickAdapter<SchemeDetailBean.GoodsBean, BaseViewHolder> {

    private ImageView mIvBodyPic;
    private TextView mTvTitle;
    private TextView mTvSubTitle;
    private TextView mTvPrice;

    public SchemeSingleAdapter(List<SchemeDetailBean.GoodsBean> data) {
        super(R.layout.adapter_home_item_single, data);
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = super.onCreateViewHolder(parent, viewType);
        View itemView = viewHolder.itemView;
        mIvBodyPic = itemView.findViewById(R.id.ivBodyPic);
        mTvTitle = itemView.findViewById(R.id.tvTitle);
        mTvSubTitle = itemView.findViewById(R.id.tvSubTitle);
        mTvPrice = itemView.findViewById(R.id.tvPrice);
        LinearLayout llTextView = itemView.findViewById(R.id.llTextView);
        int i = ScreenUtils.getWidthOfScreen(mContext, 10, 2);
        mIvBodyPic.getLayoutParams().width = i;
        llTextView.getLayoutParams().width = i;
        mIvBodyPic.getLayoutParams().height = i;
        return super.onCreateViewHolder(parent, viewType);
    }

    @Override
    protected void convert(BaseViewHolder helper, SchemeDetailBean.GoodsBean item) {
        Glide.with(mContext).load(item.goods_img)
                .apply(GlideUtils.init().options(R.drawable.home_item_single01))
                .into(mIvBodyPic);
        helper.setText(R.id.tvTitle, StringUtils.getInstance().isNullable(item.goods_title, "整个家"))
                .setText(R.id.tvPrice, String.format(Locale.CHINA, "￥%s", item.goods_price + ""))
                .setText(R.id.tvSubTitle, StringUtils.getInstance().isNullable(item.goods_subtitle, "精心推荐"));
    }
}