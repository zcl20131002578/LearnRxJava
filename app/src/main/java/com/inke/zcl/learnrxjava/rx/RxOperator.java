package com.inke.zcl.learnrxjava.rx;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func0;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Create By chunliangzhang on 2019-11-18
 * Version 1.0
 * Description:
 * 本章介绍了多个创建操作符，它们用于创建被观察者，所以创建操作符是Rx.Java 比较基础的操作符。
 */
public class RxOperator {

    public static final String TAG = "RxOperator";

    public static void mainOper() {
        interval();
        timer();
    }

    private static void timer() {
        Observable.timer(5, TimeUnit.SECONDS).subscribe(new Action1<Long>() {
            @Override
            public void call(Long aLong) {
                Log.d(TAG, " aLong: " + aLong);
            }
        });
    }

    private static void interval() {
        Observable.interval(10, 1, TimeUnit.SECONDS,
                Schedulers.computation()).subscribe(new Action1<Long>() {
            @Override
            public void call(Long aLong) {
                Log.d(TAG, "aLong_" + aLong);
            }
        });
    }

    private static void defer() {
        Observable deferObservable = Observable.defer(new Func0<Observable<String>>() {
            @Override
            public Observable<String> call() {
                Log.d(TAG, " Observable<String> to generate Observable");
                return Observable.create(new Observable.OnSubscribe<String>() {
                    @Override
                    public void call(Subscriber<? super String> subscriber) {
                        Log.d(TAG, "Observable.create call OnSubscribe ");
                        subscriber.onNext("111");
                    }
                });
            }
        });
        Log.d(TAG, "mainOper: start subscribe");
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                deferObservable.subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        Log.d(TAG, "new Action1<String> call: " + s);
                    }
                });
            }
        }, 5000);
    }

    private static void repeat() {
        repeatIndefinitely();

        repeatWithTimes(4);

        repeatWhen();
    }

    private static void repeatWhen() {
        Observable.range(0, 8).repeatWhen(new Func1<Observable<? extends Void>, Observable<?>>() {
            @Override
            public Observable<?> call(Observable<? extends Void> observable) {
                System.out.println("tenggee " + "Observable");
                return Observable.timer(5, TimeUnit.SECONDS);
            }
        }).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                System.out.println("tenggee " + integer);
            }
        });
    }

    private static void repeatWithTimes(long count) {
        Observable.just("repeat").repeat(count).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {

            }
        });
    }

    private static void repeatIndefinitely() {
        Observable.just("repeat").repeat().subscribe(new Action1<String>() {
            @Override
            public void call(String s) {

            }
        });
    }


    private static void from() {
        fromIterable();

        fromFutureWithOutTime();

        fromFuture();
    }

    private static void fromFuture() {
        Future<String> future = getStringFuture();
        Observable.from(future).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {

            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {

            }
        });
    }

    private static void fromFutureWithOutTime() {
        Future<String> future = getStringFuture();
        Observable.from(future, 4, TimeUnit.SECONDS).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {

            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {

            }
        });
    }

    private static Future<String> getStringFuture() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        return executorService.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                Log.d(TAG, "call: 模拟一些耗时任务");
                Thread.sleep(5000);
                return "hello world";
            }
        });
    }

    private static void fromIterable() {
        //Collection<E> extends Iterable<E>
        ArrayList<String> stringArrayList = new ArrayList<>();
        Observable.from(stringArrayList).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {

            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {

            }
        });
    }

    private static void just() {
        justList();

        justSingleObject();
    }

    private static void justSingleObject() {
        Observable.just("hello world", "1", "2").subscribe(new Action1<String>() {
            @Override
            public void call(String s) {

            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {

            }
        }, new Action0() {
            @Override
            public void call() {

            }
        });
    }

    private static void justList() {
        ArrayList<String> stringArrayList = new ArrayList<>();
        Observable.just(stringArrayList).subscribe(new Action1<ArrayList<String>>() {
            @Override
            public void call(ArrayList<String> strings) {

            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {

            }
        });
    }

    private static void create() {
        //Observable 被观察者 //Subscriber 观察者
        Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> observer) {
                try {
                    if (!observer.isUnsubscribed()) {
                        for (int i = 1; i < 5; i++) {
                            observer.onNext(i);
                        }
                        observer.onCompleted();
                    }
                } catch (Exception e) {
                    observer.onError(e);
                }
            }
        }).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                Log.d(TAG, "call: on next");
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                Log.d(TAG, "call: on error");
            }
        }, new Action0() {
            @Override
            public void call() {
                Log.d(TAG, "call: on Complete");
            }
        });
    }


}
