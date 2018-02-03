package com.modiwu.mah.mvp.model.bean;

import java.util.List;

import top.jplayer.baseprolibrary.mvp.model.bean.BaseBean;

/**
 * Created by Obl on 2018/1/31.
 * com.modiwu.mah.mvp.model.bean
 */

public class SelectBean extends BaseBean {
    public List<RowsBean> rows;

    public static class RowsBean {
        /**
         * cat_class : lp.hx
         * cat_value : ss
         */

        public String cat_class;
        public String cat_value;
    }
}
