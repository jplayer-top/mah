package com.modiwu.mah.base;

import android.app.Application;
import android.content.Context;

import top.jplayer.baseprolibrary.utils.Utils;

/**
 * Created by Obl on 2018/1/9.
 * top.jplayer.zhenggejia
 */

public class BaseApplication extends Application {
    public static Context AppContext;

    @Override
    public void onCreate() {
        super.onCreate();
        AppContext = getApplicationContext();
        Utils.init(this);
    }
}
