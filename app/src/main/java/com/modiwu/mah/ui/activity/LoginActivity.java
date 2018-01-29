package com.modiwu.mah.ui.activity;

import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.modiwu.mah.R;
import com.modiwu.mah.base.BaseSpecialActivity;

/**
 * Created by Obl on 2018/1/29.
 * com.modiwu.mah.ui.activity
 */

public class LoginActivity extends BaseSpecialActivity {
    @Override
    public int setBaseLayout() {
        return R.layout.activity_login;
    }

    @Override
    public void initBaseData() {
        findView(mBaseView);
    }

    private void findView(View view) {
        LinearLayout llToSelect = view.findViewById(R.id.llToSelect);
        LinearLayout llToLogin = view.findViewById(R.id.llToLogin);
        LinearLayout llToNext = view.findViewById(R.id.llToNext);
        LinearLayout llFinishRegister = view.findViewById(R.id.llFinishRegister);
        Button btnToLogin = view.findViewById(R.id.btnToLogin);
        Button btnToRegister = view.findViewById(R.id.btnToRegister);
        Button btnCode = view.findViewById(R.id.btnCode);
        Button btnNext = view.findViewById(R.id.btnNext);
        Button btnRegister = view.findViewById(R.id.btnRegister);

    }
}
