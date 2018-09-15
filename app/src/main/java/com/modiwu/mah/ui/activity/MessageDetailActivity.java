package com.modiwu.mah.ui.activity;

import android.widget.Button;
import android.widget.TextView;

import com.modiwu.mah.R;
import com.modiwu.mah.base.BaseCommonActivity;
import com.modiwu.mah.mvp.model.bean.MsgListBean;
import com.modiwu.mah.mvp.model.event.DecorateMessageSelEvent;
import com.modiwu.mah.mvp.presenter.DecorateBasePresenter;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Obl on 2018/8/29.
 * com.modiwu.mah.ui.activity
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */

public class MessageDetailActivity extends BaseCommonActivity {


    @BindView(R.id.tvProName)
    TextView mTvProName;
    @BindView(R.id.tvName)
    TextView mTvName;
    @BindView(R.id.tvPhone)
    TextView mTvPhone;
    @BindView(R.id.btnSure)
    Button mBtnSure;
    @BindView(R.id.btnCancel)
    Button mBtnCancel;
    private Unbinder mBind;
    private DecorateBasePresenter mBasePresenter;
    private MsgListBean.InvtsBean mBean;

    @Override
    public int setBaseLayout() {
        return R.layout.activity_decorate_message_detail;
    }

    @Override
    public void initBaseData() {
        mBind = ButterKnife.bind(this, mFlRootView);
        mBasePresenter = new DecorateBasePresenter(this);
        mBean = mBundle.getParcelable("bean");
        if (mBean != null) {
            mTvName.setText(mBean.invu_name);
            mTvPhone.setText(mBean.invu_phone);
            mTvProName.setText(mBean.project_name);
            mBtnSure.setOnClickListener(v -> mBasePresenter.invAgree(mBean.invite_id + ""));
            mBtnCancel.setOnClickListener(v -> mBasePresenter.invCancel(mBean.invite_id + ""));
        }
    }

    @Override
    public void invAgree() {
        super.invAgree();
        EventBus.getDefault().post(new DecorateMessageSelEvent(1));
        finish();
    }

    @Override
    public void invCancel() {
        super.invCancel();
        EventBus.getDefault().post(new DecorateMessageSelEvent(0));
        finish();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mBind.unbind();
    }
}
