package com.example.lg.networkrequest.network.api;

import java.util.Map;


import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * Created by Administrator on 2018/3/27.
 */

public interface RetrofitApi {

    /**
     * get请求
     * @param url 访问的路径
     * @param headers 请求头
     * @param params 提交给服务的参数
     * @return
     */
    @GET
    Observable<String> get(@Url String url, @HeaderMap Map<String, String> headers, @QueryMap Map<String, String> params);

    /**
     * post请求
     * @param url 访问的路径
     * @param headers 请求头
     * @param params 提交给服务的参数
     * @return
     */
    @FormUrlEncoded
    @POST
    Observable<String> post(@Url String url,@HeaderMap Map<String, String> headers, @FieldMap Map<String, String> params);


    @Streaming
    @GET
    Observable<ResponseBody> downloadFile(@Url String url,@Header("RANGE") String start);

}
