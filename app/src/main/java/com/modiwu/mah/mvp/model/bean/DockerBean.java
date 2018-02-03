package com.modiwu.mah.mvp.model.bean;

import java.util.List;

import top.jplayer.baseprolibrary.mvp.model.bean.BaseBean;

/**
 * Created by Obl on 2018/2/2.
 * com.modiwu.mah.mvp.model.bean
 */

public class DockerBean extends BaseBean {
    /**
     * top : {"navType":"url","navValue":"https://mbd.baidu.com/newspage/data/landingsuper?context=%7B%22nid%22%3A%22news_17839096873250634050%22%7D&n_type=0&p_from=1","imgUrl":"https://ss2.baidu.com/6ONYsjip0QIZ8tyhnq/it/u=1673219351,1277611773&fm=173&s=C0107E9C068167E17D35744F03007076&w=640&h=424&img.JPEG"}
     * records : [{"cat_id":"16","parent_id":"0","cat_name":"小椅盒子","cat_desc":"电视盒子","enabled":1},{"cat_id":"17","parent_id":"0","cat_name":"血压仪","cat_desc":"血压仪","enabled":1},{"cat_id":"18","parent_id":"0","cat_name":"血糖仪","cat_desc":"血糖仪","enabled":1},{"cat_id":"19","parent_id":"0","cat_name":"智能腕表","cat_desc":"智能腕表","enabled":1},{"cat_id":"20","parent_id":"0","cat_name":"小椅周边","cat_desc":"","enabled":1}]
     */

    public TopBean top;
    public List<RecordsBean> records;

    public static class TopBean {
        /**
         * navType : url
         * navValue : https://mbd.baidu.com/newspage/data/landingsuper?context=%7B%22nid%22%3A%22news_17839096873250634050%22%7D&n_type=0&p_from=1
         * imgUrl : https://ss2.baidu.com/6ONYsjip0QIZ8tyhnq/it/u=1673219351,1277611773&fm=173&s=C0107E9C068167E17D35744F03007076&w=640&h=424&img.JPEG
         */

        public String navType;
        public String navValue;
        public String imgUrl;
    }

    public static class RecordsBean {
        /**
         * cat_id : 16
         * parent_id : 0
         * cat_name : 小椅盒子
         * cat_desc : 电视盒子
         * enabled : 1
         */

        public String cat_id;
        public String parent_id;
        public String cat_name;
        public String cat_desc;
        public int enabled;
    }
}
