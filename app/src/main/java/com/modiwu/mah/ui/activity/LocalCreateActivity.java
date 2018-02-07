package com.modiwu.mah.ui.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.modiwu.mah.R;
import com.modiwu.mah.base.BaseCommonActivity;
import com.modiwu.mah.mvp.model.bean.LocalBean;
import com.modiwu.mah.mvp.model.bean.LocalListBean;
import com.modiwu.mah.mvp.model.bean.PostLocalBean;
import com.modiwu.mah.mvp.model.event.LocalEvent;
import com.modiwu.mah.mvp.presenter.LocalCreatePresenter;
import com.modiwu.mah.utils.StringUtils;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import top.jplayer.baseprolibrary.utils.ToastUtils;
import top.jplayer.baseprolibrary.widgets.SwitchView;

/**
 * Created by Obl on 2018/2/7.
 * com.modiwu.mah.ui.activity
 */

public class LocalCreateActivity extends BaseCommonActivity {
    @BindView(R.id.editName)
    EditText mEditName;
    @BindView(R.id.editPhone)
    EditText mEditPhone;
    @BindView(R.id.tvLocalSecOk)
    TextView mTvLocalSecOk;
    @BindView(R.id.llSecLocal)
    LinearLayout mLlSecLocal;
    @BindView(R.id.editDetailLocal)
    EditText mEditDetailLocal;
    @BindView(R.id.btnSave)
    Button btnSave;
    @BindView(R.id.switchView)
    SwitchView mSwitchView;
    private Unbinder mUnbinder;
    private boolean mIsEdit;
    private Map<String, String> mMap;
    private LocalCreatePresenter mPresenter;

    @Override
    public int setBaseLayout() {
        return R.layout.activity_local_create;
    }

    @Override
    public void initBaseData() {
        mUnbinder = ButterKnife.bind(this, addRootView);
        mMap = new HashMap<>();

        Bundle bundle = getIntent().getBundleExtra("bundle");
        LocalListBean.RecordsBean recordsBean = bundle.getParcelable("local");
        mIsEdit = "0".equals(bundle.getString("isCreate"));
        if (mIsEdit) {
            if (recordsBean != null) {
                mProvince_name = recordsBean.rp_province;
                mCity_name = recordsBean.rp_city;
                mArea_name = recordsBean.rp_area;
                String addr = StringUtils.getInstance().isNullable("", mProvince_name, mCity_name,
                        mArea_name);
                mTvLocalSecOk.setText(addr);
                mEditName.setText(StringUtils.getInstance().isNullable(recordsBean.rp_name, ""));
                mEditName.setSelection(mEditName.getText().length());
                mEditPhone.setText(StringUtils.getInstance().isNullable(recordsBean.phone, ""));
                mEditDetailLocal.setText(StringUtils.getInstance().isNullable(recordsBean.rp_addr, ""));
            }
        }
        mPresenter = new LocalCreatePresenter(this);
        initPicker();
        mLlSecLocal.setOnClickListener(v -> {
            if (mLocalBean != null) {
                setLocalBean(mLocalBean);
            } else {
                mPresenter.requestLocalBean();
            }
        });
        btnSave.setOnClickListener(v -> {
            customSave();
        });
    }

    protected void customSave() {
        if (StringUtils.getInstance().isNullObj(mEditName.getText().toString())) {
            ToastUtils.init().showInfoToast(this, "请完善姓名信息");
            return;
        }
        mMap.put("rp_name", mEditName.getText().toString());
        if (StringUtils.getInstance().isNullObj(mEditPhone.getText().toString())) {
            ToastUtils.init().showInfoToast(this, "请完善手机信息");
            return;
        }
        mMap.put("rp_phone", mEditPhone.getText().toString());
        if (StringUtils.getInstance().isNullObj(mEditDetailLocal.getText().toString())) {
            ToastUtils.init().showInfoToast(this, "请完善地址信息");
            return;
        }
        mMap.put("rp_addr", mEditDetailLocal.getText().toString());
        if (StringUtils.getInstance().isNullObj(mProvince_name)) {
            ToastUtils.init().showInfoToast(this, "请完善地址信息");
            return;
        }
        mMap.put("rp_province", mProvince_name);
        mMap.put("rp_city", mCity_name);
        mMap.put("rp_area", mArea_name);
        if (mIsEdit) {
            mPresenter.editLocal(mMap);
        } else {
            mPresenter.saveLocal(mMap);
        }
    }

    private ArrayList<PostLocalBean> optionsLocalSItems = new ArrayList<>();
    private ArrayList<ArrayList<PostLocalBean>> optionsLocalXItems = new ArrayList<>();
    private ArrayList<ArrayList<ArrayList<PostLocalBean>>> optionsQItems = new ArrayList<>();
    private OptionsPickerView mLocalPickerView;
    private LocalBean mLocalBean;

    public void setLocalBean(LocalBean localBean) {
        mLocalBean = localBean;
        if (localBean != null && localBean.areas.size() > 0) {
            for (int i = 0; i < localBean.areas.size(); i++) {//省
                ArrayList<ArrayList<PostLocalBean>> minLocalItems = new ArrayList<>();
                ArrayList<PostLocalBean> subsString = new ArrayList<>();
                for (int j = 0; j < localBean.areas.get(i).subs.size(); j++) {
                    String area_nameS = localBean.areas.get(i).subs.get(j).area_name;
                    String area_codeS = localBean.areas.get(i).subs.get(j).area_code;
                    PostLocalBean postLocalBeanS = new PostLocalBean();
                    postLocalBeanS.name = area_nameS;
                    postLocalBeanS.code = area_codeS;
                    subsString.add(postLocalBeanS);
                    ArrayList<PostLocalBean> subsXString = new ArrayList<>();
                    for (int k = 0; k < localBean.areas.get(i).subs.get(j).subs.size(); k++) {
                        String area_nameX = localBean.areas.get(i).subs.get(j).subs.get(k).area_name;
                        String area_codeX = localBean.areas.get(i).subs.get(j).subs.get(k).area_code;
                        PostLocalBean postLocalBeanX = new PostLocalBean();
                        postLocalBeanX.name = area_nameX;
                        postLocalBeanX.code = area_codeX;
                        subsXString.add(postLocalBeanX);
                    }
                    minLocalItems.add(subsXString);
                }
                optionsLocalXItems.add(subsString);
                optionsQItems.add(minLocalItems);
                String area_name = localBean.areas.get(i).area_name;
                String area_code = localBean.areas.get(i).area_code;
                PostLocalBean postLocalBean = new PostLocalBean();
                postLocalBean.code = area_code;
                postLocalBean.name = area_name;
                optionsLocalSItems.add(postLocalBean);
            }
            if (optionsLocalSItems.size() != 0 & optionsLocalSItems != null) {
                mLocalPickerView.setPicker(optionsLocalSItems, optionsLocalXItems, optionsQItems);
            }
        }
        if (!mLocalPickerView.isShowing()) {
            mLocalPickerView.show();
        }
    }

    private String mProvince_name;
    private String mCity_name;
    private String mArea_name;

    private void initPicker() {
        //地区
        mLocalPickerView = new OptionsPickerView.Builder(this, (options1, option2, options3, v) -> {
            mProvince_name = optionsLocalSItems.get(options1).name + "";
            mCity_name = (optionsLocalXItems.get(options1).get(option2).name) == null ? "" : (optionsLocalXItems.get(options1).get(option2).name + "");
            mArea_name = (optionsQItems.get(options1).get(option2).get(options3).name) == null ? "" : optionsQItems.get(options1).get(option2).get(options3).name + "";
            mTvLocalSecOk.setText(String.format(Locale.CHINA, "%s%s%s", mProvince_name, mCity_name, mArea_name));
        }).setSubmitText("确定")//确定按钮文字
                .setCancelText("取消")//取消按钮文字
                .setSubCalSize(18)//确定和取消文字大小
                .setSubmitColor(getResources().getColor(R.color.colorBlackGold))
                .setCancelColor(getResources().getColor(R.color.grey))
                .setContentTextSize(18)//滚轮文字大小
                .setCyclic(false, false, false)//循环与否
                .setSelectOptions(0, 0, 0)  //设置默认选中项
                .setOutSideCancelable(false)//点击外部dismiss default true
                .build();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
    }

    public void upDataSuccess() {
        EventBus.getDefault().post(new LocalEvent());
        finish();
    }
}
