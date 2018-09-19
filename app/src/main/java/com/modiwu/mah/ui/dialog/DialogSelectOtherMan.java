package com.modiwu.mah.ui.dialog;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.modiwu.mah.R;
import com.modiwu.mah.utils.StringUtils;

import java.util.Locale;

import top.jplayer.baseprolibrary.utils.ToastUtils;
import top.jplayer.baseprolibrary.widgets.dialog.BaseCustomDialog;

/**
 * Created by Obl on 2018/8/30.
 * com.modiwu.mah.ui.dialog
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */

public class DialogSelectOtherMan extends BaseCustomDialog {


    private TextView mTvTipPhone;
    private TextView mTvFindPhone;
    private TextView mTvFirstName;
    private TextView mTvName;
    public EditText mEditPhone;

    public DialogSelectOtherMan(Context context) {
        super(context);
    }

    public DialogSelectOtherMan setTip(String tip) {
        mTvTipPhone.setText(String.format(Locale.CHINA, "请输入%s手机号：", tip));
        return this;
    }

    public DialogSelectOtherMan setName(String name) {
        mTvName.setText(name);
        mTvFirstName.setText(name.substring(0, 1));
        return this;
    }

    public DialogSelectOtherMan setOnFindListener(FindListener listener) {
        this.mFindListener = listener;
        return this;
    }

    @Override
    protected void initView(View view) {
        mTvTipPhone = view.findViewById(R.id.tvTipPhone);
        mTvFindPhone = view.findViewById(R.id.tvFindPhone);
        mTvFirstName = view.findViewById(R.id.tvFirstName);
        mTvName = view.findViewById(R.id.tvName);
        mEditPhone = view.findViewById(R.id.editPhone);
        mTvFindPhone.setOnClickListener(v -> {
            if (mFindListener != null) {
                String phone = mEditPhone.getText().toString();
                if (StringUtils.getInstance().isNullObj(phone)) {
                    ToastUtils.init().showInfoToast(getContext(), "请输入要添加的电话号码");
                    return;
                }
                mFindListener.onFindListener(phone);
            }
        });
    }

    private FindListener mFindListener;

    public interface FindListener {
        void onFindListener(String phone);
    }

    @Override
    public void dismiss() {
        mEditPhone.setText("");
        super.dismiss();

    }

    @Override
    public int initLayout() {
        return R.layout.dialog_select_other_man;
    }

    @Override
    public int setWidth(int i) {
        return super.setWidth(8);
    }

    @Override
    public int setAnim() {
        return R.style.AnimFade;
    }

    @Override
    public int setGravity() {
        return Gravity.CENTER;
    }
}
