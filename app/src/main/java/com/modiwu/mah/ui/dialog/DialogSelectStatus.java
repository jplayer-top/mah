package com.modiwu.mah.ui.dialog;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.modiwu.mah.R;
import com.modiwu.mah.mvp.model.event.DialogSelStatusEvent;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import top.jplayer.baseprolibrary.widgets.dialog.BaseCustomDialog;

/**
 * Created by Obl on 2018/8/30.
 * com.modiwu.mah.ui.dialog
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */

public class DialogSelectStatus extends BaseCustomDialog {


    private TextView mTvPre;
    private TextView mTvIng;
    private TextView mTvEnd;
    private List<TextView> mViewList;

    public DialogSelectStatus(Context context) {
        super(context);
    }


    @Override
    protected void initView(View view) {
        mViewList = new ArrayList<>();
        mTvPre = view.findViewById(R.id.tvPre);
        mTvIng = view.findViewById(R.id.tvIng);
        mTvEnd = view.findViewById(R.id.tvEnd);
        mViewList.add(mTvPre);
        mViewList.add(mTvIng);
        mViewList.add(mTvEnd);
        mTvPre.setOnClickListener(v -> EventBus.getDefault().post(new DialogSelStatusEvent(0)));
        mTvIng.setOnClickListener(v -> EventBus.getDefault().post(new DialogSelStatusEvent(1)));
        mTvEnd.setOnClickListener(v -> EventBus.getDefault().post(new DialogSelStatusEvent(2)));
    }

    public void setBg(int status) {
        for (int i = 0; i < mViewList.size(); i++) {
            mViewList.get(i).setBackground(getContext().getResources().getDrawable(i == status ? R.drawable
                    .shape_cir_theme : R.drawable.shape_cir_gray));
        }
    }

    @Override
    public int initLayout() {
        return R.layout.dialog_select_status;
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
