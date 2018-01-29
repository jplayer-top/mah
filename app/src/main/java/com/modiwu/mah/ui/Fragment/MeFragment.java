package com.modiwu.mah.ui.Fragment;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.modiwu.mah.R;
import com.modiwu.mah.base.BaseFragment;

/**
 * Created by Obl on 2018/1/19.
 * com.modiwu.mah.ui.Fragment
 */

public class MeFragment extends BaseFragment {

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
        Glide.with(getContext()).load(R.drawable.home_toshop).apply(RequestOptions.circleCropTransform()).into
                (ivMeAvatar);
    }

    private void findView(View rootView) {
        ivMeAvatar = rootView.findViewById(R.id.ivMeAvatar);
        tvSet = rootView.findViewById(R.id.tvSet);
    }


    @Override
    protected void onShowFragment() {
        tvSet.setOnClickListener(view -> view.setVisibility(View.GONE));
    }
}
