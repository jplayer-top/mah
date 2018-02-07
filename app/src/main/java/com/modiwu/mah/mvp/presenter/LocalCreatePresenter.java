package com.modiwu.mah.mvp.presenter;

import com.modiwu.mah.mvp.model.LocalModel;
import com.modiwu.mah.mvp.model.bean.LocalBean;
import com.modiwu.mah.ui.activity.LocalCreateActivity;

import java.util.Map;

import top.jplayer.baseprolibrary.mvp.contract.BasePresenter;
import top.jplayer.baseprolibrary.mvp.contract.IContract;
import top.jplayer.baseprolibrary.mvp.model.bean.BaseBean;
import top.jplayer.baseprolibrary.net.SampleShowDialogObserver;

/**
 * Created by Obl on 2018/1/25.
 * com.modiwu.mah.mvp.presenter
 */

public class LocalCreatePresenter extends BasePresenter<LocalCreateActivity> implements IContract.IPresenter {

    private final LocalModel mModel;

    public LocalCreatePresenter(LocalCreateActivity iView) {
        super(iView);
        mModel = new LocalModel();
    }

    public void requestLocalBean() {
        mModel.requestLocalBean()
                .subscribe(new SampleShowDialogObserver<LocalBean>(mIView) {
                    @Override
                    protected void onSuccess(LocalBean baseBean) throws Exception {
                        mIView.setLocalBean(baseBean);
                    }
                });
    }

    public void editLocal(Map<String, String> map) {
        mModel.requestLocalEditBean(map)
                .subscribe(new SampleShowDialogObserver<BaseBean>(mIView) {
                    @Override
                    protected void onSuccess(BaseBean baseBean) throws Exception {
                        mIView.upDataSuccess();
                    }
                });
    }

    public void saveLocal(Map<String, String> map) {
        mModel.requestLocalSaveBean(map)
                .subscribe(new SampleShowDialogObserver<BaseBean>(mIView) {
                    @Override
                    protected void onSuccess(BaseBean baseBean) throws Exception {
                        mIView.upDataSuccess();
                    }
                });
    }
}
