package com.modiwu.mah.mvp.presenter;

import com.modiwu.mah.mvp.constract.ShopCartContract;
import com.modiwu.mah.mvp.model.ShopCartDaoUtil;
import com.modiwu.mah.mvp.model.bean.ShopCartBean;
import com.modiwu.mah.ui.activity.ShopCartActivity;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import top.jplayer.baseprolibrary.mvp.contract.BasePresenter;
import top.jplayer.baseprolibrary.net.IoMainSchedule;

/**
 * Created by Obl on 2018/1/29.
 * com.modiwu.mah.mvp.presenter
 */

public class ShopCartPresenter extends BasePresenter<ShopCartActivity> implements ShopCartContract.IShopCartPresenter {

    private final ShopCartDaoUtil mDaoUtil;

    public ShopCartPresenter(ShopCartActivity iView) {
        super(iView);
        mDaoUtil = new ShopCartDaoUtil(iView);
    }

    @Override
    public void requestShopCartData() {
        List<ShopCartBean> shopCartBeans = mDaoUtil.queryAllbean();
        Observable.timer(0, TimeUnit.SECONDS)
                .compose(new IoMainSchedule<>())
                .map(aLong -> shopCartBeans)
                .subscribe(beans -> {
                    mIView.smartRefreshLayout.finishRefresh();
                    if (beans == null || beans.size() < 1) {
                        mIView.showEmpty();
                    } else {
                        mIView.mMultipleStatusView.showContent();
                        mIView.setShopCartData(beans);
                    }
                }, throwable -> mIView.showError());
    }

    public void delData(List<ShopCartBean> delList) {

        Observable.fromIterable(delList).subscribe(mDaoUtil::deleteShopCartBean, throwable -> {
        }, () -> mIView.delOneData());
    }

    public void delData(ShopCartBean bean) {
        Observable.just(bean).subscribe(mDaoUtil::deleteShopCartBean, throwable -> {
        }, () -> mIView.delOneData());
    }

    public void updataBean(int position, ShopCartBean shopCartBean) {
        mDaoUtil.updatebean(shopCartBean);
    }
}
