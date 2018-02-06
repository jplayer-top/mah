package com.modiwu.mah.ui.fragment;

import android.app.Dialog;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.modiwu.mah.BuildConfig;
import com.modiwu.mah.R;
import com.modiwu.mah.base.BaseFragment;
import com.modiwu.mah.ui.activity.AboutMahActivity;
import com.modiwu.mah.ui.activity.LoginAnimActivity;
import com.modiwu.mah.ui.activity.MeContentActivity;
import com.modiwu.mah.ui.activity.MeFangAnActivity;
import com.modiwu.mah.ui.activity.MeOrderActivity;
import com.modiwu.mah.ui.activity.MeShouCangActivity;
import com.modiwu.mah.ui.activity.ShopCartActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import top.jplayer.baseprolibrary.net.download.DownloadByChrome;
import top.jplayer.baseprolibrary.utils.ActivityUtils;

/**
 * Created by Obl on 2018/1/19.
 * com.modiwu.mah.ui.Fragment
 */

public class MeFragment extends BaseFragment {

    @BindView(R.id.llToLogin)
    LinearLayout llToLogin;
    @BindView(R.id.tvUpdate)
    TextView tvUpdate;
    Unbinder unbinder;
    @BindView(R.id.tvSet)
    TextView tvSet;
    @BindView(R.id.tvAbout)
    TextView tvAbout;
    @BindView(R.id.tvMe)
    TextView tvMe;
    @BindView(R.id.tvOrder)
    TextView tvOrder;
    @BindView(R.id.tvFangAn)
    TextView tvFangAn;
    @BindView(R.id.tvShouCang)
    TextView tvShouCang;
    @BindView(R.id.tvShopCart)
    TextView tvShopCart;
    @BindView(R.id.ivMeAvatar)
    ImageView ivMeAvatar;

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
        tvAbout.setOnClickListener(view -> ActivityUtils.init().start(getContext(), AboutMahActivity.class, "关于"));
        llToLogin.setOnClickListener(view -> ActivityUtils.init().start(getContext(), LoginAnimActivity.class));
        tvMe.setOnClickListener(view -> ActivityUtils.init().start(getContext(), MeContentActivity.class, "个人资料"));
        tvOrder.setOnClickListener(view -> ActivityUtils.init().start(getContext(), MeOrderActivity.class, "我的订单"));
        tvFangAn.setOnClickListener(view -> ActivityUtils.init().start(getContext(), MeFangAnActivity.class, "我的方案"));
        tvShopCart.setOnClickListener(view -> ActivityUtils.init().start(getContext(), ShopCartActivity.class, "购物车"));
        tvShouCang.setOnClickListener(view -> ActivityUtils.init().start(getContext(), MeShouCangActivity.class,
                "我的收藏"));
        tvUpdate.setOnClickListener(view -> checkUpdate(true));

    }

    /**
     * - 检测软件更新
     */
    public void checkUpdate(final boolean isToast) {
        /**
         * 在这里请求后台接口，获取更新的内容和最新的版本号
         */
        // 版本的更新信息
        String version_info = "更新内容\n" + "    1. 首页异常处理\n" + "    2. 发布新商品UI\n" + "    ";
        int mVersion_code = BuildConfig.VERSION_CODE;// 当前的版本号
        int nVersion_code = 2;
        showNoticeDialog(version_info);
        if (isToast) {
            Toast.makeText(getActivity(), "已经是最新版本", Toast.LENGTH_SHORT).show();
        }

    }

    /**
     * 显示更新对话框
     *
     * @param version_info
     */
    private void showNoticeDialog(String version_info) {
        // 构造对话框
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("更新提示");
        builder.setMessage(version_info);
        // 更新
        builder.setPositiveButton("立即更新", (dialog, which) -> {
            dialog.dismiss();
            DownloadByChrome.byChrome(getContext(), Uri.parse("http://jplayer.top/app-release.apk"));
        });
        // 稍后更新
        builder.setNegativeButton("以后更新", (dialog, which) -> dialog.dismiss());
        Dialog noticeDialog = builder.create();
        noticeDialog.show();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
