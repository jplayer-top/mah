package com.modiwu.mah.ui.fragment;

import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.modiwu.mah.R;
import com.modiwu.mah.base.BaseFragment;
import com.modiwu.mah.mvp.model.bean.ShopGoodsInfoBean;
import com.modiwu.mah.ui.activity.ShopDetailActivity;

import java.util.List;
import java.util.Locale;

import top.jplayer.baseprolibrary.utils.SizeUtils;

/**
 * Created by Obl on 2018/2/8.
 * com.modiwu.mah.ui.fragment
 */

public class ShopCanShuFragment extends BaseFragment {

    List<ShopGoodsInfoBean.ArgsBean> mArgs;

    @Override
    public int initLayout() {
        return R.layout.fragment_shop_detail_canshu;
    }

    ShopDetailActivity activity;

    @Override
    protected void initData(View rootView) {
        activity = (ShopDetailActivity) getActivity();
        mArgs = activity.bean.args;
        LinearLayout llParent = rootView.findViewById(R.id.llParent);
        if (mArgs != null) {
            for (ShopGoodsInfoBean.ArgsBean arg : mArgs) {
                TextView textView = new TextView(getContext());
                textView.setText(String.format(Locale.CHINA, "%s:%s", arg.arg_name, arg.arg_value));
                textView.setGravity(Gravity.CENTER_VERTICAL);
                textView.setBackgroundColor(Color.WHITE);
                textView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, SizeUtils
                        .dp2px(50)));
                textView.setPadding(SizeUtils.dp2px(15), 0, 0, 0);
                llParent.addView(textView);
            }
        }
    }
}
