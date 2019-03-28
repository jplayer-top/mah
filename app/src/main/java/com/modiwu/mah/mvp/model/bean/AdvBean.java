package com.modiwu.mah.mvp.model.bean;

import top.jplayer.baseprolibrary.mvp.model.bean.BaseBean;

/**
 * Created by Administrator on 2019/3/28.
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */

public class AdvBean extends BaseBean {

    /**
     * banner : {"banner_id":10006,"banner_title":"免费获取","banner_img":"https://mah.oss-cn-beijing.aliyuncs.com/bner/159916e48b5241d1930659f9c32c07cf.png","banner_value":"#","banner_type":"sjfa","sort":"1","flag":"0","pop_adv":"1"}
     */

    public BannerBean banner;

    public static class BannerBean {
        /**
         * banner_id : 10006
         * banner_title : 免费获取
         * banner_img : https://mah.oss-cn-beijing.aliyuncs.com/bner/159916e48b5241d1930659f9c32c07cf.png
         * banner_value : #
         * banner_type : sjfa
         * sort : 1
         * flag : 0
         * pop_adv : 1
         */

        public int banner_id;
        public String banner_title;
        public String banner_img;
        public String banner_value;
        public String banner_type;
        public String sort;
        public String flag;
        public String pop_adv;
    }
}
