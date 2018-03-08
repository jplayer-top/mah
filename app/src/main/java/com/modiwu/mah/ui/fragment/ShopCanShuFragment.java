package com.modiwu.mah.ui.fragment;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.modiwu.mah.R;
import com.modiwu.mah.base.BaseFragment;
import com.modiwu.mah.mvp.model.bean.ShopGoodsInfoBean;
import com.modiwu.mah.ui.activity.ShopDetailActivity;

import java.util.List;

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
                View view = View.inflate(getContext(), R.layout.item_canshu_fragment, null);
                TextView tv1 = view.findViewById(R.id.tv1);
                TextView tv2 = view.findViewById(R.id.tv2);
                tv1.setText(arg.arg_name);
                tv2.setText(arg.arg_value);
                llParent.addView(view);
            }
        }
    }
}
