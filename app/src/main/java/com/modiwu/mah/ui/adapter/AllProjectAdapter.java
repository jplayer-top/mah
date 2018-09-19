package com.modiwu.mah.ui.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.modiwu.mah.R;
import com.modiwu.mah.mvp.model.bean.DecorateAllProBean;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by Obl on 2018/8/30.
 * com.modiwu.mah.ui.adapter
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */

public class AllProjectAdapter extends BaseQuickAdapter<DecorateAllProBean.ProjectsBean, BaseViewHolder> {
    public AllProjectAdapter(ArrayList<DecorateAllProBean.ProjectsBean> strings) {
        super(R.layout.adapter_app_project, strings);
    }

    @Override
    protected void convert(BaseViewHolder helper, DecorateAllProBean.ProjectsBean item) {
        helper.setText(R.id.tvProId, String.format(Locale.CHINA, "项目编号：%s", item.project_id))
                .setText(R.id.tvNamePhone, String.format(Locale.CHINA, "负责人：%s %s", item.user_name, item.user_phone))
                .setText(R.id.tvTask, String.format(Locale.CHINA, "参与项目环节：%s", item.flow_name))
                .setText(R.id.tvRating, String.format(Locale.CHINA, "%2.1f", item.appraise))
                .setText(R.id.tvProName, String.format(Locale.CHINA, "%s", item.project_name));
    }
}
