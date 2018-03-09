package com.modiwu.mah.ui.activity;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.modiwu.mah.R;
import com.modiwu.mah.base.BaseCommonActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

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

    @Override
    public int setBaseLayout() {
        return R.layout.activity_yy_house;
    }

    @Override
    public void initBaseData() {
        mUnbinder = ButterKnife.bind(this);
        mHuxing_type = mBundle.getString("huxing_type");
        mBuilding_id = mBundle.getString("building_id");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
    }
}
