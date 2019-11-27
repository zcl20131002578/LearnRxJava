package com.inke.zcl.learnrxjava.rx;


import android.os.Looper;
import android.util.Log;

import rx.Observable;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Create By chunliangzhang on 2019-11-19
 * Version 1.0
 * Description:
 */
public class RxScheduler {


    private static final String TAG = "RxScheduler";


    public static void mainScheduler() {
        Log.d(TAG, "mainScheduler: isMainThread:" + isMainThread());
        Observable.just("aaa")
                .observeOn(Schedulers.io())
                .map(new Func1<String, String>() {
                    @Override
                    public String call(String s) {
                        Log.d(TAG, "map call: s_" + s + " isMainThread " + isMainThread());
                        return s;
                    }
                })
                .subscribeOn(AndroidSchedulers.mainThread())
                .map(new Func1<String, String>() {
                    @Override
                    public String call(String s) {
                        Log.d(TAG, "two call: s_" + s + " isMainThread " + isMainThread());
                        return s;
                    }
                })
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        Log.d(TAG, "subscribe call: s_" + s + " isMainThread " + isMainThread());
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {

                    }
                });
    }

    private static boolean isMainThread() {
        Log.d(TAG, "isMainThread() currentThreadId: " + Thread.currentThread().getId() + " mainThreadId: " + Looper.getMainLooper().getThread().getId());
        return Thread.currentThread().getId() == Looper.getMainLooper().getThread().getId();
    }
}
