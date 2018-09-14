package com.modiwu.mah.mvp.presenter;

import android.util.ArrayMap;

import com.modiwu.mah.base.BaseCommonActivity;
import com.modiwu.mah.mvp.model.DecorateModel;
import com.modiwu.mah.mvp.model.bean.SelectWorkerTypeBean;

import top.jplayer.baseprolibrary.mvp.contract.BasePresenter;
import top.jplayer.baseprolibrary.mvp.model.bean.BaseBean;
import top.jplayer.baseprolibrary.net.SampleShowDialogObserver;

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

    public void regWorker(ArrayMap<String, String> map) {
        mModel.regWorker(map)
                .subscribe(new SampleShowDialogObserver<BaseBean>(mIView) {
                    @Override
                    protected void onSuccess(BaseBean baseBean) throws Exception {
                        mIView.regWorker();
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
}
