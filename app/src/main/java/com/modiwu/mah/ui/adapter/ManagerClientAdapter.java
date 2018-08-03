package com.modiwu.mah.ui.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.modiwu.mah.R;
import com.modiwu.mah.mvp.model.bean.ManagerClientBean;

import java.util.ArrayList;

/**
 * Created by Obl on 2018/7/30.
 * com.modiwu.mah.ui.adapter
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */

public class ManagerClientAdapter extends BaseQuickAdapter<ManagerClientBean.ProfileBean, BaseViewHolder> {
    public ManagerClientAdapter(ArrayList<ManagerClientBean.ProfileBean> list) {
        super(R.layout.adapter_manager_client, list);
    }

    @Override
    protected void convert(BaseViewHolder helper, ManagerClientBean.ProfileBean item) {
        boolean b = item.lv2 == 0;
        helper.setText(R.id.tvName, item.user_name)
                .setText(R.id.tvPhone, item.user_phone)
                .setTextColor(R.id.tvOther, b ? R.color.chocolate : R.color.color999);
        ImageView ivAvatar = helper.itemView.findViewById(R.id.ivAvatar);
        Glide.with(mContext).load(item.user_avatar).apply(RequestOptions.circleCropTransform()).into(ivAvatar);
    }
}
