package com.modiwu.mah.mvp.model.bean;

import java.util.List;

import top.jplayer.baseprolibrary.mvp.model.bean.BaseBean;

/**
 * Created by Obl on 2018/9/15.
 * com.modiwu.mah.mvp.model.bean
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */

public class DecorateSvBean  extends BaseBean{

    /**
     * issv : 1
     * haspj : 1
     * project : {"project_id":"DMA20180913100054","user_id":10005,"ver_no":"ZGJ01","project_name":"公寓 108-3单元-502","project_imgs":"project/10005/79d83f151b3045a5a751795d62c1b4cf.png,project/10005/2e3643eee283463f840d7beaa6562ac1.png,project/10005/32b6b5e38c3e494b8c5933ecf294efda.png,project/10005/393838cdbbdf4a0d985f42c8dde198ff.png,project/10005/0c5b512903f842ee90d4fd199879d5d0.png,project/10005/acee21aaaa994332aff1ee57300903c4.png,project/10005/d80c8f05275645a2b4ff055b68a33076.png,project/10005/65b692f07fb94f3ebacfebb19623a7d9.png","city_code":"210100","area_code":"210106","building_name":"公寓","building_no":"108","unit_no":"3单元","house_no":"502","status":"0","ct":"2018-09-13 15:50:17","imgsurl":["https://mah.oss-cn-beijing.aliyuncs.com/project/10005/79d83f151b3045a5a751795d62c1b4cf.png","https://mah.oss-cn-beijing.aliyuncs.com/project/10005/2e3643eee283463f840d7beaa6562ac1.png","https://mah.oss-cn-beijing.aliyuncs.com/project/10005/32b6b5e38c3e494b8c5933ecf294efda.png","https://mah.oss-cn-beijing.aliyuncs.com/project/10005/393838cdbbdf4a0d985f42c8dde198ff.png","https://mah.oss-cn-beijing.aliyuncs.com/project/10005/0c5b512903f842ee90d4fd199879d5d0.png","https://mah.oss-cn-beijing.aliyuncs.com/project/10005/acee21aaaa994332aff1ee57300903c4.png","https://mah.oss-cn-beijing.aliyuncs.com/project/10005/d80c8f05275645a2b4ff055b68a33076.png","https://mah.oss-cn-beijing.aliyuncs.com/project/10005/65b692f07fb94f3ebacfebb19623a7d9.png"]}
     * tasks : [{"task_id":10089,"project_id":"DMA20180913100054","seg_id":"ZGJ0101","seg_name":"房屋交接及检测","status":"0","works":[]},{"task_id":10091,"project_id":"DMA20180913100054","seg_id":"ZGJ0102","seg_name":"开工成品保护","status":"0","works":[]},{"task_id":10093,"project_id":"DMA20180913100054","seg_id":"ZGJ0103","seg_name":"拆除施工","status":"0","works":[]},{"task_id":10095,"project_id":"DMA20180913100054","seg_id":"ZGJ0104","seg_name":"水电暖施工","status":"0","works":[]},{"task_id":10097,"project_id":"DMA20180913100054","seg_id":"ZGJ0105","seg_name":"瓦工施工","status":"0","works":[]},{"task_id":10099,"project_id":"DMA20180913100054","seg_id":"ZGJ0106","seg_name":"木工施工","status":"0","works":[]},{"task_id":10101,"project_id":"DMA20180913100054","seg_id":"ZGJ0107","seg_name":"油工施工","status":"0","works":[]},{"task_id":10103,"project_id":"DMA20180913100054","seg_id":"ZGJ0108","seg_name":"安装施工","status":"0","works":[]},{"task_id":10105,"project_id":"DMA20180913100054","seg_id":"ZGJ0109","seg_name":"完工验收","status":"0","works":[]},{"task_id":10107,"project_id":"DMA20180913100054","seg_id":"ZGJ0110","seg_name":"竣工验收","status":"0","works":[]},{"task_id":10109,"project_id":"DMA20180913100054","seg_id":"ZGJ0111","seg_name":"交付，进入保修期","status":"0","works":[]}]
     */

    public String issv;
    public String haspj;
    public ProjectBean project;
    public List<TasksBean> tasks;

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

    public static class TasksBean {
        /**
         * task_id : 10089
         * project_id : DMA20180913100054
         * seg_id : ZGJ0101
         * seg_name : 房屋交接及检测
         * status : 0
         * works : []
         */

        public int task_id;
        public String project_id;
        public String seg_id;
        public String seg_name;
        public String seg_icon;
        public String status;
        public List<?> works;
    }
}
