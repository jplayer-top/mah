package com.modiwu.mah.base;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.StrictMode;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;
import android.util.Log;
import android.widget.Toast;

import com.modiwu.mah.BuildConfig;
import com.modiwu.mah.message.CustomizeBPMessage;
import com.modiwu.mah.message.CustomizeBPMessageItemProvide;
import com.tencent.bugly.Bugly;
import com.tencent.bugly.beta.Beta;
import com.tencent.bugly.beta.interfaces.BetaPatchListener;
import com.tencent.smtt.sdk.QbSdk;
import com.uuzuche.lib_zxing.activity.ZXingLibrary;

import java.util.Locale;

import io.rong.imkit.RongIM;
import top.jplayer.baseprolibrary.BaseInitApplication;
import top.jplayer.baseprolibrary.utils.Utils;

import static com.tencent.bugly.beta.tinker.TinkerManager.getApplication;

/**
 * Created by Obl on 2018/1/9.
 * top.jplayer.zhenggejia
 */

public class BaseApplication extends MultiDexApplication {
    public static Context AppContext;
    public final static String APP_ID = "wx510d1dded9bba6cb";
    private static Handler mMainThreadHandler;

    /**
     * 得到主线程里面的创建的一个hanlder
     */
    public static Handler getMainThreadHandler() {
        return mMainThreadHandler;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        AppContext = getApplicationContext();
        Utils.init(this);
        zxing();
        fixFileProvide();
        //主线程的Handler
        mMainThreadHandler = new Handler();
        BaseInitApplication.init(AppContext);
        if (getApplicationInfo().packageName.equals(getCurProcessName(getApplicationContext()))) {
            RongIM.init(this);
            Bugly.init(this, "d764308e59", false);
            openBugly();
            RongIM.registerMessageType(CustomizeBPMessage.class);
            RongIM.registerMessageTemplate(new CustomizeBPMessageItemProvide());
        }
        QbSdk.PreInitCallback cb = new QbSdk.PreInitCallback() {

            @Override
            public void onViewInitFinished(boolean arg0) {
                // TODO Auto-generated method stub
                //x5內核初始化完成的回调，为true表示x5内核加载成功，否则表示x5内核加载失败，会自动切换到系统内核。
                Log.d("app", " onViewInitFinished is " + arg0);
            }

            @Override
            public void onCoreInitFinished() {
                // TODO Auto-generated method stub
            }
        };
        //x5内核初始化接口
        QbSdk.initX5Environment(getApplicationContext(),  cb);
    }

    /**
     * 初始化zxing
     */
    public void zxing() {
        ZXingLibrary.initDisplayOpinion(this);
    }

    public void fixFileProvide() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
            StrictMode.setVmPolicy(builder.build());
        }
    }

    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        // you must install multiDex whatever tinker is installed!
        MultiDex.install(base);
        // 安装tinker
        Beta.installTinker();
    }

    /**
     * 获得当前进程的名字
     *
     * @param context
     * @return 进程号
     */
    public static String getCurProcessName(Context context) {

        int pid = android.os.Process.myPid();

        ActivityManager activityManager = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);

        for (ActivityManager.RunningAppProcessInfo appProcess : activityManager
                .getRunningAppProcesses()) {

            if (appProcess.pid == pid) {
                return appProcess.processName;
            }
        }
        return null;
    }

    private void openBugly() {
        // 补丁回调接口
        if (BuildConfig.DEBUG) {
            Beta.canNotifyUserRestart = BuildConfig.DEBUG;
            Beta.betaPatchListener = new BetaPatchListener() {
                @Override
                public void onPatchReceived(String patchFile) {
                    Toast.makeText(getApplication(), "补丁下载地址" + patchFile, Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onDownloadReceived(long savedLength, long totalLength) {
                    Toast.makeText(getApplication(),
                            String.format(Locale.getDefault(), "%s %d%%",
                                    Beta.strNotificationDownloading,
                                    (int) (totalLength == 0 ? 0 : savedLength * 100 / totalLength)),
                            Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onDownloadSuccess(String msg) {
                    Toast.makeText(getApplication(), "补丁下载成功", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onDownloadFailure(String msg) {
                    Toast.makeText(getApplication(), "补丁下载失败", Toast.LENGTH_SHORT).show();

                }

                @Override
                public void onApplySuccess(String msg) {
                    Toast.makeText(getApplication(), "补丁应用成功", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onApplyFailure(String msg) {
                    Toast.makeText(getApplication(), "补丁应用失败", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onPatchRollback() {

                }
            };

        }
    }

}
