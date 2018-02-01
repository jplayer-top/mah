package com.modiwu.mah.mvp.constract;

import com.modiwu.mah.mvp.model.bean.CarpenterBean;

import top.jplayer.baseprolibrary.mvp.contract.IContract;

/**
 * Created by Obl on 2018/1/25.
 * com.modiwu.mah.mvp.constract
 */

public interface CarpenterContract {
    interface ICarpenterPresenter extends IContract.IPresenter {
        void requestCarpenterData();
    }

    interface ICarpenterView extends IContract.IView {
        void setCarpenterData(CarpenterBean bean);
    }
}
