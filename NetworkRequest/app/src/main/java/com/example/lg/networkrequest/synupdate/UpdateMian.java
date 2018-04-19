package com.example.lg.networkrequest.synupdate;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.example.lg.networkrequest.R;

import java.util.Observable;
import java.util.Observer;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/3/29.
 */

public class UpdateMian extends Activity {
     @BindView(R.id.btn_start)
     Button btnStart;
    @BindView(R.id.btn_stop)
    Button btnStop;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    DownloadObservable downloadObservable;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.updatemian);
        ButterKnife.bind(this);

        downloadObservable = DownloadObservable.getDownloadObservable();

        downloadObservable.addObserver(new MyObserver());

    }

    @OnClick({R.id.btn_start, R.id.btn_stop})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_start:
                downloadObservable.DownloadFromServer();
                break;
            case R.id.btn_stop:
                break;
        }
    }

    class MyObserver implements Observer {

        @Override
        public void update(Observable o, Object arg) {
            //Bundle bundle = (Bundle) arg;
            Log.e("update","arg"+arg.toString());
            //setProgressBar(Integer.parseInt(bundle.getString("soFarBytes")), Integer.parseInt(bundle.getString("totalBytes") + ""));
        }

        public void setProgressBar(int soFarBytes, int totalBytes) {
            progressBar.setMax(totalBytes);
            progressBar.setProgress(soFarBytes);
        }




    }


}