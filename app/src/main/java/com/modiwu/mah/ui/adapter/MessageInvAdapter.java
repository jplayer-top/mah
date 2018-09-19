package com.modiwu.mah.ui.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.modiwu.mah.R;
import com.modiwu.mah.mvp.model.bean.InvListBean;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by Obl on 2018/8/30.
 * com.modiwu.mah.ui.adapter
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */

public class MessageInvAdapter extends BaseQuickAdapter<InvListBean.InvtsBean, BaseViewHolder> {
    public MessageInvAdapter(ArrayList<InvListBean.InvtsBean> strings) {
        super(R.layout.adapter_message_inv, strings);
    }

    @Override
    protected void convert(BaseViewHolder helper, InvListBean.InvtsBean item) {
        helper.setVisible(R.id.ivRedTip, "0".equals(item.status))
                .setText(R.id.tvTitle, String.format(Locale.CHINA, "%s邀您加入%s", item.invu_name, item.project_name))
                .setText(R.id.tvTime, item.ct);
    }
}
