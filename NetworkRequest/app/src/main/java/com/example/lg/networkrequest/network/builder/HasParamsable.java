package com.example.lg.networkrequest.network.builder;



import java.util.Map;

/**
 * Created by zhy on 16/3/1.
 */
public interface HasParamsable
{
    RetrofitRequestBuilder params(Map<String, String> params);
    RetrofitRequestBuilder addParams(String key, String val);

}
