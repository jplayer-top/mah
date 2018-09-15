package com.modiwu.mah.ui.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.modiwu.mah.R;
import com.modiwu.mah.base.BaseCommonActivity;
import com.modiwu.mah.mvp.model.bean.ProInfoBean;
import com.modiwu.mah.mvp.presenter.DecorateBasePresenter;
import com.modiwu.mah.ui.adapter.DecorateItemCommonAdapter;
import com.modiwu.mah.ui.dialog.DialogSelectOtherMan;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.bingoogolapple.bgabanner.BGABanner;
import io.reactivex.Observable;
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
    @BindView(R.id.tvProName)
    TextView tvProName;
    @BindView(R.id.tvProIdNum)
    TextView tvProIdNum;
    @BindView(R.id.recyclerViewVisor)
    RecyclerView mRecyclerViewVisor;
    @BindView(R.id.recyclerViewConst)
    RecyclerView mRecyclerViewConst;
    private Unbinder mUnbinder;
    private DecorateBasePresenter mPresenter;
    private DecorateItemCommonAdapter mManAdapter;
    private List<ProInfoBean.CommonBean> mCommonManBeans;
    private List<ProInfoBean.CommonBean> mCommonSvsBeans;
    private List<ProInfoBean.CommonBean> mCommonWorkerBeans;
    private DecorateItemCommonAdapter mSuperViewAdapter;
    private DecorateItemCommonAdapter mWorkerAdapter;
    private String mProId;
    private DialogSelectOtherMan mSelectOtherMan;

    @Override
    public int setBaseLayout() {
        return R.layout.activity_decorate_detail_pro;
    }

    @Override
    public void initBaseData() {
        mUnbinder = ButterKnife.bind(this, mFlRootView);
        mPresenter = new DecorateBasePresenter(this);
        mProId = mBundle.getString("pro_id");
        mPresenter.getProInfo(mProId);
        mSelectOtherMan = new DialogSelectOtherMan(this);
        mRecyclerViewOwner.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        mManAdapter = new DecorateItemCommonAdapter(null);
        mRecyclerViewOwner.setAdapter(mManAdapter);
        mManAdapter.addFooterView(View.inflate(this, R.layout.adapter_footer_edit_pro_item, null));
        mManAdapter.getFooterLayout().setOnClickListener(v -> {
            if (mSelectOtherMan != null && !mSelectOtherMan.isShowing()) {
                mSelectOtherMan.setTip("业主")
                        .setOnFindListener(phone -> {
                            mPresenter.addMan(phone, mProId);
                        }).show();
            }
        });
        mRecyclerViewVisor.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        mSuperViewAdapter = new DecorateItemCommonAdapter(null);
        mRecyclerViewVisor.setAdapter(mSuperViewAdapter);
        mSuperViewAdapter.addFooterView(View.inflate(this, R.layout.adapter_footer_edit_pro_item, null));
        mSuperViewAdapter.getFooterLayout().setOnClickListener(v -> {
            if (mSelectOtherMan != null && !mSelectOtherMan.isShowing()) {
                mSelectOtherMan.setTip("负责人")
                        .setOnFindListener(phone -> {
                            mPresenter.addSuperView(phone, mProId);
                        }).show();
            }
        });
        mRecyclerViewConst.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        mWorkerAdapter = new DecorateItemCommonAdapter(null);
        mRecyclerViewConst.setAdapter(mWorkerAdapter);
        mWorkerAdapter.addFooterView(View.inflate(this, R.layout.adapter_footer_edit_pro_item, null));
        mWorkerAdapter.getFooterLayout().setOnClickListener(v -> {
            if (mSelectOtherMan != null && !mSelectOtherMan.isShowing()) {
                mSelectOtherMan.setTip("施工人员")
                        .setOnFindListener(phone -> {
                            mPresenter.addWorker(phone, mProId);
                        }).show();
            }
        });
        mBGABanner.setAdapter((banner, itemView, model, position) ->
                Glide.with(mBaseActivity)
                        .load(model)
                        .apply(GlideUtils.init().options(R.drawable.placeholder))
                        .into((ImageView) itemView));
        tvBarRight.setVisibility(View.VISIBLE);
        tvBarRight.setText("编辑");
        tvBarRight.setOnClickListener(v -> {
            boolean isEdit = "编辑".equals(tvBarRight.getText().toString());
            tvBarRight.setText(isEdit ? "保存" : "编辑");
            Observable.fromIterable(mCommonManBeans).subscribe(commonBean -> commonBean.isEdit = isEdit);
            Observable.fromIterable(mCommonSvsBeans).subscribe(commonBean -> commonBean.isEdit = isEdit);
            Observable.fromIterable(mCommonWorkerBeans).subscribe(commonBean -> commonBean.isEdit = isEdit);
            mManAdapter.notifyDataSetChanged();
            mSuperViewAdapter.notifyDataSetChanged();
            mWorkerAdapter.notifyDataSetChanged();
        });
    }

    @Override
    public void addMan() {
        super.addMan();
        addSendOk();
    }

    private void addSendOk() {
        if (mSelectOtherMan != null && mSelectOtherMan.isShowing()) {
            mSelectOtherMan.dismiss();
        }
    }

    @Override
    public void addSuperView() {
        super.addSuperView();
        addSendOk();
    }

    @Override
    public void addWorker() {
        super.addWorker();
        addSendOk();
    }

    @Override
    public void getProInfo(ProInfoBean bean) {
        super.getProInfo(bean);
        ProInfoBean.ProjectBean project = bean.project;
        List<String> imgsurl = project.imgsurl;
        mBGABanner.setData(imgsurl, null);
        tvProName.setText(project.project_name);
        tvProIdNum.setText(project.project_id);
        Gson gson = new Gson();
        mCommonManBeans = gson.fromJson(gson.toJson(bean.owners), new TypeToken<List<ProInfoBean.CommonBean>>() {
        }.getType());
        mManAdapter.setNewData(mCommonManBeans);

        mCommonSvsBeans = gson.fromJson(gson.toJson(bean.svs), new TypeToken<List<ProInfoBean.CommonBean>>() {
        }.getType());
        mSuperViewAdapter.setNewData(mCommonSvsBeans);

        mCommonWorkerBeans = gson.fromJson(gson.toJson(bean.wokers), new TypeToken<List<ProInfoBean.CommonBean>>() {
        }.getType());
        mWorkerAdapter.setNewData(mCommonWorkerBeans);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
    }
}
