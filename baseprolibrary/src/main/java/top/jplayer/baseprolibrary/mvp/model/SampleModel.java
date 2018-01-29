package top.jplayer.baseprolibrary.mvp.model;

import android.os.SystemClock;

import java.util.Date;
import java.util.Locale;

import io.reactivex.Observable;
import top.jplayer.baseprolibrary.mvp.model.bean.GradBean;
import top.jplayer.baseprolibrary.mvp.model.bean.SampleBean;
import top.jplayer.baseprolibrary.net.ApiService;
import top.jplayer.baseprolibrary.net.IoMainSchedule;
import top.jplayer.baseprolibrary.net.JsonRefixInterceptor;
import top.jplayer.baseprolibrary.net.RetrofitManager;
import top.jplayer.baseprolibrary.utils.LogUtil;

/**
 * Created by Obl on 2018/1/13.
 * top.jplayer.baseprolibrary.mvp.model
 */

public class SampleModel {
    public Observable<SampleBean> requestHBList() {
        String time = String.valueOf(new Date().getTime());
        return RetrofitManager.init().reset("https://m.leader001.cn/", new JsonRefixInterceptor())
                .reCreate(ApiService.class)
                .getSampleBean("{\"information\":\"bd_web_api\",\"command\":\"redhallwill\",\"platform\":\"html\"," +
                                "\"version\":\"5.2.30\",\"productName\":\"lzcp\"}", time,
                        String.format(Locale.CHINA, "Zepto%s", time))
                .compose(new IoMainSchedule<>());
    }

    public Observable<GradBean> requestGrad(String id, String userNo) {
        String time = String.valueOf(new Date().getTime());
        String parameter = String.format(Locale.CHINA, "{\"information\":\"bd_web_api\",\"command\":\"grab\",\"userno\":\"%s\",\"id\":\"%s\",\"platform\":\"html\",\"version\":\"5.2.36\",\"productName\":\"lzcp\"}", userNo, id);
        return RetrofitManager.init().reset("https://m.leader001.cn/", new JsonRefixInterceptor())
                .reCreate(ApiService.class)
                .getGradBean(parameter, time, String.format(Locale.CHINA, "Zepto%s", time))
                .compose(new IoMainSchedule<>());
    }

    public Observable<GradBean> requestGet(String id, String userNo) {
        LogUtil.e(SystemClock.currentThreadTimeMillis());
        String parameter = String.format(Locale.CHINA,
                "{\"information\":\"bd_web_api\",\"command\":\"reddetail\",\"userno\":\"%s\",\"id\":\"%s\",\"start\":0,\"size\":50,\"platform\":\"html\",\"version\":\"5.2.30\",\"productName\":\"lzcp\"}", userNo, id);
        return RetrofitManager.init().reset("https://m.leader001.cn/", new JsonRefixInterceptor())
                .reCreate(ApiService.class)
                .getGradBean(parameter, "1514383490705", "Zepto1514383490533")
                .compose(new IoMainSchedule<>());
    }

}

