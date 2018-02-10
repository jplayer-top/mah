package com.modiwu.mah.ui.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.modiwu.mah.R;
import com.modiwu.mah.base.BaseFragment;
import com.modiwu.mah.mvp.model.bean.ShopGoodsInfoBean;
import com.modiwu.mah.ui.activity.ShopDetailActivity;
import com.modiwu.mah.ui.adapter.ShopSpecAdapter;
import com.modiwu.mah.ui.dialog.FsShopDetialDialog;
import com.modiwu.mah.utils.StringUtils;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import top.jplayer.baseprolibrary.glide.GlideUtils;
import top.jplayer.baseprolibrary.utils.ToastUtils;

/**
 * Created by Obl on 2018/2/6.
 * com.modiwu.mah.ui.fragment
 */

public class ShopDetailAllFragment extends BaseFragment implements View.OnClickListener {
    @BindView(R.id.ivShopPic)
    ImageView mIvShopPic;
    @BindView(R.id.tvTitle)
    TextView mTvTitle;
    @BindView(R.id.tvCollection)
    TextView mTvCollection;
    @BindView(R.id.tvSubTitle)
    TextView mTvSubTitle;
    @BindView(R.id.tvMoney)
    TextView mTvMoney;
    private Unbinder mUnbinder;
    @BindView(R.id.recycleItem)
    RecyclerView mRecyclerView;
    @BindView(R.id.flClickDialog)
    FrameLayout flClickDialog;
    private ShopSpecAdapter mAdapter;
    private int curItem = -1;
    private ImageView mIvTumb;
    private View viewFooter;
    public TextView mTvCount;
    public TextView tvOnePrice;
    public TextView tvGoodNum;

    @Override
    public int initLayout() {
        viewFooter = View.inflate(getActivity(), R.layout.item_sel_type_foot, null);
        viewFooter.setVisibility(View.GONE);
        return R.layout.fragment_shop_detail_all;
    }

    ShopDetailActivity activity;

    @Override
    protected void initData(View rootView) {
        activity = (ShopDetailActivity) getActivity();
        ShopGoodsInfoBean infoBean = activity.bean;
        ShopGoodsInfoBean.GoodsBean goods = infoBean.goods;
        List<ShopGoodsInfoBean.SpecsBean> specs = infoBean.specs;
        mUnbinder = ButterKnife.bind(this, rootView);
        Glide.with(getContext()).load(goods.goods_thumb).apply(GlideUtils.init().options()).into(mIvShopPic);
        mTvTitle.setText(StringUtils.getInstance().isNullable(goods.goods_title, "精选单品"));
        mTvSubTitle.setText(String.format(Locale.CHINA, "库存：%d", goods.goods_stocks));
        mTvMoney.setText(String.format(Locale.CHINA, "￥ %s", goods.goods_price_yuan));
        mTvCount = viewFooter.findViewById(R.id.tvCount);
        viewFooter.findViewById(R.id.ivAddGoods).setOnClickListener(this);
        viewFooter.findViewById(R.id.ivDelGoods).setOnClickListener(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        if (infoBean.attrs != null) {
            attr_ids = new StringBuilder();
            mAdapter = new ShopSpecAdapter(getContext(), infoBean.attrs);
            mRecyclerView.setAdapter(mAdapter);
        }
        mAdapter.addFooterView(viewFooter);
        flClickDialog.setOnClickListener(view -> {
        });
        mTvCollection.setOnClickListener(v -> {
        });
    }

    public StringBuilder attr_ids;

    @Override
    public void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
    }

    @Override
    public void onClick(View v) {
        String count = mTvCount.getText().toString();
        int parseInt = Integer.parseInt(count);
        switch (v.getId()) {
            case R.id.ivDelGoods:
                if (parseInt != 1) {
                    mTvCount.setText(String.format(Locale.CHINA, "%d", parseInt - 1));
                }
                break;
            case R.id.ivAddGoods:
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
                    EventBus.getDefault().post(new FsShopDetialDialog.ShodeDetialOKEvent(string.substring(0, string.lastIndexOf(",")), parseInt));
                    break;
                }

        }
    }
}
