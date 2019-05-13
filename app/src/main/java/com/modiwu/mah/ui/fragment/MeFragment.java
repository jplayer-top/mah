package com.modiwu.mah.ui.fragment;

import android.app.Dialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.ArrayMap;
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
import com.modiwu.mah.mvp.model.bean.VersionBean;
import com.modiwu.mah.mvp.model.event.LoginSuccessEvent;
import com.modiwu.mah.mvp.model.event.LogoutEvent;
import com.modiwu.mah.mvp.model.event.ShareAllEvent;
import com.modiwu.mah.mvp.model.event.ShareOneEvent;
import com.modiwu.mah.mvp.model.event.UpAvatarEvent;
import com.modiwu.mah.ui.activity.AboutMahActivity;
import com.modiwu.mah.ui.activity.LocalListActivity;
import com.modiwu.mah.ui.activity.LoginAnimActivity;
import com.modiwu.mah.ui.activity.ManagerActivity;
import com.modiwu.mah.ui.activity.MeContentActivity;
import com.modiwu.mah.ui.activity.MeFangAnActivity;
import com.modiwu.mah.ui.activity.MeOrderListActivity;
import com.modiwu.mah.ui.activity.MeShouCangActivity;
import com.modiwu.mah.ui.activity.ShopCartActivity;
import com.modiwu.mah.ui.dialog.ShareDialog;
import com.modiwu.mah.utils.StringUtils;
import com.modiwu.mah.wxapi.WXShare;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.rong.imkit.RongIM;
import io.rong.imlib.model.Conversation;
import top.jplayer.baseprolibrary.glide.GlideUtils;
import top.jplayer.baseprolibrary.net.SampleShowDialogObserver;
import top.jplayer.baseprolibrary.net.download.DownloadByNotify;
import top.jplayer.baseprolibrary.utils.ActivityUtils;
import top.jplayer.baseprolibrary.utils.SharePreUtil;
import top.jplayer.baseprolibrary.utils.ToastUtils;

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
    @BindView(R.id.tvManager)
    TextView tvManager;
    @BindView(R.id.tvLocal)
    TextView tvLocal;
    @BindView(R.id.tvName)
    TextView tvName;
    @BindView(R.id.tvShare)
    TextView tvShare;
    @BindView(R.id.tvServer)
    TextView tvServer;
    @BindView(R.id.ivMeAvatar)
    ImageView ivMeAvatar;
    @BindView(R.id.smartRefreshLayout)
    SmartRefreshLayout mRefreshLayout;
    private MeInfoModel mModel;
    private WXShare mWxShare;

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
        mWxShare = new WXShare(getContext());
        mRefreshLayout.setOnRefreshListener(refresh -> {
            refresh(uid);
        });
    }

    private void findView(View rootView) {
        unbinder = ButterKnife.bind(this, rootView);
        tvAbout.setOnClickListener(view -> ActivityUtils.init().start(getContext(), AboutMahActivity.class, "关于"));
        llToLogin.setOnClickListener(view -> ActivityUtils.init().start(getContext(), LoginAnimActivity.class));
        tvMe.setOnClickListener(view -> ActivityUtils.init().start(getContext(), MeContentActivity.class, "个人资料"));

        tvOrder.setOnClickListener(view -> {
            if (StringUtils.getInstance().assertNoLogin(getContext())) {
                return;
            }
            ActivityUtils.init().start(getContext(), MeOrderListActivity.class, "我的订单");
        });
        tvLocal.setOnClickListener(view -> {
            if (StringUtils.getInstance().assertNoLogin(getContext())) {
                return;
            }
            ActivityUtils.init().start(getContext(), LocalListActivity.class,
                    "收货地址");
        });
        tvFangAn.setOnClickListener(view -> {
            if (StringUtils.getInstance().assertNoLogin(getContext())) {
                return;
            }
            ActivityUtils.init().start(getContext(), MeFangAnActivity.class, "我的方案");
        });
        tvShopCart.setOnClickListener(view -> {
            if (StringUtils.getInstance().assertNoLogin(getContext())) {
                return;
            }
            ActivityUtils.init().start(getContext(), ShopCartActivity.class, "购物车");
        });

        tvManager.setOnClickListener(v -> {
            if (StringUtils.getInstance().assertNoLogin(getContext())) {
                return;
            }
            String infoJson = (String) SharePreUtil.getData(getContext(), "info", "");
            MeInfoBean meInfoBean = new Gson().fromJson(infoJson, MeInfoBean.class);
            Bundle bundle = new Bundle();
            MeInfoBean.ProfileBean profile = meInfoBean.profile;
            if (profile != null) {
                bundle.putInt("uid", profile.user_id);
                bundle.putString("avatar", profile.user_avatar);
                bundle.putString("name", profile.user_name);
                bundle.putInt("lv1", profile.lv1);
                bundle.putInt("lv2", profile.lv2);
                ActivityUtils.init().start(getContext(), ManagerActivity.class, "全民经纪人", bundle);
            } else {
                ToastUtils.init().showErrorToast(getContext(), "用户数据信息错误");
            }
        });
        tvShouCang.setOnClickListener(view -> {

            if (StringUtils.getInstance().assertNoLogin(getContext())) return;
            ActivityUtils.init().start(getContext(), MeShouCangActivity.class, "我的收藏");
        });

        tvUpdate.setOnClickListener(view -> {
            mModel.requestVersion().subscribe(new SampleShowDialogObserver<VersionBean>(getContext()) {
                @Override
                protected void onSuccess(VersionBean versionBean) throws Exception {
                    if (versionBean != null && versionBean.ver != null) {
                        checkUpdate(versionBean);
                    }
                }
            });
        });
        tvShare.setOnClickListener(v -> new ShareDialog(getContext()).show());
        tvSet.setOnClickListener(v -> {
            Map<String, Boolean> map = new ArrayMap<>();
            map.put(Conversation.ConversationType.PRIVATE.getName(), false);
            RongIM.getInstance().startConversationList(getContext(), map);
        });
        mModel = new MeInfoModel();
        isLogin();
    }


    @Subscribe
    public void shareEvent(ShareOneEvent event) {
        if (mWxShare.checkWX()) {

            Bitmap thumb = BitmapFactory
                    .decodeResource(getResources(), R.mipmap.ic_launcher);
            mWxShare.shareUrl("https://app.modiwu.com/app/download", getString(R.string.app_name), thumb,
                    getString(R.string.solagon), SendMessageToWX.Req.WXSceneSession);
        } else {
            ToastUtils.init().showInfoToast(getContext(), "请先安装微信");
        }
    }

    @Subscribe
    public void shareEvent(ShareAllEvent event) {
        if (mWxShare.checkWX()) {
            Bitmap thumb = BitmapFactory
                    .decodeResource(getResources(), R.mipmap.ic_launcher);
            mWxShare.shareUrl("https://app.modiwu.com/app/download", getString(R.string.app_name), thumb,
                    getString(R.string.solagon), SendMessageToWX.Req.WXSceneTimeline);
        } else {
            ToastUtils.init().showInfoToast(getContext(), "请先安装微信");
        }
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
                tvServer.setOnClickListener(view -> {
                    if (StringUtils.getInstance().assertNoLogin(getContext())) return;
                    RongIM.getInstance().startConversation(getContext(), Conversation.ConversationType.PRIVATE, "10020", "客服");
                });
            } else {
                llToLogin.setEnabled(true);
                Glide.with(getContext()).load(R.mipmap.ic_launcher).apply(RequestOptions.circleCropTransform()).into(ivMeAvatar);
                tvName.setText("点击登录");
                SharePreUtil.saveData(getContext(), "mark_login", "0");
            }


        }, throwable -> {
        });
    }

    /**
     * - 检测软件更新
     */
    public void checkUpdate(VersionBean versionBean) {

        VersionBean.VerBean verBean = versionBean.ver;
        String version_info = verBean.content;
        int curVerCode = BuildConfig.VERSION_CODE;// 当前的版本号
        int urlCode = verBean.build;
        if (urlCode > curVerCode) {
            showNoticeDialog(verBean);
        } else {
            ToastUtils.init().showSuccessToast(getContext(), "已经是最新版本了");
        }
    }

    /**
     * 显示更新对话框
     */
    private void showNoticeDialog(VersionBean.VerBean verBean) {
        // 构造对话框
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("更新提示");
        builder.setMessage(verBean.content);
        // 更新
        builder.setPositiveButton("立即更新", (dialog, which) -> {
            dialog.dismiss();
            DownloadByNotify.byNotify(getContext(), Uri.parse(verBean.file_url));
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
                mRefreshLayout.finishRefresh();
            }
        });
    }

    private void refresh(String uid) {
        mModel.requestGetInfo(uid).subscribe(new Observer<MeInfoBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(MeInfoBean meInfoBean) {
                bindInfo(meInfoBean);
                String json = new Gson().toJson(meInfoBean);
                SharePreUtil.saveData(getContext(), "info", json);
                if (mRefreshLayout != null) {
                    mRefreshLayout.finishRefresh();
                }
            }

            @Override
            public void onError(Throwable e) {
                if (mRefreshLayout != null) {
                    mRefreshLayout.finishRefresh();
                }
            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Subscribe
    public void logout(LogoutEvent logoutEvent) {
        isLogin();
    }

    @Subscribe
    public void upAvatar(UpAvatarEvent event) {
        refreshInfo(event.uid + "");
    }

    private void bindInfo(MeInfoBean baseBean) {
        MeInfoBean.ProfileBean profile = baseBean.profile;
        if (profile == null) {
            return;
        }
        this.uid = baseBean.profile.user_id + "";
        if (profile.user_avatar == null) {
            Glide.with(getContext()).load(getResources().getDrawable(R.mipmap.ic_launcher)).into(ivMeAvatar);
        } else {
            Glide.with(getContext()).load(profile.user_avatar)
                    .apply(GlideUtils.init().options())
                    .apply(RequestOptions.circleCropTransform()).into(ivMeAvatar);

        }
        tvName.setText(profile.user_name);
        llToLogin.setEnabled(false);
        tvSet.setVisibility(baseBean.iskf == 1 ? View.VISIBLE : View.GONE);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        EventBus.getDefault().unregister(this);
    }
}
