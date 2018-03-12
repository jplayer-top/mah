package com.modiwu.mah.mvp.model.event;

import com.modiwu.mah.mvp.model.bean.SchemeDetailBean;

/**
 * Created by Obl on 2018/3/9.
 * com.modiwu.mah.mvp.model.event
 */

public class BGAPagerSelectEvent {
    public SchemeDetailBean.LoupanhuxingBean bean;

    public BGAPagerSelectEvent(SchemeDetailBean.LoupanhuxingBean bean) {
        this.bean = bean;
    }
}
