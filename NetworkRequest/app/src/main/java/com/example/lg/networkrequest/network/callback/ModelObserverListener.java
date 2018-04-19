package com.example.lg.networkrequest.network.callback;

/**
 * Created by Administrator on 2018/3/28.
 */

public interface  ModelObserverListener<T> {

    void onNext(T result);

    void onError(Throwable e);
}
