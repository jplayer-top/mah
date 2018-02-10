package com.modiwu.mah.ui.dialog;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.modiwu.mah.R;
import com.modiwu.mah.mvp.model.bean.ShopGoodsInfoBean;
import com.modiwu.mah.ui.adapter.ShopSpecAdapter;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import top.jplayer.baseprolibrary.glide.GlideUtils;
import top.jplayer.baseprolibrary.utils.ScreenUtils;
import top.jplayer.baseprolibrary.utils.ToastUtils;

/**
 * Created by Obl on 2017/5/27.
 * com.ilanchuang.xiaoi.views
 */

public class FsShopDetialDialog extends BottomTopDialogFragment implements View.OnClickListener {

    public TextView mTvCount;
    public TextView tvOnePrice;
    public TextView tvGoodNum;
    private RecyclerView mRecyclerView;
    private ShopSpecAdapter mAdapter;
    private int curItem = -1;
    private ImageView mIvTumb;
    private View viewFooter;

    @Override
    public void bindContent(ViewGroup viewGroup) {
        viewGroup.getLayoutParams().height = ScreenUtils.getScreenHeight() / 5 * 4;
        tvOnePrice = (TextView) viewGroup.findViewById(R.id.tvOnePrice);
        tvGoodNum = (TextView) viewGroup.findViewById(R.id.tvGoodNum);
        mRecyclerView = (RecyclerView) viewGroup.findViewById(R.id.recycleItem);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        viewFooter.findViewById(R.id.ivDelGoods).setOnClickListener(this);
        mTvCount = (TextView) viewFooter.findViewById(R.id.tvCount);
        viewFooter.findViewById(R.id.ivAddGoods).setOnClickListener(this);
        viewGroup.findViewById(R.id.tvOKSel).setOnClickListener(this);
        viewGroup.findViewById(R.id.ivNotSel).setOnClickListener(this);
        mIvTumb = (ImageView) viewGroup.findViewById(R.id.ivGoodsTumb);
        setData();
    }

    @Override
    public int getGravity() {
        return Gravity.BOTTOM;
    }

    @Override
    public int getContentLayoutId() {
        viewFooter = View.inflate(getActivity(), R.layout.item_sel_type_foot, null);
        return R.layout.shop_detial_dialog;
    }

    @Override
    public void onClick(View v) {
        String count = mTvCount.getText().toString();
        int parseInt = Integer.parseInt(count);
        switch (v.getId()) {
            case R.id.ivDelGoods:
                if (parseInt <= 1) {
                    ToastUtils.init().showInfoToast(getContext(), "客官，不能再少了");
                    return;
                }
                mTvCount.setText(String.format(Locale.CHINA, "%d", parseInt - 1));
                break;
            case R.id.ivAddGoods:
                if (parseInt >= 999) {
                    ToastUtils.init().showInfoToast(getContext(), "客官，分批买吧");
                    return;
                }
                mTvCount.setText(String.format(Locale.CHINA, "%d", parseInt + 1));
                break;
            case R.id.tvOKSel:
                if (mAdapter.mSelAttrMap.size() < mAdapter.getData().size()) {
                    ToastUtils.init().showInfoToast(getActivity(), "请选择商品规格");
                    break;
                } else {
                    List<Integer> list = new ArrayList<>();
                    for (Map.Entry<Integer, Integer> entry : mAdapter.mSelAttrMap.entrySet()) {
                        list.add(entry.getValue());
                    }
                    Collections.sort(list);
                    for (Integer integer : list) {
                        attr_ids.append(integer);
                        attr_ids.append(",");
                    }
                    String string = attr_ids.toString();
                    EventBus.getDefault().post(new ShodeDetialOKEvent(string.substring(0, string.lastIndexOf(",")), parseInt));
                    onDismissWithAnim();
                    break;
                }
            case R.id.ivNotSel:
                onDismissWithAnim();
                break;
        }
    }

    public void setData() {
        Bundle bundle = getArguments();
        final ShopGoodsInfoBean detialBean = bundle.getParcelable("type");
        tvOnePrice.setText(String.format(Locale.CHINA, "￥%s", bundle.getString("price")));
        tvGoodNum.setText(String.format(Locale.CHINA, "订单编号：%s", bundle.getString("num")));
        if (detialBean != null) {
            Glide.with(getContext()).load(detialBean.goods.goods_thumb).apply(GlideUtils.init().options()).into(mIvTumb);
            if (detialBean.attrs != null) {
                attr_ids = new StringBuilder();
                mAdapter = new ShopSpecAdapter(getContext(), detialBean.attrs);
                mRecyclerView.setAdapter(mAdapter);
            }
            mAdapter.addFooterView(viewFooter);
        }

    }

    public StringBuilder attr_ids;

    public static class ShodeDetialOKEvent {
        public String type;
        public int amount;
        public String attr_ids;

        public ShodeDetialOKEvent(String attr_ids, int amount) {
            this.attr_ids = attr_ids;
            this.amount = amount;
        }
    }

}
