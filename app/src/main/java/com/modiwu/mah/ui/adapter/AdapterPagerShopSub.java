package com.modiwu.mah.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.modiwu.mah.ui.fragment.ShopSubFragment;

import java.util.ArrayList;
import java.util.List;

import top.jplayer.baseprolibrary.ui.Fragment.SuperBaseFragment;
import top.jplayer.baseprolibrary.ui.adapter.BaseViewPagerFragmentAdapter;

/**
 * Created by Obl on 2018/1/23.
 * com.modiwu.mah.ui.adapter
 */

public class AdapterPagerShopSub extends BaseViewPagerFragmentAdapter<String> {


    public AdapterPagerShopSub(FragmentManager fm, List<String> data) {
        super(fm, data);
        mFragmentList = new ArrayList<>();
        for (String ignored : data) {
            ShopSubFragment fragment = new ShopSubFragment();
            mFragmentList.add(fragment);
        }
    }

    private List<SuperBaseFragment> mFragmentList = null;

    @Override

    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return data.get(position);
    }
}
