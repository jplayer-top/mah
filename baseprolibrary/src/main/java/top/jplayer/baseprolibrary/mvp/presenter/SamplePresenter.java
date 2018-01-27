package top.jplayer.baseprolibrary.mvp.presenter;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import top.jplayer.baseprolibrary.mvp.contract.BasePresenter;
import top.jplayer.baseprolibrary.mvp.contract.IContract;
import top.jplayer.baseprolibrary.mvp.contract.SampleContract;
import top.jplayer.baseprolibrary.mvp.model.SampleModel;
import top.jplayer.baseprolibrary.mvp.model.bean.SampleBean;
import top.jplayer.baseprolibrary.ui.SampleActivity;
import top.jplayer.baseprolibrary.utils.LogUtil;

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
        Disposable disposable = sampleModel.requestHBList().subscribe(new Consumer<SampleBean>() {
            @Override
            public void accept(SampleBean sampleBean) throws Exception {
                LogUtil.e(sampleBean.errorCode);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {

            }
        });
        addSubscription(disposable);
    }
}
