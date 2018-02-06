package com.modiwu.mah.ui.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.modiwu.mah.R;
import com.modiwu.mah.mvp.model.bean.MeFangAnBean;

import java.util.ArrayList;

/**
 * Created by Obl on 2018/2/6.
 * com.modiwu.mah.ui.adapter
 */

public class MeFanganAdapter extends BaseQuickAdapter<MeFangAnBean, BaseViewHolder> {
    public MeFanganAdapter(ArrayList<MeFangAnBean> data) {
        super(R.layout.adapter_me_fangan, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MeFangAnBean item) {

    }
}
