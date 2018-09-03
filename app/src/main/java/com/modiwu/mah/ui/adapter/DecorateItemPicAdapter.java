package com.modiwu.mah.ui.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.modiwu.mah.R;

import java.util.List;

/**
 * Created by Obl on 2018/9/3.
 * com.modiwu.mah.ui.adapter
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */

public class DecorateItemPicAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    public DecorateItemPicAdapter(List<String> data) {
        super(R.layout.adapter_item_decorate_pic, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {

    }
}
