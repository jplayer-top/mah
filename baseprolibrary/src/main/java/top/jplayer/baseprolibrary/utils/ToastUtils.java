package top.jplayer.baseprolibrary.utils;

import android.content.Context;
import android.widget.Toast;

import java.lang.ref.WeakReference;

/**
 * Created by Administrator on 2018/1/27.
 */

public class ToastUtils {
    private static ToastUtils toastUtils;

    public static synchronized ToastUtils init() {
        if (toastUtils == null) {
            synchronized (ToastUtils.class) {
                if (toastUtils == null) {
                    toastUtils = new ToastUtils();
                }
            }
        }
        return toastUtils;
    }

    public ToastUtils() {

    }

    private Toast toast;

    public void showQuickToast(Context context, String msg) {
        WeakReference<Context> weakReference = new WeakReference<>(context);
        if (toast == null) {
            toast = Toast.makeText(weakReference.get(), "", Toast.LENGTH_SHORT);
        }
        toast.setText(msg);
        toast.show();
    }
}
