package com.modiwu.mah.ui.adapter;

import android.media.Image;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.modiwu.mah.R;
import com.modiwu.mah.mvp.model.bean.MeOrderBean;

import java.util.List;
import java.util.Locale;

/**
 * Created by Obl on 2018/2/6.
 * com.modiwu.mah.ui.adapter
 */

public class MeOrderAdapter extends BaseQuickAdapter<MeOrderBean.RecordsBean, BaseViewHolder> {
    public MeOrderAdapter(List<MeOrderBean.RecordsBean> data) {
        super(R.layout.adapter_me_order_info, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MeOrderBean.RecordsBean item) {
        helper.setText(R.id.tvOrderId, String.format(Locale.CHINA, "订单编号：%s", item.order_id))
                .setText(R.id.tvOrderTitle, item.order_title)
                .addOnClickListener(R.id.tvOrderToCancel)
                .addOnClickListener(R.id.tvOrderToPay)
                .setText(R.id.tvStatus, item.order_status == 0 ? "待支付" : "已支付");
        RecyclerView recyclerView = helper.itemView.findViewById(R.id.recycleItem);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(new MeOrderInfoAdapter(item.detail));
    }
}
