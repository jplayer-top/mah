package com.modiwu.mah.mvp.model.bean;

import top.jplayer.baseprolibrary.mvp.model.bean.BaseBean;

/**
 * Created by Obl on 2018/8/30.
 * com.modiwu.mah.mvp.model.bean
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */


public class FilterBean extends BaseBean {
    public String title;
    public int res;
    public boolean isSel;

    public FilterBean(String title, int res, boolean isSel) {
        this.title = title;
        this.res = res;
        this.isSel = isSel;
    }
}