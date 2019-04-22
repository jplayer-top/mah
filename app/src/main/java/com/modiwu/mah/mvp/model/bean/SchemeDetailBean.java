package com.modiwu.mah.mvp.model.bean;

import java.util.List;

import top.jplayer.baseprolibrary.mvp.model.bean.BaseBean;

/**
 * Created by Obl on 2018/2/3.
 * com.modiwu.mah.mvp.model.bean
 */

public class SchemeDetailBean extends BaseBean {


    /**
     * loupan : {"building_id":1,"building_name":"中华小区","building_adv_img":"/upload/lp/5eb0288b03382c3c107d77c2bec0a2cc.png","building_city_code":"370600","building_area_code":"370602","building_address":"福山西山路与汇福街交汇处东北角（北方家纺西300米）","building_price":6500,"building_kaipan":"还在修","building_sell_address":"福山西山路与汇福街交汇处东北角","building_phone":"0535888888","building_ruzhu":"预计2017年6月30日一期交房","building_zlhx":"60.1","building_wylx":"普通住宅","building_cqnx":"1000年","building_xmjj":"       转身繁华，出入优雅，拥三山两湖天赋之优越，成喧闹之中绝佳养生领域，是您一生的无悔臻选。\r\n      89-109㎡全明通透多层，4万抵7万，10月开盘 ，额满在即。怡景苑独创一楼赠送地上一层复式结构，并给业主预留近60平米的院子。\r\n\t\t\t\t\t\r\n\t\t\t\t\t","building_imgs":"/upload/lp/433acb2aebdf734290d9ba8b083ddb99.png","building_flag":"1"}
     * zheng : [{"detail_id":10002,"detail_type":"fangan","biz_id":5,"title":"asdf","subtitle":"sdf","img":"sdfasd","sort":""}]
     * ying : [{"detail_id":10004,"detail_type":"fangan","biz_id":8,"title":"asdfasd","subtitle":"232","img":"asdfasdf"}]
     * ruan : [{"detail_id":10003,"detail_type":"fangan","biz_id":7,"title":"a3","subtitle":"adf","img":"2"}]
     * goods : [{"goods_id":10017,"cat_id":16,"goods_sn":"","goods_title":"小椅盒子","goods_subtitle":"","goods_img":"shop/77efa3e8cbc54bffb3ff85a1b440d9a6.jpg,shop/de1701aaab624400b3477f29ed248a9b.jpg","goods_price":77700,"goods_stocks":180,"keywords":"","is_on":1,"goods_ct":"2017-07-11 19:40:57","goods_ut":"2017-07-13 15:05:45","goods_bt":"2017-07-13 15:05:48","goods_rt":"2017-07-20 09:51:02","is_real":1,"is_delete":1,"is_preheat":0,"goods_remark":"测试"}]
     */
    public String fangan_name;
    public String fangan_id;
    public LoupanBean loupan;
    public List<ZhengBean> zheng;
    public List<YingBean> ying;
    public List<RuanBean> ruan;
    public List<GoodsBean> goods;
    public List<FanganBean> fangans;
    public List<LoupanhuxingBean> loupanhuxing;
    public ProductBean proudct;

    public static class ProductBean {

        /**
         * fangan_id : 10003
         * building_id : 10001
         * building_name : 目简
         * huxing_id : 10000
         * huxing_type : 三室一厅
         * huxing_size : 160
         * fangan_name : tesadf
         * fangan_desc : aasdfasd
         * fangan_avatar : https://mah.oss-cn-beijing.aliyuncs.com/fangan/616deb1b1e734eefafba7486a239df0b.jpg
         * fangan_style : 地中海
         * fangan_city_code : 370600
         * fangan_area_code : 370613
         * fangan_type : 设计
         * fangan_top_id : 10002
         * designer_id : 10000
         * fangan_flag : 1
         * sort :
         * product_id : 10392
         * type : 1
         */

        public int fangan_id;
        public int building_id;
        public String building_name;
        public int huxing_id;
        public String huxing_type;
        public int huxing_size;
        public String fangan_name;
        public String fangan_desc;
        public String fangan_avatar;
        public String fangan_style;
        public String fangan_city_code;
        public String fangan_area_code;
        public String fangan_type;
        public int fangan_top_id;
        public int designer_id;
        public String fangan_flag;
        public String sort;
        public int product_id;
        public String type;
    }

    public static class LoupanBean {
        /**
         * building_id : 1
         * building_name : 中华小区
         * building_adv_img : /upload/lp/5eb0288b03382c3c107d77c2bec0a2cc.png
         * building_city_code : 370600
         * building_area_code : 370602
         * building_address : 福山西山路与汇福街交汇处东北角（北方家纺西300米）
         * building_price : 6500
         * building_kaipan : 还在修
         * building_sell_address : 福山西山路与汇福街交汇处东北角
         * building_phone : 0535888888
         * building_ruzhu : 预计2017年6月30日一期交房
         * building_zlhx : 60.1
         * building_wylx : 普通住宅
         * building_cqnx : 1000年
         * building_xmjj :        转身繁华，出入优雅，拥三山两湖天赋之优越，成喧闹之中绝佳养生领域，是您一生的无悔臻选。
         * 89-109㎡全明通透多层，4万抵7万，10月开盘 ，额满在即。怡景苑独创一楼赠送地上一层复式结构，并给业主预留近60平米的院子。
         * <p>
         * <p>
         * building_imgs : /upload/lp/433acb2aebdf734290d9ba8b083ddb99.png
         * building_flag : 1
         */

        public int building_id;
        public String building_name;
        public String building_adv_img;
        public String building_city_code;
        public String building_area_code;
        public String building_address;
        public int building_price;
        public String building_kaipan;
        public String building_sell_address;
        public String building_phone;
        public String building_ruzhu;
        public String building_zlhx;
        public String building_wylx;
        public String building_cqnx;
        public String building_xmjj;
        public String building_imgs;
        public String building_flag;
    }

    public static class ZhengBean {
        /**
         * detail_id : 10002
         * detail_type : fangan
         * biz_id : 5
         * title : asdf
         * subtitle : sdf
         * img : sdfasd
         * sort :
         */

        public int detail_id;
        public String detail_type;
        public int biz_id;
        public String title;
        public String subtitle;
        public String img;
        public String link_url;
        public String sort;
    }

    public static class YingBean {
        /**
         * detail_id : 10004
         * detail_type : fangan
         * biz_id : 8
         * title : asdfasd
         * subtitle : 232
         * img : asdfasdf
         */

        public int detail_id;
        public String detail_type;
        public int biz_id;
        public String title;
        public String subtitle;
        public String link_url;
        public String img;
    }

    public static class RuanBean {
        /**
         * detail_id : 10003
         * detail_type : fangan
         * biz_id : 7
         * title : a3
         * subtitle : adf
         * img : 2
         */

        public int detail_id;
        public String detail_type;
        public int biz_id;
        public String title;
        public String subtitle;
        public String link_url;
        public String img;
    }

    public static class FanganBean {

        /**
         * fangan_id : 10014
         * building_id : 10004
         * building_name : 安德利花园
         * huxing_id : 10001
         * huxing_type : 三室一厅
         * huxing_size : 144
         * fangan_name : 安德利迎海花园 | 2室2厅1厨1卫 | 96㎡
         * fangan_desc : 黑白灰为基调搭配原木色，兼具情感与理性，找到人与自然共生的平衡，对功能性的要求不仅是实用，更是舒适。
         * fangan_avatar : https://mah.oss-cn-beijing.aliyuncs.com/fangan/7fe1b3ba0aab4d0ca5f4420c28a6a95d.jpg
         * fangan_style : 极简主义
         * fangan_city_code : 370600
         * fangan_area_code : 370612
         * fangan_type : 整装
         * fangan_top_id : 0
         * fangan_flag : 0
         * sort : 1
         * product_id : 10392
         * type : 1
         */

        public int fangan_id;
        public int building_id;
        public String building_name;
        public int huxing_id;
        public String huxing_type;
        public int huxing_size;
        public String fangan_name;
        public String fangan_desc;
        public String fangan_avatar;
        public String fangan_style;
        public String fangan_city_code;
        public String fangan_area_code;
        public String fangan_type;
        public int fangan_top_id;
        public String fangan_flag;
        public String sort;
        public int product_id;
        public String type;
    }

    public static class LoupanhuxingBean {
        /**
         * huxing_id : 3
         * building_id : 1
         * huxing_name : 两室一厅 84
         * huxing_avatar : https://mah.oss-cn-beijing.aliyuncs.com/foo/7fb256affb3231569e9ff547235ba2b1.png
         * huxing_type : 两室一厅
         * huxing_size : 84.0
         */

        public int huxing_id;
        public int building_id;
        public String huxing_name;
        public String huxing_avatar;
        public String huxing_type;
        public double huxing_size;
    }

    public static class GoodsBean {
        /**
         * goods_id : 10017
         * cat_id : 16
         * goods_sn :
         * goods_title : 小椅盒子
         * goods_subtitle : asdfasdfasdf
         * goods_thumb : https://mah.oss-cn-beijing.aliyuncs.com/foo/3.jpg
         * goods_img : shop/77efa3e8cbc54bffb3ff85a1b440d9a6.jpg,shop/de1701aaab624400b3477f29ed248a9b.jpg
         * goods_price : 77700
         * goods_stocks : 180
         * keywords :
         * is_on : 1
         * goods_ct : 2017-07-11 19:40:57
         * goods_ut : 2017-07-13 15:05:45
         * goods_bt : 2017-07-13 15:05:48
         * goods_rt : 2017-07-20 09:51:02
         * is_real : 1
         * is_delete : 1
         * is_preheat : 0
         * goods_remark : 测试
         * goods_price_yuan : 777.00
         */

        public int goods_id;
        public int cat_id;
        public String goods_sn;
        public String goods_title;
        public String goods_subtitle;
        public String goods_thumb;
        public String goods_img;
        public int goods_price;
        public int goods_stocks;
        public String keywords;
        public int is_on;
        public String goods_ct;
        public String goods_ut;
        public String goods_bt;
        public String goods_rt;
        public int is_real;
        public int is_delete;
        public int is_preheat;
        public String goods_remark;
        public String goods_price_yuan;
    }
}
