package com.modiwu.mah.mvp.model.event;

/**
 * Created by Obl on 2018/2/1.
 * com.modiwu.mah.mvp.model.event
 * 首页点更多事件分发
 */

public class HomeTypeModeEvent {
    public int clickMore;

    public HomeTypeModeEvent(int clickMore, int type) {
        this.clickMore = clickMore;
        this.type = type;
    }

    public int type = 0;

    public HomeTypeModeEvent(int clickMore) {
        this.clickMore = clickMore;
    }
}
