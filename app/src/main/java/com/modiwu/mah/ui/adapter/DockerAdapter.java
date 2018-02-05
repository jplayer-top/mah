package com.modiwu.mah.ui.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.modiwu.mah.R;
import com.modiwu.mah.mvp.model.bean.DockerBean;
import com.modiwu.mah.utils.StringUtils;

import java.util.List;

import top.jplayer.baseprolibrary.glide.GlideUtils;
import top.jplayer.baseprolibrary.utils.ScreenUtils;

/**
 * Created by Obl on 2018/1/23.
 * com.modiwu.mah.ui.adapter
 */

public class DockerAdapter extends BaseQuickAdapter<DockerBean.RecordsBean, BaseViewHolder> {

    private ImageView mIvBodyPic;

    public DockerAdapter(List<DockerBean.RecordsBean> data) {
        super(R.layout.adapter_home_item_single, data);
    }

    @Override
    protected BaseViewHolder onCreateDefViewHolder(ViewGroup parent, int viewType) {
        BaseViewHolder viewHolder = super.onCreateDefViewHolder(parent, viewType);
        View view = viewHolder.itemView;
        mIvBodyPic = view.findViewById(R.id.ivBodyPic);
        int i = ScreenUtils.getWidthOfScreen(mContext, 10, 3);
        mIvBodyPic.getLayoutParams().width = i;
        LinearLayout llTextView = view.findViewById(R.id.llTextView);
        llTextView.getLayoutParams().width = i;
        mIvBodyPic.getLayoutParams().height = i;
        return viewHolder;
    }

    @Override
    protected void convert(BaseViewHolder helper, DockerBean.RecordsBean item) {
        View itemView = helper.itemView;
        mIvBodyPic = itemView.findViewById(R.id.ivBodyPic);
        Glide.with(mContext).load(item.cat_name)
                .apply(GlideUtils.init().options())
                .into(mIvBodyPic);
        helper.setText(R.id.tvTitle, StringUtils.getInstance().isNullable(item.cat_name, "整个家"))
                .setVisible(R.id.tvPrice, false)
                .setVisible(R.id.tvSubTitle, false);
    }
}
