package com.modiwu.mah.mvp.model;

import com.modiwu.mah.mvp.MahServer;
import com.modiwu.mah.mvp.model.bean.LoginStatusbean;
import com.modiwu.mah.mvp.model.bean.MeInfoBean;

import java.io.File;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import top.jplayer.baseprolibrary.mvp.model.bean.BaseBean;
import top.jplayer.baseprolibrary.net.IoMainSchedule;
import top.jplayer.baseprolibrary.net.RetrofitManager;

/**
 * Created by Administrator on 2018/2/9.
 */

public class MeInfoModel {

    public Observable<BaseBean> requestInfoBean(String col, String value) {
        return RetrofitManager.init()
                .create(MahServer.class)
                .getMeInfoBean(col, value)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<MeInfoBean> requestGetInfo(String uid) {
        return RetrofitManager.init()
                .create(MahServer.class)
                .getMeInfoStartBean(uid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<BaseBean> requestAvatarInfo(String img, String fileName, File file) {
        RequestBody requestFile =
                RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part body =
                MultipartBody.Part.createFormData("img", file.getName(), requestFile);
        return RetrofitManager.init()
                .create(MahServer.class)
                .getMeAvatarBean( body)
                .compose(new IoMainSchedule<>());
    }

    public Observable<LoginStatusbean> requestIsLogin() {
        return RetrofitManager.init()
                .create(MahServer.class)
                .getLoginStatus()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
