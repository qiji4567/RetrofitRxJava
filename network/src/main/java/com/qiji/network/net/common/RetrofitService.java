package com.qiji.network.net.common;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.qiji.network.net.converter.GsonConverterFactory;
import com.qiji.network.net.interceptor.HttpCacheInterceptor;
import com.qiji.network.net.interceptor.HttpHeaderInterceptor;
import com.qiji.network.net.interceptor.LoggingInterceptor;
import com.qiji.network.utils.LogUtils;
import com.qiji.network.utils.Utils;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 * Created by zhpan on 2018/3/21.
 */

public class RetrofitService {

    public static Retrofit.Builder getRetrofitBuilder(String baseUrl) {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").serializeNulls().create();
        OkHttpClient okHttpClient = getOkHttpClientBuilder().build();
        return new Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(baseUrl);
    }

    public static OkHttpClient.Builder getOkHttpClientBuilder() {
        File cacheFile = new File(Utils.getContext().getCacheDir(), "cache");
        Cache cache = new Cache(cacheFile, 1024 * 1024 * 100); //100Mb
//        HttpLoggingInterceptor loggingInterceptor = getLogInterceptor(HttpLoggingInterceptor.Level.BODY);

        return new OkHttpClient.Builder()
                .readTimeout(Constants.DEFAULT_TIMEOUT, TimeUnit.MILLISECONDS)
                .connectTimeout(Constants.DEFAULT_TIMEOUT, TimeUnit.MILLISECONDS)
                .addInterceptor(new LoggingInterceptor())
                .addInterceptor(new HttpHeaderInterceptor())
                .addNetworkInterceptor(new HttpCacheInterceptor())
                // .sslSocketFactory(SslContextFactory.getSSLSocketFactoryForTwoWay())  // https认证 如果要使用https且为自定义证书 可以去掉这两行注释，并自行配制证书。
                // .hostnameVerifier(new SafeHostnameVerifier())
                .cache(cache);
    }

    //  请求头拦截器
    private static HttpLoggingInterceptor getLogInterceptor(HttpLoggingInterceptor.Level level) {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                LogUtils.e("OKHttp-----", message);
            }
        });
        loggingInterceptor.setLevel(level);
        return loggingInterceptor;
    }
}
