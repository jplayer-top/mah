package com.modiwu.mah.mvp.model.bean;

import java.util.List;

import top.jplayer.baseprolibrary.mvp.model.bean.BaseBean;

/**
 * Created by Obl on 2018/2/5.
 * com.modiwu.mah.mvp.model.bean
 */

public class ShopSubListBean extends BaseBean {

    /**
     * records : [{"goods_title":"小椅盒子","goods_price":77700,"goods_stocks":180,"goods_id":10017},{"goods_title":"实用卫浴扶手，关键的时候，总希望有人扶一下","goods_price":1500,"goods_stocks":100,"goods_id":10021},{"goods_title":"卫生洗澡凳子","goods_price":29999,"goods_stocks":100,"goods_id":10023},{"goods_title":"我是测试","goods_price":13900,"goods_stocks":20,"goods_id":10026}]
     * more : true
     * total : 4
     */

    public boolean more;
    public int total;
    public List<RecordsBean> records;

    public static class RecordsBean {
        /**
         * goods_title : 小椅盒子
         * goods_price : 77700
         * goods_stocks : 180
         * goods_id : 10017
         */

        public String goods_title;
        public String goods_subtitle;
        public String goods_thumb;
        public String goods_img;
        public int goods_price;
        public String goods_price_yuan;
        public int goods_stocks;
        public int goods_id;
    }
}
