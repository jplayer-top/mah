package com.modiwu.mah.ui.adapter;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.modiwu.mah.R;
import com.modiwu.mah.net.bean.HomeBean;

import java.util.List;

/**
 * Created by Obl on 2018/1/22.
 * com.modiwu.mah.ui.adapter
 */

public class HomeAdapter extends BaseMultiItemQuickAdapter<HomeBean, BaseViewHolder> {
    public HomeAdapter(List<HomeBean> data) {
        super(data);
        addItemType(HomeBean.BODY_RECOMMEND, R.layout.adapter_home_body_recommend);
        addItemType(HomeBean.BODY_ADV, R.layout.adapter_home_body_adv);
        addItemType(HomeBean.BODY_TOSHOP, R.layout.adapter_home_body_toshop);
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeBean item) {
        switch (helper.getItemViewType()) {
            case HomeBean.BODY_RECOMMEND:
                break;
        }
    }

}
