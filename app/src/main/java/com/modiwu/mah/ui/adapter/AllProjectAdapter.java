package com.modiwu.mah.ui.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.modiwu.mah.R;
import com.modiwu.mah.mvp.model.bean.DecorateAllProBean;

import java.util.ArrayList;

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

    }
}
