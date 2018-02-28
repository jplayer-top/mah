package com.modiwu.mah.ui.fragment;

import android.support.v7.widget.RecyclerView;

import com.modiwu.mah.R;
import com.modiwu.mah.mvp.model.bean.MeOrderBean;
import com.modiwu.mah.ui.adapter.OrderIsPayAdapter;

import top.jplayer.baseprolibrary.mvp.model.bean.BaseBean;
import top.jplayer.baseprolibrary.net.SampleShowDialogObserver;

/**
 * Created by Obl on 2018/2/27.
 * com.modiwu.mah.ui.fragment
 */

public class OrderIsPayFragment extends OrderBaseFragment {


    private OrderIsPayAdapter mAdapter;

    @Override
    public String getStatus() {
        return "1";
    }

    @Override
    public RecyclerView.Adapter bindAdapter(MeOrderBean meOrderBean) {
        mAdapter = new OrderIsPayAdapter(meOrderBean.records);
        mAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            MeOrderBean.RecordsBean bean = mAdapter.getData().get(position);
            switch (view.getId()) {
                case R.id.tvOrderToPay:
                    mModel.requestOrderOkBean(bean.order_id)
                            .subscribe(new SampleShowDialogObserver<BaseBean>(getContext()) {
                                @Override
                                protected void onSuccess(BaseBean baseBean) throws Exception {
                                    requestDate();
                                }
                            });
                    break;
            }
            return false;
        });
        return mAdapter;
    }
}
