package com.example.lg.networkrequest.synupdate;

import android.os.Bundle;
import android.os.Environment;
import android.util.Log;

import com.liulishuo.filedownloader.BaseDownloadTask;
import com.liulishuo.filedownloader.FileDownloadLargeFileListener;
import com.liulishuo.filedownloader.FileDownloader;

import java.util.Observable;

/**
 * Created by Administrator on 2018/3/29.
 */

public class DownloadObservable extends Observable{

    static DownloadObservable downloadObservable=null;

    public static DownloadObservable getDownloadObservable(){
         if(downloadObservable==null){
               synchronized (DownloadObservable.class){
                   downloadObservable=new DownloadObservable();
               }
         }
         return downloadObservable;
    }



    Bundle bundle=new Bundle();

    public Bundle getData(){
        return bundle;
    }


    public void DownloadFromServer()
    {

        notifyObservers("notifyObservers");
       /* FileDownloader
                .getImpl()
                .create("http://imtt.dd.qq.com/16891/22E9CBC43EFA6FDC324A06B15333FF27.apk?fsname=com.imangi.templerun2_4.3.1_4639.apk&csr=1bbd")
                .setPath(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + "/" + System.currentTimeMillis() + ".apk")
                .setListener(new FileDownloadLargeFileListener() {
                    @Override
                    protected void pending(BaseDownloadTask task, long soFarBytes, long totalBytes) {

                        Log.e("pending", "soFarBytes+=" + soFarBytes + "--" + "totalBytes+=" + totalBytes);
                    }

                    @Override
                    protected void progress(BaseDownloadTask task, long soFarBytes, long totalBytes) {
                        //Log.e("----------","------------");
                       *//* bundle.putString("soFarBytes",soFarBytes+"");
                        bundle.putString("totalBytes",totalBytes+"");*//*


                        Log.e("progress", "soFarBytes+=" + soFarBytes + "--" + "totalBytes+=" + totalBytes);

                    }

                    @Override
                    protected void paused(BaseDownloadTask task, long soFarBytes, long totalBytes) {
                        Log.e("progress", "soFarBytes+=" + soFarBytes + "--" + "totalBytes+=" + totalBytes);
                    }

                    @Override
                    protected void completed(BaseDownloadTask task) {
                        Log.e("completed", "----");
                        //task.start();
                    }

                    @Override
                    protected void error(BaseDownloadTask task, Throwable e) {
                        Log.e("error", "----" + e.getMessage());
                    }

                    @Override
                    protected void warn(BaseDownloadTask task) {
                        Log.e("warn", "----");
                    }
                }).start();*/
    }

    public void setStop(){
        FileDownloader
                .getImpl().pauseAll();

        notifyObservers(bundle);
    }

}
