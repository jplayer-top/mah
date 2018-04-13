package com.modiwu.mah.ui.dialog;

import android.os.Bundle;
import android.support.annotation.NonNull;
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
import com.modiwu.mah.mvp.model.event.TouchAttrEvent;
import com.modiwu.mah.ui.adapter.ShopSpecAdapter;
import com.modiwu.mah.utils.StringUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import io.reactivex.Observable;
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
    public TextView tvOKSel;
    private RecyclerView mRecyclerView;
    private ShopSpecAdapter mAdapter;
    private int curItem = -1;
    private ImageView mIvTumb;
    private View viewFooter;
    private ShopGoodsInfoBean mDetialBean;

    @Override
    public void bindContent(ViewGroup viewGroup) {
        EventBus.getDefault().register(this);
        viewGroup.getLayoutParams().height = ScreenUtils.getScreenHeight() / 5 * 4;
        tvOnePrice = (TextView) viewGroup.findViewById(R.id.tvOnePrice);
        tvOKSel = (TextView) viewGroup.findViewById(R.id.tvOKSel);
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
                    String string = getAttrId();
                    ShodeDetialOKEvent event = new ShodeDetialOKEvent(string.substring(0, string.lastIndexOf(",")),
                            parseInt, tvOnePrice.getText().toString().replace("￥", ""), attrs_values);
                    EventBus.getDefault().post(event);
                    onDismissWithAnim();
                    break;
                }
            case R.id.ivNotSel:
                onDismissWithAnim();
                break;
        }
    }

    @NonNull
    private String getAttrId() {
        attr_ids.delete(0, attr_ids.length());
        List<Integer> list = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : mAdapter.mSelAttrMap.entrySet()) {
            list.add(entry.getValue());
        }
        Collections.sort(list);
        for (Integer integer : list) {
            attr_ids.append(integer);
            attr_ids.append(",");
        }
        return attr_ids.toString();
    }

    public void setData() {
        Bundle bundle = getArguments();
        mDetialBean = bundle.getParcelable("type");
        tvOnePrice.setText(String.format(Locale.CHINA, "￥%s", bundle.getString("price")));
        tvOKSel.setText(bundle.getString("tip"));
        if (mDetialBean != null) {
            Glide.with(getContext()).load(mDetialBean.goods.goods_thumb).apply(GlideUtils.init().options()).into(mIvTumb);
            tvGoodNum.setText(StringUtils.getInstance().isNullable(mDetialBean.goods.goods_title, "整个家"));
            if (mDetialBean.attrs != null) {
                attr_ids = new StringBuilder();
                mAdapter = new ShopSpecAdapter(getContext(),mDetialBean.attrs);
                mRecyclerView.setAdapter(mAdapter);
            }
            mAdapter.addFooterView(viewFooter);
        }

    }

    @Subscribe
    public void touchAttr(TouchAttrEvent event) {
        Map<Integer, Integer> selAttrMap = event.selAttrMap;
        if (mDetialBean != null && mDetialBean.attrs != null) {
            List<ShopGoodsInfoBean.AttrsBean> attrs = mDetialBean.attrs;
            if (attrs.size() == selAttrMap.size()) {
                String attrId = getAttrId();
                Observable.fromIterable(mDetialBean.specs)
                        .filter(specsBean -> attrId.equals(specsBean.attr_id + ","))
                        .subscribe(specsBean -> {
                            attrs_values = specsBean.attr_values;
                            tvOnePrice.setText(String.format(Locale.CHINA, "￥%s", specsBean.goods_best_price_yuan));
//                            tvGoodNum.setText(String.format(Locale.CHINA, "库存：%d", specsBean.goods_stock));
                        });
            }
        }
    }

    private String attrs_values = "";

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    public StringBuilder attr_ids;

    public static class ShodeDetialOKEvent {
        public String type;
        public int amount;
        public String attr_ids;
        public String attrs;
        public String price;

        public ShodeDetialOKEvent(String attr_ids, int amount, String price, String attrs) {
            this.attr_ids = attr_ids;
            this.amount = amount;
            this.price = price;
            this.attrs = attrs;
        }
    }

}
