package top.jplayer.baseprolibrary.listener.observer;

import android.content.Context;

import java.lang.ref.WeakReference;
import java.util.Locale;
import java.util.Observable;

import top.jplayer.baseprolibrary.utils.ToastUtils;

/**
 * Created by Obl on 2018/3/12.
 * top.jplayer.baseprolibrary.listener.observer
 */

public class IntegerObservable extends Observable {
    public void change(Context context, Integer itg) {
        WeakReference<Context> weakReference = new WeakReference<>(context);
        if (itg < 5) {
            if (itg <= 0) {
                // setChanged 一下才能通知
                setChanged();
                //通知观察者
                notifyObservers(itg);
                ToastUtils.init().showQuickToast(weakReference.get(), "已进入开发者模式");
            } else {
                ToastUtils.init().showQuickToast(weakReference.get(), String.format(Locale.CHINA, "再按%d下进入开发者模式",
                        itg));
            }
        }
    }
}
