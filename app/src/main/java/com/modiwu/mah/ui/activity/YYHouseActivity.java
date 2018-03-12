package com.modiwu.mah.ui.activity;

import android.graphics.Color;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.bigkoo.pickerview.TimePickerView;
import com.modiwu.mah.R;
import com.modiwu.mah.base.BaseCommonActivity;
import com.modiwu.mah.mvp.model.YBJModel;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import top.jplayer.baseprolibrary.mvp.model.bean.BaseBean;
import top.jplayer.baseprolibrary.net.SampleShowDialogObserver;
import top.jplayer.baseprolibrary.utils.ToastUtils;
import top.jplayer.baseprolibrary.widgets.dialog.DialogFlowSure;

/**
 * Created by Obl on 2018/1/24.
 * com.modiwu.mah.ui.activity
 */

public class YYHouseActivity extends BaseCommonActivity {


    @BindView(R.id.editName)
    EditText mEditName;
    @BindView(R.id.editPhone)
    EditText mEditPhone;
    @BindView(R.id.tvTime)
    TextView tvTime;
    @BindView(R.id.btnSublime)
    Button mBtnSublime;
    private Unbinder mUnbinder;
    private String mHuxing_type;
    private String mBuilding_id;
    private String mBuilding_name;
    private TimePickerView pvTime;
    private YBJModel mModel;
    private Map<String, String> mMap;

    @Override
    public int setBaseLayout() {
        return R.layout.activity_yy_house;
    }

    @Override
    public void initBaseData() {
        mUnbinder = ButterKnife.bind(this, addRootView);
        mHuxing_type = mBundle.getString("huxing_type");
        mBuilding_id = mBundle.getString("building_id");
        mBuilding_name = mBundle.getString("building_name");
        mMap = new HashMap<>();
        mMap.put("huxing_type", mHuxing_type);
        mMap.put("building_id", mBuilding_id);
//        mMap.put("building_name", mBuilding_name);
        initTimePicker();
        tvTime.setOnClickListener(v -> pvTime.show(v));
        mBtnSublime.setOnClickListener(v -> {
            if (TextUtils.isEmpty(mEditName.getText())) {
                ToastUtils.init().showInfoToast(this, "请输入姓名");
                return;
            }
            mMap.put("user_name", mEditName.getText().toString());
            if (TextUtils.isEmpty(mEditPhone.getText())) {
                ToastUtils.init().showInfoToast(this, "请输入电话");
                return;
            }
            mMap.put("user_phone", mEditPhone.getText().toString());
            if (TextUtils.isEmpty(tvTime.getText())) {
                ToastUtils.init().showInfoToast(this, "请选择时间");
                return;
            }
            mModel = new YBJModel();
            mMap.put("yy_time", tvTime.getText().toString());
            mModel.requestYYHouseBean(mMap).subscribe(new SampleShowDialogObserver<BaseBean>(this) {
                @Override
                protected void onSuccess(BaseBean baseBean) throws Exception {
                    setYYSubmitBean(baseBean);
                }
            });
        });
    }

    public void setYYSubmitBean(BaseBean bean) {
        final DialogFlowSure rxDialogSure = new DialogFlowSure(mBaseActivity);//提示弹窗
        rxDialogSure.getLogoView().setImageResource(R.mipmap.ic_launcher);
        rxDialogSure.getSureView().setOnClickListener(v1 -> {
            rxDialogSure.cancel();
            finish();
        });
        rxDialogSure.show();
    }

    private void initTimePicker() {
        //控制时间范围(如果不设置范围，则使用默认时间1900-2100年，此段代码可注释)
        //因为系统Calendar的月份是从0-11的,所以如果是调用Calendar的set方法来设置时间,月份的范围也要是从0-11
        Calendar selectedDate = Calendar.getInstance();
        Calendar startDate = Calendar.getInstance();
        Calendar endDate = Calendar.getInstance();
        endDate.set(2019, 12, 31);
        //时间选择器
        pvTime = new TimePickerView.Builder(this, (date, v) -> {//选中事件回调
            tvTime.setText(getTime(date));
        })
                .setType(new boolean[]{true, true, true, true, true, false})
                .setLabel("年", "月", "日", "时", "分", "秒")
                .isCenterLabel(false)
                .setDividerColor(Color.TRANSPARENT)
                .setContentSize(21)
                .setDate(selectedDate)
                .setRangDate(startDate, endDate)
//                .setBackgroundId(0x00FFFFFF) //设置外部遮罩颜色
                .setDecorView(null)
                .build();
    }

    private String getTime(Date date) {//可根据需要自行截取数据显示
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
        return format.format(date);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
    }
}
