package com.modiwu.mah.mvp.model.bean;

import java.util.List;

import top.jplayer.baseprolibrary.mvp.model.bean.BaseBean;

/**
 * Created by Obl on 2018/2/5.
 * com.modiwu.mah.mvp.model.bean
 */

public class SubTitleBean extends BaseBean {

    public List<RecordsBean> records;

    public static class RecordsBean {
        /**
         * cat_name : 全部
         */

        public String cat_name;
        public String cat_id;

    }
}
