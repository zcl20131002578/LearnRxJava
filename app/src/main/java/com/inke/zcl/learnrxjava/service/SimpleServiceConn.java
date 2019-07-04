package com.inke.zcl.learnrxjava.service;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;

/**
 * Create By chunliangzhang on 2019-07-03
 * Version 1.0
 * Description:
 */
public class SimpleServiceConn implements ServiceConnection {


    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {

    }

    @Override
    public void onServiceDisconnected(ComponentName name) {

    }
}
