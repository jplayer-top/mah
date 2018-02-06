package com.modiwu.mah.ui.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.modiwu.mah.R;
import com.modiwu.mah.mvp.model.bean.MeOrderBean;

import java.util.List;

/**
 * Created by Obl on 2018/2/6.
 * com.modiwu.mah.ui.adapter
 */

public class MeOrderAdapter extends BaseQuickAdapter<MeOrderBean.RecordsBean, BaseViewHolder> {
    public MeOrderAdapter(List<MeOrderBean.RecordsBean> data) {
        super(R.layout.adapter_me_order, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MeOrderBean.RecordsBean item) {

    }
}
