package com.modiwu.mah.mvp.constract;

import com.modiwu.mah.mvp.model.bean.MeShouCangBean;

import top.jplayer.baseprolibrary.mvp.contract.IContract;
import top.jplayer.baseprolibrary.mvp.model.bean.BaseBean;

/**
 * Created by Obl on 2018/1/25.
 * com.modiwu.mah.mvp.constract
 */

public interface ShouCangContract {
    interface IShouCangPresenter extends IContract.IPresenter {
        void requestShouCangData();


        void requestShouCangDel(int fangan_id,int position);
    }

    interface IShouCangView extends IContract.IView {
        void setShouCangData(MeShouCangBean bean);

        void setDelShouCang(BaseBean baseBean,int position);
    }
}
