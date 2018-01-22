package top.jplayer.baseprolibrary.ui.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import top.jplayer.baseprolibrary.utils.LogUtil;

/**
 * Created by Obl on 2018/1/19.
 * top.jplayer.baseprolibrary.ui.Fragment
 */

public abstract class SuperBaseFragment extends Fragment {
    protected View rootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(initLayout(), container, false);
        rootView = view;
        LogUtil.e("1");
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData(rootView);
        LogUtil.e("2");

    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (isHidden()) {
            onHideFragment();
        } else {
            onShowFragment();
        }
    }

    protected void onShowFragment() {

    }

    protected void onHideFragment() {
    }

    protected abstract void initData(View rootView);

    public abstract int initLayout();
}
