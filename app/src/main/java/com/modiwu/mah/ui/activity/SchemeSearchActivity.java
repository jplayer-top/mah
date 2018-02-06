package com.modiwu.mah.ui.activity;

import android.graphics.Color;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.modiwu.mah.R;
import com.modiwu.mah.base.BaseCommonActivity;
import com.modiwu.mah.mvp.constract.SchemeSelectContract;
import com.modiwu.mah.mvp.presenter.SchemeSelectPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import top.jplayer.baseprolibrary.utils.LogUtil;

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

    @Override
    public int setBaseLayout() {
        return R.layout.activity_scheme_search;
    }

    @Override
    public void initBaseData() {
        findToolBarView(mFlRootView);
        mUnbinder = ButterKnife.bind(this, addRootView);
        tvBarTitle.setText("选择条件");
        ivBarSearch.setVisibility(View.VISIBLE);
        ivBarSearch.setImageResource(R.drawable.main_me);
        ivBarSearch.setOnClickListener(v -> LogUtil.e("客服"));
        initPicker();
        mPresenter = new SchemeSelectPresenter(this);
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
            if (this.floors != null) {
                setTypeData(this.floors);
                return;
            }
            dialogShow(this);
            mPresenter.requestFloorData();
        });
        mLlLocalSelect.setOnClickListener(v -> {
            if (this.locals != null) {
                setLocalData(this.locals);
                return;
            }
            dialogShow(this);
            mPresenter.requestLocalData("370200");
        });
    }

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

    @Override
    public void setFloorData(List<String> floors) {
        this.floors = floors;
        mFloorPicker = getPicker("楼盘", mTvFloor, floors);
        mFloorPicker.setPicker(floors);
        if (!mFloorPicker.isShowing()) {
            dialogDismiss("");
            mFloorPicker.show();
        }
    }

    private List<String> locals;

    @Override
    public void setLocalData(List<String> locals) {
        this.locals = locals;
        mLocalPicker = getPicker("地区", mTvLocal, locals);
        mLocalPicker.setPicker(locals);
        if (!mLocalPicker.isShowing()) {
            dialogDismiss("");
            mLocalPicker.show();
        }
    }
}
