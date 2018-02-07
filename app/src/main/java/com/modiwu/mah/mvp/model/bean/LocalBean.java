package com.modiwu.mah.mvp.model.bean;

import java.util.List;

import top.jplayer.baseprolibrary.mvp.model.bean.BaseBean;

/**
 * Created by PEO on 2017/2/23.
 */

public class LocalBean extends BaseBean {


    public List<AreasBean> areas;

    public static class AreasBean {

        public String area_code;
        public String area_name;
        public String area_parent;
        public String area_lv;
        public String area_ext;
        public List<SubsBeanX> subs;

        public static class SubsBeanX {

            public String area_code;
            public String area_name;
            public String area_parent;
            public String area_lv;
            public String area_ext;
            public List<SubsBean> subs;

            public static class SubsBean {

                public String area_code;
                public String area_name;
                public String area_parent;
                public String area_lv;
                public String area_ext;
                public List<?> subs;
            }
        }
    }
}
