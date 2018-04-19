package com.example.lg.networkrequest.network.builder;

import com.example.lg.networkrequest.network.callback.ModelObserverListener;
import com.trello.rxlifecycle2.components.RxActivity;

import java.lang.ref.SoftReference;
import java.util.LinkedHashMap;
import java.util.Map;

import io.reactivex.Flowable;
import io.reactivex.Observable;

/**
 * Created by Administrator on 2018/3/27.
 */

public abstract class RetrofitRequestBuilder<R,T extends RetrofitRequestBuilder> {

    protected String url;
    protected String baseUrl;
    protected Map<String,String> headers=new LinkedHashMap<>();
    protected Map<String, String> params=new LinkedHashMap<>();


    public T url(String url){
        this.url=url;
        return (T)this;
    }
    public T baseUrl(String baseUrl)
    {
        this.baseUrl=baseUrl;
        return (T)this;
    }

    public T headers(Map<String,String> headers){
        this.headers=headers;
        return (T)headers;
    }

    public T addHeader(String key, String val)
    {

        headers.put(key, val);
        return (T) this;
    }


    public abstract Observable<R> build();


}
