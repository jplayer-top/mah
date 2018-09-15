package com.modiwu.mah.ui.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.modiwu.mah.R;
import com.modiwu.mah.mvp.model.bean.DecorateAllProBean;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by Obl on 2018/8/31.
 * com.modiwu.mah.ui.adapter
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */

public class AddProjectAdapter extends BaseQuickAdapter<DecorateAllProBean.ProjectsBean, BaseViewHolder> {
    public AddProjectAdapter(ArrayList<DecorateAllProBean.ProjectsBean> strings) {
        super(R.layout.adapter_add_pro, strings);
    }

    @Override
    protected void convert(BaseViewHolder helper, DecorateAllProBean.ProjectsBean item) {
        helper.setText(R.id.tvProIdNum, String.format(Locale.CHINA, "项目编号：%s", item.project_id))
                .setText(R.id.tvProName, item.project_name)
                .setText(R.id.tvName, String.format(Locale.CHINA, "负责人：%s", item.user_name))
                .setText(R.id.tvPhone, String.format(Locale.CHINA, "电话：%s", item.user_phone));


    }
}
