package com.modiwu.mah.mvp.presenter;

import com.modiwu.mah.mvp.model.DecorateModel;
import com.modiwu.mah.ui.fragment.DecorateFragment;

import top.jplayer.baseprolibrary.mvp.contract.BasePresenter;
import top.jplayer.baseprolibrary.mvp.model.bean.BaseBean;
import top.jplayer.baseprolibrary.net.SampleShowDialogObserver;

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
                });
    }

    public void requestSVPro(String id) {
        mModel.requestSVPro(id)
                .subscribe(bean -> {
                    mIView.responseSv(bean);
                }, throwable -> {
                });
    }

    public void requestManPro() {
        mModel.requestManPro()
                .subscribe(decorateBean -> mIView.responseMan(decorateBean), throwable -> {
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

}
