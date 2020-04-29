package com.example.servicedemo;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class StartService extends Service {
    public StartService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    //当Service第一次创建时，回调该方法（只一次）,除非Service被销毁重新创建
    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("调用方法：", "onCreate");
    }

    //当其他组件调用startService()方法请求启动Service时，该方法被回调（每次）。
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e("调用方法：", "onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    //当Service被销毁时回调，释放占用的资源
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("调用方法：", "onDestroy");
    }
}
