package com.modiwu.mah.mvp.model.bean;

import java.util.List;

import top.jplayer.baseprolibrary.mvp.model.bean.BaseBean;

/**
 * Created by Obl on 2018/9/15.
 * com.modiwu.mah.mvp.model.bean
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */

public class DecorateAllProBean extends BaseBean{

    public List<ProjectsBean> projects;

    public static class ProjectsBean {
        /**
         * project_id : DMA20180913100054
         * project_name : 公寓 108-3单元-502
         * user_name : 袁鹏2
         * user_phone : 17664080212
         */

        public String project_id;
        public String project_name;
        public String user_name;
        public String user_phone;
        public String flow_name;
        public int appraise;
    }
}
