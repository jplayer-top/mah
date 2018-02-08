package com.modiwu.mah.ui.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.modiwu.mah.R;
import com.modiwu.mah.base.BaseCommonActivity;
import com.modiwu.mah.mvp.model.bean.YBJBean;
import com.modiwu.mah.mvp.presenter.YBJPresenter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import top.jplayer.baseprolibrary.glide.GlideUtils;
import top.jplayer.baseprolibrary.mvp.model.bean.BaseBean;
import top.jplayer.baseprolibrary.utils.ToastUtils;
import top.jplayer.baseprolibrary.widgets.MultipleStatusView;
import top.jplayer.baseprolibrary.widgets.dialog.DialogFlowSure;

/**
 * Created by Obl on 2018/1/24.
 * com.modiwu.mah.ui.activity
 */

public class HouseSampleActivity extends BaseCommonActivity {


    @BindView(R.id.ivPic)
    ImageView ivPic;
    @BindView(R.id.tvInfo)
    TextView tvInfo;
    @BindView(R.id.checkBox1)
    CheckBox checkBox1;
    @BindView(R.id.checkBox2)
    CheckBox checkBox2;
    @BindView(R.id.checkBox3)
    CheckBox checkBox3;
    @BindView(R.id.checkBox4)
    CheckBox checkBox4;
    @BindView(R.id.checkBox5)
    CheckBox checkBox5;
    @BindView(R.id.checkBox6)
    CheckBox checkBox6;
    @BindView(R.id.tvOther)
    TextView tvOther;
    @BindView(R.id.btnSublime)
    Button btnSublime;
    @BindView(R.id.editName)
    EditText editName;
    @BindView(R.id.editPhone)
    EditText editPhone;
    @BindView(R.id.editFloor)
    EditText editFloor;
    @BindView(R.id.editType)
    EditText editType;
    @BindView(R.id.editArea)
    EditText editArea;
    @BindView(R.id.editOther)
    EditText editOther;
    private Unbinder unbinder;
    private YBJPresenter presenter;
    private Map<String, String> map;
    private CheckBox[] checkBoxes;
    private boolean isYBJ;

    @Override
    public int setBaseLayout() {
        return R.layout.activity_house_sample;
    }

    @Override
    public void initBaseData() {
        unbinder = ButterKnife.bind(this, addRootView);
        String title = getIntent().getStringExtra("title");
        isYBJ = "样板间征集".equals(title);
        map = new HashMap<>();
        mMultipleStatusView = addRootView.findViewById(R.id.multiplestatusview);
        addRootView.findViewById(R.id.btnSublime).setOnClickListener(v -> {
            if (TextUtils.equals(editName.getText(), "")) {
                ToastUtils.init().showInfoToast(this, "请输入姓名");
                return;
            }
            map.put("user_name", getTrimString(editName));
            if (TextUtils.equals(editPhone.getText(), "")) {
                ToastUtils.init().showInfoToast(this, "请输入联系电话");
                return;
            }
            map.put("user_phone", getTrimString(editPhone));

            if (TextUtils.equals(editFloor.getText(), "")) {
                ToastUtils.init().showInfoToast(this, "请输入楼盘名称");
                return;
            }
            map.put("building_name", getTrimString(editFloor));

            if (TextUtils.equals(editType.getText(), "")) {
                ToastUtils.init().showInfoToast(this, "请输入您的户型");
                return;
            }
            mapPutKey("huxing", "huxing_type", editType);

            if (TextUtils.equals(editArea.getText(), "")) {
                ToastUtils.init().showInfoToast(this, "请输入您的房屋面积");
                return;
            }
            mapPutKey("mianji", "huxing_mian", editArea);

            StringBuilder stringBuilder = new StringBuilder("");
            for (CheckBox checkBox : checkBoxes) {
                if (checkBox.isChecked()) {
                    stringBuilder.append(checkBox.getText());
                    stringBuilder.append(",");
                }
            }
            stringBuilder.append(getTrimString(editOther));
            if (stringBuilder.toString().equals("")) {
                ToastUtils.init().showInfoToast(this, "请选择你想要的风格");
                return;
            }
            map.put("fengge", (stringBuilder.toString()));
            if (isYBJ) {
                presenter.requestSubmit(map);
            }else {
                presenter.requestYYSubmit(map);
            }
        });
        presenter = new YBJPresenter(this);
        showLoading();
        presenter.requestInfoBean();
        checkBoxes = new CheckBox[]{checkBox1, checkBox2, checkBox3, checkBox4, checkBox5, checkBox6};
    }

    private void mapPutKey(String ybj, String yy, EditText edit) {
        if (isYBJ) {
            map.put(ybj, getTrimString(edit));
        } else {
            map.put(yy, getTrimString(edit));
        }
    }

    @NonNull
    private String getTrimString(EditText edit) {
        return edit.getText().toString().trim();
    }

    public void setYBJInfoBean(YBJBean bean) {
        Glide.with(this).load(bean.img).apply(GlideUtils.init().options()).into(ivPic);
        tvInfo.setText(bean.info);
    }

    public void setSubmitBean(BaseBean baseBean) {
        final DialogFlowSure rxDialogSure = new DialogFlowSure(mBaseActivity);//提示弹窗
        rxDialogSure.getLogoView().setImageResource(R.mipmap.ic_launcher);
        rxDialogSure.getSureView().setOnClickListener(v1 -> rxDialogSure.cancel());
        rxDialogSure.show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    public void setYYSubmitBean(BaseBean bean) {
        final DialogFlowSure rxDialogSure = new DialogFlowSure(mBaseActivity);//提示弹窗
        rxDialogSure.getLogoView().setImageResource(R.mipmap.ic_launcher);
        rxDialogSure.getSureView().setOnClickListener(v1 -> rxDialogSure.cancel());
        rxDialogSure.show();
    }
}
