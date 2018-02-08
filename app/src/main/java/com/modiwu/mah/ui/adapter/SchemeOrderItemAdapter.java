package com.modiwu.mah.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.vlayout.LayoutHelper;
import com.bumptech.glide.Glide;
import com.modiwu.mah.R;
import com.modiwu.mah.mvp.model.bean.SchemeOrderCreateBean;
import com.modiwu.mah.mvp.model.event.MoneyChangeEvent;

import org.greenrobot.eventbus.EventBus;

import java.util.List;
import java.util.Locale;

import top.jplayer.baseprolibrary.glide.GlideUtils;
import top.jplayer.baseprolibrary.ui.adapter.VLayoutAdapter;

/**
 * Created by Obl on 2018/2/8.
 * com.modiwu.mah.ui.adapter
 */

public class SchemeOrderItemAdapter extends VLayoutAdapter<RecyclerView.ViewHolder> {
    private List<SchemeOrderCreateBean.RuanBean> mRuan;
    private List<SchemeOrderCreateBean.YingBean> mYing;
    private List<SchemeOrderCreateBean.SheBean> mShe;

    public SchemeOrderItemAdapter(Context context, LayoutHelper helper, int count, int itemType) {
        super(context, helper, count, itemType);
    }

    @Override
    protected int addHolderLayout(ViewGroup parent, int viewType) {
        return R.layout.adapter_scheme_order_item;
    }

    @Override
    protected void onBindViewHolderWithOffset(RecyclerView.ViewHolder holder, int position, int offsetTotal) {
        View itemView = holder.itemView;
        ImageView ivShopDel = itemView.findViewById(R.id.ivShopDel);
        TextView tvTitle = itemView.findViewById(R.id.tvTitle);
        TextView tvSubTitle = itemView.findViewById(R.id.tvSubTitle);
        CheckBox checkbox = itemView.findViewById(R.id.checkbox);
        CheckBox checkHeard = itemView.findViewById(R.id.checkHeard);
        checkHeard.setVisibility(position == 0 ? View.VISIBLE : View.GONE);
        checkHeard.setText(mTitle);
        if (type == SHE) {
            checkHeard.setChecked(true);
            checkHeard.setEnabled(false);

            checkbox.setChecked(true);
            checkbox.setEnabled(false);

            SchemeOrderCreateBean.SheBean sheBean = mShe.get(position);
            tvTitle.setText(sheBean.goods_title);
            tvSubTitle.setText(String.format(Locale.CHINA, "￥%s", sheBean.goods_price_yuan));
            Glide.with(context).load(sheBean.goods_thumb).apply(GlideUtils.init().options()).into(ivShopDel);
        } else if (type == YING) {
            checkHeard.setOnClickListener(v -> {
                yingCheck();
            });
            checkbox.setOnClickListener(v -> {
                mYing.get(position).isCheck = !mYing.get(position).isCheck;
                this.notifyItemChanged(position);
                EventBus.getDefault().post(new MoneyChangeEvent());
            });
            SchemeOrderCreateBean.YingBean yingBean = mYing.get(position);
            checkbox.setChecked(yingBean.isCheck);
            tvTitle.setText(yingBean.goods_title);
            tvSubTitle.setText(String.format(Locale.CHINA, "￥%s", yingBean.goods_price_yuan));
            Glide.with(context).load(yingBean.goods_thumb).apply(GlideUtils.init().options()).into(ivShopDel);
        } else {
            checkHeard.setOnClickListener(v -> {
                ruanCheck();
            });
            checkbox.setOnClickListener(v -> {
                mRuan.get(position).isCheck = !mRuan.get(position).isCheck;
                this.notifyItemChanged(position);
                EventBus.getDefault().post(new MoneyChangeEvent());
            });
            SchemeOrderCreateBean.RuanBean ruanBean = mRuan.get(position);
            checkbox.setChecked(ruanBean.isCheck);
            tvTitle.setText(ruanBean.goods_title);
            tvSubTitle.setText(String.format(Locale.CHINA, "￥%s", ruanBean.goods_price_yuan));
            Glide.with(context).load(ruanBean.goods_thumb).apply(GlideUtils.init().options()).into(ivShopDel);
        }
    }

    private void ruanCheck() {
        for (SchemeOrderCreateBean.RuanBean bean : mRuan) {
            bean.isCheck = !bean.isCheck;
        }
        notifyDataSetChanged();
        EventBus.getDefault().post(new MoneyChangeEvent());
    }

    private void yingCheck() {
        for (SchemeOrderCreateBean.YingBean bean : mYing) {
            bean.isCheck = !bean.isCheck;
        }
        notifyDataSetChanged();
        EventBus.getDefault().post(new MoneyChangeEvent());
    }

    public void setRuan(List<SchemeOrderCreateBean.RuanBean> ruan) {
        type = RUAN;
        mRuan = ruan;
        mTitle = "软装";

    }

    public void setYing(List<SchemeOrderCreateBean.YingBean> ying) {
        type = YING;
        mYing = ying;
        mTitle = "硬装";

    }

    private int type = 0;
    private final static int SHE = 0;
    private final static int YING = 1;
    private final static int RUAN = 2;
    private String mTitle = "";

    public void setShe(List<SchemeOrderCreateBean.SheBean> she) {
        type = SHE;
        mShe = she;
        mTitle = "设计方案";


    }
}
