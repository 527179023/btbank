package com.example.lg.networkrequest.network.callback;


import com.example.lg.networkrequest.network.RequestUtils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


/**
 * Created by Administrator on 2018/3/28.
 */

public class StringCallBack<T> implements Observer<String> {

    private ModelObserverListener mModelObserverListener;


    public StringCallBack(ModelObserverListener<T> modelObserverListener) {

        this.mModelObserverListener = modelObserverListener;
    }


    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(String result) {

        Type[] types = mModelObserverListener.getClass().getGenericInterfaces();
        if (MethodHandler(types) == null || MethodHandler(types).size() == 0) {
            return ;
        }
        Type finalNeedType = MethodHandler(types).get(0);

        if (RequestUtils.getGson().fromJson(result, finalNeedType)== null) {
            throw new NullPointerException();
        }

        mModelObserverListener.onNext(RequestUtils.getGson().fromJson(result, finalNeedType));

    }

    @Override
    public void onError(Throwable e) {

        mModelObserverListener.onError(e);

    }

    @Override
    public void onComplete() {

    }
    /**
     * MethodHandler
     */
    private List<Type> MethodHandler(Type[] types) {
        List<Type> needtypes = new ArrayList<>();
        Type needParentType = null;

        for (Type paramType : types) {
            if (paramType instanceof ParameterizedType) {
                Type[] parentypes = ((ParameterizedType) paramType).getActualTypeArguments();
                for (Type childtype : parentypes) {

                    needtypes.add(childtype);

                    if (childtype instanceof ParameterizedType) {
                        Type[] childtypes = ((ParameterizedType) childtype).getActualTypeArguments();
                        for (Type type : childtypes) {
                            needtypes.add(type);

                        }
                    }
                }
            }
        }
        return needtypes;
    }


}
