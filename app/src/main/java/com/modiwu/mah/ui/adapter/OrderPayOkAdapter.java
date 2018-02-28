package com.modiwu.mah.ui.adapter;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.modiwu.mah.R;
import com.modiwu.mah.mvp.model.bean.MeOrderBean;

import java.util.List;
import java.util.Locale;

import top.jplayer.baseprolibrary.utils.MoneyUtils;

/**
 * Created by Obl on 2018/2/27.
 * com.modiwu.mah.ui.adapter
 */

public class OrderPayOkAdapter extends BaseQuickAdapter<MeOrderBean.RecordsBean, BaseViewHolder> {
    public OrderPayOkAdapter(List<MeOrderBean.RecordsBean> data) {
        super(R.layout.adapter_me_order_whill_pay, data);
    }


    @Override
    protected void convert(BaseViewHolder helper, MeOrderBean.RecordsBean item) {
        helper.setText(R.id.tvOrderId, String.format(Locale.CHINA, "订单编号：%s", item.order_id))
                .setText(R.id.tvOrderTitle, item.order_title)
                .addOnClickListener(R.id.tvOrderToCancel)
                .setVisible(R.id.tvOrderToCancel, false)
                .addOnClickListener(R.id.tvOrderToPay)
                .setText(R.id.tvOrderToPay, "再次购买")
                .setText(R.id.tvStatus, "已收货")
                .setText(R.id.tvOrderMoney, String.format(Locale.CHINA, "合计：%s", MoneyUtils.formatIntF(item.actual_price)))
                .setText(R.id.tvOrderNum, String.format(Locale.CHINA, "共%d件", item.total_amount));
        RecyclerView recyclerView = helper.itemView.findViewById(R.id.recycleItem);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(new MeOrderInfoAdapter(item.detail));
    }
}
