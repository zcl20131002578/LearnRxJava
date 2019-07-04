package com.inke.zcl.learnrxjava.service;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;

import androidx.annotation.Nullable;

/**
 * Create By chunliangzhang on 2019-07-02
 * Version 1.0
 * Description:
 */

public class SimpleService extends Service {


    /**
     *
     */
    @Override
    public void onCreate() {
        super.onCreate();
        getApplicationContext().startService(new Intent());
        getApplicationContext().bindService(new Intent(), new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {

            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        }, 0);
    }

    /**
     * 绑定服务时才会调用必须要实现的方法
     * @param intent
     * @return
     */
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    /**
     *
     * @param intent
     * @param flags
     * @param startId
     * @return
     */
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    /**
     *
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
