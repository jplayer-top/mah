package com.modiwu.mah.mvp;


import com.modiwu.mah.mvp.model.bean.HomeBean;
import com.modiwu.mah.mvp.model.bean.LoginBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Obl on 2018/1/25.
 * com.modiwu.mah.net
 */

public interface MahServer {
    @GET("home")
    Observable<HomeBean> getHomeBean();

    @POST("user/login?")
    Observable<LoginBean> getLoginBean(@Query("phone") String phone, @Query("password") String password);
}
