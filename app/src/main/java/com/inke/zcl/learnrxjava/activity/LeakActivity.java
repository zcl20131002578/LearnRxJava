package com.inke.zcl.learnrxjava.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.inke.zcl.learnrxjava.manager.LeakActivityMgr;

/**
 * Create By chunliangzhang on 2019-06-25
 * Version 1.0
 * Description:
 */
public class LeakActivity extends AppCompatActivity {


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
