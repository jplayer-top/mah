package com.modiwu.mah.mvp.constract;

import com.modiwu.mah.mvp.model.bean.DecorateAllProBean;
import com.modiwu.mah.mvp.model.bean.FlowSelBean;
import com.modiwu.mah.mvp.model.bean.MsgHasBean;
import com.modiwu.mah.mvp.model.bean.MsgListBean;
import com.modiwu.mah.mvp.model.bean.ProInfoBean;
import com.modiwu.mah.mvp.model.bean.SelWorkerBean;
import com.modiwu.mah.mvp.model.bean.SelectWorkerTypeBean;

import top.jplayer.baseprolibrary.mvp.contract.IContract;

/**
 * Created by Obl on 2018/9/14.
 * com.modiwu.mah.mvp.constract
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */

public interface DecorateContract {
    interface IDecorateView extends IContract.IView {
        void sendCode();

        void verCode();

        void regWorker();

        void regSuperView();

        void addMan();

        void invAgree();

        void invCancel();

        void addSuperView();

        void addWorker();

        void getProInfo(ProInfoBean bean);

        void getAllProList(DecorateAllProBean bean);

        void getWorkerAllProList(DecorateAllProBean bean);

        void getMsgHasInfo(MsgHasBean bean);

        void sendPush();

        void getMsgList(MsgListBean bean);

        void selectWorkerType(SelectWorkerTypeBean bean);

        void getFlowSel(FlowSelBean bean);

        void getSelWorker(SelWorkerBean bean);
    }
}
