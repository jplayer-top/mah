package com.modiwu.mah.mvp.model.bean;

import java.util.List;

import top.jplayer.baseprolibrary.mvp.model.bean.BaseBean;

/**
 * Created by Obl on 2018/2/6.
 * com.modiwu.mah.mvp.model.bean
 */

public class MeShouCangBean extends BaseBean {

    public List<RowsBean> rows;

    public static class RowsBean {
        /**
         * fangan_id : 5
         * user_id : 0
         * fangan_name : 未来幻想
         * fangan_avatar : https://mah.oss-cn-beijing.aliyuncs.com/foo/fa1.jpg
         */

        public int fangan_id;
        public int user_id;
        public String fangan_name;
        public String fangan_avatar;
    }
}
