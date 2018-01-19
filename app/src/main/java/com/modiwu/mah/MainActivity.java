package com.modiwu.mah;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;

import com.modiwu.mah.base.BaseSpecialActivity;
import com.modiwu.mah.base.FragmentFactory;

import java.util.ArrayList;

import devlight.io.library.ntb.NavigationTabBar;
import top.jplayer.baseprolibrary.listener.NetNavigationBarListener;

public class MainActivity extends BaseSpecialActivity {

    public FrameLayout mFlFragment;

    @Override
    public int setBaseLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initBaseData() {
        final NavigationTabBar navigationTabBar = mBaseView.findViewById(R.id.ntb);
        mFlFragment = mBaseView.findViewById(R.id.flFragment);
        bottomBar(navigationTabBar);
    }


    /**
     * 设置底部栏
     */
    private void bottomBar(NavigationTabBar navigationTabBar) {

        final ArrayList<NavigationTabBar.Model> models = new ArrayList<>();
        String[] titleArrs = new String[]{"首页", "方案", "匠·器", "我的"};
        int[] drawArrs = new int[]{R.drawable.home, R.drawable.home, R.drawable.home, R.drawable.home};
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
}
