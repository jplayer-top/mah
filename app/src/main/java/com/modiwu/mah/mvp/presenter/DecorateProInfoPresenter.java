package com.modiwu.mah.mvp.presenter;

import com.modiwu.mah.mvp.model.DecorateModel;
import com.modiwu.mah.ui.fragment.DecorateFragment;

import top.jplayer.baseprolibrary.mvp.contract.BasePresenter;
import top.jplayer.baseprolibrary.mvp.model.bean.BaseBean;
import top.jplayer.baseprolibrary.net.SampleShowDialogObserver;
import top.jplayer.baseprolibrary.utils.ToastUtils;

/**
 * Created by Obl on 2018/9/12.
 * com.modiwu.mah.mvp.presenter
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */

public class DecorateProInfoPresenter extends BasePresenter<DecorateFragment> {

    private final DecorateModel mModel;

    public DecorateProInfoPresenter(DecorateFragment iView) {
        super(iView);
        mModel = new DecorateModel();
    }

    public void requestWorkerPro(String id) {
        mModel.requestWorkerPro(id)
                .subscribe(bean -> {
                    mIView.responseWorker(bean);
                }, throwable -> {
                    if (throwable.getMessage().contains("401")) {
                        ToastUtils.init().showErrorToast(mIView.getContext(), "请先登录");
                    }
                    mIView.smartRefreshLayout.finishRefresh(true);
                    mIView.mMultipleStatusView.showError();
                });
    }

    public void requestSVPro(String id) {
        mModel.requestSVPro(id)
                .subscribe(bean -> mIView.responseSv(bean), throwable -> {
                    if (throwable.getMessage().contains("401")) {
                        ToastUtils.init().showErrorToast(mIView.getContext(), "请先登录");
                    }
                    mIView.smartRefreshLayout.finishRefresh(true);
                    mIView.mMultipleStatusView.showError();
                });
    }

    public void requestPmPro(String id) {
        mModel.requestPmPro(id)
                .subscribe(bean -> mIView.responsePm(bean), throwable -> {
                    if (throwable.getMessage().contains("401")) {
                        ToastUtils.init().showErrorToast(mIView.getContext(), "请先登录");
                    }
                    mIView.smartRefreshLayout.finishRefresh(true);
                    mIView.mMultipleStatusView.showError();
                });
    }

    public void requestManPro() {
        mModel.requestManPro()
                .subscribe(decorateBean -> mIView.responseMan(decorateBean)
                        , throwable -> {
                            if (throwable.getMessage().contains("401")) {
                                ToastUtils.init().showErrorToast(mIView.getContext(), "请先登录");
                            }
                            mIView.smartRefreshLayout.finishRefresh(true);
                            mIView.mMultipleStatusView.showError();
                        });
    }

    public void requestDelPush(String proId, String taskId) {
        mModel.requestDelPush(proId, taskId)
                .subscribe(new SampleShowDialogObserver<BaseBean>(mIView.getContext()) {
                    @Override
                    protected void onSuccess(BaseBean bean) throws Exception {
                        mIView.reponseDelPush();
                    }
                });
    }

    public void ratingWork(String proId, String workId, String rating) {
        mModel.ratingWork(proId, workId, rating)
                .subscribe(new SampleShowDialogObserver<BaseBean>(mIView.getContext()) {
                    @Override
                    protected void onSuccess(BaseBean bean) throws Exception {
                        ToastUtils.init().showSuccessToast(mIView.getContext(), bean.msg);
                        mIView.ratingWorkFinish();
                    }
                });
    }

    public void taskRatingFinish(String proId, String taskid, String rating) {
        mModel.taskRatingFinish(proId, taskid, rating)
                .subscribe(new SampleShowDialogObserver<BaseBean>(mIView.getContext()) {
                    @Override
                    protected void onSuccess(BaseBean bean) throws Exception {
                        ToastUtils.init().showSuccessToast(mIView.getContext(), bean.msg);
                        mIView.ratingWorkFinish();
                    }
                });
    }

    public void workIng(String proId, String taskId) {
        mModel.workIng(proId, taskId)
                .subscribe(new SampleShowDialogObserver<BaseBean>(mIView.getContext()) {
                    @Override
                    protected void onSuccess(BaseBean bean) throws Exception {
                        mIView.workIng(1);
                    }
                });
    }

    public void workEnd(String proId, String taskId) {
        mModel.workEnd(proId, taskId)
                .subscribe(new SampleShowDialogObserver<BaseBean>(mIView.getContext()) {
                    @Override
                    protected void onSuccess(BaseBean bean) throws Exception {
                        ToastUtils.init().showSuccessToast(mIView.getContext(), bean.msg);
                        mIView.workEnd(2);
                    }
                });
    }
}
