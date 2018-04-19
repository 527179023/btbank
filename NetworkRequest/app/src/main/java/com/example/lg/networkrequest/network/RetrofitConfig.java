package com.example.lg.networkrequest.network;

import android.support.annotation.NonNull;
import android.util.Log;


import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by Administrator on 2018/3/27.
 */

public class RetrofitConfig {

    private OkHttpClient okHttpClient;

    private Retrofit.Builder retrofit = new Retrofit.Builder();

    private static RetrofitConfig retrofitConfig;

    /**
     * 主url的路径
     */
    private String strBaseUrl="http://www.wanandroid.com/";


    public static RetrofitConfig initRetrofitConfig(){
         if(retrofitConfig==null){
            synchronized (RetrofitConfig.class){
                if(retrofitConfig==null){
                    retrofitConfig=new RetrofitConfig();
                }
            }
         }
         return retrofitConfig;
    }


    @NonNull
    private Retrofit.Builder Factory(){
        return retrofit
                .client(OkHttpClient())
                .addConverterFactory(ScalarsConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create());
    }

    public Retrofit.Builder baseUrl(String baseUrl)
    {
        if(baseUrl==null){
            baseUrl=strBaseUrl;
        }
        return Factory().baseUrl(baseUrl);
    }

    /**
     * 单例okhttpclient对象
     * @return
     */
    private OkHttpClient OkHttpClient()
    {
        if(okHttpClient==null){
            synchronized (OkHttpClient.class){
                if(okHttpClient==null){
                    okHttpClient=new OkHttpClient.Builder().connectTimeout(10000L, TimeUnit.MILLISECONDS)
                            .readTimeout(10000L, TimeUnit.MILLISECONDS)
                            .addInterceptor(getHttpLoggingInterceptor()).build();
                }
            }
        }

        return okHttpClient;
    }


    /**
     * 日志输出
     * 自行判定是否添加
     *
     * @return
     */
    private HttpLoggingInterceptor getHttpLoggingInterceptor() {
        //日志显示级别
        HttpLoggingInterceptor.Level level = HttpLoggingInterceptor.Level.BODY;
        //新建log拦截器
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.d("网络日志", "Retrofit====Message:" + message);
            }
        });
        loggingInterceptor.setLevel(level);
        return loggingInterceptor;
    }
}
