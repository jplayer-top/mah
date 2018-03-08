package com.modiwu.mah.mvp.constract;

import com.modiwu.mah.mvp.model.bean.HomeBean;
import com.modiwu.mah.mvp.model.bean.VersionBean;

import top.jplayer.baseprolibrary.mvp.contract.IContract;

/**
 * Created by Obl on 2018/1/25.
 * com.modiwu.mah.mvp.constract
 */

public interface HomeContract {
    interface IHomePresenter extends IContract.IPresenter {
        void requestHomeData();
    }

    interface HomeView extends IContract.IView {
        void setHomeData(HomeBean homeBean);

        void versionUpData(VersionBean versionBean);
    }
}
