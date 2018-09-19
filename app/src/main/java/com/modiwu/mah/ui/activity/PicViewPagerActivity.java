package com.modiwu.mah.ui.activity;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.modiwu.mah.R;
import com.modiwu.mah.base.BaseCommonActivity;

import java.util.ArrayList;

import top.jplayer.baseprolibrary.glide.GlideUtils;

/**
 * Created by Obl on 2018/9/19.
 * com.modiwu.mah.ui.activity
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */

public class PicViewPagerActivity extends BaseCommonActivity {

    private ViewPager mViewPager;
    private ArrayList<String> mPics;

    @Override
    public int setBaseLayout() {
        return R.layout.activity_decorate_pic_viewpager;
    }

    @Override
    public void initBaseData() {
        mPics = mBundle.getStringArrayList("pics");
        mViewPager = mFlRootView.findViewById(R.id.viewPager);
        mViewPager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return mPics.size();
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                ImageView v = new ImageView(mBaseActivity);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout
                        .LayoutParams.MATCH_PARENT);
                v.setScaleType(ImageView.ScaleType.CENTER_CROP);
                v.setLayoutParams(params);
                Glide.with(mBaseActivity)
                        .load(mPics.get(position))
                        .apply(GlideUtils.init().options(R.drawable.placeholder))
                        .into(v);
                container.addView(v);
                return v;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView((View) object);
            }
        });
    }
}
