package com.modiwu.mah.ui.fragment;

import android.app.Dialog;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;
import com.modiwu.mah.BuildConfig;
import com.modiwu.mah.R;
import com.modiwu.mah.base.BaseFragment;
import com.modiwu.mah.mvp.model.MeInfoModel;
import com.modiwu.mah.mvp.model.bean.MeInfoBean;
import com.modiwu.mah.mvp.model.event.LoginSuccessEvent;
import com.modiwu.mah.mvp.model.event.LogoutEvent;
import com.modiwu.mah.mvp.model.event.UpAvatarEvent;
import com.modiwu.mah.ui.activity.AboutMahActivity;
import com.modiwu.mah.ui.activity.LocalListActivity;
import com.modiwu.mah.ui.activity.LoginAnimActivity;
import com.modiwu.mah.ui.activity.MeContentActivity;
import com.modiwu.mah.ui.activity.MeFangAnActivity;
import com.modiwu.mah.ui.activity.MeOrderActivity;
import com.modiwu.mah.ui.activity.MeShouCangActivity;
import com.modiwu.mah.ui.activity.ShopCartActivity;
import com.modiwu.mah.ui.dialog.ShareDialog;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import top.jplayer.baseprolibrary.glide.GlideUtils;
import top.jplayer.baseprolibrary.net.SampleShowDialogObserver;
import top.jplayer.baseprolibrary.net.download.DownloadByChrome;
import top.jplayer.baseprolibrary.utils.ActivityUtils;
import top.jplayer.baseprolibrary.utils.SharePreUtil;

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
    @BindView(R.id.tvLocal)
    TextView tvLocal;
    @BindView(R.id.tvName)
    TextView tvName;
    @BindView(R.id.tvShare)
    TextView tvShare;
    @BindView(R.id.ivMeAvatar)
    ImageView ivMeAvatar;
    private MeInfoModel mModel;

    @Override
    public int initLayout() {
        return R.layout.fragment_me;
    }

    @Override
    protected void initData(View rootView) {
        super.initData(rootView);
        tvBarTitle.setText("我的");
        findView(rootView);
        EventBus.getDefault().register(this);
    }

    private void findView(View rootView) {
        unbinder = ButterKnife.bind(this, rootView);
        tvAbout.setOnClickListener(view -> ActivityUtils.init().start(getContext(), AboutMahActivity.class, "关于"));
        llToLogin.setOnClickListener(view -> ActivityUtils.init().start(getContext(), LoginAnimActivity.class));
        tvMe.setOnClickListener(view -> ActivityUtils.init().start(getContext(), MeContentActivity.class, "个人资料"));
        tvOrder.setOnClickListener(view -> ActivityUtils.init().start(getContext(), MeOrderActivity.class, "我的订单"));
        tvLocal.setOnClickListener(view -> ActivityUtils.init().start(getContext(), LocalListActivity.class,
                "收货地址"));
        tvFangAn.setOnClickListener(view -> ActivityUtils.init().start(getContext(), MeFangAnActivity.class, "我的方案"));
        tvShopCart.setOnClickListener(view -> ActivityUtils.init().start(getContext(), ShopCartActivity.class, "购物车"));
        tvShouCang.setOnClickListener(view -> ActivityUtils.init().start(getContext(), MeShouCangActivity.class,
                "我的收藏"));
        tvUpdate.setOnClickListener(view -> checkUpdate(true));
        tvShare.setOnClickListener(v -> new ShareDialog(getContext()).show());
        mModel = new MeInfoModel();
        isLogin();
    }

    private void isLogin() {
        mModel.requestIsLogin().subscribe(baseBean -> {
            if (baseBean != null && "1".equals(baseBean.login)) {
                String infoJson = (String) SharePreUtil.getData(getContext(), "info", "");
                MeInfoBean meInfoBean = new Gson().fromJson(infoJson, MeInfoBean.class);
                if (meInfoBean != null) {
                    bindInfo(meInfoBean);
                }
                llToLogin.setEnabled(false);
            } else {
                llToLogin.setEnabled(true);
                Glide.with(getContext()).load(R.mipmap.ic_launcher).apply(RequestOptions.circleCropTransform()).into(ivMeAvatar);
                tvName.setText("点击登录");
            }
        });
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

    private String uid = "0";

    @Subscribe
    public void userInfo(LoginSuccessEvent event) {

        int uid = event.uid;
        this.uid = uid + "";
        refreshInfo(this.uid);
    }

    private void refreshInfo(String uid) {
        mModel.requestGetInfo(uid).subscribe(new SampleShowDialogObserver<MeInfoBean>(getContext()) {
            @Override
            protected void onSuccess(MeInfoBean baseBean) throws Exception {
                bindInfo(baseBean);
                String json = new Gson().toJson(baseBean);
                SharePreUtil.saveData(getContext(), "info", json);
            }
        });
    }

    @Subscribe
    public void logout(LogoutEvent logoutEvent) {
        isLogin();
    }

    @Subscribe
    public void upAvatar(UpAvatarEvent event) {
        refreshInfo(uid);
    }

    private void bindInfo(MeInfoBean baseBean) {
        Glide.with(getContext()).load(baseBean.profile.user_avatar)
                .apply(GlideUtils.init().options())
                .apply(RequestOptions.circleCropTransform()).into(ivMeAvatar);
        tvName.setText(baseBean.profile.user_name);
        llToLogin.setEnabled(false);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        EventBus.getDefault().unregister(this);
    }
}
