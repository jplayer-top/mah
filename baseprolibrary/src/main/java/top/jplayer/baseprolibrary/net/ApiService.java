package top.jplayer.baseprolibrary.net;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import top.jplayer.baseprolibrary.mvp.model.bean.LoginBean;

/**
 * Created by Obl on 2018/1/17.
 * top.jplayer.baseprolibrary.net
 */

public interface ApiService {
    @GET("support/bdapi/sharporder/redhallwill?")
    Observable<String> getSampleBean(@Query("parameter") String parameter,
                                     @Query("_") String cur_,
                                     @Query("callback") String callback);

    @POST("app/user/login?")
    Observable<LoginBean> getLoginBean(@Query("phone") String phone,
                                       @Query("password") String password);
}
