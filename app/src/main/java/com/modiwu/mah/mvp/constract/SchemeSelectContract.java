package com.modiwu.mah.mvp.constract;

import com.modiwu.mah.mvp.model.bean.FloorBean;
import com.modiwu.mah.mvp.model.bean.SelectLocalBean;

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

        void requestFloorData(String area_code);

        void requestLocalData(String city_code);
    }

    interface ISchemeSelectView extends IContract.IView {
        void setStyleData(List<String> styles);

        void setTypeData(List<String> styles);

        void setFloorData(FloorBean styles);

        void setLocalData(SelectLocalBean styles);

    }
}
