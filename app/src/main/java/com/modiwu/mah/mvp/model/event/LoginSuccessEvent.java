package com.modiwu.mah.mvp.model.event;

/**
 * Created by Obl on 2018/2/9.
 * com.modiwu.mah.mvp.model.event
 */

public class LoginSuccessEvent {
    public int uid;
    public String imtoken;

    public LoginSuccessEvent(int uid, String imtoken) {
        this.uid = uid;
        this.imtoken = imtoken;
    }
}
