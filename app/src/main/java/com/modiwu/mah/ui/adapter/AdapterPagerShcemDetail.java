package com.modiwu.mah.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.modiwu.mah.ui.Fragment.SchemeFloorFragment;
import com.modiwu.mah.ui.Fragment.SchemeHardFragment;

import java.util.List;

import top.jplayer.baseprolibrary.ui.Fragment.TestFragment;
import top.jplayer.baseprolibrary.ui.adapter.BaseViewPagerFragmentAdapter;

/**
 * Created by Obl on 2018/1/23.
 * com.modiwu.mah.ui.adapter
 */

public class AdapterPagerShcemDetail extends BaseViewPagerFragmentAdapter<String> {
    public AdapterPagerShcemDetail(FragmentManager fm, List<String> data) {
        super(fm, data);
    }

    @Override
    public Fragment getItem(int position) {
        return position == 0 || position == 1 ?
                new SchemeHardFragment() : position ==3 ?
                new SchemeFloorFragment():new TestFragment();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return data.get(position);
    }
}
