package com.inke.zcl.learnrxjava.rx;

import android.util.Log;


import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.functions.Action0;
import rx.functions.Action1;

/**
 * Create By chunliangzhang on 2019-09-29
 * Version 1.0
 * Description:
 */
public class RxHelloWorld {

    public static final String TAG = "RxHelloWorld";

    private static final RxHelloWorld outInstance = new RxHelloWorld();

    private RxHelloWorld() {
    }

    public static RxHelloWorld getInstance() {
        return outInstance;
    }


    public void mainStart(){
        Observable.just("hello world").doOnNext(new Action1<String>() {
            @Override
            public void call(String s) {
                Log.d(TAG, "call: doOnNext");
            }
        }).doOnCompleted(new Action0() {
            @Override
            public void call() {
                Log.d(TAG, "call: doOnCompleted");
            }
        }).subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {
                Log.d(TAG, "onCompleted: ");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                Log.d(TAG, "onNext: ");
            }
        });
    }

}
