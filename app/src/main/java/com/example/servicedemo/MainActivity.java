package com.example.servicedemo;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button bnt_start;
    private Button bnt_unbond;
    private Button bnt_bond;
    private Button bnt_destroy;
    private BindService.MyBinder myBinder;

    private ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder binder) {
            Log.e("调用的方法：", "onServiceConnected");
            myBinder = (BindService.MyBinder) binder;
            BindService service = myBinder.getService();
            int randomNumber = service.getRandomNumber();
            System.out.println(randomNumber);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.e("调用的方法：", "onServiceDisconnected");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bnt_start = findViewById(R.id.bnt_start);
        bnt_bond = findViewById(R.id.bnt_bond);
        bnt_unbond = findViewById(R.id.bnt_unbond);
        bnt_destroy = findViewById(R.id.bnt_destroy);

        bnt_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, StartService.class);
                startService(intent);
            }
        });

        bnt_destroy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, StartService.class);
                stopService(intent);
            }
        });

        bnt_bond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, BindService.class);
                /**
                 * 这里传入BIND_AUTO_CREATE，表示在Activity和Service关联后自动创建Service
                 * 这会使得MyService中的onCreate()方法得到执行，但onStartCommand()方法不会执行
                 */
                bindService(intent, conn, BIND_AUTO_CREATE);
            }
        });

        bnt_unbond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * 如果没有绑定服务就执行unbindService就会报错
                 * java.lang.IllegalArgumentException: Service not registered: com.example.servicedemo.MainActivity$1@3f402f68
                 */
                unbindService(conn);
            }
        });
    }
}
