package com.modiwu.mah.ui.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.modiwu.mah.R;
import com.modiwu.mah.base.BaseCommonActivity;
import com.modiwu.mah.ui.adapter.DecorateItemCommonAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.bingoogolapple.bgabanner.BGABanner;
import top.jplayer.baseprolibrary.glide.GlideUtils;

/**
 * Created by Obl on 2018/9/11.
 * com.modiwu.mah.ui.activity
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */

public class DecorateProDetailActivity extends BaseCommonActivity {


    @BindView(R.id.recyclerViewOwner)
    RecyclerView mRecyclerViewOwner;
    @BindView(R.id.bgaBanner)
    BGABanner mBGABanner;
    @BindView(R.id.recyclerViewVisor)
    RecyclerView mRecyclerViewVisor;
    @BindView(R.id.recyclerViewConst)
    RecyclerView mRecyclerViewConst;
    private Unbinder mUnbinder;

    @Override
    public int setBaseLayout() {
        return R.layout.activity_decorate_detail_pro;
    }

    @Override
    public void initBaseData() {
        mUnbinder = ButterKnife.bind(this, mFlRootView);
        ArrayList<String> data = new ArrayList<>();
        data.add("1");
        data.add("1");
        data.add("1");
        data.add("1");
        mRecyclerViewOwner.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        mRecyclerViewOwner.setAdapter(new DecorateItemCommonAdapter(data));
        mRecyclerViewVisor.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        mRecyclerViewVisor.setAdapter(new DecorateItemCommonAdapter(data));
        mRecyclerViewConst.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        mRecyclerViewConst.setAdapter(new DecorateItemCommonAdapter(data));
        mBGABanner.setAdapter((banner, itemView, model, position) -> Glide.with(mBaseActivity).load(model)
                .apply(GlideUtils.init().options(R.drawable.placeholder))
                .into((ImageView) itemView));
        mBGABanner.setData(data,data);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
    }
}
