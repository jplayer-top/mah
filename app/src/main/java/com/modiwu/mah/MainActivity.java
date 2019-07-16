package com.modiwu.mah;

import android.app.Dialog;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.modiwu.mah.base.BaseSpecialActivity;
import com.modiwu.mah.base.FragmentFactory;
import com.modiwu.mah.mvp.model.bean.CameraBean;
import com.modiwu.mah.mvp.model.bean.VersionBean;
import com.modiwu.mah.mvp.model.event.HomeTypeModeEvent;
import com.modiwu.mah.ui.activity.ActivityCustomCapture;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import devlight.io.library.ntb.NavigationTabBar;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import top.jplayer.baseprolibrary.listener.NetNavigationBarListener;
import top.jplayer.baseprolibrary.net.download.DownloadByNotify;
import top.jplayer.baseprolibrary.utils.ActivityUtils;
import top.jplayer.baseprolibrary.utils.ToastUtils;

import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static top.jplayer.baseprolibrary.utils.PermissionUtils.setPermission;

public class MainActivity extends BaseSpecialActivity {

    public FrameLayout mFlFragment;
    private boolean isCameraClick = false;
    private List<LinearLayout> mViewList;

    @Override
    public int setBaseLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initBaseData() {
//        mNavigationTabBar = contentView.findViewById(R.id.ntb);
        mFlFragment = contentView.findViewById(R.id.flFragment);
//        bottomBar(mNavigationTabBar);
        bottomBar();
        EventBus.getDefault().register(this);
    }

    private void bottomBar() {
        mViewList = new ArrayList<>();
        LinearLayout llHome = contentView.findViewById(R.id.llHome);
        mViewList.add(llHome);
        llHome.setOnClickListener(v -> onTabClick((LinearLayout) v, 0));
        LinearLayout llScheme = contentView.findViewById(R.id.llScheme);
        mViewList.add(llScheme);
        llScheme.setOnClickListener(v -> onTabClick((LinearLayout) v, 1));
        LinearLayout llDecorate = contentView.findViewById(R.id.llDecorate);
        mViewList.add(llDecorate);
        llDecorate.setOnClickListener(v -> onTabClick((LinearLayout) v, 2));
        LinearLayout llDesign = contentView.findViewById(R.id.llDesign);
        mViewList.add(llDesign);
        llDesign.setOnClickListener(v -> onTabClick((LinearLayout) v, 3));
        LinearLayout llMe = contentView.findViewById(R.id.llMe);
        mViewList.add(llMe);
        llMe.setOnClickListener(v -> onTabClick((LinearLayout) v, 4));
        onTabClick(mViewList.get(0), 0);
    }

    private int curIndex = -1;

    private void onTabClick(LinearLayout view, int index) {
        if (curIndex == index) {
            return;
        }
        for (LinearLayout linearLayout : mViewList) {
            linearLayout.getChildAt(0).setSelected(false);
            linearLayout.getChildAt(1).setSelected(false);
        }
        view.getChildAt(0).setSelected(true);
        view.getChildAt(1).setSelected(true);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        Fragment fragment = getSupportFragmentManager().findFragmentByTag(index + "");
        if (fragment == null) {
            fragment = FragmentFactory.instance().getSingleFragment(index);
            transaction.add(R.id.flFragment, fragment, index + "");
        }
        for (Fragment fragmentItem : getSupportFragmentManager().getFragments()) {
            if (!fragmentItem.isHidden()) {
                transaction.hide(fragmentItem);
            }
        }
        if (fragment.isHidden()) {
            transaction.show(fragment);
        }
        curIndex = index;
        transaction.commitAllowingStateLoss();
    }


    /**
     * 设置底部栏
     */

    private void bottomBar(NavigationTabBar navigationTabBar) {

        final ArrayList<NavigationTabBar.Model> models = new ArrayList<>();
        String[] titleArrs = new String[]{"首页", "方案", "我的家装", "匠·器", "我的"};
        int[] drawArrs = new int[]{R.drawable.main_home_sel, R.drawable.main_scheme, R.drawable.main_deocrate, R.drawable
                .main_charpenter, R
                .drawable.main_me};
        for (int i = 0; i < titleArrs.length; i++) {
            models.add(new NavigationTabBar.Model.Builder(getResources().getDrawable(drawArrs[i]), getResources().getColor(R.color.trans))
                    .title(titleArrs[i])
                    .build()
            );
        }
        navigationTabBar.setModels(models);
        navigationTabBar.setOnTabBarSelectedIndexListener(new NetNavigationBarListener() {
            @Override
            public void onceSelected(NavigationTabBar.Model model, int index) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                Fragment fragment = getSupportFragmentManager().findFragmentByTag(index + "");
                if (fragment == null) {
                    fragment = FragmentFactory.instance().getSingleFragment(index);
                    transaction.add(R.id.flFragment, fragment, index + "");
                }
                for (Fragment fragmentItem : getSupportFragmentManager().getFragments()) {
                    if (!fragmentItem.isHidden()) {

                        transaction.hide(fragmentItem);
                    }
                }
                if (fragment.isHidden()) {
                    transaction.show(fragment);
                }
                transaction.commitAllowingStateLoss();
            }
        });
        navigationTabBar.setModelIndex(0, true);
    }


    // 首页自定义左上角
    @Override
    public void customBarLeft() {
    }

    public int carFragmentType = 0;

    /**
     * 首页点击更多去处
     *
     * @param event 1.方案 2. 匠 3.器
     */
    @Subscribe
    public void moreClick(HomeTypeModeEvent event) {
        switch (event.clickMore) {
            case 1://方案
                onTabClick(mViewList.get(1), 1);
                break;
            case 2://匠
                onTabClick(mViewList.get(3), 3);
                carFragmentType = event.type;
                break;
            case 3://器
                onTabClick(mViewList.get(3), 3);
                carFragmentType = event.type;
                break;
        }
    }

    @Subscribe
    public void cameraClick(CameraBean event) {
        isCameraClick = true;
        setPermission(this, 100, WRITE_EXTERNAL_STORAGE, CAMERA);
    }

    private int back = 0;

    @Override
    public void onBackPressed() {
        back++;
        if (back > 1) {
            super.onBackPressed();
        } else {
            ToastUtils.init().showQuickToast(mBaseActivity, "再按一次退出应用");
        }

        Observable.timer(1, TimeUnit.SECONDS, AndroidSchedulers.mainThread())
                .subscribe(aLong -> back = 0);

    }

//    @PermissionYes(100)
    protected void getLocationYes(List<String> grantedPermissions) {
        if (isCameraClick) {
            ActivityUtils.init().start(this, ActivityCustomCapture.class);
        } else {
            if (verBean != null) {
                DownloadByNotify.byNotify(this, Uri.parse(verBean.file_url));
            }
        }
    }

//    @PermissionNo(100)
//    protected void getLocationNo(List<String> deniedPermissions) {
//        if (AndPermission.hasAlwaysDeniedPermission(this, deniedPermissions)) {
//            AndPermission.defaultSettingDialog(this, 100).show();
//        }
//    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);

    }

    /**
     * 显示更新对话框
     */
    VersionBean.VerBean verBean;

    public void showNoticeDialog(VersionBean.VerBean verBean) {
        // 构造对话框
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("更新提示");
        builder.setMessage(verBean.content);
        // 更新
        builder.setPositiveButton("立即更新", (dialog, which) -> {
            this.verBean = verBean;
            dialog.dismiss();
            isCameraClick = false;
            setPermission(this, 100, WRITE_EXTERNAL_STORAGE, CAMERA);
        });
        // 稍后更新
        builder.setNegativeButton("以后更新", (dialog, which) -> dialog.dismiss());
        Dialog noticeDialog = builder.create();
        noticeDialog.show();

    }
}
