package com.modiwu.mah.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.modiwu.mah.ui.fragment.OrderIsPayFragment;
import com.modiwu.mah.ui.fragment.OrderPayOkFragment;
import com.modiwu.mah.ui.fragment.OrderWillPayFragment;

import java.util.ArrayList;
import java.util.List;

import top.jplayer.baseprolibrary.ui.Fragment.SuperBaseFragment;
import top.jplayer.baseprolibrary.ui.adapter.BaseViewPagerFragmentAdapter;

/**
 * Created by Obl on 2018/1/23.
 * com.modiwu.mah.ui.adapter
 */

public class AdapterPagerOrderList extends BaseViewPagerFragmentAdapter<String> {


    public AdapterPagerOrderList(FragmentManager fm, List<String> data) {
        super(fm, data);
        mFragmentList = new ArrayList<>();
        /**
         * 待付款
         */
        OrderWillPayFragment willPayFragment = new OrderWillPayFragment();
        mFragmentList.add(willPayFragment);
        /**
         * 已付款
         */
        OrderIsPayFragment isPayFragment = new OrderIsPayFragment();
        mFragmentList.add(isPayFragment);
        /**
         * 已完成
         */
        OrderPayOkFragment payOkFragment = new OrderPayOkFragment();
        mFragmentList.add(payOkFragment);
    }

    public List<SuperBaseFragment> mFragmentList = null;

    @Override

    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return data.get(position);
    }
}
