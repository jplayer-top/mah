package com.modiwu.mah.mvp.constract;

import com.modiwu.mah.mvp.model.bean.SchemeBean;

import top.jplayer.baseprolibrary.mvp.contract.IContract;

/**
 * Created by Obl on 2018/1/25.
 * com.modiwu.mah.mvp.constract
 */

public interface SchemeContract {
    interface ISchemePresenter extends IContract.IPresenter {
        void requestSchemeData(String city_code);
    }

    interface ISchemeView extends IContract.IView {
        void setSchemeData(SchemeBean schemeBean);
    }
}
