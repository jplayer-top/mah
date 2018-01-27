package top.jplayer.baseprolibrary.mvp.contract;

/**
 * Created by Administrator on 2018/1/27.
 */

public interface SampleContract {
    interface ISampleView extends IContract.IView {
        void setHBList();
    }
    interface ISamplePresenter extends IContract.IPresenter{
        void requestHBList();
    }
}
