package com.inke.zcl.learnrxjava.activity;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.inke.zcl.learnrxjava.manager.LeakActivityMgr;

/**
 * Create By chunliangzhang on 2019-06-25
 * Version 1.0
 * Description:
 */
public class LeakActivity extends Activity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LeakActivityMgr.getInstance().addActivity(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
