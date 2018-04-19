package com.example.lg.networkrequest.network.builder;

import com.example.lg.networkrequest.network.RetrofitConfig;
import com.example.lg.networkrequest.network.api.RetrofitApi;

import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Administrator on 2018/3/28.
 */

public class PostBuilder extends RetrofitRequestBuilder<String,PostBuilder> implements HasParamsable{

    @Override
    public Observable<String> build() {
        return RetrofitConfig
                .initRetrofitConfig()
                .baseUrl(baseUrl)
                .build()
                .create(RetrofitApi.class)
                .post(url,headers,params)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public RetrofitRequestBuilder<String,PostBuilder> params(Map<String, String> params) {
        this.params=params;
        return this;
    }

    @Override
    public RetrofitRequestBuilder<String,PostBuilder> addParams(String key, String val) {
        params.put(key, val);
        return this;
    }
}
