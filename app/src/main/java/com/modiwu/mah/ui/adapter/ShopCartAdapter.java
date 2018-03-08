package com.modiwu.mah.ui.adapter;

import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.modiwu.mah.R;
import com.modiwu.mah.mvp.model.bean.ShopCartBean;

import java.util.List;
import java.util.Locale;

import top.jplayer.baseprolibrary.glide.GlideUtils;
import top.jplayer.baseprolibrary.utils.ScreenUtils;
import top.jplayer.baseprolibrary.widgets.SlidingButtonView;

/**
 * Created by Administrator on 2018/1/28.
 * 购物车界面
 */

public class ShopCartAdapter extends BaseQuickAdapter<ShopCartBean, BaseViewHolder> implements SlidingButtonView
        .IonSlidingButtonListener {
    public ShopCartAdapter(List<ShopCartBean> data) {
        super(R.layout.adapter_shop_cart, data);
    }

    private SlidingButtonView mMenu = null;

    @Override
    protected void convert(BaseViewHolder helper, ShopCartBean item) {
        View viewContent = helper.itemView.findViewById(R.id.layout_content);
        SlidingButtonView slidingButtonView = helper.itemView.findViewById(R.id.slideButton);
        slidingButtonView.setSlidingButtonListener(this);
        if (menuIsOpen()) {
            slidingButtonView.closeMenu();
        }
        viewContent.getLayoutParams().width = ScreenUtils.getScreenWidth();

        ImageView ivPic = helper.itemView.findViewById(R.id.ivShopDel);
        Glide.with(mContext).load(item.getPic_url()).apply(GlideUtils.init().options()).into(ivPic);
        helper.setVisible(R.id.llContent, !item.isEdit)
                .addOnClickListener(R.id.checkbox)
                .setText(R.id.tvTitle, item.title)
                .setText(R.id.tvTab, item.sel_type)
                .setText(R.id.tvSubTitle, String.format(Locale.CHINA, "￥%s", item.price))
                .setText(R.id.tvEditNum, item.count)
                .addOnClickListener(R.id.tvAdd)
                .addOnClickListener(R.id.tv_delete)
                .addOnClickListener(R.id.tvRemove)
                .setText(R.id.tvNum, String.format(Locale.CHINA, "x%s", item.count))
                .setChecked(R.id.checkbox, item.isCheck);

    }

    @Override
    public void onMenuIsOpen(View view) {
        mMenu = (SlidingButtonView) view;
    }

    @Override
    public void onDownOrMove(SlidingButtonView slidingButtonView) {
        if (menuIsOpen()) {
            if (mMenu != slidingButtonView) {
                closeMenu();
            }
        }
    }

    /**
     * 关闭菜单
     */
    public void closeMenu() {
        mMenu.closeMenu();
        mMenu = null;

    }

    /**
     * 判断是否有菜单打开
     */
    public Boolean menuIsOpen() {
        if (mMenu != null) {
            return true;
        }
        return false;
    }
}
