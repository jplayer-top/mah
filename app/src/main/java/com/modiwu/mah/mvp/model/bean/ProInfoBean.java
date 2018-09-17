package com.modiwu.mah.mvp.model.bean;

import java.util.List;

import top.jplayer.baseprolibrary.mvp.model.bean.BaseBean;

/**
 * Created by Obl on 2018/9/15.
 * com.modiwu.mah.mvp.model.bean
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */

public class ProInfoBean extends BaseBean {

    /**
     * project : {"project_id":"DMA20180913100054","user_id":10005,"ver_no":"ZGJ01","project_name":"公寓 108-3单元-502","project_imgs":"project/10005/79d83f151b3045a5a751795d62c1b4cf.png,project/10005/2e3643eee283463f840d7beaa6562ac1.png,project/10005/32b6b5e38c3e494b8c5933ecf294efda.png,project/10005/393838cdbbdf4a0d985f42c8dde198ff.png,project/10005/0c5b512903f842ee90d4fd199879d5d0.png,project/10005/acee21aaaa994332aff1ee57300903c4.png,project/10005/d80c8f05275645a2b4ff055b68a33076.png,project/10005/65b692f07fb94f3ebacfebb19623a7d9.png","city_code":"210100","area_code":"210106","building_name":"公寓","building_no":"108","unit_no":"3单元","house_no":"502","status":"0","ct":"2018-09-13 15:50:17","imgsurl":["https://mah.oss-cn-beijing.aliyuncs.com/project/10005/79d83f151b3045a5a751795d62c1b4cf.png","https://mah.oss-cn-beijing.aliyuncs.com/project/10005/2e3643eee283463f840d7beaa6562ac1.png","https://mah.oss-cn-beijing.aliyuncs.com/project/10005/32b6b5e38c3e494b8c5933ecf294efda.png","https://mah.oss-cn-beijing.aliyuncs.com/project/10005/393838cdbbdf4a0d985f42c8dde198ff.png","https://mah.oss-cn-beijing.aliyuncs.com/project/10005/0c5b512903f842ee90d4fd199879d5d0.png","https://mah.oss-cn-beijing.aliyuncs.com/project/10005/acee21aaaa994332aff1ee57300903c4.png","https://mah.oss-cn-beijing.aliyuncs.com/project/10005/d80c8f05275645a2b4ff055b68a33076.png","https://mah.oss-cn-beijing.aliyuncs.com/project/10005/65b692f07fb94f3ebacfebb19623a7d9.png"]}
     * owners : [{"user_id":10005,"user_name":"fjnndff","user_phone":"17667936541"}]
     * svs : []
     * wokers : []
     */

    public ProjectBean project;
    public List<OwnersBean> owners;
    public List<SvsBean> svs;
    public List<WorkerBean> wokers;
    public List<CommonBean> commonBeans;

    public static class ProjectBean {
        /**
         * project_id : DMA20180913100054
         * user_id : 10005
         * ver_no : ZGJ01
         * project_name : 公寓 108-3单元-502
         * project_imgs : project/10005/79d83f151b3045a5a751795d62c1b4cf.png,project/10005/2e3643eee283463f840d7beaa6562ac1.png,project/10005/32b6b5e38c3e494b8c5933ecf294efda.png,project/10005/393838cdbbdf4a0d985f42c8dde198ff.png,project/10005/0c5b512903f842ee90d4fd199879d5d0.png,project/10005/acee21aaaa994332aff1ee57300903c4.png,project/10005/d80c8f05275645a2b4ff055b68a33076.png,project/10005/65b692f07fb94f3ebacfebb19623a7d9.png
         * city_code : 210100
         * area_code : 210106
         * building_name : 公寓
         * building_no : 108
         * unit_no : 3单元
         * house_no : 502
         * status : 0
         * ct : 2018-09-13 15:50:17
         * imgsurl : ["https://mah.oss-cn-beijing.aliyuncs.com/project/10005/79d83f151b3045a5a751795d62c1b4cf.png","https://mah.oss-cn-beijing.aliyuncs.com/project/10005/2e3643eee283463f840d7beaa6562ac1.png","https://mah.oss-cn-beijing.aliyuncs.com/project/10005/32b6b5e38c3e494b8c5933ecf294efda.png","https://mah.oss-cn-beijing.aliyuncs.com/project/10005/393838cdbbdf4a0d985f42c8dde198ff.png","https://mah.oss-cn-beijing.aliyuncs.com/project/10005/0c5b512903f842ee90d4fd199879d5d0.png","https://mah.oss-cn-beijing.aliyuncs.com/project/10005/acee21aaaa994332aff1ee57300903c4.png","https://mah.oss-cn-beijing.aliyuncs.com/project/10005/d80c8f05275645a2b4ff055b68a33076.png","https://mah.oss-cn-beijing.aliyuncs.com/project/10005/65b692f07fb94f3ebacfebb19623a7d9.png"]
         */

        public String project_id;
        public int user_id;
        public String ver_no;
        public String project_name;
        public String project_imgs;
        public String city_code;
        public String area_code;
        public String building_name;
        public String building_no;
        public String unit_no;
        public String house_no;
        public String status;
        public String ct;
        public List<String> imgsurl;
    }

    public static class OwnersBean {
        /**
         * user_id : 10005
         * user_name : fjnndff
         * user_phone : 17667936541
         */

        public int user_id;
        public String user_name;
        public String user_phone;
    }

    public static class SvsBean {
        /**
         * user_id : 10005
         * user_name : fjnndff
         * user_phone : 17667936541
         */

        public int user_id;
        public String user_name;
        public String user_phone;
    }

    public static class WorkerBean {
        /**
         * user_id : 10005
         * user_name : fjnndff
         * user_phone : 17667936541
         */

        public int user_id;
        public String user_name;
        public String user_phone;
        public String work_type;
    }

    public static class CommonBean {
        /**
         * user_id : 10005
         * user_name : fjnndff
         * user_phone : 17667936541
         */

        public int user_id;
        public String user_name;
        public String user_phone;
        public boolean isEdit = false;
        public String work_type = "业主";
    }
}
