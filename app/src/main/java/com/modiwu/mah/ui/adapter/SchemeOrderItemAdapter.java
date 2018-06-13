package com.modiwu.mah.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.vlayout.LayoutHelper;
import com.bumptech.glide.Glide;
import com.modiwu.mah.R;
import com.modiwu.mah.mvp.model.bean.SchemeOrderCreateBean;
import com.modiwu.mah.mvp.model.event.MoneyChangeEvent;
import com.modiwu.mah.utils.StringUtils;

import org.greenrobot.eventbus.EventBus;

import java.util.List;
import java.util.Locale;

import top.jplayer.baseprolibrary.glide.GlideUtils;
import top.jplayer.baseprolibrary.ui.adapter.VLayoutAdapter;
import top.jplayer.baseprolibrary.utils.ToastUtils;

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

        LinearLayout llEditNum = itemView.findViewById(R.id.llEditNum);
        TextView tvRemove = itemView.findViewById(R.id.tvRemove);
        TextView tvEditNum = itemView.findViewById(R.id.tvEditNum);
        TextView tvAdd = itemView.findViewById(R.id.tvAdd);
        TextView tvAttrs = itemView.findViewById(R.id.tvAttrs);
        TextView tvNum = itemView.findViewById(R.id.tvNum);
        ImageView ivShopDel = itemView.findViewById(R.id.ivShopDel);
        TextView tvTitle = itemView.findViewById(R.id.tvTitle);
        TextView tvSubTitle = itemView.findViewById(R.id.tvSubTitle);
        CheckBox checkbox = itemView.findViewById(R.id.checkbox);
        CheckBox checkHeard = itemView.findViewById(R.id.checkHeard);
        checkHeard.setVisibility(position == 0 ? View.VISIBLE : View.GONE);
        checkHeard.setText(mTitle);
        if (type == SHE) {
            llEditNum.setVisibility(View.GONE);

            checkHeard.setOnClickListener(buttonView -> sheCheck());
            checkbox.setOnClickListener(v -> {
                mShe.get(position).isCheck = !mShe.get(position).isCheck;
                this.notifyItemChanged(position);
                EventBus.getDefault().post(new MoneyChangeEvent());
            });


            SchemeOrderCreateBean.SheBean sheBean = mShe.get(position);
            checkbox.setChecked(sheBean.isCheck);

            tvNum.setText(String.format(Locale.CHINA, "x%d", sheBean.goods_num));
            tvTitle.setText(sheBean.goods_title);
            tvAttrs.setText(sheBean.attr_name);
            tvSubTitle.setText(String.format(Locale.CHINA, "￥%s", StringUtils.getInstance().isNullable(sheBean
                    .goods_price_yuan, "0")));
            Glide.with(context).load(sheBean.goods_thumb).apply(GlideUtils.init().options()).into(ivShopDel);
        } else if (type == YING) {
            llEditNum.setVisibility(View.GONE);

            checkHeard.setOnClickListener(buttonView -> yingCheck());
            checkbox.setOnClickListener(v -> {
                mYing.get(position).isCheck = !mYing.get(position).isCheck;
                this.notifyItemChanged(position);
                EventBus.getDefault().post(new MoneyChangeEvent());
            });

            SchemeOrderCreateBean.YingBean yingBean = mYing.get(position);
            checkbox.setChecked(yingBean.isCheck);
            tvTitle.setText(yingBean.goods_title);
            tvAttrs.setText(yingBean.attr_name);

            tvNum.setText(String.format(Locale.CHINA, "x%d", yingBean.goods_num));
            tvSubTitle.setText(String.format(Locale.CHINA, "￥%s", StringUtils.getInstance().isNullable(yingBean
                    .goods_price_yuan, "0")));
            Glide.with(context).load(yingBean.goods_thumb).apply(GlideUtils.init().options()).into(ivShopDel);
        } else {
            llEditNum.setVisibility(View.VISIBLE);
            checkHeard.setEnabled(false);
            checkbox.setEnabled(false);
            checkHeard.setOnClickListener(buttonView -> ruanCheck());
            checkbox.setOnClickListener(v -> {
                mRuan.get(position).isCheck = !mRuan.get(position).isCheck;
                this.notifyItemChanged(position);
                EventBus.getDefault().post(new MoneyChangeEvent());
            });

            SchemeOrderCreateBean.RuanBean ruanBean = mRuan.get(position);
            checkHeard.setChecked(ruanBean.isHeardCheck);
            int goods_num = ruanBean.goods_num;
            tvEditNum.setText(String.valueOf(goods_num));

            tvAdd.setOnClickListener(v -> {
                if (goods_num >= 999) {
                    ToastUtils.init().showInfoToast(context, "客官，分批买吧");
                    return;
                }
                numChange(position, ruanBean, goods_num, 1);
            });
            tvRemove.setOnClickListener(v -> {
                if (goods_num <= 1) {
                    ToastUtils.init().showInfoToast(context, "客官，不能再少了");
                    return;
                }
                numChange(position, ruanBean, goods_num, -1);
            });
            checkbox.setChecked(ruanBean.isCheck);
            tvTitle.setText(ruanBean.goods_title);
            tvAttrs.setText(ruanBean.attr_name);
            tvSubTitle.setText(String.format(Locale.CHINA, "￥%s", StringUtils.getInstance().isNullable(ruanBean
                    .goods_price_yuan, "0")));
            Glide.with(context).load(ruanBean.goods_thumb).apply(GlideUtils.init().options()).into(ivShopDel);
        }
    }

    private void numChange(int position, SchemeOrderCreateBean.RuanBean ruanBean, int goods_num, int value) {
        int a = goods_num;
        a += value;
        ruanBean.goods_num = a;
        this.notifyItemChanged(position);
        EventBus.getDefault().post(new MoneyChangeEvent());
    }

    private void ruanCheck() {
        boolean isHeardCheck = mRuan.get(0).isHeardCheck;
        for (SchemeOrderCreateBean.RuanBean bean : mRuan) {
            bean.isCheck = !isHeardCheck;
            bean.isHeardCheck = !isHeardCheck;
        }
        notifyDataSetChanged();
        EventBus.getDefault().post(new MoneyChangeEvent());
    }

    private void sheCheck() {
        boolean isHeardCheck = mShe.get(0).isHeardCheck;
        for (SchemeOrderCreateBean.SheBean bean : mShe) {
            bean.isCheck = !isHeardCheck;
            bean.isHeardCheck = !isHeardCheck;
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
