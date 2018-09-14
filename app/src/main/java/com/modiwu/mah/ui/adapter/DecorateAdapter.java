package com.modiwu.mah.ui.adapter;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.modiwu.mah.R;

import java.util.ArrayList;

/**
 * Created by Obl on 2018/8/29.
 * com.modiwu.mah.ui.adapter
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */

public class DecorateAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    public DecorateAdapter(ArrayList<String> strings) {
        super(R.layout.adapter_decorate, strings);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        RecyclerView recyclerViewPic = helper.itemView.findViewById(R.id.recyclerViewItem);
        RecyclerView recyclerViewPerson = helper.itemView.findViewById(R.id.recyclerViewItemPerson);
        recyclerViewPic.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
        recyclerViewPerson.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
        recyclerViewPic.setAdapter(new DecorateItemPicAdapter(null));
        ArrayList<String> dataPerson = new ArrayList<>();
        dataPerson.add("1");
        dataPerson.add("1");
        dataPerson.add("1");
        dataPerson.add("1");
        recyclerViewPerson.setAdapter(new DecorateItemPersonAdapter(dataPerson));
    }
}
