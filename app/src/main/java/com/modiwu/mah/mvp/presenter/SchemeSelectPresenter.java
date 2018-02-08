package com.modiwu.mah.mvp.presenter;

import com.modiwu.mah.mvp.constract.SchemeSelectContract;
import com.modiwu.mah.mvp.model.SchemeSelectModel;
import com.modiwu.mah.ui.activity.SchemeSearchActivity;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
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
    public void requestFloorData(String area_code) {
        Disposable disposable = mModel.requestFloorData(area_code).subscribe(strings ->
                mIView.setFloorData(strings), throwable
                -> mIView.dialogDismiss(""));
        addSubscription(disposable);
    }

    @Override
    public void requestLocalData(String city_code) {
        Disposable disposable = mModel.requestLocalData(city_code)
                .subscribe(strings -> mIView.setLocalData(strings),
                throwable
                -> mIView.dialogDismiss(""));
        addSubscription(disposable);
    }

    public void requestCode(String city) {
        Disposable disposable = mModel.requestCodeData(city)
                .subscribe(bean -> mIView.setCityCodeData(bean),
                throwable
                        -> mIView.dialogDismiss(""));
        addSubscription(disposable);
    }
}
