package com.modiwu.mah.ui.adapter;

import android.text.TextUtils;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.modiwu.mah.R;
import com.modiwu.mah.mvp.model.bean.MeFangAnBean;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by Obl on 2018/2/6.
 * com.modiwu.mah.ui.adapter
 */

public class MeFanganAdapter extends BaseQuickAdapter<MeFangAnBean.RowsBean, BaseViewHolder> {
    public MeFanganAdapter(ArrayList<MeFangAnBean.RowsBean> data) {
        super(R.layout.adapter_me_fangan, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MeFangAnBean.RowsBean item) {
        String status = String.format(Locale.CHINA, "%s", item.order_status);
        helper.setText(R.id.tvOrderID, String.format(Locale.CHINA, "订单编号:%s", item.order_no))
                .setText(R.id.tvOrderStatue, status)
                .setText(R.id.tvOrder, String.format(Locale.CHINA, "%s", item.order_time))
                .setText(R.id.tvOrderMoney, String.format(Locale.CHINA, "￥%s", item.order_fee_yuan))
                .setText(R.id.tvOrderLocal, String.format(Locale.CHINA, "%s", item.order_desc))
                .setText(R.id.tvOrderTitle, String.format(Locale.CHINA, "【%s】", item.order_title))
                .setVisible(R.id.llStatus, !TextUtils.equals("待付款", status));
    }
}
