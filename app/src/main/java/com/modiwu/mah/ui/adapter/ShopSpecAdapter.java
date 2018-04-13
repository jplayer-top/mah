package com.modiwu.mah.ui.adapter;

import android.content.Context;
import android.util.ArrayMap;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.modiwu.mah.R;
import com.modiwu.mah.mvp.model.bean.ShopGoodsInfoBean;
import com.modiwu.mah.mvp.model.event.TouchAttrEvent;
import com.zhy.view.flowlayout.TagFlowLayout;

import org.greenrobot.eventbus.EventBus;

import java.util.List;
import java.util.Map;

import top.jplayer.baseprolibrary.utils.LogUtil;

/**
 * Created by Obl on 2017/7/6.
 * com.ilanchuang.xiaoi.adapter
 */

public class ShopSpecAdapter extends BaseQuickAdapter<ShopGoodsInfoBean.AttrsBean, BaseViewHolder> {
    private Context mContext;
    public Map<Integer, Integer> mSelAttrMap;

    public ShopSpecAdapter(Context context, List<ShopGoodsInfoBean.AttrsBean> data) {
        super(R.layout.item_shop_sel_type, data);
        this.mContext = context;
        mSelAttrMap = new ArrayMap<>();
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return super.onCreateViewHolder(parent, viewType);
    }

    @Override
    protected void convert(final BaseViewHolder helper, final ShopGoodsInfoBean.AttrsBean item) {
        helper.setText(R.id.tvFlowType, item.type_name);
        TagFlowLayout mFlowLayout = (TagFlowLayout) helper.itemView.findViewById(R.id.flowLayout);
        mFlowLayout.setAdapter(new TypeFlowAdapter(item.attrInfo, mContext));
        mFlowLayout.setOnTagClickListener((view, position, parent) -> {
            int layoutPosition = helper.getLayoutPosition();
            if (mSelAttrMap.containsKey(layoutPosition) && mSelAttrMap.get(layoutPosition) == item.attrInfo.get(position).attr_id) {
                mSelAttrMap.remove(layoutPosition);
            } else {
                mSelAttrMap.put(layoutPosition, item.attrInfo.get(position).attr_id);
                LogUtil.e(mSelAttrMap);
                EventBus.getDefault().post(new TouchAttrEvent(mSelAttrMap));
            }
            return true;
        });
    }
}
