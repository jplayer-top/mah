package com.modiwu.mah.mvp.model.event;

/**
 * Created by Obl on 2018/2/9.
 * com.modiwu.mah.mvp.model.event
 */

public class LocalSelectEvent {
    public String phone;
    public String name;
    public String local;

    public LocalSelectEvent(String phone, String name, String local) {
        this.phone = phone;
        this.name = name;
        this.local = local;
    }
}
