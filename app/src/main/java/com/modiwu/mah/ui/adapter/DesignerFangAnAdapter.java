package com.modiwu.mah.ui.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.vlayout.LayoutHelper;
import com.bumptech.glide.Glide;
import com.modiwu.mah.R;
import com.modiwu.mah.mvp.model.bean.DesignBean;
import com.modiwu.mah.ui.activity.SchemeDetailActivity;
import com.modiwu.mah.utils.StringUtils;

import java.util.List;
import java.util.Locale;

import top.jplayer.baseprolibrary.glide.GlideUtils;
import top.jplayer.baseprolibrary.ui.adapter.VLayoutAdapter;
import top.jplayer.baseprolibrary.utils.ActivityUtils;

/**
 * Created by Obl on 2018/2/5.
 * com.modiwu.mah.ui.adapter
 */

public class DesignerFangAnAdapter extends VLayoutAdapter<RecyclerView.ViewHolder> {

    private List<DesignBean.FanganBean> mFanganBeans;

    public DesignerFangAnAdapter(Context context, LayoutHelper helper, int count, int itemType) {
        super(context, helper, count, itemType);
    }

    @Override
    protected int addHolderLayout(ViewGroup parent, int viewType) {
        return R.layout.adapter_fangan_designer;
    }

    @Override
    protected void onBindViewHolderWithOffset(RecyclerView.ViewHolder holder, int position, int offsetTotal) {
        View itemView = holder.itemView;
        ImageView ivBodyPic = itemView.findViewById(R.id.ivBodyPic);
        TextView tvItemTitle = itemView.findViewById(R.id.tvItemTitle);
        TextView tvItemBody = itemView.findViewById(R.id.tvItemBody);

        DesignBean.FanganBean fanganBean = mFanganBeans.get(position);

        Bundle bundle = new Bundle();
        bundle.putString("fangan_id", String.format(Locale.CHINA, "%d", fanganBean.fangan_id));
        itemView.setOnClickListener(v -> ActivityUtils.init().start(context, SchemeDetailActivity.class, fanganBean.fangan_name,
                bundle));

        Glide.with(context).load(fanganBean.fangan_avatar).apply(GlideUtils.init().options()).into(ivBodyPic);
        tvItemTitle.setText(StringUtils.getInstance().isNullable(fanganBean.fangan_name, ""));
        tvItemBody.setText(StringUtils.getInstance().isNullable(fanganBean.fangan_desc, ""));
    }

    public void setFangAn(List<DesignBean.FanganBean> fangAn) {
        mFanganBeans = fangAn;
    }

}
