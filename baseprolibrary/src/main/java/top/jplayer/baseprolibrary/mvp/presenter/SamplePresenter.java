package top.jplayer.baseprolibrary.mvp.presenter;

import android.text.TextUtils;

import io.reactivex.disposables.Disposable;
import top.jplayer.baseprolibrary.mvp.contract.BasePresenter;
import top.jplayer.baseprolibrary.mvp.contract.SampleContract;
import top.jplayer.baseprolibrary.mvp.model.SampleModel;
import top.jplayer.baseprolibrary.ui.SampleActivity;

/**
 * Created by Administrator on 2018/1/27.
 */

public class SamplePresenter extends BasePresenter<SampleActivity> implements SampleContract.ISamplePresenter {
    private SampleModel sampleModel;

    public SamplePresenter(SampleActivity iView) {
        super(iView);
        sampleModel = new SampleModel();
    }

    @Override
    public void requestHBList() {
        Disposable disposable = sampleModel.requestHBList()
                .map(sampleBean -> {
                    if (TextUtils.equals("0000", sampleBean.errorCode)) {
                        if (sampleBean.data != null && sampleBean.data.list != null) {
                            return sampleBean;
                        } else return null;
                    }
                    return null;
                })
                .subscribe(sampleBean ->
                {
                    if (sampleBean.data.list.size() < 1) {
                        mIView.showEmpty();
                    } else
                        mIView.setHBList(sampleBean);
                }, throwable -> mIView.showError());
        addSubscription(disposable);
    }

    @Override
    public void requestGrad(String id, String userNo) {
        sampleModel.requestGrad(id, userNo).subscribe(gradBean ->
        {
            if (TextUtils.equals("0000", gradBean.errorCode)) {
                requestGet(id, userNo);
                requestGet(id, userNo);
            }
        });
    }

    @Override
    public void requestGet(String id, String userNo) {
        sampleModel.requestGet(id, userNo);
        sampleModel.requestGet(id, userNo);
    }
}
