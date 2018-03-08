package com.modiwu.mah.ui.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alibaba.android.vlayout.LayoutHelper;
import com.modiwu.mah.R;
import com.modiwu.mah.mvp.model.event.HomeTypeModeEvent;

import org.greenrobot.eventbus.EventBus;

import top.jplayer.baseprolibrary.ui.adapter.VLayoutAdapter;

/**
 * Created by Obl on 2018/1/23.
 * com.modiwu.mah.ui.adapter
 */

public class HomeSectionFooterLayoutAdapter extends VLayoutAdapter<RecyclerView.ViewHolder> {
    private String mTitle = "";

    public HomeSectionFooterLayoutAdapter(Context context, LayoutHelper helper, int count, int itemType) {
        super(context, helper, count, itemType);
    }

    @Override

    protected int addHolderLayout(ViewGroup parent, int viewType) {
        return R.layout.adapter_home_body_section_footer;
    }

    @Override
    protected void onBindViewHolderWithOffset(RecyclerView.ViewHolder holder, int position, int offsetTotal) {
        TextView tvToMore = holder.itemView.findViewById(R.id.tvToMore);
        if (mTitle.equals("颜值单品")) {
            tvToMore.setBackgroundColor(Color.WHITE);
        } else {
            tvToMore.setBackgroundColor(Color.parseColor("#f5f5f5"));
        }
        tvToMore.setOnClickListener(v -> {
            int clickMore;
            if (mTitle.equals("方案")) {
                clickMore = 1;
                EventBus.getDefault().post(new HomeTypeModeEvent(clickMore));
            } else if (mTitle.equals("颜值单品")) {
                clickMore = 2;
                EventBus.getDefault().post(new HomeTypeModeEvent(clickMore, 1));
            } else {
                clickMore = 3;
                EventBus.getDefault().post(new HomeTypeModeEvent(clickMore, 0));
            }

        });
    }

    public void setTitle(String title) {
        mTitle = title;
    }
}
