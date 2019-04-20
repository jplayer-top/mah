package com.modiwu.mah.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.modiwu.mah.ui.activity.SchemeDetailActivity;
import com.modiwu.mah.ui.fragment.SchemeAllFragment;
import com.modiwu.mah.ui.fragment.SchemeFloorFragment;
import com.modiwu.mah.ui.fragment.SchemeHardFragment;
import com.modiwu.mah.ui.fragment.SchemeSingleFragment;
import com.modiwu.mah.ui.fragment.SchemeSoftFragment;

import java.util.ArrayList;
import java.util.List;

import top.jplayer.baseprolibrary.ui.Fragment.SuperBaseFragment;
import top.jplayer.baseprolibrary.ui.adapter.BaseViewPagerFragmentAdapter;

/**
 * Created by Obl on 2018/1/23.
 * com.modiwu.mah.ui.adapter
 */

public class AdapterPagerSchemeDetail extends BaseViewPagerFragmentAdapter<String> {


    public AdapterPagerSchemeDetail(FragmentManager fm, List<String> data, SchemeDetailActivity activity) {
        super(fm, data);
        mFragmentList = new ArrayList<>();
        /**
         * 整装
         */
        SchemeAllFragment allFragment = new SchemeAllFragment();
        mFragmentList.add(allFragment);
        if (!activity.ttype) {

            /**
             * 硬装
             */
            SchemeHardFragment hardFragment = new SchemeHardFragment();
            mFragmentList.add(hardFragment);
            /**
             * 软装
             */
            SchemeSoftFragment softFragment = new SchemeSoftFragment();
            mFragmentList.add(softFragment);
            /**
             * 单品
             */
            SchemeSingleFragment singleFragment = new SchemeSingleFragment();
            mFragmentList.add(singleFragment);
        } else {
            /**
             * 楼盘
             */
            SchemeFloorFragment floorFragment = new SchemeFloorFragment();
            mFragmentList.add(floorFragment);
            /**
             * 单品
             */
            SchemeSingleFragment singleFragment = new SchemeSingleFragment();
            mFragmentList.add(singleFragment);

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
