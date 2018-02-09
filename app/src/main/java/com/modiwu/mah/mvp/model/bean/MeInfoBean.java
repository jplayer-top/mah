package com.modiwu.mah.mvp.model.bean;

import top.jplayer.baseprolibrary.mvp.model.bean.BaseBean;

/**
 * Created by Obl on 2018/2/9.
 * com.modiwu.mah.mvp.model.bean
 */

public class MeInfoBean extends BaseBean {

    /**
     * profile : {"user_id":10001,"user_name":"王哥","user_phone":"15949886832","user_email":"25@qq.com","user_building":"中华","user_huxing":"三室","user_mian":"96","user_points":0}
     */

    public ProfileBean profile;

    public static class ProfileBean {
        /**
         * user_id : 10001
         * user_name : 王哥
         * user_phone : 15949886832
         * user_email : 25@qq.com
         * user_building : 中华
         * user_huxing : 三室
         * user_mian : 96
         * user_points : 0
         */

        public int user_id;
        public String user_name;
        public String user_phone;
        public String user_email;
        public String user_building;
        public String user_huxing;
        public String user_mian;
        public String user_avatar;
        public int user_points;
    }
}
