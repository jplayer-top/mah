package com.modiwu.mah.ui.adapter;

import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.modiwu.mah.R;

import java.util.List;

import top.jplayer.baseprolibrary.ui.SuperBaseActivity;

/**
 * Created by Obl on 2018/1/22.
 * com.modiwu.mah.ui.adapter
 */

public class CarpenterAdapter extends BaseQuickAdapter<Integer> {
    private SuperBaseActivity activity;

    public CarpenterAdapter(List<Integer> data) {
        super(R.layout.adapter_scheme_body, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, Integer integer) {
        ImageView ivBodyPic = baseViewHolder.convertView.findViewById(R.id.ivBodyPic);
        TextView tvItemTitle = baseViewHolder.convertView.findViewById(R.id.tvItemTitle);
        TextView tvItemBody = baseViewHolder.convertView.findViewById(R.id.tvItemBody);
        ivBodyPic.setImageResource(integer);
        tvItemTitle.setText(R.string.carpenter_title);
        tvItemBody.setText(R.string.carpenter_body);
    }
}