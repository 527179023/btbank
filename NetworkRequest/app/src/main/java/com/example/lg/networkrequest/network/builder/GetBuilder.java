package com.example.lg.networkrequest.network.builder;


import com.example.lg.networkrequest.network.RetrofitConfig;
import com.example.lg.networkrequest.network.api.RetrofitApi;
import com.example.lg.networkrequest.network.callback.ModelObserverListener;
import com.example.lg.networkrequest.network.callback.StringCallBack;
import com.trello.rxlifecycle2.android.ActivityEvent;
import com.trello.rxlifecycle2.components.RxActivity;

import java.lang.ref.SoftReference;
import java.util.LinkedHashMap;
import java.util.Map;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Administrator on 2018/3/27.
 */

public class GetBuilder extends RetrofitRequestBuilder<String,GetBuilder> implements HasParamsable
{

    @Override
    public Observable<String> build() {
        return  RetrofitConfig
                .initRetrofitConfig()
                .baseUrl(baseUrl)
                .build()
                .create(RetrofitApi.class)
                .get(url,headers,params)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }



    @Override
    public RetrofitRequestBuilder<String,GetBuilder> params(Map<String, String> params) {
        this.params = params;
        return this;
    }


    @Override
    public RetrofitRequestBuilder<String,GetBuilder> addParams(String key, String val) {
        params.put(key, val);
        return this;
    }



}
