package com.modiwu.mah.mvp.model.bean;

import java.util.List;

import top.jplayer.baseprolibrary.mvp.model.bean.BaseBean;

/**
 * Created by Obl on 2018/2/6.
 * com.modiwu.mah.mvp.model.bean
 */

public class MeOrderBean extends BaseBean {

    public List<RecordsBean> records;

    public static class RecordsBean {
        /**
         * order_id : 2017071713510583047
         * order_title : 小椅盒子
         * order_status : 0
         * actual_price : 888000
         * total_amount : 10
         * detail : [{"order_detail_id":168,"order_id":"2017071713510583047","goods_id":10017,"goods_title":"小椅盒子","goods_thumb":"https://mah.oss-cn-beijing.aliyuncs.com/shop/a5920e9133bb4dbdab70ff94249ca396.jpg","goods_img":"shop/77efa3e8cbc54bffb3ff85a1b440d9a6.jpg,shop/de1701aaab624400b3477f29ed248a9b.jpg","goods_desc":"shop/7ea2da21cb2444929a3bb8970fe11704.png,shop/01043c4109714b4aa26bd1985ba7dcfb.jpg","attr_ids":"9","attr_values":"黑色","amount":10,"is_real":1,"org_price":99900,"unit_price":88800,"total_price":888000,"ct":"2017-07-17 13:51:05"}]
         */

        public String order_id;
        public String order_title;
        public int order_status;
        public int actual_price;
        public int total_amount;
        public List<DetailBean> detail;

        public static class DetailBean {
            /**
             * order_detail_id : 168
             * order_id : 2017071713510583047
             * goods_id : 10017
             * goods_title : 小椅盒子
             * goods_thumb : https://mah.oss-cn-beijing.aliyuncs.com/shop/a5920e9133bb4dbdab70ff94249ca396.jpg
             * goods_img : shop/77efa3e8cbc54bffb3ff85a1b440d9a6.jpg,shop/de1701aaab624400b3477f29ed248a9b.jpg
             * goods_desc : shop/7ea2da21cb2444929a3bb8970fe11704.png,shop/01043c4109714b4aa26bd1985ba7dcfb.jpg
             * attr_ids : 9
             * attr_values : 黑色
             * amount : 10
             * is_real : 1
             * org_price : 99900
             * unit_price : 88800
             * total_price : 888000
             * ct : 2017-07-17 13:51:05
             */

            public int order_detail_id;
            public String order_id;
            public int goods_id;
            public String goods_title;
            public String goods_thumb;
            public String goods_img;
            public String goods_desc;
            public String attr_ids;
            public String attr_values;
            public int amount;
            public int is_real;
            public int org_price;
            public int unit_price;
            public int total_price;
            public String ct;
        }
    }
}
