package com.modiwu.mah.mvp.model.bean;

import java.util.List;

import top.jplayer.baseprolibrary.mvp.model.bean.BaseBean;

/**
 * Created by Obl on 2018/2/1.
 * com.modiwu.mah.mvp.model.bean
 */

public class CarpenterBean extends BaseBean {
    /**
     * records : [{"designer_id":10000,"designer_name":"尼古拉赵四","designer_desc":"擅长设计卫生间","designer_flag":"1"}]
     * more : false
     */

    public boolean more;
    public List<RecordsBean> records;

    public static class RecordsBean {
        /**
         * designer_id : 10000
         * designer_name : 尼古拉赵四
         * designer_desc : 擅长设计卫生间
         * designer_flag : 1
         */

        public int designer_id;
        public String designer_name;
        public String designer_desc;
        public String designer_flag;
        public String designer_url;
    }
}
