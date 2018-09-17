package com.modiwu.mah.mvp.model.event;

/**
 * Created by Obl on 2018/9/17.
 * com.modiwu.mah.mvp.model.event
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */

public class PickerItemSel {
    public int options1;
    public String value;
    public String type;

    public PickerItemSel(int options1, String value, String type) {
        this.options1 = options1;
        this.value = value;
        this.type = type;
    }
}
