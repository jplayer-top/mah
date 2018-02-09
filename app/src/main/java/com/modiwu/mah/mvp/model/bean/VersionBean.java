package com.modiwu.mah.mvp.model.bean;

import top.jplayer.baseprolibrary.mvp.model.bean.BaseBean;

/**
 * Created by Obl on 2017/5/9.
 * com.ilanchuang.xiaoi.bean
 */

public class VersionBean extends BaseBean {

    /**
     * ver : {"id":60,"ver_num":"dsfds","build_num":"3","channel":"1","terminal":1,"url":"https://xiaoi-img.oss-cn-qingdao.aliyuncs.com/app/xiaoi-app-dsfds.apk","description":"sdfsf","ct":"2017-04-27 14:51:23"}
     */

    public VerBean ver;

    public static class VerBean {
        /**
         * id : 60
         * ver_num : dsfds
         * build_num : 3
         * channel : 1
         * terminal : 1
         * url : https://xiaoi-img.oss-cn-qingdao.aliyuncs.com/app/xiaoi-app-dsfds.apk
         * description : sdfsf
         * ct : 2017-04-27 14:51:23
         */

        public int id;
        public String ver_num;
        public String build_num;
        public String channel;
        public int terminal;
        public String url;
        public String description;
        public String ct;
    }
}
