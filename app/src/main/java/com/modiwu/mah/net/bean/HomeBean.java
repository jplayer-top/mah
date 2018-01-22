package com.modiwu.mah.net.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * Created by Obl on 2018/1/22.
 * com.modiwu.mah.net.bean
 */

public class HomeBean implements MultiItemEntity {
    public static final int BODY_RECOMMEND = 0;
    public static final int BODY_SINGLE = 1;
    public static final int BODY_ADV = 2;
    public static final int BODY_TOSHOP = 3;
    public String type;

    public HomeBean(String type) {
        this.type = type;
    }

    @Override
    public int getItemType() {
        if (type.equals("BODY_RECOMMEND")) {
            return BODY_RECOMMEND;
        } else if (type.equals("BODY_SINGLE")) {
            return BODY_SINGLE;
        } else if (type.equals("BODY_ADV")) {
            return BODY_ADV;
        } else {
            return BODY_TOSHOP;
        }

    }
}

