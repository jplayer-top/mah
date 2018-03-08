package com.modiwu.mah.mvp.model.bean;

import top.jplayer.baseprolibrary.mvp.model.bean.BaseBean;

/**
 * Created by Obl on 2017/5/9.
 * com.ilanchuang.xiaoi.bean
 */

public class VersionBean extends BaseBean {
    /**
     * ver : {"version":"1.1","build":2,"content":"1.UI调整","file_url":"https://mah.oss-cn-beijing.aliyuncs.com/app/xiaoi-app-1.1.apk","add_time":"2018-03-02 16:16:11"}
     */

    public VerBean ver;

    public static class VerBean {
        /**
         * version : 1.1
         * build : 2
         * content : 1.UI调整
         * file_url : https://mah.oss-cn-beijing.aliyuncs.com/app/xiaoi-app-1.1.apk
         * add_time : 2018-03-02 16:16:11
         */

        public String version;
        public int build;
        public String content;
        public String file_url;
        public String add_time;
    }

//    /**
//     * ver : {"id":60,"ver_num":"dsfds","build_num":"3","channel":"1","terminal":1,"url":"https://xiaoi-img.oss-cn-qingdao.aliyuncs.com/app/xiaoi-app-dsfds.apk","description":"sdfsf","ct":"2017-04-27 14:51:23"}
//     */
//
//    public VerBean ver;
//
//    public static class VerBean {
//        /**
//         * id : 60
//         * ver_num : dsfds
//         * build_num : 3
//         * channel : 1
//         * terminal : 1
//         * url : https://xiaoi-img.oss-cn-qingdao.aliyuncs.com/app/xiaoi-app-dsfds.apk
//         * description : sdfsf
//         * ct : 2017-04-27 14:51:23
//         */
//
//        public String version;
//        public String build;
//        public int versionCode;
//        public String file_url;
//        public String add_time;
//        public String content;
//    }

}
