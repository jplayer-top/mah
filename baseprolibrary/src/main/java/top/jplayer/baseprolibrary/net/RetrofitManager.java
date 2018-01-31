package top.jplayer.baseprolibrary.net;

import android.support.annotation.NonNull;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import top.jplayer.baseprolibrary.BuildConfig;
import top.jplayer.baseprolibrary.utils.GsonUtils;
import top.jplayer.baseprolibrary.utils.LogUtil;

/**
 * Created by Obl on 2018/1/12.
 * top.jplayer.baseprolibrary.net
 */

public class RetrofitManager {
    private static RetrofitManager mRetrofitManager;
    private Retrofit defRetrofit;
    private Retrofit reRetrofit;

    private RetrofitManager() {
        defRetrofit = client(defBuilder(), BuildConfig.HOST);
    }

    public static synchronized RetrofitManager init() {

        if (mRetrofitManager == null) {
            mRetrofitManager = new RetrofitManager();
        }
        return mRetrofitManager;
    }

    public OkHttpClient.Builder reBuilder(Interceptor... interceptors) {
        final OkHttpClient.Builder builder = defBuilder();
        Observable.fromArray(interceptors)
                .subscribe(new Consumer<Interceptor>() {
                    @Override
                    public void accept(Interceptor interceptor) throws Exception {
                        if (interceptor != null) {
                            builder.addInterceptor(interceptor);
                        }
                    }
                });
        return builder;
    }

    /**
     * 重置Url,添加拦截器
     */
    public RetrofitManager reset(String url, Interceptor... interceptors) {
        reRetrofit = mRetrofitManager.client(reBuilder(interceptors), url);
        return this;
    }

    private Retrofit client(OkHttpClient.Builder builder, String url) {
        OkHttpClient reClient = builder.connectTimeout(30L, TimeUnit.SECONDS)
                .readTimeout(30L, TimeUnit.SECONDS)
                .writeTimeout(30L, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .build();
        return new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create(GsonUtils.setGsonFilter()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(reClient)
                .build();
    }

    /**
     * 设置头信息
     */
    private Interceptor addHeaderInterceptor() {
        return new Interceptor() {
            @Override
            public Response intercept(@NonNull Chain chain) throws IOException {
                Request request = chain.request();
                Request.Builder newBuilder = request.newBuilder();
                Request build = newBuilder
                        .header("token", "")
                        .method(request.method(), request.body())
                        .build();
                return chain.proceed(build);
            }
        };
    }

    /**
     * 设置公共参数
     */
    private Interceptor addQueryParameterInterceptor() {
        return chain -> {
            Request request = chain.request();
            HttpUrl.Builder builder = request.url().newBuilder();
            HttpUrl httpUrl = builder
                    .addQueryParameter("version", "5.2.3")
                    .addQueryParameter("source", "app_android")
                    .build();
            Request build = request.newBuilder().url(httpUrl).build();
            return chain.proceed(build);
        };
    }

    /**
     * 添加Log 拦截
     */
    @NonNull
    private HttpLoggingInterceptor addHttpLoggingInterceptor() {
        HttpLoggingInterceptor LoginInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                LogUtil.net(message);
            }
        });
        LoginInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return LoginInterceptor;
    }

    @NonNull
    private OkHttpClient.Builder defBuilder() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        HttpLoggingInterceptor LoginInterceptor = addHttpLoggingInterceptor();
        builder.addInterceptor(addQueryParameterInterceptor())
                .addInterceptor(addHeaderInterceptor());

        if (BuildConfig.DEBUG) {
            builder.addInterceptor(LoginInterceptor);
        }

        return builder;
    }

    public <T> T create(Class<T> reqServer) throws NullPointerException {
        return defRetrofit.create(reqServer);
    }

    public <T> T reCreate(Class<T> reqServer) throws NullPointerException {
        return reRetrofit.create(reqServer);
    }

}
