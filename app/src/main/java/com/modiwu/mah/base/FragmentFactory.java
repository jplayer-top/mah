package com.modiwu.mah.base;

import android.support.v4.util.ArrayMap;

import com.modiwu.mah.ui.Fragment.CarpenterFragment;
import com.modiwu.mah.ui.Fragment.HomeFragment;
import com.modiwu.mah.ui.Fragment.MeFragment;
import com.modiwu.mah.ui.Fragment.SchemeFragment;

import java.util.Map;

/**
 * Created by Obl on 2017/8/11.
 * com.ilanchuang.xiaoi.base
 */

public class FragmentFactory {
    private static FragmentFactory mFactory;

    public static FragmentFactory instance() {
        if (mFactory == null) {
            mFactory = new FragmentFactory();
        }
        return mFactory;
    }

    private HomeFragment mHomeFragment;

    public HomeFragment singleFirstFragment() {
        if (mHomeFragment == null) {
            mHomeFragment = new HomeFragment();
        }
        return mHomeFragment;
    }

    private Map<Integer, BaseFragment> map = new ArrayMap<>();

    public BaseFragment getSingleFragment(int position) {
        BaseFragment fragment;
        if (map.containsKey(position)) {
            return map.get(position);
        }
        switch (position) {
            case 0:
                fragment = new HomeFragment();
                break;
            case 1:
                fragment = new SchemeFragment();
                break;
            case 2:
                fragment = new CarpenterFragment();
                break;
            default:
                fragment = new MeFragment();
                break;
        }
        map.put(position, fragment);
        return fragment;
    }
}
