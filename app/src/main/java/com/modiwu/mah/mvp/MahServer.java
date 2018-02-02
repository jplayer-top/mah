package com.modiwu.mah.mvp;


import com.modiwu.mah.mvp.model.bean.CarpenterBean;
import com.modiwu.mah.mvp.model.bean.DockerBean;
import com.modiwu.mah.mvp.model.bean.FloorBean;
import com.modiwu.mah.mvp.model.bean.HomeBean;
import com.modiwu.mah.mvp.model.bean.LoginBean;
import com.modiwu.mah.mvp.model.bean.SchemeBean;
import com.modiwu.mah.mvp.model.bean.SelectBean;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

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

}
