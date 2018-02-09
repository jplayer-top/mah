package com.modiwu.mah.ui.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.alibaba.android.vlayout.LayoutHelper;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.modiwu.mah.R;
import com.modiwu.mah.mvp.model.bean.HomeBean;
import com.modiwu.mah.mvp.model.event.HomeTypeModeEvent;
import com.modiwu.mah.ui.activity.HouseSampleActivity;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cn.bingoogolapple.bgabanner.BGABanner;
import io.reactivex.Observable;
import top.jplayer.baseprolibrary.glide.GlideUtils;
import top.jplayer.baseprolibrary.ui.WebFullScreenActivity;
import top.jplayer.baseprolibrary.ui.adapter.VLayoutAdapter;
import top.jplayer.baseprolibrary.utils.ActivityUtils;

/**
 * Created by Obl on 2018/1/23.
 * com.modiwu.mah.ui.adapter
 */

public class HomeHeardLayoutAdapter extends VLayoutAdapter<RecyclerView.ViewHolder> {

    private final List<String> mImgUrls;
    private BGABanner mBgaBanner;
    private List<HomeBean.BannerBean> mBanner;

    public HomeHeardLayoutAdapter(Context context, LayoutHelper helper, int count, int itemType) {
        super(context, helper, count, itemType);
        mImgUrls = new ArrayList<>();
    }

    @Override
    protected int addHolderLayout(ViewGroup parent, int viewType) {
        return R.layout.adapter_home_body_heard;
    }

    @Override
    public void onViewRecycled(RecyclerView.ViewHolder holder) {
        super.onViewRecycled(holder);
        if (mBgaBanner != null) {
            mBgaBanner.setAdapter(null);
            mBgaBanner.setData(null);
        }
    }

    @Override
    protected void onBindViewHolderWithOffset(RecyclerView.ViewHolder holder, int position, int offsetTotal) {
        mBgaBanner = holder.itemView.findViewById(R.id.bgaBanner);
        mBgaBanner.setAdapter((banner, itemView, model, urlPosition) -> {
            Glide.with(context).load(model)
                    .apply(GlideUtils.init().options())
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into((ImageView) itemView);
            itemView.setOnClickListener(v -> {
                if (TextUtils.equals(mBanner.get(urlPosition).navType, "fangan")) {
                    EventBus.getDefault().post(new HomeTypeModeEvent(1));
                } else if (TextUtils.equals(mBanner.get(urlPosition).navType, "goods")) {
                    EventBus.getDefault().post(new HomeTypeModeEvent(2, 1));
                } else if (TextUtils.equals(mBanner.get(urlPosition).navType, "sjs")) {
                    EventBus.getDefault().post(new HomeTypeModeEvent(2, 0));
                } else if (TextUtils.equals(mBanner.get(urlPosition).navType, "ybj")) {
                    ActivityUtils.init().start(context, HouseSampleActivity.class, "样板间征集");
                } else {
                    Bundle bundle = new Bundle();
                    bundle.putString("url", mBanner.get(urlPosition).navValue);
                    ActivityUtils.init().start(context, WebFullScreenActivity.class, context.getString(R.string
                                    .app_name),
                            bundle);
                }
            });
        });
        mBgaBanner.setData(mImgUrls, Arrays.asList("", "", "", "", ""));
    }

    public void setBanner(List<HomeBean.BannerBean> banner) {
        {
            mBanner = banner;
            Observable.fromIterable(mBanner).subscribe(bannerBean -> mImgUrls.add(bannerBean.imgUrl));
        }
    }
}
