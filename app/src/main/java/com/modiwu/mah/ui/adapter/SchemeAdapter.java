package com.modiwu.mah.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.modiwu.mah.R;

import java.util.List;

import top.jplayer.baseprolibrary.ui.SuperBaseActivity;

/**
 * Created by Administrator on 2018/1/21.
 * SchemeAdapter 方案页面适配器
 */

public class SchemeAdapter extends BaseQuickAdapter<Integer> {
    private SuperBaseActivity activity;

    public SchemeAdapter( List<Integer> data) {
        super(R.layout.adapter_scheme_body, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, Integer integer) {
        ImageView ivBodyPic = baseViewHolder.convertView.findViewById(R.id.ivBodyPic);
        ivBodyPic.setImageResource(integer);
    }
}
