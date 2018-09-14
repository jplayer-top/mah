package com.modiwu.mah.ui.activity;

import android.util.ArrayMap;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.modiwu.mah.R;
import com.modiwu.mah.base.BaseCommonActivity;
import com.modiwu.mah.mvp.model.bean.SelectWorkerTypeBean;
import com.modiwu.mah.mvp.model.event.RegDecorateEvent;
import com.modiwu.mah.mvp.presenter.DecorateBasePresenter;
import com.modiwu.mah.utils.PickerUtils;
import com.modiwu.mah.utils.StringUtils;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Observable;
import top.jplayer.baseprolibrary.net.IoMainSchedule;
import top.jplayer.baseprolibrary.utils.KeyBoardUtils;
import top.jplayer.baseprolibrary.utils.SharePreUtil;
import top.jplayer.baseprolibrary.utils.ToastUtils;

/**
 * Created by Obl on 2018/8/31.
 * com.modiwu.mah.ui.activity
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */

public class DecorateShiGongRegisterActivity extends BaseCommonActivity {
    @BindView(R.id.editName)
    EditText mEditName;
    @BindView(R.id.editBirth)
    TextView mEditBirth;
    @BindView(R.id.editSex)
    TextView mEditSex;
    @BindView(R.id.editId)
    EditText mEditId;
    @BindView(R.id.editType)
    TextView mEditType;
    @BindView(R.id.editAge)
    EditText mEditAge;
    @BindView(R.id.btnSure)
    Button mBtnSure;
    @BindView(R.id.llRegOk)
    LinearLayout llRegOk;
    private Unbinder mBind;
    private PickerUtils mPickerUtils;
    private ArrayList<String> optionsItems = new ArrayList<>();
    private ArrayMap<String, String> mMap;
    private DecorateBasePresenter mPresenter;

    @Override
    public int setBaseLayout() {
        return R.layout.activity_decorate_shigong_register;
    }

    @Override
    public void initBaseData() {
        mBind = ButterKnife.bind(this, mFlRootView);
        mPickerUtils = new PickerUtils();
        mPresenter = new DecorateBasePresenter(this);
        mEditBirth.setOnClickListener(v -> {
            KeyBoardUtils.closeInput(this, v);
            mPickerUtils.initTimePicker(this);
            mPickerUtils.pvTime.show(mEditBirth);
        });
        mEditSex.setOnClickListener(v -> {
            KeyBoardUtils.closeInput(this, v);
            optionsItems.clear();
            optionsItems.add("男");
            optionsItems.add("女");
            mPickerUtils.initStringPicker(optionsItems, 0, this);
            if (!mPickerUtils.mPickerView.isShowing()) {
                mPickerUtils.mPickerView.show(mEditSex);
            }
        });
        mEditType.setOnClickListener(v -> {
            if (this.bean == null) {
                mPresenter.selectWorkerType();
            } else {
                bindPickerWorkerType(bean);
            }
        });
        mBtnSure.setOnClickListener(v -> {
            if (mMap != null) {
                mMap.clear();
            } else {
                mMap = new ArrayMap<>();
            }
            putText("请输入姓名", "user_name", mEditName);
            putText("请选择性别", "sex", mEditSex);
            putText("请选择出生日期", "birthday", mEditBirth);
            putText("请输入身份信息", "id_card", mEditId);
            putText("请选择工种类型", "work_type", mEditType);
            putText("请输入工龄", "work_years", mEditAge);
            mMap.put("user_phone", (String) SharePreUtil.getData(this, "login_phone", "0"));
            if (mMap.size() >= 7) {
                mPresenter.regWorker(mMap);
            }
        });
    }

    SelectWorkerTypeBean bean;

    @Override
    public void selectWorkerType(SelectWorkerTypeBean bean) {
        super.selectWorkerType(bean);
        this.bean = bean;
        bindPickerWorkerType(bean);
    }

    private void bindPickerWorkerType(SelectWorkerTypeBean bean) {
        optionsItems.clear();
        Observable.fromIterable(bean.rows).subscribe(rowsBean -> {
            optionsItems.add(rowsBean.cat_value);
        });
        mPickerUtils.initStringPicker(optionsItems, 0, this);
        if (!mPickerUtils.mPickerView.isShowing()) {
            mPickerUtils.mPickerView.show(mEditType);
        }
    }

    @Override
    public void regWorker() {
        super.regWorker();
        llRegOk.setVisibility(View.VISIBLE);
        EventBus.getDefault().post(new RegDecorateEvent("施工"));
        SharePreUtil.saveData(this, "decorate_select", "施工");
        SharePreUtil.saveData(this, "decorate_worker", "1");
        Observable.timer(500, TimeUnit.MILLISECONDS).compose(new IoMainSchedule<>()).subscribe(
                aLong -> finish()
        );
    }

    private void putText(String info, String key, TextView valueView) {
        String string = valueView.getText().toString();
        if (StringUtils.getInstance().isNullObj(string)) {
            ToastUtils.init().showInfoToast(this, info);
            return;
        }
        if (mMap != null) {
            mMap.put(key, string);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mBind.unbind();
    }


}
