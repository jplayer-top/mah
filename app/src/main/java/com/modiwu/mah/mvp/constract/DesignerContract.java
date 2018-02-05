package com.modiwu.mah.mvp.constract;

import com.modiwu.mah.mvp.model.bean.DesignBean;

import top.jplayer.baseprolibrary.mvp.contract.IContract;

/**
 * Created by Obl on 2018/1/25.
 * com.modiwu.mah.mvp.constract
 */

public interface DesignerContract {
    interface IDesignerPresenter extends IContract.IPresenter {
        void requestDesignerData(String designer_id);

    }

    interface IDesignerView extends IContract.IView {
        void setDesignerData(DesignBean bean);

    }
}
