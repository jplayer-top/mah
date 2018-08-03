package com.modiwu.mah.ui.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.modiwu.mah.R;
import com.modiwu.mah.mvp.model.bean.ManagerClientBean;

import java.util.ArrayList;

import top.jplayer.baseprolibrary.glide.GlideUtils;

/**
 * Created by Obl on 2018/7/30.
 * com.modiwu.mah.ui.adapter
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */

public class ManagerClient2Adapter extends BaseQuickAdapter<ManagerClientBean.ProfileBean, BaseViewHolder> {
    public ManagerClient2Adapter(ArrayList<ManagerClientBean.ProfileBean> list) {
        super(R.layout.adapter_manager_client, list);
    }

    @Override
    protected void convert(BaseViewHolder helper, ManagerClientBean.ProfileBean item) {
        helper.setText(R.id.tvName, item.user_name)
                .setText(R.id.tvPhone, item.user_phone)
                .setVisible(R.id.tvOther, false)
                .setVisible(R.id.ivRight, false);
        ImageView ivAvatar = helper.itemView.findViewById(R.id.ivAvatar);
        Glide.with(mContext).load(item.user_avatar).apply(GlideUtils.init().options(R.drawable.ic_launcher_round)).into(ivAvatar);
    }
}
