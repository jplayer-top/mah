package com.modiwu.mah.mvp.presenter;

import com.modiwu.mah.mvp.constract.SchemeSelectContract;
import com.modiwu.mah.mvp.model.SchemeSelectModel;
import com.modiwu.mah.ui.activity.SchemeSearchActivity;

import io.reactivex.disposables.Disposable;
import top.jplayer.baseprolibrary.mvp.contract.BasePresenter;

/**
 * Created by Obl on 2018/1/25.
 * com.modiwu.mah.mvp.presenter
 */

public class SchemeSelectPresenter extends BasePresenter<SchemeSearchActivity> implements SchemeSelectContract.ISchemeSelectPresenter {


    private final SchemeSelectModel mModel;

    public SchemeSelectPresenter(SchemeSearchActivity iView) {
        super(iView);
        mModel = new SchemeSelectModel();
    }

    @Override
    public void requestStyleData() {
        Disposable disposable = mModel.requestStyleData().subscribe(strings -> mIView.setStyleData(strings), throwable
                -> mIView.dialogDismiss(""));
        addSubscription(disposable);
    }

    @Override
    public void requestTypeData() {
        Disposable disposable = mModel.requestTypeData().subscribe(strings -> mIView.setTypeData(strings), throwable
                -> mIView.dialogDismiss(""));
        addSubscription(disposable);


    }

    @Override
    public void requestFloorData() {
        Disposable disposable = mModel.requestFloorData().subscribe(strings -> mIView.setFloorData(strings), throwable
                -> mIView.dialogDismiss(""));
        addSubscription(disposable);
    }

    @Override
    public void requestLocalData(String city_code) {
        Disposable disposable = mModel.requestLocalData(city_code).subscribe(strings -> mIView.setLocalData(strings),
                throwable
                -> mIView.dialogDismiss(""));
        addSubscription(disposable);
    }
}
