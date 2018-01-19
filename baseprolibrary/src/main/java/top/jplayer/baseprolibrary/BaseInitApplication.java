package top.jplayer.baseprolibrary;

import android.app.Activity;

import com.gyf.barlibrary.ImmersionBar;

/**
 * Created by Obl on 2018/1/9.
 * top.jplayer.baseprolibrary
 */

public class BaseInitApplication {
    public static void init(Activity activity) {
        ImmersionBar.with(activity).init();
    }
}
