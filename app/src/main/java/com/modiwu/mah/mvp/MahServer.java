package com.modiwu.mah.mvp;


import com.modiwu.mah.mvp.model.bean.CarpenterBean;
import com.modiwu.mah.mvp.model.bean.DesignBean;
import com.modiwu.mah.mvp.model.bean.DockerBean;
import com.modiwu.mah.mvp.model.bean.FloorBean;
import com.modiwu.mah.mvp.model.bean.HomeBean;
import com.modiwu.mah.mvp.model.bean.LocalBean;
import com.modiwu.mah.mvp.model.bean.LocalListBean;
import com.modiwu.mah.mvp.model.bean.LoginBean;
import com.modiwu.mah.mvp.model.bean.MeOrderBean;
import com.modiwu.mah.mvp.model.bean.MeShouCangBean;
import com.modiwu.mah.mvp.model.bean.SchemeBean;
import com.modiwu.mah.mvp.model.bean.SchemeDetailBean;
import com.modiwu.mah.mvp.model.bean.SelectBean;
import com.modiwu.mah.mvp.model.bean.SelectLocalBean;
import com.modiwu.mah.mvp.model.bean.ShopGoodsInfoBean;
import com.modiwu.mah.mvp.model.bean.ShopSubListBean;
import com.modiwu.mah.mvp.model.bean.SubTitleBean;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import top.jplayer.baseprolibrary.mvp.model.bean.BaseBean;

/**
 * Created by Obl on 2018/1/25.
 * com.modiwu.mah.net
 */

public interface MahServer {
    @GET("home")
    Observable<HomeBean> getHomeBean();

    @POST("user/login?")
    Observable<LoginBean> getLoginBean(@Query("phone") String phone, @Query("password") String password);

    @POST("user/smcode?")
    Observable<LoginBean> getSmsBean(@QueryMap() Map<String, String> map);

    @POST("user/verfiysmcode?")
    Observable<LoginBean> verfiyCode(@Query("phone") String phone, @Query("smCode") String password);

    @GET("select/style")
    Observable<SelectBean> getStyleSelectBean();

    @GET("select/area?")
    Observable<SelectLocalBean> getLocalSelectBean(@Query("city_code") String city_code);

    @GET("select/hxtype")
    Observable<SelectBean> getTypeSelectBean();

    @GET("select/building/select")
    Observable<FloorBean> getFloorSelectBean();

    @GET("fangan/list")
    Observable<SchemeBean> getSchemeBean();

    @GET("designer/list")
    Observable<CarpenterBean> getCarpenterBean();

    @GET("mall/goods/home")
    Observable<DockerBean> getDockerBean();

    @GET("fangan/detail?")
    Observable<SchemeDetailBean> getSchemeDetailBean(@Query("fangan_id") String fangan_id);

    @GET("designer/detail?")
    Observable<DesignBean> getDesignBean(@Query("designer_id") String designer_id);

    @GET("mall/goods/subcat?")
    Observable<SubTitleBean> getSubTitleBean(@Query("cat_id") String cat_id);

    @GET("mall/goods/l?")
    Observable<ShopSubListBean> getSubListBean(@Query("pageNum") String page_num, @Query("cat_id") String cat_id);

    @GET("mall/goods/info?")
    Observable<ShopGoodsInfoBean> getGoodsInfoBean(@Query("goods_id") String goods_id);

    @GET("shoucang/all")
    Observable<MeShouCangBean> getShouCangBean();

    @POST("mall/order/l")
    Observable<MeOrderBean> getOrderListBean();

    @POST("shoucang/cancel")
    Observable<BaseBean> getShouCangDel(@Query("fangan_id") String fangan_id);

    @GET("area")
    Observable<LocalBean> getLocalBean();

    @GET("mall/addr/l")
    Observable<LocalListBean> getLocalListBean();

    @POST("mall/addr/remove?")
    Observable<BaseBean> getLocalDelBean(@Query("rpid") String rpid);

    @POST("mall/addr/update?")
    Observable<BaseBean> getLocalEditBean(@QueryMap() Map<String, String> map);

    @POST("mall/addr/create?")
    Observable<BaseBean> getLocalSaveBean(@QueryMap() Map<String, String> map);

    @POST("mall/addr/sd?")
    Observable<BaseBean> getLocalDefBean(@Query("rpid") String rpid, @Query("rp_default") String rp_default);

}
