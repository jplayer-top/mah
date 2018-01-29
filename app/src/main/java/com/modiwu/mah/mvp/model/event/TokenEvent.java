package com.modiwu.mah.mvp.model.event;

/**
 * Created by Obl on 2017/4/17.
 * com.ilanchuang.xiaoi.event
 */

public class TokenEvent {
    public String isCheck;
    public String token;

    public TokenEvent(String isCheck, String token) {
        this.isCheck = isCheck;
        this.token = token;
    }
}
