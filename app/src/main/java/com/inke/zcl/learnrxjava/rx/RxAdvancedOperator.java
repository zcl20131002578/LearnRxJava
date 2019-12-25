package com.inke.zcl.learnrxjava.rx;

import android.util.Log;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Create By chunliangzhang on 2019-11-28
 * Version 1.0
 * Description:
 */
public class RxAdvancedOperator {

    private static final String TAG = "RxAdvancedOperator";

    public static void mainAdvancedOperator() {
        Observable.from(new String[]{"aaa", "bbb", "ccc"}).map(new Func1<String, Integer>() {
            @Override
            public Integer call(String s) {
                return s.length();
            }
        }).concatMap(new Func1<Integer, Observable<String>>() {
            @Override
            public Observable<String> call(Integer integer) {
                return Observable.just(integer.toString());
            }
        }).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                Log.d(TAG, "call: s:" + s);
            }
        });
    }
}
