package com.modiwu.mah.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.modiwu.mah.R;
import com.modiwu.mah.mvp.model.bean.HomeBean;
import com.modiwu.mah.mvp.model.bean.SchemeDetailBean;

import java.util.List;

import top.jplayer.baseprolibrary.utils.ScreenUtils;

/**
 * Created by Obl on 2018/2/3.
 * com.modiwu.mah.ui.adapter
 */

public class SchemeSingleAdapter extends BaseQuickAdapter<SchemeDetailBean.GoodsBean, BaseViewHolder> {

    private List<HomeBean.GoodBean> mGoods;
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

    }
}