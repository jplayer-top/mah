package com.modiwu.mah.mvp.presenter;

import com.modiwu.mah.mvp.model.MeInfoModel;
import com.modiwu.mah.mvp.model.YBJModel;
import com.modiwu.mah.ui.activity.HouseSampleActivity;
import com.modiwu.mah.ui.activity.MeContentActivity;

import java.util.Map;

import io.reactivex.disposables.Disposable;
import top.jplayer.baseprolibrary.mvp.contract.BasePresenter;
import top.jplayer.baseprolibrary.mvp.contract.IContract;
import top.jplayer.baseprolibrary.mvp.model.bean.BaseBean;
import top.jplayer.baseprolibrary.net.SampleShowDialogObserver;

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
        mModel.requestInfoBean(col,value)
                .subscribe(new SampleShowDialogObserver<BaseBean>(mIView) {
                    @Override
                    protected void onSuccess(BaseBean bean) throws Exception {
                        mIView.success(col,value);
                    }
                });
    }


    public void getMeInfo(String uid) {

        Disposable subscribe = mModel.requestGetInfo(uid).subscribe(baseBean -> mIView.successGet(baseBean));
        addSubscription(subscribe);
    }
}
