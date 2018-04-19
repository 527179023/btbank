package com.example.lg.networkrequest.network;

import android.support.annotation.NonNull;

import com.example.lg.networkrequest.network.builder.GetBuilder;
import com.example.lg.networkrequest.network.builder.PostBuilder;
import com.google.gson.Gson;

/**
 * Created by Administrator on 2018/3/27.
 */

public class RequestUtils {

    private static Gson mGson;

    public static Gson getGson()
    {
        if(mGson==null){
            synchronized (Gson.class){
                mGson=new Gson();
            }
        }
        return mGson;
    }


    @NonNull
    public static GetBuilder get(){
        return new GetBuilder();
    }

    @NonNull
    public static PostBuilder post(){
        return new PostBuilder();
    }
}
