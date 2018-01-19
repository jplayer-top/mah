package top.jplayer.baseprolibrary.net;

import android.support.annotation.NonNull;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

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
    private Retrofit mRetrofit;

    private RetrofitManager() {
        url = BuildConfig.HOST;
    }

    private String url;

    public String getUrl() {
        return url;
    }

    public RetrofitManager url(String url) {
        this.url = url;
        return this;
    }

    public static synchronized RetrofitManager init() {

        if (mRetrofitManager == null) {
            mRetrofitManager = new RetrofitManager();
        }
        return mRetrofitManager;
    }

    /**
     * 默认调用方式
     */
    public RetrofitManager build() {
        mRetrofitManager.client().newBuild();
        return this;
    }

    /**
     * 默认create ApiServer
     */
    public ApiService create() {
        return build().createReq(ApiService.class);
    }

    /**
     * 重置Url
     */
    public RetrofitManager urlBuild(String url) {
        mRetrofitManager.url(url).client().newBuild();
        return this;
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
        return new Interceptor() {
            @Override
            public Response intercept(@NonNull Chain chain) throws IOException {
                Request request = chain.request();
                HttpUrl.Builder builder = request.url().newBuilder();
                HttpUrl httpUrl = builder
                        .addQueryParameter("version", "5.2.3")
                        .build();
                Request build = request.newBuilder().url(httpUrl).build();
                return chain.proceed(build);
            }
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

    private OkHttpClient defClient;

    public RetrofitManager client() {
        HttpLoggingInterceptor LoginInterceptor = addHttpLoggingInterceptor();
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.addInterceptor(addQueryParameterInterceptor())
                .addInterceptor(addHeaderInterceptor());

        if (BuildConfig.DEBUG) {
            builder.addInterceptor(LoginInterceptor);
        }
        defClient = builder.connectTimeout(30L, TimeUnit.SECONDS)
                .readTimeout(30L, TimeUnit.SECONDS)
                .writeTimeout(30L, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .build();
        return this;
    }


    public RetrofitManager newBuild() {
        mRetrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create(GsonUtils.setGsonFilter()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(defClient)
                .build();
        return this;
    }


    public <T> T createReq(Class<T> reqServer) throws NullPointerException {
        return mRetrofit.create(reqServer);
    }

}
