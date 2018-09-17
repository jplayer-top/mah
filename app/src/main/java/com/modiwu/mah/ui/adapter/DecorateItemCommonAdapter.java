package com.modiwu.mah.ui.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.modiwu.mah.R;
import com.modiwu.mah.mvp.model.bean.ProInfoBean;

import java.util.List;

/**
 * Created by Obl on 2018/9/10.
 * com.modiwu.mah.ui.adapter
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */

public class DecorateItemCommonAdapter extends BaseQuickAdapter<ProInfoBean.CommonBean, BaseViewHolder> {
    public DecorateItemCommonAdapter(List<ProInfoBean.CommonBean> data) {
        super(R.layout.adapter_decorate_item_common, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ProInfoBean.CommonBean item) {
        helper.setText(R.id.ivItemSrc, item.user_name.substring(0, 1))
                .setText(R.id.tvTip, item.work_type)
                .setVisible(R.id.tvIsEdit, item.isEdit)
                .setText(R.id.tvName, item.user_name);
    }
}
