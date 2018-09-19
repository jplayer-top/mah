package com.modiwu.mah.mvp.model.bean;

import top.jplayer.baseprolibrary.mvp.model.bean.BaseBean;

/**
 * Created by Obl on 2018/9/14.
 * com.modiwu.mah.mvp.model.bean
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */

public class DecorateWorkerBean extends BaseBean {

    /**
     * iswm : 1
     * appraise : 0
     * ing : 1
     * ed : 0
     * info : {"user_id":10005,"user_phone":"17667936541","user_name":"466268","sex":"男","birthday":"2019-09-15 00:00:00","id_card":"370923166364441287","work_type":"木工","work_years":4,"ct":"2018-09-14 15:46:29"}
     */

    public String iswm;
    public float appraise;
    public int ing;
    public int ed;
    public InfoBean info;

    public static class InfoBean {
        /**
         * user_id : 10005
         * user_phone : 17667936541
         * user_name : 466268
         * sex : 男
         * birthday : 2019-09-15 00:00:00
         * id_card : 370923166364441287
         * work_type : 木工
         * work_years : 4
         * ct : 2018-09-14 15:46:29
         */

        public int user_id;
        public String user_phone;
        public String user_name;
        public String sex;
        public String birthday;
        public String id_card;
        public String work_type;
        public int work_years;
        public String ct;
    }
}
