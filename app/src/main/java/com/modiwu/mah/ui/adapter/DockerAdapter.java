package com.modiwu.mah.ui.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.modiwu.mah.R;

import java.util.List;

/**
 * Created by Obl on 2018/1/23.
 * com.modiwu.mah.ui.adapter
 */

public class DockerAdapter extends BaseQuickAdapter<Integer, BaseViewHolder> {


    public DockerAdapter(List<Integer> data) {
        super(R.layout.adapter_home_item_single, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Integer item) {

    }
}
