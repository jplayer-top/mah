package com.modiwu.mah.mvp.model.bean;

import java.util.List;

import top.jplayer.baseprolibrary.mvp.model.bean.BaseBean;

/**
 * Created by Obl on 2018/9/17.
 * com.modiwu.mah.mvp.model.bean
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */

public class SelWorkerBean extends BaseBean {

    public List<RowsBean> rows;

    public static class RowsBean {
        /**
         * user_id : 10041
         * user_name : 袁鹏3
         * user_phone : 17664080213
         */

        public int user_id;
        public String user_name;
        public String user_phone;
        public String work_type;
    }
}
