package com.modiwu.mah.mvp.model.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.List;

import top.jplayer.baseprolibrary.mvp.model.bean.BaseBean;

/**
 * Created by Obl on 2018/2/3.
 * com.modiwu.mah.mvp.model.bean
 */

public class DesignBean extends BaseBean implements MultiItemEntity {


    /**
     * designer : {"designer_id":10000,"designer_name":"尼古拉赵四","designer_desc":"擅长设计卫生间","designer_avatar":"dfasdf","designer_flag":"1"}
     * details : [{"detail_id":10000,"detail_type":"sjs","biz_id":10000,"title":"风一样的男子","subtitle":"丰富的想象、创新能力和前瞻性是必不可少的，这是设计师与工程师的一大区别。工程设计采用计算法或类比法，工作的性质主要是改进、完善而非创新；造型设计则非常讲究原创和独创性，设计的元素是变化无穷的线条和曲面，而不是严谨、繁琐的数据，\u201c类比\u201d出来的造型设计不可能是优秀的。","img":"adsf","sort":""},{"detail_id":10001,"detail_type":"sjs","biz_id":10000,"title":"作为一名优秀的游戏设计师","subtitle":"简单而言是画画的水平，进一步说则是美学水平和审美观。可以肯定全世界没有一个设计师是不会画画的，\u201c图画是设计师的语言\u201d，这道理也不用多说了。虽然现今已有其它能表达设计的方法（如计算机），但纸笔作画仍是最简单、直接、快速的方法。","img":"asdfsdffd","sort":""}]
     * fangan : [{"fangan_id":5,"fangan_name":"未来幻想","fangan_desc":"我们的世界\t\t\t","fangan_avatar":"/upload/fa/240ecb98ed34fbb5e1bfea07474dacd3.jpg","fangan_type":"整装","fangan_top_id":0}]
     */

    public DesignerBean designer;
    public List<DetailsBean> details;
    public List<FanganBean> fangan;

    public static class DesignerBean {
        /**
         * designer_id : 10000
         * designer_name : 尼古拉赵四
         * designer_desc : 擅长设计卫生间
         * designer_avatar : dfasdf
         * designer_flag : 1
         */

        public int designer_id;
        public String designer_name;
        public String designer_desc;
        public String designer_avatar;
        public String designer_flag;
    }

    public static final int BODY_DETIALS = 0;
    public static final int BODY_FANGAN = 1;
    public String type;

    @Override
    public int getItemType() {
        if (type.equals("BODY_DETIALS")) {
            return BODY_DETIALS;
        } else {
            return BODY_FANGAN;
        }
    }

    public DesignBean(String type) {
        this.type = type;
    }

    public static class DetailsBean {
        /**
         * detail_id : 10000
         * detail_type : sjs
         * biz_id : 10000
         * title : 风一样的男子
         * subtitle : 丰富的想象、创新能力和前瞻性是必不可少的，这是设计师与工程师的一大区别。工程设计采用计算法或类比法，工作的性质主要是改进、完善而非创新；造型设计则非常讲究原创和独创性，设计的元素是变化无穷的线条和曲面，而不是严谨、繁琐的数据，“类比”出来的造型设计不可能是优秀的。
         * img : adsf
         * sort :
         */

        public int detail_id;
        public String detail_type;
        public int biz_id;
        public String title;
        public String subtitle;
        public String img;
        public String sort;
    }

    public static class FanganBean {
        /**
         * fangan_id : 5
         * fangan_name : 未来幻想
         * fangan_desc : 我们的世界
         * fangan_avatar : /upload/fa/240ecb98ed34fbb5e1bfea07474dacd3.jpg
         * fangan_type : 整装
         * fangan_top_id : 0
         */

        public int fangan_id;
        public String fangan_name;
        public String fangan_desc;
        public String fangan_avatar;
        public String fangan_type;
        public int fangan_top_id;
    }
}
