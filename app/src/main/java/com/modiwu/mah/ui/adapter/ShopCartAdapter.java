package com.modiwu.mah.ui.adapter;

import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.modiwu.mah.R;

import java.util.List;

/**
 * Created by Administrator on 2018/1/28.
 * 购物车界面
 */

public class ShopCartAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    public ShopCartAdapter(List<String> data) {
        super(R.layout.adapter_shop_cart, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {

    }
}
