package top.jplayer.baseprolibrary.mvp.model;

import android.os.SystemClock;

import java.util.Timer;

import io.reactivex.Observable;
import top.jplayer.baseprolibrary.mvp.model.bean.SampleBean;
import top.jplayer.baseprolibrary.net.ApiService;
import top.jplayer.baseprolibrary.net.IoMainSchedule;
import top.jplayer.baseprolibrary.net.JsonRefixInterceptor;
import top.jplayer.baseprolibrary.net.RetrofitManager;
import top.jplayer.baseprolibrary.net.SampleObserver;
import top.jplayer.baseprolibrary.utils.LogUtil;

/**
 * Created by Obl on 2018/1/13.
 * top.jplayer.baseprolibrary.mvp.model
 */

public class SampleModel {
    public Observable<SampleBean> requestHBList() {
        LogUtil.e(SystemClock.currentThreadTimeMillis());
        return RetrofitManager.init().reset("https://m.leader001.cn/", new JsonRefixInterceptor())
                .reCreate(ApiService.class)
                .getSampleBean("{\"information\":\"bd_web_api\",\"command\":\"redhallwill\",\"platform\":\"html\"," +
                        "\"version\":\"5.2.30\",\"productName\":\"lzcp\"}", "1514383490705", "Zepto1514383490533")
                .compose(new IoMainSchedule<SampleBean>());
    }
}
