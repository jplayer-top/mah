package com.modiwu.mah.mvp.model.event;

import java.util.Map;

/**
 * Created by Obl on 2018/2/10.
 * com.modiwu.mah.mvp.model.event
 */

public class TouchAttrEvent {
    public Map<Integer, Integer> selAttrMap;

    public TouchAttrEvent(Map<Integer, Integer> selAttrMap) {
        this.selAttrMap = selAttrMap;
    }
}
