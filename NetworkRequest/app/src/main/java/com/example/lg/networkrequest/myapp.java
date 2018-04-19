package com.example.lg.networkrequest;

import android.app.Application;

import com.liulishuo.filedownloader.FileDownloader;
import com.liulishuo.filedownloader.database.RemitDatabase;
import com.squareup.leakcanary.LeakCanary;

/**
 * Created by Administrator on 2018/3/28.
 */

public class myapp extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);

        FileDownloader.setupOnApplicationOnCreate(this).database(new RemitDatabase.Maker());

    }
}
