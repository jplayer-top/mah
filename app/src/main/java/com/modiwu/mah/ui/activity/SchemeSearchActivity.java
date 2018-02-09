package com.modiwu.mah.ui.activity;

import android.graphics.Color;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.modiwu.mah.R;
import com.modiwu.mah.base.BaseCommonActivity;
import com.modiwu.mah.mvp.constract.SchemeSelectContract;
import com.modiwu.mah.mvp.model.bean.CityCodeBean;
import com.modiwu.mah.mvp.model.bean.FloorBean;
import com.modiwu.mah.mvp.model.bean.SelectLocalBean;
import com.modiwu.mah.mvp.model.event.SchemeSelectEvent;
import com.modiwu.mah.mvp.presenter.SchemeSelectPresenter;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Observable;
import top.jplayer.baseprolibrary.utils.ActivityUtils;
import top.jplayer.baseprolibrary.utils.SharePreUtil;
import top.jplayer.baseprolibrary.utils.ToastUtils;

/**
 * Created by Obl on 2018/1/24.
 * com.modiwu.mah.ui.activity
 */

public class SchemeSearchActivity extends BaseCommonActivity implements SchemeSelectContract.ISchemeSelectView {
    @BindView(R.id.llLocalSelect)
    LinearLayout mLlLocalSelect;
    @BindView(R.id.tvLocal)
    TextView mTvLocal;
    @BindView(R.id.tvFloor)
    TextView mTvFloor;
    @BindView(R.id.llFloorSelect)
    LinearLayout mLlFloorSelect;
    @BindView(R.id.tvStyle)
    TextView mTvStyle;
    @BindView(R.id.llStyleSelect)
    LinearLayout mLlStyleSelect;
    @BindView(R.id.tvType)
    TextView mTvType;
    @BindView(R.id.llTypeSelect)
    LinearLayout mLlTypeSelect;
    @BindView(R.id.btnSure)
    Button mBtnSure;
    private Unbinder mUnbinder;
    OptionsPickerView mStylePicker;
    OptionsPickerView mTypePicker;
    OptionsPickerView mFloorPicker;
    OptionsPickerView mLocalPicker;
    private SchemeSelectPresenter mPresenter;
    private String city_code = "";

    @Override
    public int setBaseLayout() {
        return R.layout.activity_scheme_search;
    }

    @Override
    public void initBaseData() {
        findToolBarView(mFlRootView);
        mUnbinder = ButterKnife.bind(this, addRootView);
        tvBarTitle.setText("选择条件");
        initPicker();
        mPresenter = new SchemeSelectPresenter(this);
        mBtnSure.setOnClickListener(view -> {
//            if ("".equals(selectAreaCode)) {
//                ToastUtils.init().showInfoToast(this, "请选择地区");
//                return;
//            }
//            if ("".equals(selectBuildingId)) {
//                ToastUtils.init().showInfoToast(this, "请选择楼盘");
//                return;
//            }

            String style = mTvStyle.getText().toString().trim();
//            if ("".equals(style)) {
//                ToastUtils.init().showInfoToast(this, "请选择风格");
//                return;
//            }
            String type = mTvType.getText().toString().trim();
//            if ("".equals(type)) {
//                ToastUtils.init().showInfoToast(this, "请选择户型");
//                return;
//            }
            SchemeSelectEvent event = new SchemeSelectEvent
                    (city_code, selectAreaCode, selectBuildingId, style, type);
            EventBus.getDefault().post(event);
            finish();
        });
    }

    private void initPicker() {
        mLlStyleSelect.setOnClickListener(v -> {
            if (this.styles != null) {
                setStyleData(this.styles);
                return;
            }
            dialogShow(this);
            mPresenter.requestStyleData();
        });
        mLlTypeSelect.setOnClickListener(v -> {
            if (this.types != null) {
                setTypeData(this.types);
                return;
            }
            dialogShow(this);
            mPresenter.requestTypeData();
        });
        mLlFloorSelect.setOnClickListener(v -> {

            if (selectAreaCode.equals("")) {
                ToastUtils.init().showInfoToast(this, "请先选择地区");
                return;
            }
            dialogShow(this);

            mPresenter.requestFloorData(selectAreaCode);
        });
        mLlLocalSelect.setOnClickListener(v -> {
            if (this.selectLocalBean != null) {
                setLocalData(this.selectLocalBean);
                return;
            }
            city_code = (String) SharePreUtil.getData(this, "city_code", "");
            dialogShow(this);
            if ("".equals(city_code) || city_code == null) {
                String city = (String) SharePreUtil.getData(this, "city", "烟台");
                mPresenter.requestCode(city);
            } else {
                mPresenter.requestLocalData(city_code);
            }
        });
    }

    private String selectAreaCode = "";
    private String selectBuildingId = "";

    /**
     * picker
     *
     * @return picker
     */
    private OptionsPickerView getPicker(String title, TextView tv, List<String> strings) {
        return new OptionsPickerView.Builder(this, (options1, options2,
                                                    options3, v)
                -> tv.setText(strings.get(options1)))
                .setTitleText(title)
                .setTitleSize(18)
                .setTitleColor(Color.BLACK)
                .setSubmitText("确定")//确定按钮文字
                .setCancelText("取消")//取消按钮文字
                .setSubCalSize(14)//确定和取消文字大小
                .setSubmitColor(getResources().getColor(R.color.colorBlackGold))
                .setCancelColor(getResources().getColor(R.color.grey))
                .setSelectOptions(0)
                .build();
    }

    private OptionsPickerView getPickerLocal(String title, TextView tv, List<String> strings) {
        return new OptionsPickerView.Builder(this, (options1, options2,
                                                    options3, v)
                -> {
            selectAreaCode = selectLocalBean.rows.get(options1).area_code;
            String text = strings.get(options1);
            tv.setText(text);

        })
                .setTitleText(title)
                .setTitleSize(18)
                .setTitleColor(Color.BLACK)
                .setSubmitText("确定")//确定按钮文字
                .setCancelText("取消")//取消按钮文字
                .setSubCalSize(14)//确定和取消文字大小
                .setSubmitColor(getResources().getColor(R.color.colorBlackGold))
                .setCancelColor(getResources().getColor(R.color.grey))
                .setSelectOptions(0)
                .build();
    }

    private OptionsPickerView getPickerFloor(String title, TextView tv, List<String> strings) {
        return new OptionsPickerView.Builder(this, (options1, options2,
                                                    options3, v)
                -> {

            selectBuildingId = floorBean.rows.get(options1).building_id + "";
            String text = strings.get(options1);
            tv.setText(text);
            if ("其他".equals(text)) {
                ActivityUtils.init().start(this, HouseSampleActivity.class, "预约方案");
                finish();
            }
        })
                .setTitleText(title)
                .setTitleSize(18)
                .setTitleColor(Color.BLACK)
                .setSubmitText("确定")//确定按钮文字
                .setCancelText("取消")//取消按钮文字
                .setSubCalSize(14)//确定和取消文字大小
                .setSubmitColor(getResources().getColor(R.color.colorBlackGold))
                .setCancelColor(getResources().getColor(R.color.grey))
                .setSelectOptions(0)
                .build();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
    }

    private List<String> styles;

    @Override
    public void setStyleData(List<String> styles) {
        this.styles = styles;
        mStylePicker = getPicker("风格", mTvStyle, styles);
        mStylePicker.setPicker(styles);
        if (!mStylePicker.isShowing()) {
            dialogDismiss("");
            mStylePicker.show();
        }
    }

    private List<String> types;

    @Override
    public void setTypeData(List<String> types) {
        this.types = types;
        mTypePicker = getPicker("户型", mTvType, types);
        mTypePicker.setPicker(types);
        if (!mTypePicker.isShowing()) {
            dialogDismiss("");
            mTypePicker.show();
        }
    }

    private List<String> floors;
    private FloorBean floorBean;

    @Override
    public void setFloorData(FloorBean floorBean) {
        this.floorBean = floorBean;
        Observable.just(floorBean).map(selectBean -> selectBean.rows)
                .map(rowsBeans -> {
                    List<String> list = new ArrayList<>();
                    Observable.fromIterable(rowsBeans).subscribe(rowsBean -> list.add(rowsBean.building_name));
                    return list;
                }).subscribe(strings -> this.floors = strings);
        mFloorPicker = getPickerFloor("楼盘", mTvFloor, floors);
        mFloorPicker.setPicker(floors);
        if (!mFloorPicker.isShowing()) {
            dialogDismiss("");
            mFloorPicker.show();
        }
    }

    private List<String> locals;
    private SelectLocalBean selectLocalBean;

    @Override
    public void setLocalData(SelectLocalBean bean) {
        this.selectLocalBean = bean;
        Observable.just(bean)
                .map(selectBean -> selectBean.rows)
                .map(rowsBeans -> {
                    List<String> list = new ArrayList<>();
                    Observable.fromIterable(rowsBeans).subscribe(rowsBean -> list.add(rowsBean.area_name));
                    return list;
                }).subscribe(strings -> this.locals = strings);
        mLocalPicker = getPickerLocal("地区", mTvLocal, locals);
        mLocalPicker.setPicker(locals);
        if (!mLocalPicker.isShowing()) {
            dialogDismiss("");
            mLocalPicker.show();
        }
    }

    public void setCityCodeData(CityCodeBean bean) {
        city_code = bean.city_code;
        SharePreUtil.saveData(this, "city_code", city_code);
        mPresenter.requestLocalData(city_code);
    }
}
