package com.modiwu.mah.ui.fragment;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.modiwu.mah.R;
import com.modiwu.mah.base.BaseFragment;
import com.modiwu.mah.mvp.model.bean.DecorateManBean;
import com.modiwu.mah.mvp.model.bean.DecorateWorkerBean;
import com.modiwu.mah.mvp.model.event.SelProIdDecorateEvent;
import com.modiwu.mah.mvp.model.event.SelectDecorateEvent;
import com.modiwu.mah.mvp.presenter.DecorateProInfoPresenter;
import com.modiwu.mah.ui.activity.DecorateAddProjectActivity;
import com.modiwu.mah.ui.activity.DecorateAllProjectActivity;
import com.modiwu.mah.ui.activity.DecorateCreateProActivity;
import com.modiwu.mah.ui.activity.DecorateMessageHasActivity;
import com.modiwu.mah.ui.activity.DecorateProDetailActivity;
import com.modiwu.mah.ui.activity.DecorateSelectActivity;
import com.modiwu.mah.ui.activity.DecorateSendPushActivity;
import com.modiwu.mah.ui.activity.DecorateShiGongActivity;
import com.modiwu.mah.ui.adapter.DecorateAdapter;
import com.modiwu.mah.ui.adapter.DecorateProgressAdapter;
import com.modiwu.mah.ui.dialog.DialogChangeMan;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import top.jplayer.baseprolibrary.utils.ActivityUtils;
import top.jplayer.baseprolibrary.utils.DateUtils;
import top.jplayer.baseprolibrary.utils.SharePreUtil;
import top.jplayer.baseprolibrary.widgets.MultipleStatusView;

/**
 * Created by Obl on 2018/8/29.
 * com.modiwu.mah.ui.fragment
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */

public class DecorateFragment extends BaseFragment {
    @BindView(R.id.ivGoBack)
    ImageView mIvGoBack;
    @BindView(R.id.tvBarTitle)
    TextView mTvBarTitle;
    @BindView(R.id.ivBarSearch)
    ImageView mIvBarSearch;
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.multiplestatusview)
    MultipleStatusView mMultiplestatusview;
    @BindView(R.id.smartRefreshLayout)
    SmartRefreshLayout mSmartRefreshLayout;
    private Unbinder mUnbinder;
    private DecorateAdapter mAdapter;
    private RecyclerView mRecyclerViewProgress;
    private DecorateProInfoPresenter mPresenter;
    private View mHeaderWorker;
    private View mHeaderProgress;
    private TextView mTvTitleHeader;
    private LinearLayout mLlIntroduction;
    private TextView mTvProDetail;
    private TextView mTvWorkerFirstName;
    private TextView mTvWorkerName;
    private TextView mTvWorkerSex;
    private TextView mTvWorkerAge;
    private TextView mTvWorkerYear;
    private TextView mTvWorkerType;
    private TextView mTvWorkerIngEd;
    private TextView mTvWorkerRating;
    private RatingBar mRatingBar;
    private View mTvAllPro;
    private View mTvAddPro;
    private View mTvCreatePro;
    private String mProId;
    private DecorateProgressAdapter mProgressAdapter;
    private String mSelectData;
    private TextView mTvSendPush;
    private ConstraintLayout mConManSure;

    @Override
    public int initLayout() {
        return R.layout.fragment_decorate;
    }

    @Override
    protected void initData(View rootView) {
        super.initData(rootView);
        EventBus.getDefault().register(this);
        mProId = (String) SharePreUtil.getData(this.getContext(), "decorate_pro_id", "0");
        mMultipleStatusView = rootView.findViewById(R.id.multiplestatusview);
        smartRefreshLayout = rootView.findViewById(R.id.smartRefreshLayout);
        mUnbinder = ButterKnife.bind(this, rootView);
        mTvBarTitle.setText("我的家装");
        mIvGoBack.setVisibility(View.VISIBLE);
        mIvBarSearch.setVisibility(View.VISIBLE);
        mIvGoBack.setImageDrawable(getResources().getDrawable(R.drawable.decorate_yezhu));
        mIvBarSearch.setImageDrawable(getResources().getDrawable(R.drawable.decorate_notification));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        mAdapter = new DecorateAdapter(null);
        mRecyclerView.setAdapter(mAdapter);
        mIvGoBack.setOnClickListener(v -> {
            ActivityUtils.init().start(this.getContext(), DecorateSelectActivity.class, "身份选择");
        });
        mIvBarSearch.setOnClickListener(v -> {
            ActivityUtils.init().start(this.getContext(), DecorateMessageHasActivity.class, "消息通知");
        });
        View header = View.inflate(this.getContext(), R.layout.layout_header_decorate, null);
        initHeader(header);
        mAdapter.addHeaderView(header, 0);

        mHeaderProgress = View.inflate(this.getContext(), R.layout.layout_header_select_progress, null);
        initHeaderProgress(mHeaderProgress);

        mHeaderWorker = View.inflate(this.getContext(), R.layout.layout_header_decorate_workerinfo, null);
        initHeaderWorkerInfo(mHeaderWorker);

        showLoading();
        mPresenter = new DecorateProInfoPresenter(this);
        mSelectData = (String) SharePreUtil.getData(this.getContext(), "decorate_select", "业主");
        requestInfoByText(mSelectData);
        smartRefreshLayout.setOnRefreshListener(refresh -> {
            requestInfoByText((String) SharePreUtil.getData(this.getContext(), "decorate_select", "业主"));
        });

    }


    private void initHeaderProgress(View header_progress) {
        mRecyclerViewProgress = header_progress.findViewById(R.id.recyclerViewHeader);
        mConManSure = header_progress.findViewById(R.id.conManSure);
        mTvSendPush = header_progress.findViewById(R.id.tvSendPush);
        mRecyclerViewProgress.setLayoutManager(new LinearLayoutManager(this.getContext(), LinearLayoutManager
                .HORIZONTAL, false));
        mProgressAdapter = new DecorateProgressAdapter(null);
        mRecyclerViewProgress.setAdapter(mProgressAdapter);
    }

    private void initHeader(View header) {
        mTvAllPro = header.findViewById(R.id.tvAllPro);
        mTvAllPro.setOnClickListener(v -> {
            ActivityUtils.init().start(this.getContext(), DecorateAllProjectActivity.class, "所有项目");
        });
        mTvAddPro = header.findViewById(R.id.tvAddPro);
        mTvAddPro.setOnClickListener(v -> {
            ActivityUtils.init().start(this.getContext(), DecorateAddProjectActivity.class, "加入项目");
        });
        header.findViewById(R.id.tvChangeMan).setOnClickListener(v -> {
            new DialogChangeMan(this.getContext()).show();
        });

        mTvCreatePro = header.findViewById(R.id.tvCreatePro);
        mTvCreatePro.setOnClickListener(v -> {
            ActivityUtils.init().start(getContext(), DecorateCreateProActivity.class, "创建项目");
        });
        mTvTitleHeader = header.findViewById(R.id.tvProjectName);
        mTvProDetail = header.findViewById(R.id.tvProDetail);
        mLlIntroduction = header.findViewById(R.id.llIntroduction);

    }

    private void initHeaderWorkerInfo(View header) {
        mTvWorkerFirstName = header.findViewById(R.id.tvFirstName);
        mTvWorkerName = header.findViewById(R.id.tvName);
        mTvWorkerSex = header.findViewById(R.id.tvWorkerSex);
        mTvWorkerAge = header.findViewById(R.id.tvWorkerAge);
        mTvWorkerYear = header.findViewById(R.id.tvWorkerYear);
        mTvWorkerType = header.findViewById(R.id.tvWorkerType);
        mTvWorkerIngEd = header.findViewById(R.id.tvWorkerIngEd);
        mTvWorkerRating = header.findViewById(R.id.tvWorkerRating);
        mRatingBar = header.findViewById(R.id.ratingBar);
    }

    @Subscribe
    public void onEvnet(SelectDecorateEvent event) {
        requestInfoByText(event.type);
    }

    @Subscribe
    public void onEvnet(SelProIdDecorateEvent event) {
        this.mProId = event.pro_id;
        SharePreUtil.saveData(getContext(), "decorate_pro_id", mProId);
        requestInfoByText(event.type);
    }

    private void requestInfoByText(String type) {
        int des = R.drawable.decorate_yezhu;
        if ("施工".equals(type)) {
            des = R.drawable.decorate_shigong;
            mPresenter.requestWorkerPro(mProId);
        } else if ("监理".equals(type)) {
            des = R.drawable.decorate_jianli;
            mPresenter.requestSVPro(mProId);
        } else {
            mPresenter.requestManPro();
        }
        mIvGoBack.setImageDrawable(getResources().getDrawable(des));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
        EventBus.getDefault().unregister(this);
    }

    public void responseMan(DecorateManBean baseBean) {
        smartRefreshLayout.finishRefresh(true);
        mMultipleStatusView.showContent();

        mTvCreatePro.setVisibility(View.VISIBLE);
        mTvAllPro.setVisibility(View.GONE);
        mLlIntroduction.setVisibility(View.INVISIBLE);
        mTvProDetail.setVisibility(View.VISIBLE);

        if ("0".equals(baseBean.haspj)) {
            mTvTitleHeader.setText("尊敬的用户，您暂时没有装修项目");
            mTvProDetail.setText("赶紧创建一个吧");
        } else {
            mProId = baseBean.project.project_id;
            mAdapter.removeHeaderView(mHeaderWorker);
            mAdapter.addHeaderView(mHeaderProgress, 1);
            mTvTitleHeader.setText(baseBean.project.project_name);
            mProgressAdapter.setNewData(baseBean.tasks);
            mTvProDetail.setText("项目介绍 >");
            mTvProDetail.setOnClickListener(v -> {
                if (!"0".equals(baseBean.haspj)) {
                    Bundle bundle = new Bundle();
                    bundle.putString("pro_id", mProId);
                    ActivityUtils.init().start(getContext(), DecorateProDetailActivity.class, "项目介绍", bundle);
                }

            });
        }

    }

    public void responseSv(DecorateManBean baseBean) {
        smartRefreshLayout.finishRefresh(true);
        mMultipleStatusView.showContent();

        mTvCreatePro.setVisibility(View.GONE);
        mTvAllPro.setVisibility(View.VISIBLE);

        mTvSendPush.setVisibility(View.VISIBLE);

        mLlIntroduction.setVisibility(View.INVISIBLE);
        mTvProDetail.setVisibility(View.VISIBLE);

        if ("0".equals(baseBean.issv)) {
            ActivityUtils.init().start(getContext(), DecorateShiGongActivity.class, "我是监理人员");
            return;
        }
        if ("0".equals(baseBean.haspj)) {
            mTvTitleHeader.setText("尊敬的监理，您暂时没有装修项目");
            mTvProDetail.setText("赶紧加入一个吧");
        } else {
            mProId = baseBean.project.project_id;
            mAdapter.removeHeaderView(mHeaderWorker);
            mAdapter.addHeaderView(mHeaderProgress, 1);
            mAdapter.setNewData(baseBean.tasks.get(0).works);
            mAdapter.setOnItemChildClickListener((adapter, view, position) -> {
                if (view.getId() == R.id.ivPushDel) {
                    mPresenter.requestDelPush(mProId, mAdapter.getData().get(position).work_id + "");
                }
                return false;
            });
            mTvTitleHeader.setText(baseBean.project.project_name);
            Gson gson = new Gson();
            String json = gson.toJson(baseBean.tasks);
            List<DecorateManBean.TasksBean> list = gson.fromJson(json, new
                    TypeToken<List<DecorateManBean.TasksBean>>() {
                    }.getType());
            mProgressAdapter.setNewData(list);
            mTvProDetail.setText("项目介绍 >");
            mTvProDetail.setOnClickListener(v -> {
                if (!"0".equals(baseBean.haspj)) {
                    Bundle bundle = new Bundle();
                    bundle.putString("pro_id", mProId);
                    ActivityUtils.init().start(getContext(), DecorateProDetailActivity.class, "项目介绍", bundle);
                }

            });
            mProgressAdapter.setOnItemClickListener((adapter, view, position) -> {

            });
            mTvSendPush.setEnabled(true);
            mTvSendPush.setOnClickListener(v -> {
                Bundle bundle = new Bundle();
                DecorateManBean.TasksBean tasksBean = mProgressAdapter.getData().get(0);
                bundle.putString("project_id", tasksBean.project_id);
                bundle.putString("task_id", tasksBean.task_id + "");
                ActivityUtils.init().start(getContext(), DecorateSendPushActivity.class, "添加新推送", bundle);
            });
        }
    }

    public void responseWorker(DecorateWorkerBean baseBean) {
        smartRefreshLayout.finishRefresh(true);
        mMultipleStatusView.showContent();
        mTvAllPro.setVisibility(View.VISIBLE);
        mTvCreatePro.setVisibility(View.GONE);
        mLlIntroduction.setVisibility(View.VISIBLE);
        mTvProDetail.setVisibility(View.INVISIBLE);
        if ("0".equals(baseBean.iswm)) {
            ActivityUtils.init().start(getContext(), DecorateShiGongActivity.class, "我是施工人员");
            return;
        }

        mAdapter.removeHeaderView(mHeaderProgress);
        mAdapter.addHeaderView(mHeaderWorker, 1);
        DecorateWorkerBean.InfoBean infoBean = baseBean.info;
        mTvTitleHeader.setText(String.format(Locale.CHINA, "%s施工人员", infoBean.work_type));
        mTvWorkerFirstName.setText(infoBean.user_name.substring(0, 1));
        int appraise = baseBean.appraise;
        mRatingBar.setRating(appraise);
        mTvWorkerType.setText(infoBean.work_type);
        mTvWorkerName.setText(infoBean.user_name);
        mTvWorkerSex.setText(infoBean.sex);
        mTvWorkerIngEd.setText(String.format(Locale.CHINA, "%d/%d户", baseBean.ing, baseBean.ed));
        mTvWorkerRating.setText(String.valueOf(appraise));
        mTvWorkerYear.setText(String.format(Locale.CHINA, "%d年", infoBean.work_years));
        try {
            String birthday = infoBean.birthday;
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
            int precise = DateUtils.dayComparePrecise(sdf.parse(birthday), new Date());
            mTvWorkerAge.setText(String.format(Locale.CHINA, "%d岁", precise));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void reponseDelPush() {
        requestInfoByText((String) SharePreUtil.getData(this.getContext(), "decorate_select", "业主"));
    }
}
