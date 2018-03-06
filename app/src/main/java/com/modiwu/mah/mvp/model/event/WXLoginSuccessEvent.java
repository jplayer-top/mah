package com.modiwu.mah.mvp.model.event;

import com.modiwu.mah.mvp.model.bean.LoginBean;

/**
 * Created by Obl on 2018/3/6.
 * com.modiwu.mah.mvp
 */

public class WXLoginSuccessEvent {
    public LoginBean loginBean;

    public WXLoginSuccessEvent(LoginBean loginBean) {
        this.loginBean = loginBean;
    }
}

