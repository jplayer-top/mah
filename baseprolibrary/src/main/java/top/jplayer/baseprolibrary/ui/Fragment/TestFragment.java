package top.jplayer.baseprolibrary.ui.Fragment;

import android.view.View;
import android.widget.TextView;

import top.jplayer.baseprolibrary.R;
import top.jplayer.baseprolibrary.utils.LogUtil;

/**
 * Created by Obl on 2018/1/23.
 * top.jplayer.baseprolibrary.ui.Fragment
 */

public class TestFragment extends SuperBaseFragment {
    @Override
    protected void initData(View rootView) {
        TextView base_text = rootView.findViewById(R.id.base_text);
        base_text.setText(getClass().getSimpleName());
    }

    @Override
    public int initLayout() {
        return R.layout.fragment_base;
    }

    @Override
    protected void onShowFragment() {
        super.onShowFragment();
        LogUtil.e("isShow---");
    }
}
