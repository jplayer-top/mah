package com.modiwu.mah.mvp.model.event;

/**
 * Created by Administrator on 2018/2/9.
 */

public class MessageEvent {
    public String key;
    public String value;

    public MessageEvent(String key,String value) {
        this.key = key;
        this.value = value;
    }
}
