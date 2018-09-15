package com.modiwu.mah.mvp.model.bean;

import top.jplayer.baseprolibrary.mvp.model.bean.BaseBean;

/**
 * Created by Obl on 2018/9/15.
 * com.modiwu.mah.mvp.model.bean
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */

public class MsgHasBean extends BaseBean {


    /**
     * msg : {"title":"请联系~","subtitle":"请联系~","ct":"2018-09-15 14:49:29"}
     * hastip : 1
     * hasinv : 1
     * inv : {"invite_id":13,"user_id":10005,"ide_type":"WM","phone":"17667936541","project_id":"DMA20180913100054","project_name":"公寓 108-3单元-502","invu_id":10005,"invu_name":"fjnndff","invu_phone":"17667936541","status":"0","ct":"2018-09-15 14:23:29"}
     * hasmsg : 0
     */

    public MsgBean wmsg;
    public String hastip;
    public String hasinv;
    public InvBean inv;
    public String hasmsg;

    public static class MsgBean {
        /**
         * title : 请联系~
         * subtitle : 请联系~
         * ct : 2018-09-15 14:49:29
         */

        public String title;
        public String subtitle;
        public String ct;
    }

    public static class InvBean {
        /**
         * invite_id : 13
         * user_id : 10005
         * ide_type : WM
         * phone : 17667936541
         * project_id : DMA20180913100054
         * project_name : 公寓 108-3单元-502
         * invu_id : 10005
         * invu_name : fjnndff
         * invu_phone : 17667936541
         * status : 0
         * ct : 2018-09-15 14:23:29
         */

        public int invite_id;
        public int user_id;
        public String ide_type;
        public String phone;
        public String project_id;
        public String project_name;
        public int invu_id;
        public String invu_name;
        public String invu_phone;
        public String status;
        public String ct;
    }
}
