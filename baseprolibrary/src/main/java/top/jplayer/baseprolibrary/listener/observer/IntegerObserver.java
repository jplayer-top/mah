package top.jplayer.baseprolibrary.listener.observer;

import android.content.Context;

import java.lang.ref.WeakReference;
import java.util.Observable;
import java.util.Observer;

import top.jplayer.baseprolibrary.ui.activity.ActivityDialog;
import top.jplayer.baseprolibrary.utils.ActivityUtils;

/**
 * Created by Obl on 2018/3/12.
 * top.jplayer.baseprolibrary.listener
 */

public class IntegerObserver implements Observer {
    public Context context;

    public IntegerObserver(Context context) {
        this.context = new WeakReference<>(context).get();
    }

    @Override
    public void update(Observable o, Object arg) {
        ActivityUtils.init().start(context, ActivityDialog.class);
    }
}
