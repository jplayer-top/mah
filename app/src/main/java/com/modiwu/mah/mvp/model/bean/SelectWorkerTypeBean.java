package com.modiwu.mah.mvp.model.bean;

import java.util.List;

import top.jplayer.baseprolibrary.mvp.model.bean.BaseBean;

/**
 * Created by Obl on 2018/9/14.
 * com.modiwu.mah.mvp.model.bean
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */

public class SelectWorkerTypeBean extends BaseBean {

    public List<RowsBean> rows;

    public static class RowsBean {
        /**
         * cat_class : lp.worktype
         * cat_value : 木工
         * cat_label : 木工
         */

        public String cat_class;
        public String cat_value;
        public String cat_label;
    }
}
