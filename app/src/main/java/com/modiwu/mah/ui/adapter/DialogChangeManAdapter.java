package com.modiwu.mah.ui.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.modiwu.mah.R;
import com.modiwu.mah.mvp.model.bean.FilterBean;

import java.util.List;

/**
 * Created by Obl on 2018/8/30.
 * com.modiwu.mah.ui.adapter
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */

public class DialogChangeManAdapter extends BaseQuickAdapter<FilterBean, BaseViewHolder> {
    public DialogChangeManAdapter(List<FilterBean> list) {
        super(R.layout.adapter_change_man, list);
    }

    @Override
    protected void convert(BaseViewHolder helper, FilterBean item) {
        helper.setText(R.id.tvTitle, item.title)
                .setImageResource(R.id.ivSrc, item.res)
                .setTextColor(R.id.tvTitle, item.isSel ? top.jplayer.baseprolibrary.R.color.greenyellow : top.jplayer.baseprolibrary.R.color.white)
                .setVisible(R.id.ivBg, item.isSel);
    }
}
