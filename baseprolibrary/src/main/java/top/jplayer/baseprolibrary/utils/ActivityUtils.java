package top.jplayer.baseprolibrary.utils;

import android.content.Context;
import android.content.Intent;
import android.view.MotionEvent;

/**
 * Created by Obl on 2018/1/18.
 * top.jplayer.baseprolibrary.utils
 */

public class ActivityUtils {

    public static void start(Context context, Class tClass, MotionEvent event) {
        Intent i = new Intent(context, tClass);
        i.putExtra("x", (int) event.getX());
        i.putExtra("y", (int) event.getY());
        context.startActivity(i);
    }
}
