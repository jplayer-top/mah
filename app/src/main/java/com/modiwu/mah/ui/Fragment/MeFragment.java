package com.modiwu.mah.ui.Fragment;

import android.content.Intent;
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
import top.jplayer.baseprolibrary.ui.SampleActivity;
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
    }

    @Override
    protected void onShowFragment() {
        rootView.findViewById(R.id.tvSet).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.setVisibility(View.GONE);
//                startActivity(new Intent(getContext(), SampleActivity.class));
            }
        });
    }
}
