package com.inke.zcl.firstmode.Reflect;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import androidx.annotation.NonNull;

/**
 * Create By chunliangzhang on 2019-11-27
 * Version 1.0
 * Description:
 */
public class SafeHandler extends Handler {

    private static final String TAG = "SafeHandler";
    private Handler handler;

    public SafeHandler(@NonNull Handler handler) {
        this.handler = handler;
    }

    @Override
    public void handleMessage(Message msg) {
        handler.handleMessage(msg);

    }

    @Override
    public void dispatchMessage(Message msg) {
        try {
            super.dispatchMessage(msg);
            Log.d(TAG, "dispatchMessage: msg_" + msg.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
