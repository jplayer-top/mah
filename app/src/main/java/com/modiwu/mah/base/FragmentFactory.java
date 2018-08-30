package com.modiwu.mah.base;

import android.support.v4.util.ArrayMap;

import com.modiwu.mah.ui.fragment.CarpenterFragment;
import com.modiwu.mah.ui.fragment.DecorateFragment;
import com.modiwu.mah.ui.fragment.HomeFragment;
import com.modiwu.mah.ui.fragment.MeFragment;
import com.modiwu.mah.ui.fragment.SchemeFragment;

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
                fragment = new DecorateFragment();
                break;
            case 3:
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
