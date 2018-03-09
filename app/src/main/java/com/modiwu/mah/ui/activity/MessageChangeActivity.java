package com.modiwu.mah.ui.activity;

import android.widget.Button;
import android.widget.EditText;

import com.modiwu.mah.R;
import com.modiwu.mah.base.BaseCommonActivity;
import com.modiwu.mah.mvp.model.event.MessageEvent;

import org.greenrobot.eventbus.EventBus;

import top.jplayer.baseprolibrary.utils.ToastUtils;

/**
 * Created by Administrator on 2018/2/9.
 * 用户信息修改
 */

public class MessageChangeActivity extends BaseCommonActivity {
    @Override
    public int setBaseLayout() {
        return R.layout.activity_message;
    }

    @Override
    public void initBaseData() {
        findToolBarView(addRootView);
        EditText edit = addRootView.findViewById(R.id.edit);
        Button btnOk = addRootView.findViewById(R.id.btnOk);

        String key = mBundle.getString(mTitle);
        if (key != null) {
            edit.setText(key);
            edit.setSelection(key.length());
        }
        btnOk.setOnClickListener(view -> {
            String value = edit.getText().toString().trim();
            if ("".equals(value)) {
                ToastUtils.init().showInfoToast(this, "信息不能为空");
            }
            MessageEvent event = new MessageEvent(mTitle, value);
            EventBus.getDefault().post(event);
            finish();
        });
    }
}
