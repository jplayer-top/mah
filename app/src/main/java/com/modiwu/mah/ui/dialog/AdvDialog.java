package com.modiwu.mah.ui.dialog;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.modiwu.mah.R;
import com.modiwu.mah.mvp.model.bean.AdvBean;

import top.jplayer.baseprolibrary.utils.ScreenUtils;
import top.jplayer.baseprolibrary.utils.SizeUtils;
import top.jplayer.baseprolibrary.widgets.dialog.BaseCustomDialog;

/**
 * Created by Administrator on 2019/3/28.
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */

public class AdvDialog extends BaseCustomDialog {
    private AdvBean bean;
    private ImageView ivView;
    private ImageView ivDel;

    public AdvDialog(Context context) {
        super(context);
    }

    @Override
    public int setWidth(int i) {
        return super.setWidth(6);
    }

    @Override
    public int setHeight() {
        return ScreenUtils.getScreenWidth();
    }

    @Override
    protected void initView(View view) {
        ivView = view.findViewById(R.id.ivView);
        ivDel = view.findViewById(R.id.ivDel);
    }

    @Override
    public int initLayout() {
        return R.layout.dialog_adv;
    }

    public AdvDialog setBean(AdvBean bean) {
        this.bean = bean;
        AdvBean.BannerBean banner = bean.banner;
        Glide.with(mContentView).load(banner.banner_img).into(ivView);
        return this;
    }
}
