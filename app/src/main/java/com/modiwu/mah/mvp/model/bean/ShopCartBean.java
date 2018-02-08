package com.modiwu.mah.mvp.model.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Transient;

/**
 * Created by Administrator on 2018/1/28.
 * 购物车Bean
 */
@Entity
public class ShopCartBean {
    @Id(autoincrement = true)
    public Long _id;
    public String title;
    public String sub_title;
    public String price;
    public String count;
    public String pic_url;
    public String goods_attr_id;
    @Transient
    public boolean isEdit;
    @Transient
    public boolean isCheck;

    @Generated(hash = 68342908)
    public ShopCartBean(Long _id, String title, String sub_title, String price,
            String count, String pic_url, String goods_attr_id) {
        this._id = _id;
        this.title = title;
        this.sub_title = sub_title;
        this.price = price;
        this.count = count;
        this.pic_url = pic_url;
        this.goods_attr_id = goods_attr_id;
    }

    @Generated(hash = 656791369)
    public ShopCartBean() {
    }

    public Long get_id() {
        return this._id;
    }

    public void set_id(Long _id) {
        this._id = _id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSub_title() {
        return this.sub_title;
    }

    public void setSub_title(String sub_title) {
        this.sub_title = sub_title;
    }

    public String getPrice() {
        return this.price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCount() {
        return this.count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getPic_url() {
        return this.pic_url;
    }

    public void setPic_url(String pic_url) {
        this.pic_url = pic_url;
    }

    public String getGoods_attr_id() {
        return this.goods_attr_id;
    }

    public void setGoods_attr_id(String goods_attr_id) {
        this.goods_attr_id = goods_attr_id;
    }
}
