package com.modiwu.mah.ui.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.modiwu.mah.R;
import com.modiwu.mah.mvp.model.bean.LocalListBean;
import com.modiwu.mah.utils.StringUtils;

import java.util.List;

/**
 * Created by Obl on 2018/2/7.
 * com.modiwu.mah.ui.adapter
 */

public class LocalListAdapter extends BaseQuickAdapter<LocalListBean.RecordsBean, BaseViewHolder> {
    public LocalListAdapter(List<LocalListBean.RecordsBean> data) {
        super(R.layout.adapter_local_list, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, LocalListBean.RecordsBean item) {
        String addr = StringUtils.getInstance().isNullable("", item.rp_province, item.rp_city,
                item.rp_area, item
                        .rp_addr);
        helper.setText(R.id.tvLocalName, item.rp_name)
                .setText(R.id.tvLocalPhone, item.rp_phone)
                .setText(R.id.tvPersonLocal, addr)
                .setChecked(R.id.checkbox, item.rp_default == 1)
                .addOnClickListener(R.id.localEdit)
                .addOnClickListener(R.id.localDel)
                .addOnClickListener(R.id.checkbox);
//        CheckBox checkBox = helper.itemView.findViewById(R.id.checkbox);
//        checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> EventBus.getDefault().post(new CheckEvent(helper.getLayoutPosition())));
    }
}
