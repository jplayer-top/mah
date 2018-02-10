package com.modiwu.mah.mvp.model.bean;

import java.util.List;

import top.jplayer.baseprolibrary.mvp.model.bean.BaseBean;

/**
 * Created by Obl on 2018/2/8.
 * com.modiwu.mah.mvp.model.bean
 */

public class SchemeOrderCreateBean extends BaseBean {

    public static final int BODY_ITEM = 0;
    public static final int BODY_HEARD = 1;
    public List<SheBean> she;
    public List<YingBean> ying;
    public List<RuanBean> ruan;
    public String type;

    public SchemeOrderCreateBean(String type) {
        this.type = type;
    }

    public static class SheBean {
        /**
         * fangan_id : 5
         * goods_id : 10036
         * goods_title : test
         * goods_attr_id : 320
         * attr_name : 土豪金
         * goods_price_yuan : 9.80
         * goods_num : 1
         * goods_price : 980
         */
        public boolean isCheck = true;
        public int fangan_id;
        public int goods_id;
        public String goods_title;
        public String goods_thumb;
        public int goods_attr_id;
        public String attr_name;
        public String goods_price_yuan;
        public int goods_num;
        public int goods_price;
    }

    public static class YingBean {
        /**
         * fangan_id : 5
         * goods_id : 10019
         * goods_title : 小椅血糖仪 家用 安稳免调码套装
         * goods_attr_id : 254
         * attr_name : 青春型
         * goods_price_yuan : 99.00
         * goods_num : 2
         * goods_price : 9900
         */
        public boolean isCheck = true;
        public int fangan_id;
        public int goods_id;
        public String goods_title;
        public String goods_thumb;
        public int goods_attr_id;
        public String attr_name;
        public String goods_price_yuan;
        public int goods_num;
        public int goods_price;
    }

    public static class RuanBean {
        /**
         * fangan_id : 5
         * goods_id : 10017
         * goods_title : 小椅盒子
         * goods_thumb : https://mah.oss-cn-beijing.aliyuncs.com/foo/3.jpg
         * goods_attr_id : 260
         * attr_name : 土豪金
         * goods_price_yuan : 777.00
         * goods_num : 1
         * goods_price : 77700
         */
        public boolean isCheck;
        public boolean isHeardCheck;
        public int fangan_id;
        public int goods_id;
        public String goods_title;
        public String goods_thumb;
        public int goods_attr_id;
        public String attr_name;
        public String goods_price_yuan;
        public int goods_num;
        public int goods_price;
    }
}
