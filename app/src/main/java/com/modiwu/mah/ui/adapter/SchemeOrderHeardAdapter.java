package com.modiwu.mah.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import com.alibaba.android.vlayout.LayoutHelper;
import com.modiwu.mah.R;

import top.jplayer.baseprolibrary.ui.adapter.VLayoutAdapter;

/**
 * Created by Obl on 2018/2/8.
 * com.modiwu.mah.ui.adapter
 */

public class SchemeOrderHeardAdapter extends VLayoutAdapter<RecyclerView.ViewHolder> {


    public SchemeOrderHeardAdapter(Context context, LayoutHelper helper, int count, int itemType) {
        super(context, helper, count, itemType);
    }

    @Override
    protected int addHolderLayout(ViewGroup parent, int viewType) {
        return R.layout.adapter_scheme_order_heard;
    }

    @Override
    protected void onBindViewHolderWithOffset(RecyclerView.ViewHolder holder, int position, int offsetTotal) {
        View itemView = holder.itemView;
        CheckBox checkHeard = itemView.findViewById(R.id.checkHeard);
        checkHeard.setText(mTitle);
        if (type == SHE) {
            checkHeard.setEnabled(false);
            checkHeard.setChecked(true);
        } else if (type == YING) {
            checkHeard.setChecked(false);
            checkHeard.setEnabled(true);

        } else {
            checkHeard.setChecked(false);
            checkHeard.setEnabled(true);

        }
    }

    public void setRuan(String ruan) {
        type = RUAN;
        mTitle = ruan;
    }

    public void setYing(String ying) {
        type = YING;
        mTitle = ying;
    }

    private int type = 0;
    private final static int SHE = 0;
    private final static int YING = 1;
    private final static int RUAN = 2;
    private String mTitle = "";


    public void setShe(String she) {
        type = SHE;
        mTitle = she;
    }
}
