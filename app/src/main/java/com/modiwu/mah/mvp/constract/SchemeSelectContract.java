package com.modiwu.mah.mvp.constract;

import java.util.List;

import top.jplayer.baseprolibrary.mvp.contract.IContract;

/**
 * Created by Obl on 2018/1/25.
 * com.modiwu.mah.mvp.constract
 */

public interface SchemeSelectContract {
    interface ISchemeSelectPresenter extends IContract.IPresenter {
        void requestStyleData();

        void requestTypeData();

        void requestFloorData();

        void requestLocalData();
    }

    interface ISchemeSelectView extends IContract.IView {
        void setStyleData(List<String> styles);

        void setTypeData(List<String> styles);

        void setFloorData(List<String> styles);

        void setLocalData(List<String> styles);

    }
}
