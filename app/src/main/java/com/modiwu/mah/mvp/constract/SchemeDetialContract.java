package com.modiwu.mah.mvp.constract;

import com.modiwu.mah.mvp.model.bean.SchemeDetailBean;

import top.jplayer.baseprolibrary.mvp.contract.IContract;

/**
 * Created by Obl on 2018/1/25.
 * com.modiwu.mah.mvp.constract
 */

public interface SchemeDetialContract {
    interface ISchemeDetialPresenter extends IContract.IPresenter {
        void requestSchemeDetialData(String fangan_id);

    }

    interface ISchemeDetialView extends IContract.IView {
        void setSchemeDetialData(SchemeDetailBean bean);

    }
}
