package com.modiwu.mah.mvp.model.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.List;

import top.jplayer.baseprolibrary.mvp.model.bean.BaseBean;

/**
 * Created by Obl on 2018/1/22.
 * com.modiwu.mah.mvp.model.bean
 */

public class HomeBean extends BaseBean implements MultiItemEntity {

    public static final int BODY_RECOMMEND = 0;
    public static final int BODY_SINGLE = 1;
    public static final int BODY_ADV = 2;
    public static final int BODY_TOSHOP = 3;
    public static final int BODY_SECTION = 4;
    public static final int BODY_HEARD = 5;
    public static final int BODY_NULL = 6;
    public static final int BODY_SECTION_FOOTER = 7;
    public String type;

    public HomeBean(String type) {
        this.type = type;
    }

    @Override
    public int getItemType() {
        if (type.equals("BODY_RECOMMEND")) {
            return BODY_RECOMMEND;
        } else if (type.equals("BODY_SINGLE")) {
            return BODY_SINGLE;
        } else if (type.equals("BODY_ADV")) {
            return BODY_ADV;
        } else if (type.equals("BODY_TOSHOP")) {
            return BODY_TOSHOP;
        } else if (type.equals("BODY_SECTION")) {
            return BODY_SECTION;
        } else if (type.equals("BODY_HEARD")) {
            return BODY_HEARD;
        } else if (type.equals("BODY_SECTION_FOOTER")) {
            return BODY_SECTION_FOOTER;
        } else
            return BODY_NULL;

    }

    /**
     * msg : 操作成功
     * banner : [{"navType":"1","navValue":"#","imgUrl":"shop/6f4eba13506a4bf0922eb7083254518b.png","title":"盒子宣传"},{"navType":"1","navValue":"http://www.taobao.com","imgUrl":"shop/559ee0153184477596f0eae856a2dbc9.png","title":"测试"},{"navType":"1","navValue":"#","imgUrl":"shop/4ca7bde1484b41bd97b304baab4a212d.png","title":"是大方的说法"}]
     * fangan : [{"navType":"fangan","navValue":"5","imgUrl":"/upload/fa/240ecb98ed34fbb5e1bfea07474dacd3.jpg","title":"未来幻想","subtitle":"我们的世界\t\t\t"},{"navType":"fangan","navValue":"14","imgUrl":"/upload/fa/fead812a185e5979325bad1136d9f49b.jpg","title":"无敌金刚"},{"navType":"fangan","navValue":"18","imgUrl":"/upload/fa/9541842235e3a4115c8e7e45328c973f.png","title":"绿城\u2022之江一号"}]
     * goods : []
     * sjs : [{"navType":"sjs","navValue":"10000","title":"尼古拉赵四","subtitle":"擅长设计卫生间"},{"navType":"url","navValue":"https://mbd.baidu.com/newspage/data/landingsuper?context=%7B%22nid%22%3A%22news_17839096873250634050%22%7D&n_type=0&p_from=1","imgUrl":"https://ss2.baidu.com/6ONYsjip0QIZ8tyhnq/it/u=1673219351,1277611773&fm=173&s=C0107E9C068167E17D35744F03007076&w=640&h=424&img.JPEG"}]
     */

    public List<BannerBean> banner;
    public List<FanganBean> fangan;
    public List<GoodBean> goods;
    public List<SjsBean> sjs;
    public List<ShiGongBean> shigong;
    public List<ShiDianBean> shidian;
    public List<ShouHouBean> shouhou;

    public static class ShiGongBean {


        public String navType;
        public String navValue;
        public String imgUrl;
    }

    public static class ShiDianBean {


        public String navType;
        public String navValue;
        public String imgUrl;
    }

    public static class ShouHouBean {


        public String navType;
        public String navValue;
        public String imgUrl;
    }

    public static class BannerBean {
        /**
         * navType : 1
         * navValue : #
         * imgUrl : shop/6f4eba13506a4bf0922eb7083254518b.png
         * title : 盒子宣传
         */

        public String navType;
        public String navValue;
        public String imgUrl;
        public String title;
    }

    public static class FanganBean {
        /**
         * navType : fangan
         * navValue : 5
         * imgUrl : /upload/fa/240ecb98ed34fbb5e1bfea07474dacd3.jpg
         * title : 未来幻想
         * subtitle : 我们的世界
         */

        public String navType;
        public String navValue;
        public String imgUrl;
        public String title;
        public String subtitle;
    }

    public static class GoodBean {
        /**
         * "navType": "goods",
         * "navValue": "10026",
         * "imgUrl": "shop/6af981b24bda4454b9704675d1637ca6.png",
         * "title": "我是测试",
         * "subtitle": "我是测试"
         */

        public String navType;
        public String navValue;
        public String imgUrl;
        public String title;
        public String price;
        public String subtitle;
    }

    public static class SjsBean {
        /**
         * navType : sjs
         * navValue : 10000
         * title : 尼古拉赵四
         * subtitle : 擅长设计卫生间
         * imgUrl : https://ss2.baidu.com/6ONYsjip0QIZ8tyhnq/it/u=1673219351,1277611773&fm=173&s=C0107E9C068167E17D35744F03007076&w=640&h=424&img.JPEG
         */

        public String navType;
        public String navValue;
        public String title;
        public String subtitle;
        public String imgUrl;
    }
}

