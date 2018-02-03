package com.modiwu.mah.mvp.model.bean;

import java.util.List;

import top.jplayer.baseprolibrary.mvp.model.bean.BaseBean;

/**
 * Created by Obl on 2018/1/31.
 * com.modiwu.mah.mvp.model.bean
 */

public class FloorBean extends BaseBean {
    public List<RowsBean> rows;

    public static class RowsBean {
        /**
         * building_id : 9999
         * building_name : 其他
         */

        public int building_id;
        public String building_name;
    }
}
