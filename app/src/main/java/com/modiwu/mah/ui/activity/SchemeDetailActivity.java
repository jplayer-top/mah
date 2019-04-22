package com.modiwu.mah.ui.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.modiwu.mah.R;
import com.modiwu.mah.base.BaseSpecialActivity;
import com.modiwu.mah.message.CustomizeBPMessage;
import com.modiwu.mah.mvp.constract.SchemeDetialContract;
import com.modiwu.mah.mvp.model.bean.SchemeDetailBean;
import com.modiwu.mah.mvp.model.event.BGAPagerSelectEvent;
import com.modiwu.mah.mvp.model.event.LoginSuccessEvent;
import com.modiwu.mah.mvp.presenter.SchemeDetailPresenter;
import com.modiwu.mah.ui.adapter.AdapterPagerSchemeDetail;
import com.modiwu.mah.utils.StringUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.rong.imkit.RongIM;
import io.rong.imlib.IRongCallback;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.Message;
import top.jplayer.baseprolibrary.mvp.model.bean.BaseBean;
import top.jplayer.baseprolibrary.utils.ActivityUtils;
import top.jplayer.baseprolibrary.utils.LogUtil;
import top.jplayer.baseprolibrary.utils.ToastUtils;
import top.jplayer.baseprolibrary.widgets.MultipleStatusView;

/**
 * Created by Obl on 2018/1/23.
 * com.modiwu.mah.ui.activity
 */
public class SchemeDetailActivity extends BaseSpecialActivity implements SchemeDetialContract.ISchemeDetialView {

    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.tvToCard)
    TextView tvToCard;
    @BindView(R.id.tvServer)
    TextView tvServer;
    @BindView(R.id.ivCirRed)
    ImageView mIvCirRed;
    @BindView(R.id.tvToBuy)
    TextView mTvToBuy;
    @BindView(R.id.btn2YY)
    Button btn2YY;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.rlSchemeDetailBottom)
    LinearLayout llSchemeDetailBottom;
    @BindView(R.id.llBottom)
    LinearLayout llBottom;
    @BindView(R.id.multiplestatusview)
    MultipleStatusView mMultipleStatusView;
    @BindView(R.id.smartRefreshLayout)
    SmartRefreshLayout smartRefreshLayout;
    private Unbinder mUnbinder;
    public SchemeDetailPresenter mPresenter;
    private AdapterPagerSchemeDetail mAdapter;
    private String mFangan_id;
    public boolean ttype;

    @Override
    public int setBaseLayout() {
        return R.layout.activity_scheme_detail;
    }

    @Override
    public void initBaseData() {
        mUnbinder = ButterKnife.bind(this, contentView);
        EventBus.getDefault().register(this);
        findToolBarView(contentView);
        customBarLeft();
        ivBarSearch.setVisibility(View.VISIBLE);
        ivBarSearch.setImageDrawable(getResources().getDrawable(R.drawable.shop_collection));
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        tabLayout.setupWithViewPager(viewPager);
        ArrayList<String> strings = new ArrayList<>();
        if (mBundle != null) {
            ttype = mBundle.getBoolean("ttype");
            if (!ttype) {
                strings.add("整装");
                strings.add("主材");
                strings.add("软装");
                strings.add("案例");
            } else {
                strings.add("整装");
                strings.add("楼盘");
                strings.add("单品");
                strings.add("产品");
            }

        }

        mAdapter = new AdapterPagerSchemeDetail(getSupportFragmentManager(), strings, this);
        mPresenter = new SchemeDetailPresenter(this);
        mFangan_id = mBundle.getString("fangan_id");
        tvBarTitle.setText(getIntent().getStringExtra("title"));
        if (mFangan_id != null) {
            mMultipleStatusView.showLoading();
            if (!ttype) {
                mPresenter.requestSchemePDDetialData(mFangan_id);
            } else {
                mPresenter.requestSchemeDetialData(mFangan_id);
            }
            smartRefreshLayout.setOnRefreshListener(refresh -> {
                if (!ttype) {
                    mPresenter.requestSchemePDDetialData(mFangan_id);
                } else {
                    mPresenter.requestSchemeDetialData(mFangan_id);
                }
            });
        } else {
            mMultipleStatusView.showEmpty();
        }
        if (StringUtils.getInstance().assertNoTipLogin(this)) {
            mPresenter.requestHasCollection(mFangan_id);
        }
        ivBarSearch.setOnClickListener(view -> {
            if (StringUtils.getInstance().assert2Login(this)) {
                mPresenter.requestCollection(mFangan_id);
            }
        });

    }

    @Subscribe
    public void userInfo(LoginSuccessEvent event) {
        mPresenter.requestHasCollection(mFangan_id);
    }


    private SchemeDetailBean.LoupanhuxingBean selBean;

    @Subscribe
    public void bgaSelect(BGAPagerSelectEvent event) {
        selBean = event.bean;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
        EventBus.getDefault().unregister(this);
    }

    public SchemeDetailBean mSchemeDetailBean;

    @Override
    public void setSchemeDetialData(SchemeDetailBean bean) {
        if (bean != null && bean.proudct != null) {
            try {
                Gson gson = new Gson();
                String s = gson.toJson(bean.proudct);
                SchemeDetailBean.FanganBean fanganBean1 = gson.fromJson(s, SchemeDetailBean.FanganBean.class);
                bean.fangans = new ArrayList<>();
                bean.fangans.add(fanganBean1);
            } catch (JsonSyntaxException e) {
                LogUtil.e(e.toString());
                e.printStackTrace();
            }
        }
        mMultipleStatusView.showContent();
        smartRefreshLayout.finishRefresh();
        this.mSchemeDetailBean = bean;
        viewPager.setAdapter(mAdapter);
        btn2YY.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            if (selBean != null) {
            } else {
                selBean = mSchemeDetailBean.loupanhuxing.get(0);
            }
            bundle.putString("building_id", selBean.building_id + "");
            bundle.putString("huxing_type", selBean.huxing_type);
            bundle.putString("building_name", selBean.huxing_name);
            ActivityUtils.init().start(this, YYHouseActivity.class, "预约看房", bundle);
        });
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                LogUtil.e("onTabSelected" + tab.getPosition());
//                if (tab.getPosition() == 3 || tab.getPosition() == 4) {
//                    llSchemeDetailBottom.setVisibility(View.VISIBLE);
//                    if (tab.getPosition() == 3) {
//                        btn2YY.setVisibility(View.VISIBLE);
//                        llBottom.setVisibility(View.GONE);
//                    } else {
//                        llSchemeDetailBottom.setVisibility(View.GONE);
//                    }
//                } else {
//                    llSchemeDetailBottom.setVisibility(View.VISIBLE);
//                    btn2YY.setVisibility(View.GONE);
//                    llBottom.setVisibility(View.VISIBLE);
//
//                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                LogUtil.e("onTabUnselected" + tab.getPosition());

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                LogUtil.e("onTabReselected" + tab.getPosition());
            }
        });
        tvToCard.setOnClickListener(view -> {
            if (StringUtils.getInstance().assert2Login(this)) {
                ActivityUtils.init().start(this, ShopCartActivity.class, "购物车");
            }
        });
        mTvToBuy.setOnClickListener(view -> {
            if (StringUtils.getInstance().assert2Login(this)) {
                Bundle bundle = new Bundle();
                bundle.putString("fangan_id", mFangan_id);
                bundle.putString("fangan_name", mSchemeDetailBean.fangan_name);
                ActivityUtils.init().start(mBaseActivity, ToGetProActivity.class, "", bundle);
            }

        });
        tvServer.setOnClickListener(v -> {
            if (StringUtils.getInstance().assert2Login(this)) {
                String rname = tvBarTitle.getText().toString();

                RongIM.getInstance().sendMessage(Message.obtain(mSchemeDetailBean.kfuid,
                        Conversation.ConversationType.PRIVATE,
                        new CustomizeBPMessage(String.format(Locale.CHINA, "可能喜欢\n【%s】", rname))), "", "", new RongIMClient.SendMessageCallback() {
                    @Override
                    public void onSuccess(Integer integer) {

                    }

                    @Override
                    public void onError(Integer integer, RongIMClient.ErrorCode errorCode) {

                    }
                });
//                ActivityUtils.init().startConversion(this, ConversationOneActivity.class, mSchemeDetailBean.kfuid);
                LogUtil.e(mSchemeDetailBean.kfuid);
                RongIM.getInstance().startConversation(this, Conversation.ConversationType.PRIVATE, mSchemeDetailBean
                        .kfuid, "客服");
            }
        });
    }

    @Override
    public void showError() {
        if (mMultipleStatusView != null) {
            mMultipleStatusView.showError();
        }
        if (smartRefreshLayout != null && smartRefreshLayout.isRefreshing()) {
            smartRefreshLayout.finishRefresh();
        }
    }

    @Override
    public void showLoading() {
        if (mMultipleStatusView != null) {
            mMultipleStatusView.showLoading();
        }
    }

    @Override
    public void showEmpty() {
        if (mMultipleStatusView != null) {
            mMultipleStatusView.showEmpty();
        }
        if (smartRefreshLayout != null && smartRefreshLayout.isRefreshing()) {
            smartRefreshLayout.finishRefresh();
        }
    }

    public void setSchemeCollectionData(BaseBean baseBean) {
        ToastUtils.init().showSuccessToast(this, baseBean.msg);
        isCollection(true);
    }

    public void isCollection(boolean has) {
        if (has) {
            ivBarSearch.setImageDrawable(getResources().getDrawable(R.drawable.has_collection));
        } else {
            ivBarSearch.setImageDrawable(getResources().getDrawable(R.drawable.shop_collection));
        }
    }
}
