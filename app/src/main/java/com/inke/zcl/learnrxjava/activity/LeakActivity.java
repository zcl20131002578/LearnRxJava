package com.inke.zcl.learnrxjava.activity;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;

import androidx.annotation.Nullable;

import com.inke.zcl.learnrxjava.R;
import com.inke.zcl.learnrxjava.manager.LeakActivityMgr;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import rx.subscriptions.CompositeSubscription;

/**
 * Create By chunliangzhang on 2019-06-25
 * Version 1.0
 * Description:
 */
public class LeakActivity extends Activity {

    public static final String TAG = "LeakActivity";
    private ExecutorService executorService;
    private final CompositeSubscription compositeSubscription = new CompositeSubscription();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leak_layout);
//        init();
//        LeakActivityMgr.getInstance().addActivity(this);
        startAnim();
    }

    private void startAnim() {
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.gift_roate_anim);
        animation.setInterpolator(new LinearInterpolator());
        animation.start();
    }

    private void init() {
        try {
            throwError(null);
            this.executorService = Executors.newSingleThreadExecutor();
            if (executorService == null) {
                Log.d(TAG, "init: executorService=null");
            } else {
                Log.d(TAG, "init: executorService!=null");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void throwError(Context context) {
        try {
            SharedPreferences var3 = context.getSharedPreferences("share_data", 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeSubscription.unsubscribe();
        compositeSubscription.clear();
    }
}
