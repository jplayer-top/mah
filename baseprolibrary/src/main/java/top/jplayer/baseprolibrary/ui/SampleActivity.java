package top.jplayer.baseprolibrary.ui;

import android.view.View;
import android.widget.FrameLayout;

import top.jplayer.baseprolibrary.R;
import top.jplayer.baseprolibrary.mvp.contract.SampleContract;
import top.jplayer.baseprolibrary.mvp.presenter.SamplePresenter;

/**
 * Created by Administrator on 2018/1/27.
 * 样例
 */

public class SampleActivity extends SuperBaseActivity implements SampleContract.ISampleView {

    private SamplePresenter presenter;

    @Override
    public void initSuperData(FrameLayout mFlRootView) {
        mFlRootView.addView(View.inflate(this, R.layout.activity_sample, null));
        presenter = new SamplePresenter(this);
        presenter.requestHBList();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.detachView();
        }
    }

    @Override
    public void showError() {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void showEmpty() {

    }

    @Override
    public void setHBList() {

    }
}
