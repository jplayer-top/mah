package com.modiwu.mah.mvp.model.bean;

import java.util.List;

import top.jplayer.baseprolibrary.mvp.model.bean.BaseBean;

/**
 * Created by Obl on 2018/9/19.
 * com.modiwu.mah.mvp.model.bean
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */

public class MsgListBean extends BaseBean {
    public List<WmsgsBean> wmsgs;

    public static class WmsgsBean {
        /**
         * title : 三清洞摩西 4号楼-三单元-502 用户17664080212已加入
         * subtitle : 欢迎加入~
         * ct : 2018-09-19 16:08:26
         */

        public String title;
        public String subtitle;
        public String ct;
    }
}
