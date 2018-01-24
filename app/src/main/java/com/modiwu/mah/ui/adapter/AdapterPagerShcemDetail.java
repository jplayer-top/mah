package com.modiwu.mah.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.modiwu.mah.ui.Fragment.SchemeFloorFragment;
import com.modiwu.mah.ui.Fragment.SchemeHardFragment;

import java.util.ArrayList;
import java.util.List;

import top.jplayer.baseprolibrary.ui.Fragment.SuperBaseFragment;
import top.jplayer.baseprolibrary.ui.Fragment.TestFragment;
import top.jplayer.baseprolibrary.ui.adapter.BaseViewPagerFragmentAdapter;

/**
 * Created by Obl on 2018/1/23.
 * com.modiwu.mah.ui.adapter
 */

public class AdapterPagerShcemDetail extends BaseViewPagerFragmentAdapter<String> {

    private final SchemeHardFragment mHardFragment;

    public AdapterPagerShcemDetail(FragmentManager fm, List<String> data) {
        super(fm, data);
        mFragmentList = new ArrayList<>();
        mHardFragment = new SchemeHardFragment();
        mFragmentList.add(mHardFragment);
        mFragmentList.add(new SchemeHardFragment());
        mFragmentList.add(new SchemeHardFragment());
        mFragmentList.add(new TestFragment());
        mFragmentList.add(new SchemeFloorFragment());
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
