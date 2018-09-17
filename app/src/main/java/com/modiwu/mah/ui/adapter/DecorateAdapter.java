package com.modiwu.mah.ui.adapter;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RatingBar;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.modiwu.mah.R;
import com.modiwu.mah.mvp.model.bean.DecorateManBean;

import java.util.ArrayList;
import java.util.List;

import top.jplayer.baseprolibrary.utils.SharePreUtil;

/**
 * Created by Obl on 2018/8/29.
 * com.modiwu.mah.ui.adapter
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */

public class DecorateAdapter extends BaseQuickAdapter<DecorateManBean.TasksBean.WorksBeanX, BaseViewHolder> {
    public DecorateAdapter(ArrayList<DecorateManBean.TasksBean.WorksBeanX> strings) {
        super(R.layout.adapter_decorate, strings);
    }

    @Override
    protected void convert(BaseViewHolder helper, DecorateManBean.TasksBean.WorksBeanX item) {
        List<DecorateManBean.TasksBean.WorksBeanX.WorksBean> works = item.works;
        RecyclerView recyclerViewPerson = helper.itemView.findViewById(R.id.recyclerViewItemPerson);
        if (works.size() > 0) {
            recyclerViewPerson.setVisibility(View.VISIBLE);
            recyclerViewPerson.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
            recyclerViewPerson.setAdapter(new DecorateItemPersonAdapter(works));
        } else {
            recyclerViewPerson.setVisibility(View.GONE);
        }

        List<String> imgsurl = item.imgsurl;
        RecyclerView recyclerViewPic = helper.itemView.findViewById(R.id.recyclerViewItem);
        if (imgsurl.size() > 0) {
            recyclerViewPic.setVisibility(View.VISIBLE);
            recyclerViewPic.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
            recyclerViewPic.setAdapter(new DecorateItemPicBigAdapter(imgsurl));
        } else {
            recyclerViewPic.setVisibility(View.GONE);
        }
        RatingBar ratingBar = helper.itemView.findViewById(R.id.ratingBar);
        String who = (String) SharePreUtil.getData(mContext, "decorate_select", "业主");
        boolean isIndicator = "0".equals(item.flag) && "业主".equals(who);
        ratingBar.setIsIndicator(!isIndicator);
        ratingBar.setRating(item.appraise);
        helper.setText(R.id.tvContent, item.work_content)
                .setText(R.id.tvContentStd, item.flow_std)
                .setText(R.id.tvTime, " | " + item.ct)
                .setText(R.id.tvProName, item.flow_name)
                .setVisible(R.id.tvSure, isIndicator)
                .setVisible(R.id.ivPushDel, "监理".equals(who))
                .addOnClickListener(R.id.tvSure)
                .addOnClickListener(R.id.ivPushDel);
    }
}
