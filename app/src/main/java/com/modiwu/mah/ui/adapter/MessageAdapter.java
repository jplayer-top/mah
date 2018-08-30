package com.modiwu.mah.ui.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.modiwu.mah.R;

import java.util.ArrayList;

/**
 * Created by Obl on 2018/8/30.
 * com.modiwu.mah.ui.adapter
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */

public class MessageAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    public MessageAdapter(ArrayList<String> strings) {
        super(R.layout.adapter_message, strings);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {

    }
}
