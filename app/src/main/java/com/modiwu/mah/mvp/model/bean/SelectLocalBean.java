package com.modiwu.mah.mvp.model.bean;

import java.util.List;

import top.jplayer.baseprolibrary.mvp.model.bean.BaseBean;

/**
 * Created by Obl on 2018/2/6.
 * com.modiwu.mah.mvp.model.bean
 */

public class SelectLocalBean extends BaseBean{

    public List<RowsBean> rows;

    public static class RowsBean {
        /**
         * area_code : 370201
         * area_name : 市辖区
         * area_parent : 370200
         * area_lv : 区
         * area_ext :
         * area_status : 0
         */

        public String area_code;
        public String area_name;
        public String area_parent;
        public String area_lv;
        public String area_ext;
        public int area_status;
    }
}
