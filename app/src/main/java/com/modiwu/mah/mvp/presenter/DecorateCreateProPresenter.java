package com.modiwu.mah.mvp.presenter;

import com.modiwu.mah.mvp.model.DecorateModel;
import com.modiwu.mah.mvp.model.bean.LocalBean;
import com.modiwu.mah.ui.activity.DecorateCreateProActivity;

import okhttp3.RequestBody;
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

public class DecorateCreateProPresenter extends BasePresenter<DecorateCreateProActivity> {

    private final DecorateModel mModel;

    public DecorateCreateProPresenter(DecorateCreateProActivity iView) {
        super(iView);
        mModel = new DecorateModel();
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

    public void requestCreatePro(RequestBody body) {
        mModel.requestCreatePro(body)
                .subscribe(new SampleShowDialogObserver<BaseBean>(mIView) {
                    @Override
                    protected void onSuccess(BaseBean baseBean) throws Exception {
                        ToastUtils.init().showSuccessToast(mIView,baseBean.msg);
                    }
                });
    }

}
