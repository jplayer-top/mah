package com.modiwu.mah.ui.Fragment;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.modiwu.mah.R;
import com.modiwu.mah.base.BaseFragment;
import com.modiwu.mah.ui.activity.LoginAnimActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import top.jplayer.baseprolibrary.ui.SampleActivity;

/**
 * Created by Obl on 2018/1/19.
 * com.modiwu.mah.ui.Fragment
 */

public class MeFragment extends BaseFragment {

    @BindView(R.id.llToLogin)
    LinearLayout llToLogin;
    Unbinder unbinder;
    private TextView tvSet;
    private ImageView ivMeAvatar;

    @Override
    public int initLayout() {
        return R.layout.fragment_me;
    }

    @Override
    protected void initData(View rootView) {
        super.initData(rootView);
        tvBarTitle.setText("我的");
        findView(rootView);
        Glide.with(getContext()).load(R.drawable.home_toshop).apply(RequestOptions.circleCropTransform()).into(ivMeAvatar);
    }

    private void findView(View rootView) {
        unbinder = ButterKnife.bind(this, rootView);
        ivMeAvatar = rootView.findViewById(R.id.ivMeAvatar);
        tvSet = rootView.findViewById(R.id.tvSet);
        tvSet.setOnClickListener(view -> startActivity(new Intent(getContext(), SampleActivity.class)));
        llToLogin.setOnClickListener(view -> startActivity(new Intent(getContext(), LoginAnimActivity.class)));

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
