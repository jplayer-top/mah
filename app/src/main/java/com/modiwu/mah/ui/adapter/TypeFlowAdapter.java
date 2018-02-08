package com.modiwu.mah.ui.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.modiwu.mah.R;
import com.modiwu.mah.mvp.model.bean.ShopGoodsInfoBean;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;

import java.util.List;

/**
 * Created by Obl on 2017/7/27.
 * com.ilanchuang.xiaoi.adapter
 */

public class TypeFlowAdapter extends TagAdapter<ShopGoodsInfoBean.AttrsBean.AttrInfoBean> {
    Context  mContext;

    public TypeFlowAdapter(List<ShopGoodsInfoBean.AttrsBean.AttrInfoBean> attrInfo, Context context) {
        super(attrInfo);
        this.mContext = context;
    }


    @Override
    public View getView(FlowLayout parent, int position, ShopGoodsInfoBean.AttrsBean.AttrInfoBean attrInfoBean) {
        TextView textView = (TextView) LayoutInflater.from(mContext).inflate(R.layout.item_shop_spec, parent, false);
        textView.setText(attrInfoBean.attr_value);
        return textView;
    }
}
