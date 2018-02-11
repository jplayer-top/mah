package top.jplayer.baseprolibrary;

import android.app.Activity;
import android.content.Context;

import com.gyf.barlibrary.ImmersionBar;

import java.lang.ref.WeakReference;

/**
 * Created by Obl on 2018/1/9.
 * top.jplayer.baseprolibrary
 */

public class BaseInitApplication {

    public static void init(Activity activity) {
        ImmersionBar.with(activity).init();
    }

    public static WeakReference<Context> mWeakReference;
    public static Context mContext;

    public static void init(Context context) {
        mContext = context;
        mWeakReference = new WeakReference<>(context);
    }

    public static final int DEF_REQUEST = 1;
    public static final int DEF_RESULT = 1;
}
