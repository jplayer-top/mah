package com.modiwu.mah.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.modiwu.mah.R;
import com.modiwu.mah.mvp.model.bean.MeOrderBean;
import com.modiwu.mah.ui.activity.ShopPayActivity;
import com.modiwu.mah.ui.adapter.OrderWillPayAdapter;

import java.util.Locale;

import top.jplayer.baseprolibrary.mvp.model.bean.BaseBean;
import top.jplayer.baseprolibrary.net.SampleShowDialogObserver;
import top.jplayer.baseprolibrary.utils.ActivityUtils;

/**
 * Created by Obl on 2018/2/27.
 * com.modiwu.mah.ui.fragment
 */

public class OrderWillPayFragment extends OrderBaseFragment {


    private OrderWillPayAdapter mAdapter;

    @Override
    public String getStatus() {
        return "0";
    }

    @Override
    public RecyclerView.Adapter bindAdapter(MeOrderBean meOrderBean) {
        mAdapter = new OrderWillPayAdapter(meOrderBean.records);
        mAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            MeOrderBean.RecordsBean bean = mAdapter.getData().get(position);
            switch (view.getId()) {
                case R.id.tvOrderToCancel:
                    mModel.requestOrderDelBean(bean.order_id, "z")
                            .subscribe(new SampleShowDialogObserver<BaseBean>(getContext()) {
                                @Override
                                protected void onSuccess(BaseBean baseBean) throws Exception {
                                    requestDate();
                                }
                            });
                    break;
                case R.id.tvOrderToPay:
                    Bundle bundle = new Bundle();
                    bundle.putString("totalPrice", String.format(Locale.CHINA, "￥%.2f", bean.actual_price / 100f));
                    bundle.putString("orderId", bean.order_id);
                    bundle.putString("type", "订单");
                    ActivityUtils.init().start(getContext(), ShopPayActivity.class, "支付", bundle);
                    break;
            }
            return false;
        });
        return mAdapter;
    }
}
