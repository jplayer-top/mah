package com.modiwu.mah.utils;

import android.app.Activity;
import android.view.View;
import android.widget.EditText;

/**
 * obl
 * Created by PEO on 2017/3/9.
 * EditText的工具类
 */

public class EditTextUtils {
    /**
     * 自动居中
     */
    public static void setEditFacusListener(final EditText view, final String string, final Activity activity) {
        view.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                String editStr = view.getText().toString().trim();
                if (!hasFocus) {
                    if (editStr.equals("")) {
                        view.setHint(string);
                    } else
                        view.setHint("   ");
                } else {
//                    view.setFocusableInTouchMode(true);
//                    view.requestFocus();
//                    activity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
                    if (string.length() > editStr.length()) {
                        view.setHint("   ");
                    } else
                        view.setHint(string);
                }
            }
        });
    }
}
