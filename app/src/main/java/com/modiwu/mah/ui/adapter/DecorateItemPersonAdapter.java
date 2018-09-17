package com.modiwu.mah.ui.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.modiwu.mah.R;
import com.modiwu.mah.mvp.model.bean.DecorateManBean;

import java.util.List;

/**
 * Created by Obl on 2018/9/3.
 * com.modiwu.mah.ui.adapter
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */

class DecorateItemPersonAdapter extends BaseQuickAdapter<DecorateManBean.TasksBean.WorksBeanX.WorksBean, BaseViewHolder> {
    public DecorateItemPersonAdapter(List<DecorateManBean.TasksBean.WorksBeanX.WorksBean> dataPerson) {
        super(R.layout.adapter_item_decorate_person, dataPerson);
    }

    @Override
    protected void convert(BaseViewHolder helper, DecorateManBean.TasksBean.WorksBeanX.WorksBean item) {
        helper.setText(R.id.tvFirst, item.user_name.substring(0, 1))
        .setText(R.id.tvName,item.user_name)
        .setText(R.id.tvTip,item.work_type);
    }
}
