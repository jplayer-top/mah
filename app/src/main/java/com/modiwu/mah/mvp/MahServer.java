package com.modiwu.mah.mvp;


import com.modiwu.mah.mvp.model.bean.HomeBean;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by Obl on 2018/1/25.
 * com.modiwu.mah.net
 */

public interface MahServer  {
    @GET("home")
    Observable<HomeBean> getHomeBean();

}
