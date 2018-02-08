package com.modiwu.mah.mvp.model.bean;

import top.jplayer.baseprolibrary.mvp.model.bean.BaseBean;

/**
 * Created by Obl on 2018/2/8.
 * com.modiwu.mah.mvp.model.bean
 */

public class DefLocalBean extends BaseBean{

    /**
     * addr : {"rp_id":10001,"user_id":0,"rp_name":"454","rp_phone":"18366108542","rp_province":"北京市","rp_city":"市辖区","rp_area":"海淀区","rp_addr":"weqwe","rp_default":1,"ct":"2018-02-07 13:45:16"}
     */

    public AddrBean addr;

    public static class AddrBean {
        /**
         * rp_id : 10001
         * user_id : 0
         * rp_name : 454
         * rp_phone : 18366108542
         * rp_province : 北京市
         * rp_city : 市辖区
         * rp_area : 海淀区
         * rp_addr : weqwe
         * rp_default : 1
         * ct : 2018-02-07 13:45:16
         */

        public int rp_id;
        public int user_id;
        public String rp_name;
        public String rp_phone;
        public String rp_province;
        public String rp_city;
        public String rp_area;
        public String rp_addr;
        public int rp_default;
        public String ct;
    }
}
