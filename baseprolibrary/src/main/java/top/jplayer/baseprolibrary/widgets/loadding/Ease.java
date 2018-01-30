package top.jplayer.baseprolibrary.widgets.loadding;

import android.support.v4.view.animation.PathInterpolatorCompat;
import android.view.animation.Interpolator;

/**
 * Created by ybq.
 */
public class Ease {
    public static Interpolator inOut() {
        return PathInterpolatorCompat.create(0.42f, 0f, 0.58f, 1f);
    }
}
