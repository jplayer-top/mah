package com.modiwu.mah.mvp.model.event;

/**
 * Created by Obl on 2018/9/14.
 * com.modiwu.mah.mvp.model.event
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */

public class SelProIdDecorateEvent {
    public String pro_id;
    public String type;

    public SelProIdDecorateEvent(String pro_id, String type) {
        this.pro_id = pro_id;
        this.type = type;
    }
}
