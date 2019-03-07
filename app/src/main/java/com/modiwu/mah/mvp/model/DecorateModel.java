package com.modiwu.mah.mvp.model;

import com.modiwu.mah.mvp.MahServer;
import com.modiwu.mah.mvp.model.bean.DecorateAllProBean;
import com.modiwu.mah.mvp.model.bean.DecorateManBean;
import com.modiwu.mah.mvp.model.bean.DecorateStatusBean;
import com.modiwu.mah.mvp.model.bean.DecorateWorkerBean;
import com.modiwu.mah.mvp.model.bean.FlowSelBean;
import com.modiwu.mah.mvp.model.bean.LocalBean;
import com.modiwu.mah.mvp.model.bean.MsgHasBean;
import com.modiwu.mah.mvp.model.bean.InvListBean;
import com.modiwu.mah.mvp.model.bean.MsgListBean;
import com.modiwu.mah.mvp.model.bean.ProInfoBean;
import com.modiwu.mah.mvp.model.bean.SelWorkerBean;
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

    public Observable<DecorateStatusBean> getDecorateStatus() {
        return RetrofitManager.init()
                .create(MahServer.class)
                .getDecorateStatus()
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

    public Observable<BaseBean> ratingWork(String proId, String workId, String rating) {
        return RetrofitManager.init()
                .create(MahServer.class)
                .ratingWork(proId, workId, rating)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<BaseBean> taskRatingFinish(String proId, String taskid, String rating) {
        return RetrofitManager.init()
                .create(MahServer.class)
                .taskRatingFinish(proId, taskid, rating)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<BaseBean> requestDelPush(String proId, String taskId) {
        return RetrofitManager.init()
                .create(MahServer.class)
                .requestPushDel(proId, taskId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<BaseBean> workIng(String proId, String taskId) {
        return RetrofitManager.init()
                .create(MahServer.class)
                .workIng(proId, taskId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<BaseBean> workEnd(String proId, String taskId) {
        return RetrofitManager.init()
                .create(MahServer.class)
                .workEnd(proId, taskId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<DecorateAllProBean> getAllProList() {
        return RetrofitManager.init()
                .create(MahServer.class)
                .getAllProList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<DecorateAllProBean> getWorkerAllProList() {
        return RetrofitManager.init()
                .create(MahServer.class)
                .getWorkerAllProList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<DecorateWorkerBean> requestWorkerPro(String id) {
        return RetrofitManager.init()
                .create(MahServer.class)
                .workerProInfo(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<DecorateManBean> requestSVPro(String id) {
        return RetrofitManager.init()
                .create(MahServer.class)
                .svProInfo(id)
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

    public Observable<BaseBean> invAgree(String id) {
        return RetrofitManager.init()
                .create(MahServer.class)
                .invAgree(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<BaseBean> invCancel(String id) {
        return RetrofitManager.init()
                .create(MahServer.class)
                .invCancel(id)
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

    public Observable<BaseBean> delPro(String pro_id) {
        return RetrofitManager.init()
                .create(MahServer.class)
                .delPro(pro_id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<FlowSelBean> getFlowSel(String taskId) {
        return RetrofitManager.init()
                .create(MahServer.class)
                .getFlowSel(taskId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<SelWorkerBean> getSelWorker(String id) {
        return RetrofitManager.init()
                .create(MahServer.class)
                .getSelWorker(id)
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

    public Observable<BaseBean> sendPush(RequestBody body) {
        return RetrofitManager.init()
                .create(MahServer.class)
                .sendPush(body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<BaseBean> delWorker(String proId, String userId) {
        return RetrofitManager.init()
                .create(MahServer.class)
                .delWorker(proId, userId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<BaseBean> delSv(String proId, String userId) {
        return RetrofitManager.init()
                .create(MahServer.class)
                .delSv(proId, userId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<BaseBean> delMan(String proId, String userId) {
        return RetrofitManager.init()
                .create(MahServer.class)
                .delMan(proId, userId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<MsgListBean> getMsgList() {
        return RetrofitManager.init()
                .create(MahServer.class)
                .getMsgList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<InvListBean> getInvList() {
        return RetrofitManager.init()
                .create(MahServer.class)
                .getInvList()
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
