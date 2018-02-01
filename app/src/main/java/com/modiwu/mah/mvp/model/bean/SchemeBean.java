package com.modiwu.mah.mvp.model.bean;

import java.util.List;

/**
 * Created by Obl on 2018/2/1.
 * com.modiwu.mah.mvp.model.bean
 */

public class SchemeBean extends BaseBean {

    /**
     * records : [{"fangan_id":5,"fangan_name":"未来幻想","fangan_desc":"我们的世界\t\t\t","fangan_avatar":"/upload/fa/240ecb98ed34fbb5e1bfea07474dacd3.jpg","fangan_type":"整装","fangan_top_id":0},{"fangan_id":14,"fangan_name":"无敌金刚","fangan_avatar":"/upload/fa/fead812a185e5979325bad1136d9f49b.jpg","fangan_type":"整装","fangan_top_id":0},{"fangan_id":18,"fangan_name":"绿城\u2022之江一号","fangan_avatar":"/upload/fa/9541842235e3a4115c8e7e45328c973f.png","fangan_type":"整装","fangan_top_id":0},{"fangan_id":22,"fangan_name":"金隅","fangan_avatar":"/upload/fa/a3bb7b38943dd2363277465a3b92cd0a.jpg","fangan_type":"整装","fangan_top_id":0},{"fangan_id":26,"fangan_name":"颜值革命","fangan_avatar":"/upload/fa/126a5ed48edb5e835c016432e09c4e62.PNG","fangan_type":"整装","fangan_top_id":0},{"fangan_id":31,"fangan_name":"213找","fangan_desc":"阿斯蒂芬 ","fangan_avatar":"/upload/fa/24b787439f80c4e0b47748bb588e351e.png","fangan_type":"整装","fangan_top_id":0}]
     * more : false
     */

    public boolean more;
    public List<RecordsBean> records;

    public static class RecordsBean {
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
