package com.modiwu.mah.mvp;


import com.modiwu.mah.mvp.model.bean.AliPayInfoBean;
import com.modiwu.mah.mvp.model.bean.CarpenterBean;
import com.modiwu.mah.mvp.model.bean.CityCodeBean;
import com.modiwu.mah.mvp.model.bean.CollectionHaseBean;
import com.modiwu.mah.mvp.model.bean.DefLocalBean;
import com.modiwu.mah.mvp.model.bean.DesignBean;
import com.modiwu.mah.mvp.model.bean.DockerBean;
import com.modiwu.mah.mvp.model.bean.FloorBean;
import com.modiwu.mah.mvp.model.bean.HomeBean;
import com.modiwu.mah.mvp.model.bean.LocalBean;
import com.modiwu.mah.mvp.model.bean.LocalListBean;
import com.modiwu.mah.mvp.model.bean.LoginBean;
import com.modiwu.mah.mvp.model.bean.LoginStatusbean;
import com.modiwu.mah.mvp.model.bean.MeFangAnBean;
import com.modiwu.mah.mvp.model.bean.MeInfoBean;
import com.modiwu.mah.mvp.model.bean.MeOrderBean;
import com.modiwu.mah.mvp.model.bean.MeShouCangBean;
import com.modiwu.mah.mvp.model.bean.OrderCreateBean;
import com.modiwu.mah.mvp.model.bean.RegisterBean;
import com.modiwu.mah.mvp.model.bean.SchemeBean;
import com.modiwu.mah.mvp.model.bean.SchemeDetailBean;
import com.modiwu.mah.mvp.model.bean.SchemeOrderCreateBean;
import com.modiwu.mah.mvp.model.bean.SelectBean;
import com.modiwu.mah.mvp.model.bean.SelectLocalBean;
import com.modiwu.mah.mvp.model.bean.ShopGoodsInfoBean;
import com.modiwu.mah.mvp.model.bean.ShopSubListBean;
import com.modiwu.mah.mvp.model.bean.SubTitleBean;
import com.modiwu.mah.mvp.model.bean.VersionBean;
import com.modiwu.mah.mvp.model.bean.WxPayInfoBean;
import com.modiwu.mah.mvp.model.bean.YBJBean;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
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

    @GET("home?")
    Observable<HomeBean> getHomeBean(@Query("city_code") String city_code);

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

    @GET("profile/index")
    Observable<LoginStatusbean> getLoginStatus();

    @GET("select/building?")
    Observable<FloorBean> getFloorSelectBean(@Query("area_code") String area_code);

    @GET("fangan/list")
    Observable<SchemeBean> getSchemeBean();

    @GET("fangan/list?")
    Observable<SchemeBean> getSchemeBean(@QueryMap Map<String, String> map);

    @GET("designer/list")
    Observable<CarpenterBean> getCarpenterBean();

    @GET("mall/goods/home")
    Observable<DockerBean> getDockerBean();

    @GET("fangan/detail?")
    Observable<SchemeDetailBean> getSchemeDetailBean(@Query("fangan_id") String fangan_id);

    @GET("fangan/goods?")
    Observable<SchemeOrderCreateBean> getSchemeOrderCreateBean(@Query("fangan_id") String fangan_id);

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

    @POST("mall/order/l?")
    Observable<MeOrderBean> getOrderListBean(@Query("status") String status);

    @POST("shoucang/cancel")
    Observable<BaseBean> getShouCangDel(@Query("fangan_id") String fangan_id);

    @GET("area")
    Observable<LocalBean> getLocalBean();

    @GET("mall/addr/l")
    Observable<LocalListBean> getLocalListBean();

    @GET("fangan/order/all")
    Observable<MeFangAnBean> getMeSchemeListBean();

    @GET("mall/order/addr")
    Observable<DefLocalBean> getOrderLocalBean();

    @POST("mall/addr/remove?")
    Observable<BaseBean> getLocalDelBean(@Query("rpid") String rpid);

    @POST("mall/addr/update?")
    Observable<BaseBean> getLocalEditBean(@QueryMap() Map<String, String> map);

    @POST("mall/addr/create?")
    Observable<BaseBean> getLocalSaveBean(@QueryMap() Map<String, String> map);

    @POST("mall/addr/sd?")
    Observable<BaseBean> getLocalDefBean(@Query("rpid") String rpid, @Query("rp_default") String rp_default);

    @FormUrlEncoded
    @POST("mall/order/create?")
    Observable<OrderCreateBean> getOrderCreateBean(@FieldMap() Map<String, String> map);

    @FormUrlEncoded
    @POST("fangan/order/submit?")
    Observable<OrderCreateBean> getSchemeCreateBean(@FieldMap() Map<String, String> map);

    @GET("ope//info")
    Observable<YBJBean> getYBJBean();

    @POST("ope//ybj?")
    Observable<BaseBean> getYBJSubmitBean(@QueryMap() Map<String, String> map);

    @POST("shoucang/add?")
    Observable<BaseBean> getSchemeCollectionBean(@Query("fangan_id") String fangan_id);

    @GET("shoucang/has?")
    Observable<CollectionHaseBean> getSchemeHasCollectionBean(@Query("fangan_id") String fangan_id);

    @GET("select/city/code?")
    Observable<CityCodeBean> getCityCodeBean(@Query("city") String city);

    @POST("yyfa//add?")
    Observable<BaseBean> getYYSubmitBean(@QueryMap Map<String, String> map);

    @POST("fangan/order/remove?")
    Observable<BaseBean> getRemoveFangAn(@Query("order_no") String order_no);

    @POST("mall/order/close?")
    Observable<BaseBean> getOrderDelBean(@Query("orderId") String order_id, @Query("reason") String reason);

    @POST("mall/order/confirm?")
    Observable<BaseBean> getOrderOkBean(@Query("orderId") String order_id);

    @POST("profile/update?")
    Observable<BaseBean> getMeInfoBean(@Query("col") String col, @Query("value") String value);

    @GET("profile/info?")
    Observable<MeInfoBean> getMeInfoStartBean(@Query("uid") String uid);

    @Multipart
    @POST("profile/avatar?")
    Observable<BaseBean> getMeAvatarBean(@Part MultipartBody.Part file);

    @POST("mall/order/prePay?")
    Observable<WxPayInfoBean> getOrderWXPrePay(@Query("orderId") String orderId, @Query("payType") String payType);

    @POST("fangan/order/prePay?")
    Observable<WxPayInfoBean> getOrderWXPrePayFangAn(@Query("orderId") String orderId, @Query("payType") String payType);

    @POST("mall/order/prePay?")
    Observable<AliPayInfoBean> getOrderAliPrePay(@Query("orderId") String orderId, @Query("payType") String payType);

    @POST("fangan/order/prePay?")
    Observable<AliPayInfoBean> getOrderAliPrePayFangAn(@Query("orderId") String orderId, @Query("payType") String payType);

    @POST("user/logout?")
    Observable<BaseBean> getLogout();

    @POST("user/register?")
    Observable<RegisterBean> register(@QueryMap() Map<String, String> map);

    @POST("user/resetpw?")
    Observable<RegisterBean> forget(@QueryMap() Map<String, String> map);

    @GET("ver")
    Observable<VersionBean> getVersion();

    @POST("user/wxlogin?")
    Observable<LoginBean> getWxToken(@Query("token") String token, @Query("type") String type);

    @POST("user/wxlogin?")
    Observable<LoginBean> getWxTokenByLogin(
            @Query("token") String token,
            @Query("type") String type,
            @Query("smCode")String smCode,
            @Query("phone")String phone);
}
