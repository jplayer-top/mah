package com.modiwu.mah.mvp.presenter;

import com.modiwu.mah.mvp.model.MeInfoModel;
import com.modiwu.mah.ui.activity.MeContentActivity;

import java.io.File;

import io.reactivex.disposables.Disposable;
import top.jplayer.baseprolibrary.mvp.contract.BasePresenter;
import top.jplayer.baseprolibrary.mvp.contract.IContract;
import top.jplayer.baseprolibrary.mvp.model.bean.BaseBean;
import top.jplayer.baseprolibrary.net.SampleShowDialogObserver;
import top.jplayer.baseprolibrary.utils.LogUtil;

/**
 * Created by Obl on 2018/1/25.
 * com.modiwu.mah.mvp.presenter
 */

public class MeInfoPresenter extends BasePresenter<MeContentActivity> implements IContract.IPresenter {

    private final MeInfoModel mModel;

    public MeInfoPresenter(MeContentActivity iView) {
        super(iView);
        mModel = new MeInfoModel();
    }


    public void setMeInfo(String col, String value) {
        mModel.requestInfoBean(col, value)
                .subscribe(new SampleShowDialogObserver<BaseBean>(mIView) {
                    @Override
                    protected void onSuccess(BaseBean bean) throws Exception {
                        mIView.success(col, value);
                    }
                });
    }


    public void getMeInfo(String uid) {
        Disposable subscribe = mModel.requestGetInfo(uid).subscribe(baseBean -> mIView.successGet(baseBean));
        addSubscription(subscribe);
    }

    public void upDateAvatarMes(String img, String fileName, File file) {
        mModel.requestAvatarInfo(img, fileName, file).subscribe(baseBean -> mIView.successAvatar(baseBean), throwable
                -> {
            LogUtil.e(throwable.getMessage());
            mIView.successAvatar(new BaseBean());
        });
    }
}
