package com.modiwu.mah.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.modiwu.mah.R;

import java.util.List;

import top.jplayer.baseprolibrary.ui.SuperBaseActivity;

/**
 * Created by Administrator on 2018/1/23.
 * s
 */

public class SchemeHardAdapter extends BaseQuickAdapter<Integer, BaseViewHolder> {
    private SuperBaseActivity activity;

    public SchemeHardAdapter(List<Integer> data) {
        super(R.layout.adapter_scheme_body, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, Integer integer) {
        ImageView ivBodyPic = baseViewHolder.convertView.findViewById(R.id.ivBodyPic);
        ivBodyPic.setImageResource(integer);
    }
}
