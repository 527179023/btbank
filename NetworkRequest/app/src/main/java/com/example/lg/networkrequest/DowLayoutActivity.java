package com.example.lg.networkrequest;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.liulishuo.filedownloader.BaseDownloadTask;
import com.liulishuo.filedownloader.FileDownloadLargeFileListener;
import com.liulishuo.filedownloader.FileDownloader;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/3/29.
 */

public class DowLayoutActivity extends Activity {
    @BindView(R.id.tv_state)
    TextView tvState;
    @BindView(R.id.tv_speed)
    TextView tvSpeed;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dowlayout);
        ButterKnife.bind(this);
    }

    public void onClickDow(View view) {

        Log.e("---",Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)+"/"+System.currentTimeMillis()+".apk");
        FileDownloader
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
                        progressBar.setMax(Integer.parseInt("" + totalBytes));
                        progressBar.setProgress(Integer.parseInt("" + soFarBytes));

                        tvSpeed.setText(task.getSpeed() + "kb");

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
                }).start();

    }

    @Override
    protected void onDestroy() {
        FileDownloader
                .getImpl().pauseAll();
        super.onDestroy();

    }
}
