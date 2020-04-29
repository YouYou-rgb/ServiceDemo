package com.example.servicedemo;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import java.util.Random;

public class BindService extends Service {
    public BindService() {
    }

    //绑定服务时才会调用,必须要实现的方法
    @Override
    public IBinder onBind(Intent intent) {
        Log.e("调用方法：", "onBind");
        return new MyBinder();//通过binder实现调用者client与Service之间的通信
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.e("调用方法：", "onUnbind");
        return super.onUnbind(intent);
    }

    //当Service第一次创建时，回调该方法（只一次）,除非Service被销毁重新创建
    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("调用方法：", "onCreate");
    }

    //当调用startService()方法请求启动Service时，该方法被回调（每次）。
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

    class MyBinder extends Binder {
        public BindService getService() {
            System.out.println("绑定服务");
            return BindService.this;
        }
    }

    private final Random generator = new Random();

    //getRandomNumber是Service供client调用的公共方法
    public int getRandomNumber() {
        return generator.nextInt();
    }
}
