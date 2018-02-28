package com.modiwu.mah.ui.fragment;

import android.support.v7.widget.RecyclerView;

import com.modiwu.mah.mvp.model.bean.MeOrderBean;
import com.modiwu.mah.ui.adapter.OrderWillPayAdapter;

/**
 * Created by Obl on 2018/2/27.
 * com.modiwu.mah.ui.fragment
 */

public class OrderWillPayFragment extends OrderBaseFragment {


    @Override
    public String getStatus() {
        return "0";
    }

    @Override
    public RecyclerView.Adapter bindAdapter(MeOrderBean meOrderBean) {
        return new OrderWillPayAdapter(meOrderBean.records);
    }
}
