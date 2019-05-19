package com.modiwu.mah.ui.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;

import com.modiwu.mah.R;
import com.modiwu.mah.base.BaseCommonActivity;
import com.modiwu.mah.mvp.model.OrderListModel;
import com.modiwu.mah.mvp.model.event.FinishAddEvent;
import com.modiwu.mah.utils.StringUtils;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import top.jplayer.baseprolibrary.mvp.model.bean.BaseBean;
import top.jplayer.baseprolibrary.net.SampleShowDialogObserver;

/**
 * Created by Administrator on 2019/4/4.
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */

public class FinishZHActivity extends BaseCommonActivity {
    @BindView(R.id.lll)
    LinearLayout lll;
    @BindView(R.id.time1)
    RadioButton time1;
    @BindView(R.id.time2)
    RadioButton time2;
    @BindView(R.id.time3)
    RadioButton time3;
    @BindView(R.id.status1)
    RadioButton status1;
    @BindView(R.id.status2)
    RadioButton status2;
    @BindView(R.id.status3)
    RadioButton status3;
    @BindView(R.id.editFWZK)
    EditText editFWZK;
    @BindView(R.id.editLocalName)
    EditText editLocalName;
    @BindView(R.id.editArea)
    EditText editArea;
    @BindView(R.id.editRemark)
    EditText editRemark;
    @BindView(R.id.btnAdd)
    Button btnAdd;
    private Unbinder bind;

    @Override
    public int setBaseLayout() {
        return R.layout.activity_pushzh;
    }

    @Override
    public void initBaseData() {
        bind = ButterKnife.bind(this, addRootView);
        btnAdd.setOnClickListener(view -> {
            String fangan_name = mBundle.getString("fangan_name");
            String user_name = mBundle.getString("user_name");
            String user_phone = mBundle.getString("user_phone");
            String fangan_id = mBundle.getString("fangan_id");
            String xiaoqu_name = StringUtils.getInstance().trim(editLocalName);
            String zhxsj = time1.isChecked() ? "一个月内" : (time2.isChecked() ? "两个月内" : "两个月以上");
            String fwzk = status1.isChecked() ? "毛坯房" : (status2.isChecked() ? "旧房翻新" : "局部改造");
            String remark = StringUtils.getInstance().trim(editRemark);
            String area = StringUtils.getInstance().trim(editArea);
            new OrderListModel().sjfaAdd(fangan_name, user_name, user_phone, xiaoqu_name, zhxsj, fwzk, remark, area,fangan_id)
                    .subscribe(new SampleShowDialogObserver<BaseBean>(this) {
                @Override
                protected void onSuccess(BaseBean baseBean) throws Exception {
                    EventBus.getDefault().post(new FinishAddEvent());
                    finish();
                }
            });
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bind.unbind();
    }
}
