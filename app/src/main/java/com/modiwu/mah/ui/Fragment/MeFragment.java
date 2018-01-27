package com.modiwu.mah.ui.Fragment;

import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;
import com.modiwu.mah.R;
import com.modiwu.mah.base.BaseFragment;

import io.reactivex.functions.Function;
import top.jplayer.baseprolibrary.mvp.model.bean.SampleBean;
import top.jplayer.baseprolibrary.net.ApiService;
import top.jplayer.baseprolibrary.net.IoMainSchedule;
import top.jplayer.baseprolibrary.net.JsonRefixInterceptor;
import top.jplayer.baseprolibrary.net.RetrofitManager;
import top.jplayer.baseprolibrary.net.SampleObserver;
import top.jplayer.baseprolibrary.utils.LogUtil;

/**
 * Created by Obl on 2018/1/19.
 * com.modiwu.mah.ui.Fragment
 */

public class MeFragment extends BaseFragment {
    @Override
    public int initLayout() {
        return R.layout.fragment_me;
    }

    @Override
    protected void initData(View rootView) {
        super.initData(rootView);
        tvBarTitle.setText("我的");
        ImageView ivMeAvatar = rootView.findViewById(R.id.ivMeAvatar);
        Glide.with(getContext()).load(R.drawable.home_toshop).apply(RequestOptions.circleCropTransform()).into
                (ivMeAvatar);
        RetrofitManager.init().reset("https://m.leader001.cn/", new JsonRefixInterceptor()).reCreate(ApiService.class)
                .getSampleBean("{\"information\":\"bd_web_api\",\"command\":\"redhallwill\",\"platform\":\"html\"," +
                        "\"version\":\"5.2.30\",\"productName\":\"lzcp\"}", "1514383490705", "Zepto1514383490533")
                .compose(new IoMainSchedule<SampleBean>())
                .subscribe(new SampleObserver<SampleBean>() {
                    @Override
                    public void onNext(SampleBean o) {
                        LogUtil.e(o.errorCode);
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtil.e(e.getMessage() + "1---");
                    }
                });
    }
}
