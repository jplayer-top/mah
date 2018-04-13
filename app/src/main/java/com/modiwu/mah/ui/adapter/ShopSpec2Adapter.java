package com.modiwu.mah.ui.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.modiwu.mah.R;
import com.modiwu.mah.mvp.model.bean.ShopGoodsInfoBean;
import com.modiwu.mah.mvp.model.event.TouchAttrEvent;
import com.modiwu.mah.utils.StringUtils;
import com.zhy.view.flowlayout.TagFlowLayout;

import org.greenrobot.eventbus.EventBus;

import java.util.List;
import java.util.Map;

import top.jplayer.baseprolibrary.glide.GlideUtils;
import top.jplayer.baseprolibrary.utils.LogUtil;

/**
 * Created by Obl on 2018/4/13.
 * com.modiwu.mah.ui.adapter
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */

public class ShopSpec2Adapter extends BaseMultiItemQuickAdapter<ShopGoodsInfoBean.AttrsBean, BaseViewHolder> {
    private Context mContext;
    public Map<Integer, Integer> mSelAttrMap;
    public List<ShopGoodsInfoBean.DetailBean> mDetailBeans;
    public List<ShopGoodsInfoBean.AttrsBean> mAttrsBeans;
    private int mAttrSize;

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public ShopSpec2Adapter(Context context, List<ShopGoodsInfoBean.AttrsBean> data, List<ShopGoodsInfoBean
            .DetailBean> detailBeans) {
        super(data);
        this.mContext = context;
        addItemType(ShopGoodsInfoBean.AttrsBean.SPEC, R.layout.item_shop_sel_type);
        addItemType(ShopGoodsInfoBean.AttrsBean.DETAIL, R.layout.adapter_sepc_item);
        mAttrsBeans = data;
        if (detailBeans != null && detailBeans.size() > 0) {
            mAttrSize = data.size() - detailBeans.size();
            mDetailBeans = detailBeans;
        }
    }

    @Override
    protected void convert(final BaseViewHolder helper, final ShopGoodsInfoBean.AttrsBean item) {
        if (helper.getItemViewType() == ShopGoodsInfoBean.AttrsBean.SPEC) {
            helper.setText(R.id.tvFlowType, item.type_name);
            TagFlowLayout mFlowLayout = helper.itemView.findViewById(R.id.flowLayout);
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

        } else {
            int position = helper.getLayoutPosition();
            int curPos = position - mAttrSize;
            ImageView ivBodyPic = helper.convertView.findViewById(R.id.ivBodyPic);
            ShopGoodsInfoBean.DetailBean detailBean = mDetailBeans.get(curPos);
            Glide.with(mContext).load(detailBean.img).apply(GlideUtils.init().options()).into(ivBodyPic);
            helper.addOnClickListener(R.id.llScheme_body)
                    .setText(R.id.tvItemTitle, StringUtils.getInstance().isNullable(detailBean.title, "整个家"))
                    .setText(R.id.tvItemBody, StringUtils.getInstance().isNullable(detailBean.subtitle, "整个家精心推荐"));
        }
    }
}
