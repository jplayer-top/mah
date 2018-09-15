package com.modiwu.mah.mvp.model;

import com.modiwu.mah.mvp.MahServer;
import com.modiwu.mah.mvp.model.bean.DecorateManBean;
import com.modiwu.mah.mvp.model.bean.DecorateWorkerBean;
import com.modiwu.mah.mvp.model.bean.LocalBean;
import com.modiwu.mah.mvp.model.bean.MsgHasBean;
import com.modiwu.mah.mvp.model.bean.ProInfoBean;
import com.modiwu.mah.mvp.model.bean.SelectWorkerTypeBean;

import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.RequestBody;
import top.jplayer.baseprolibrary.mvp.model.bean.BaseBean;
import top.jplayer.baseprolibrary.net.RetrofitManager;

/**
 * Created by Obl on 2018/9/12.
 * com.modiwu.mah.mvp.model
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */

public class DecorateModel {
    public Observable<LocalBean> requestLocalBean() {
        return RetrofitManager.init()
                .create(MahServer.class)
                .getLocalBean()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<BaseBean> requestCreatePro(RequestBody body) {
        return RetrofitManager.init()
                .create(MahServer.class)
                .createPro(body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<DecorateManBean> requestManPro() {
        return RetrofitManager.init()
                .create(MahServer.class)
                .manProInfo()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<DecorateWorkerBean> requestWorkerPro() {
        return RetrofitManager.init()
                .create(MahServer.class)
                .workerProInfo()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<BaseBean> getIdeSmsCode(String phone) {
        return RetrofitManager.init()
                .create(MahServer.class)
                .getIdeSmsCode(phone)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<BaseBean> regWorker(Map<String, String> map) {
        return RetrofitManager.init()
                .create(MahServer.class)
                .regWorker(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<SelectWorkerTypeBean> selectWorkerType() {
        return RetrofitManager.init()
                .create(MahServer.class)
                .selectWorkerType()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<BaseBean> regSuperView(Map<String, String> map) {
        return RetrofitManager.init()
                .create(MahServer.class)
                .regSuperView(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<BaseBean> addMan(String phone, String id) {
        return RetrofitManager.init()
                .create(MahServer.class)
                .addMan(phone, id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<BaseBean> addSuperView(String phone, String id) {
        return RetrofitManager.init()
                .create(MahServer.class)
                .addSuperView(phone, id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
    public Observable<BaseBean> addWorker(String phone, String id) {
        return RetrofitManager.init()
                .create(MahServer.class)
                .addWorker(phone, id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<ProInfoBean> getProInfo(String pro_id) {
        return RetrofitManager.init()
                .create(MahServer.class)
                .getProInfo(pro_id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<MsgHasBean> getMsgHasInfo() {
        return RetrofitManager.init()
                .create(MahServer.class)
                .getMsgHasInfo()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<BaseBean> verIdeSmsCode(String phone, String smCode) {
        return RetrofitManager.init()
                .create(MahServer.class)
                .verIdeSmsCode(phone, smCode)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
