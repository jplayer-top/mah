package com.modiwu.mah.mvp.model;

import android.content.Context;
import android.util.Log;

import com.modiwu.mah.gen.ShopCartBeanDao;
import com.modiwu.mah.greendao.DaoManager;
import com.modiwu.mah.mvp.model.bean.ShopCartBean;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

/**
 * Created by Administrator on 2018/1/28.
 */

public class ShopCartDaoUtil {
    private static final String TAG = ShopCartDaoUtil.class.getSimpleName();
    private DaoManager mManager;

    public ShopCartDaoUtil(Context context) {
        mManager = DaoManager.getInstance();
        mManager.init(context);
    }

    /**
     * 完成bean记录的插入，如果表未创建，先创建bean表
     *
     * @param bean
     * @return
     */
    public boolean insertShopCart(ShopCartBean bean) {
        boolean flag = false;
        flag = mManager.getDaoSession().getShopCartBeanDao().insert(bean) != -1;
        Log.i(TAG, "insert bean :" + flag + "-->" + bean.toString());
        return flag;
    }

    /**
     * 插入多条数据，在子线程操作
     */
    public boolean insertMultShopCart(final List<ShopCartBean> beanList) {
        boolean flag = false;
        try {
            mManager.getDaoSession().runInTx(new Runnable() {
                @Override
                public void run() {
                    for (ShopCartBean bean : beanList) {
                        mManager.getDaoSession().insertOrReplace(bean);
                    }
                }
            });
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 修改一条数据
     *
     * @return
     */
    public boolean updatebean(ShopCartBean bean) {
        boolean flag = false;
        try {
            mManager.getDaoSession().update(bean);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 删除单条记录
     *
     * @param bean
     * @return
     */
    public boolean deleteShopCartBean(ShopCartBean bean) {
        boolean flag = false;
        try {
            //按照id删除
            mManager.getDaoSession().delete(bean);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 删除所有记录
     *
     * @return
     */
    public boolean deleteAll() {
        boolean flag = false;
        try {
            //按照id删除
            mManager.getDaoSession().deleteAll(ShopCartBean.class);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 查询所有记录
     *
     * @return
     */
    public List<ShopCartBean> queryAllbean() {
        return mManager.getDaoSession().loadAll(ShopCartBean.class);
    }

    /**
     * 根据主键id查询记录
     *
     * @param key
     * @return
     */
    public ShopCartBean querybeanById(long key) {
        return mManager.getDaoSession().load(ShopCartBean.class, key);
    }

    /**
     * 使用native sql进行查询操作
     */
    public List<ShopCartBean> querybeanByNativeSql(String sql, String[] conditions) {
        return mManager.getDaoSession().queryRaw(ShopCartBean.class, sql, conditions);
    }

    /**
     * 使用queryBuilder进行查询
     */
    public List<ShopCartBean> querybeanByQueryBuilder(long id) {
        QueryBuilder<ShopCartBean> queryBuilder = mManager.getDaoSession().queryBuilder(ShopCartBean.class);
        return queryBuilder.where(ShopCartBeanDao.Properties._id.eq(id)).list();
    }
}
