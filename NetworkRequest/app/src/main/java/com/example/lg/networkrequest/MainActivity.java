package com.example.lg.networkrequest;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.lg.networkrequest.network.RequestUtils;
import com.example.lg.networkrequest.network.bean.ArticleBean;
import com.example.lg.networkrequest.network.bean.BannerBean;
import com.example.lg.networkrequest.network.callback.ModelObserverListener;
import com.example.lg.networkrequest.network.callback.StringCallBack;
import com.example.lg.networkrequest.synupdate.UpdateMian;
import com.trello.rxlifecycle2.android.ActivityEvent;
import com.trello.rxlifecycle2.components.RxActivity;

import java.util.Observable;

import butterknife.BindView;
import butterknife.ButterKnife;



public class MainActivity extends RxActivity {

    @BindView(R.id.tv_content)
    TextView tvContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


    }

    public void onClickDow(View view){
        Intent it=new Intent(this,UpdateMian.class);
        startActivity(it);

    }



    public void onClickGet(View view) {
        RequestUtils.get()
                .url("/banner/json")
                .build()
                .compose(this.<String>bindUntilEvent(ActivityEvent.DESTROY))
                .subscribe(new StringCallBack<>(new ModelObserverListener<BannerBean>() {
                    @Override
                    public void onNext(BannerBean result) {
                        tvContent.setText(RequestUtils.getGson().toJson(result));

                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                }));
    }


    public void onClickPost(View view) {
        RequestUtils.post()
                .url("article/query/0/json")
                .addParams("k","android")
                .build()
                .compose(this.<String>bindUntilEvent(ActivityEvent.DESTROY))
                .subscribe(new StringCallBack<>(new ModelObserverListener<ArticleBean>() {

                    @Override
                    public void onNext(ArticleBean result) {
                        tvContent.setText(RequestUtils.getGson().toJson(result));
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                }));
    }
}
