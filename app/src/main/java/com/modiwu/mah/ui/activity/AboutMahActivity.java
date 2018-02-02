package com.modiwu.mah.ui.activity;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.modiwu.mah.R;
import com.modiwu.mah.base.BaseSpecialActivity;

import top.jplayer.baseprolibrary.glide.GlideUtils;

/**
 * Created by Obl on 2018/2/2.
 * com.modiwu.mah.ui.activity
 */

public class AboutMahActivity extends BaseSpecialActivity {
    @Override
    public int setBaseLayout() {
        return R.layout.activity_about;
    }

    @Override
    public void initBaseData() {
        ImageView ivAbout = contentView.findViewById(R.id.ivAbout);
        Glide.with(this)
                .asGif()
                .load("http://images.shejidaren.com/wp-content/uploads/2015/04/20150326222456182.gif")
                .apply(GlideUtils.init().gif())
                .into(ivAbout);
    }
}
