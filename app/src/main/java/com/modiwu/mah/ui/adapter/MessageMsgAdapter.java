package com.modiwu.mah.ui.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.modiwu.mah.R;
import com.modiwu.mah.mvp.model.bean.MsgListBean;

import java.util.ArrayList;

/**
 * Created by Obl on 2018/8/30.
 * com.modiwu.mah.ui.adapter
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */

public class MessageMsgAdapter extends BaseQuickAdapter<MsgListBean.WmsgsBean, BaseViewHolder> {
    public MessageMsgAdapter(ArrayList<MsgListBean.WmsgsBean> strings) {
        super(R.layout.adapter_message_msg, strings);
    }

    @Override
    protected void convert(BaseViewHolder helper, MsgListBean.WmsgsBean item) {
        helper.setVisible(R.id.ivRedTip, false)
                .setText(R.id.tvTitle,item.title)
                .setText(R.id.tvSubTitle,item.subtitle)
                .setText(R.id.tvTime, item.ct);
    }
}
