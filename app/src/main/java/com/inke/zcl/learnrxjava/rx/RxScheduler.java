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
        //subscribeOn 通过接收一个Scheduler 参数，来指定对数据的处理运行在特定的线程调度器Scheduler上。

        /**
         * subscribeOn(Schedulers.computation())
         * subscribeOn(AndroidSchedulers.mainThread())
         * 若多次执行subscribeOn ，则只有第一次起作用
         *
         * 若多次执行observeOn ，则每次都起作用，线程会一直切换。
         */
        //observeOn 同样接收一个Scheduler 参数，用来指定下游操作运行在特定的线程调度器Scheduler上。

        String[] arrayString = new String[]{"aaa"};
        Observable.from(arrayString)
                .map(new Func1<String, String>() {
                    @Override
                    public String call(String s) {
                        Log.d(TAG, "map call: s_" + s + " isMainThread " + isMainThread());
                        return s;
                    }
                })
                .map(new Func1<String, String>() {
                    @Override
                    public String call(String s) {
                        Log.d(TAG, "two call: s_" + s + " isMainThread " + isMainThread());
                        return s;
                    }
                })
                .subscribeOn(Schedulers.computation())
                .subscribeOn(AndroidSchedulers.mainThread())
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
