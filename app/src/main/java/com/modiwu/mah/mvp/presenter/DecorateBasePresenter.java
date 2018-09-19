package com.modiwu.mah.mvp.presenter;

import android.util.ArrayMap;

import com.modiwu.mah.base.BaseCommonActivity;
import com.modiwu.mah.mvp.model.DecorateModel;
import com.modiwu.mah.mvp.model.bean.FlowSelBean;
import com.modiwu.mah.mvp.model.bean.SelWorkerBean;
import com.modiwu.mah.mvp.model.bean.SelectWorkerTypeBean;

import okhttp3.RequestBody;
import top.jplayer.baseprolibrary.mvp.contract.BasePresenter;
import top.jplayer.baseprolibrary.mvp.model.bean.BaseBean;
import top.jplayer.baseprolibrary.net.SampleShowDialogObserver;
import top.jplayer.baseprolibrary.utils.ToastUtils;

/**
 * Created by Obl on 2018/9/14.
 * com.modiwu.mah.mvp.presenter
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */

public class DecorateBasePresenter extends BasePresenter<BaseCommonActivity> {

    private final DecorateModel mModel;

    public DecorateBasePresenter(BaseCommonActivity iView) {
        super(iView);
        mModel = new DecorateModel();
    }

    public void sendSmCode(String phone) {
        mModel.getIdeSmsCode(phone)
                .subscribe(new SampleShowDialogObserver<BaseBean>(mIView) {
                    @Override
                    protected void onSuccess(BaseBean baseBean) throws Exception {
                        mIView.sendCode();
                    }
                });
    }

    public void verSmCode(String phone, String code) {
        mModel.verIdeSmsCode(phone, code)
                .subscribe(new SampleShowDialogObserver<BaseBean>(mIView) {
                    @Override
                    protected void onSuccess(BaseBean baseBean) throws Exception {
                        mIView.verCode();
                    }
                });
    }

    public void getProInfo(String id) {
        mModel.getProInfo(id)
                .subscribe(proInfoBean -> {
                    mIView.getProInfo(proInfoBean);
                }, throwable -> {
                });
    }

    public void delPro(String id) {
        mModel.delPro(id)
                .subscribe(new SampleShowDialogObserver<BaseBean>(mIView) {
                    @Override
                    protected void onSuccess(BaseBean bean) throws Exception {
                        mIView.delPro(bean);
                    }
                });
    }

    public void getFlowSelBean(String id) {
        mModel.getFlowSel(id)
                .subscribe(new SampleShowDialogObserver<FlowSelBean>(mIView) {
                    @Override
                    protected void onSuccess(FlowSelBean bean) throws Exception {
                        mIView.getFlowSel(bean);
                    }
                });
    }

    public void getSelWorker(String id) {
        mModel.getSelWorker(id)
                .subscribe(new SampleShowDialogObserver<SelWorkerBean>(mIView) {
                    @Override
                    protected void onSuccess(SelWorkerBean bean) throws Exception {
                        mIView.getSelWorker(bean);
                    }
                });
    }

    public void getMsgHasInfo() {
        mModel.getMsgHasInfo()
                .subscribe(bean -> {
                    mIView.getMsgHasInfo(bean);
                }, throwable -> {
                });
    }

    public void getMsgList() {
        mModel.getMsgList()
                .subscribe(bean -> {
                    mIView.getMsgList(bean);
                }, throwable -> {
                });
    }
   public void getInvList() {
        mModel.getInvList()
                .subscribe(bean -> {
                    mIView.getInvList(bean);
                }, throwable -> {
                });
    }

    public void getAllProList() {
        mModel.getAllProList()
                .subscribe(bean -> {
                    mIView.getAllProList(bean);
                }, throwable -> {
                });
    }

    public void getWorkerAllProList() {
        mModel.getWorkerAllProList()
                .subscribe(bean -> {
                    mIView.getWorkerAllProList(bean);
                }, throwable -> {
                });
    }

    public void regWorker(ArrayMap<String, String> map) {
        mModel.regWorker(map)
                .subscribe(new SampleShowDialogObserver<BaseBean>(mIView) {
                    @Override
                    protected void onSuccess(BaseBean baseBean) throws Exception {
                        mIView.regWorker();
                    }
                });
    }

    public void sendPush(RequestBody body) {
        mModel.sendPush(body)
                .subscribe(new SampleShowDialogObserver<BaseBean>(mIView) {
                    @Override
                    protected void onSuccess(BaseBean baseBean) throws Exception {
                        ToastUtils.init().showSuccessToast(mIView, baseBean.msg);
                        mIView.sendPush();
                    }
                });
    }

    public void addMan(String phone, String id) {
        mModel.addMan(phone, id)
                .subscribe(new SampleShowDialogObserver<BaseBean>(mIView) {
                    @Override
                    protected void onSuccess(BaseBean baseBean) throws Exception {
                        ToastUtils.init().showSuccessToast(mIView, baseBean.msg);
                        mIView.addMan();
                    }
                });
    }

    public void invAgree(String id) {
        mModel.invAgree(id)
                .subscribe(new SampleShowDialogObserver<BaseBean>(mIView) {
                    @Override
                    protected void onSuccess(BaseBean baseBean) throws Exception {
                        mIView.invAgree();
                    }
                });
    }

    public void invCancel(String id) {
        mModel.invCancel(id)
                .subscribe(new SampleShowDialogObserver<BaseBean>(mIView) {
                    @Override
                    protected void onSuccess(BaseBean baseBean) throws Exception {
                        mIView.invCancel();
                    }
                });
    }

    public void addSuperView(String phone, String id) {
        mModel.addSuperView(phone, id)
                .subscribe(new SampleShowDialogObserver<BaseBean>(mIView) {
                    @Override
                    protected void onSuccess(BaseBean baseBean) throws Exception {
                        ToastUtils.init().showSuccessToast(mIView, baseBean.msg);
                        mIView.addSuperView();
                    }
                });
    }

    public void addWorker(String phone, String id) {
        mModel.addWorker(phone, id)
                .subscribe(new SampleShowDialogObserver<BaseBean>(mIView) {
                    @Override
                    protected void onSuccess(BaseBean baseBean) throws Exception {
                        ToastUtils.init().showSuccessToast(mIView, baseBean.msg);
                        mIView.addWorker();
                    }
                });
    }

    public void selectWorkerType() {
        mModel.selectWorkerType()
                .subscribe(new SampleShowDialogObserver<SelectWorkerTypeBean>(mIView) {
                    @Override
                    protected void onSuccess(SelectWorkerTypeBean baseBean) throws Exception {
                        mIView.selectWorkerType(baseBean);
                    }
                });
    }

    public void regSuperView(ArrayMap<String, String> map) {
        mModel.regSuperView(map)
                .subscribe(new SampleShowDialogObserver<BaseBean>(mIView) {
                    @Override
                    protected void onSuccess(BaseBean baseBean) throws Exception {
                        mIView.regSuperView();
                    }
                });
    }

    public void delWorker(String proId, String userId) {
        mModel.delWorker(proId, userId)
                .subscribe(new SampleShowDialogObserver<BaseBean>(mIView) {
                    @Override
                    protected void onSuccess(BaseBean baseBean) throws Exception {
                        ToastUtils.init().showSuccessToast(mIView, baseBean.msg);
                        mIView.delWorker();
                    }
                });
    }

    public void delSV(String proId, String userId) {
        mModel.delSv(proId, userId)
                .subscribe(new SampleShowDialogObserver<BaseBean>(mIView) {
                    @Override
                    protected void onSuccess(BaseBean baseBean) throws Exception {
                        ToastUtils.init().showSuccessToast(mIView, baseBean.msg);
                        mIView.delWorker();
                    }
                });
    }

    public void delMan(String proId, String userId) {
        mModel.delMan(proId, userId)
                .subscribe(new SampleShowDialogObserver<BaseBean>(mIView) {
                    @Override
                    protected void onSuccess(BaseBean baseBean) throws Exception {
                        ToastUtils.init().showSuccessToast(mIView, baseBean.msg);
                        mIView.delWorker();
                    }
                });
    }
}
