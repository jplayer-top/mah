package com.modiwu.mah.ui.activity;

import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.modiwu.mah.R;
import com.modiwu.mah.base.BaseCommonActivity;
import com.modiwu.mah.mvp.model.bean.MsgHasBean;
import com.modiwu.mah.mvp.model.event.DecorateMessageSelEvent;
import com.modiwu.mah.mvp.presenter.DecorateBasePresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import top.jplayer.baseprolibrary.utils.ActivityUtils;

/**
 * Created by Obl on 2018/9/15.
 * com.modiwu.mah.ui.activity
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */

public class DecorateMessageHasActivity extends BaseCommonActivity {
    @BindView(R.id.tvSubTitle)
    TextView mTvSubTitle;
    @BindView(R.id.tvTitle)
    TextView mTvTitle;
    @BindView(R.id.tvTitlePro)
    TextView mTvTitlePro;
    @BindView(R.id.tvTime)
    TextView mTvTime;
    @BindView(R.id.ivRedTip)
    ImageView mIvRedTip;
    @BindView(R.id.tvSubTitlePro)
    TextView mTvSubTitlePro;
    @BindView(R.id.tvTimePro)
    TextView mTvTimePro;
    @BindView(R.id.clInv)
    ConstraintLayout mClInv;
    @BindView(R.id.clPro)
    ConstraintLayout mClPro;
    private Unbinder mUnbinder;
    DecorateBasePresenter mPresenter;

    @Override
    public int setBaseLayout() {
        return R.layout.activity_decorate_message_has;
    }

    @Override
    public void initBaseData() {
        EventBus.getDefault().register(this);
        mUnbinder = ButterKnife.bind(this, mFlRootView);
        mPresenter = new DecorateBasePresenter(this);
        mPresenter.getMsgHasInfo();
        mClInv.setOnClickListener(v -> {
            ActivityUtils.init().start(this, DecorateMessageListActivity.class, "邀请详情");
        });
        mClPro.setOnClickListener(v -> {
            ActivityUtils.init().start(this, DecorateMessageListActivity.class, "进度更新");
        });
    }

    @Override
    public void getMsgHasInfo(MsgHasBean bean) {
        super.getMsgHasInfo(bean);
        boolean hasInv = "1".equals(bean.hasinv);
        boolean hasMsg = "1".equals(bean.hasmsg);
        mClInv.setVisibility(View.VISIBLE);
        mClPro.setVisibility(View.VISIBLE);
        if (hasInv) {
            mTvTitle.setText("您有新的邀请消息~");
            mTvTime.setText(bean.inv.ct);
            mTvSubTitle.setText(String.format(Locale.CHINA, "%s邀您加入%s", bean.inv.invu_name, bean.inv.project_name));
        } else {
            mTvTitle.setText("暂无邀请消息");
            mTvTime.setText("00:00");
            mTvSubTitle.setText("暂无");
        }
        if (hasMsg) {
            mTvTimePro.setText(bean.wmsg.ct);
            mTvSubTitlePro.setText(bean.wmsg.subtitle);
            mTvTitlePro.setText("您的项目进度更新了~");
        } else {
            mTvTitlePro.setText("暂无项目进度");
            mTvTimePro.setText("00:00");
            mTvSubTitlePro.setText("暂无");
        }
    }

    @Subscribe
    public void onEvent(DecorateMessageSelEvent event) {
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
        EventBus.getDefault().unregister(this);
    }

}
