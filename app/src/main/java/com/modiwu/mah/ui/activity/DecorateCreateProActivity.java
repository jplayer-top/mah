package com.modiwu.mah.ui.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.modiwu.mah.R;
import com.modiwu.mah.base.BaseCommonActivity;
import com.modiwu.mah.ui.adapter.DecorateItemCommonAdapter;
import com.modiwu.mah.ui.adapter.DecorateItemPicAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Obl on 2018/9/10.
 * com.modiwu.mah.ui.activity
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */

public class DecorateCreateProActivity extends BaseCommonActivity {
    @BindView(R.id.tvInputCity)
    TextView mTvInputCity;
    @BindView(R.id.tvInputLocal)
    TextView mTvInputLocal;
    @BindView(R.id.tvInputFloorNum)
    TextView mTvInputFloorNum;
    @BindView(R.id.tvInputUnitNum)
    TextView mTvInputUnitNum;
    @BindView(R.id.tvInputDoorNum)
    TextView mTvInputDoorNum;
    @BindView(R.id.recyclerViewOwner)
    RecyclerView mRecyclerViewOwner;
    @BindView(R.id.recyclerViewPic)
    RecyclerView mRecyclerViewPic;
    @BindView(R.id.recyclerViewVisor)
    RecyclerView mRecyclerViewVisor;
    @BindView(R.id.recyclerViewConst)
    RecyclerView mRecyclerViewConst;
    private Unbinder mUnbinder;

    @Override
    public int setBaseLayout() {
        return R.layout.activity_decorate_create_pro;
    }

    @Override
    public void initBaseData() {
        mUnbinder = ButterKnife.bind(this,mFlRootView);
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
        mRecyclerViewPic.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        mRecyclerViewPic.setAdapter(new DecorateItemPicAdapter(data));

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
    }
}
