package com.modiwu.mah.ui.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.modiwu.mah.R;
import com.modiwu.mah.mvp.model.bean.ShopCartBean;

import java.util.List;

/**
 * Created by Administrator on 2018/1/28.
 * 购物车界面
 */

public class ShopCartAdapter extends BaseQuickAdapter<ShopCartBean, BaseViewHolder> {
    public ShopCartAdapter(List<ShopCartBean> data) {
        super(R.layout.adapter_shop_cart, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ShopCartBean item) {

    }
}
