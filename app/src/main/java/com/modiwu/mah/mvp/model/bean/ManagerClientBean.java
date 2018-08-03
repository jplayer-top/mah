package com.modiwu.mah.mvp.model.bean;

import java.util.List;

import top.jplayer.baseprolibrary.mvp.model.bean.BaseBean;

/**
 * Created by Obl on 2018/8/2.
 * com.modiwu.mah.mvp.model.bean
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */

public class ManagerClientBean extends BaseBean {

    /**
     * lv1 : 0
     * lv2 : 0
     * profiles : []
     */

    public int lv1;
    public int lv2;
    public List<ProfileBean> profiles;

    public static class ProfileBean {
        public int user_id;
        public String user_name;
        public String user_phone;
        public String user_avatar;
        public int lv1;
        public int lv2;
    }
}
