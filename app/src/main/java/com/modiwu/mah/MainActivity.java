package com.modiwu.mah;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;

import com.modiwu.mah.base.BaseSpecialActivity;
import com.modiwu.mah.base.FragmentFactory;
import com.modiwu.mah.mvp.model.event.HomeTypeModeEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import devlight.io.library.ntb.NavigationTabBar;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import top.jplayer.baseprolibrary.listener.NetNavigationBarListener;
import top.jplayer.baseprolibrary.utils.ToastUtils;

public class MainActivity extends BaseSpecialActivity {

    public FrameLayout mFlFragment;
    public NavigationTabBar mNavigationTabBar;

    @Override
    public int setBaseLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initBaseData() {
        mNavigationTabBar = contentView.findViewById(R.id.ntb);
        mFlFragment = contentView.findViewById(R.id.flFragment);
        bottomBar(mNavigationTabBar);
        EventBus.getDefault().register(this);
    }


    /**
     * 设置底部栏
     */
    private void bottomBar(NavigationTabBar navigationTabBar) {

        final ArrayList<NavigationTabBar.Model> models = new ArrayList<>();
        String[] titleArrs = new String[]{"首页", "方案", "匠·器", "我的"};
        int[] drawArrs = new int[]{R.drawable.main_home, R.drawable.main_scheme, R.drawable.main_charpenter, R.drawable.main_me};
        for (int i = 0; i < titleArrs.length; i++) {
            models.add(new NavigationTabBar.Model.Builder(
                    getResources().getDrawable(drawArrs[i]),
                    getResources().getColor(top.jplayer.baseprolibrary.R.color.trans))
                    .title(titleArrs[i]).build());
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
                mNavigationTabBar.setModelIndex(1);
                break;
            case 2://匠
                mNavigationTabBar.setModelIndex(2);
                carFragmentType = event.type;
                break;
            case 3://器
                mNavigationTabBar.setModelIndex(2);
                carFragmentType = event.type;
                break;
        }
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);

    }
}
