package com.inke.zcl.learnrxjava.rx;
import android.util.Log;
import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.observables.GroupedObservable;

/**
 * Create By chunliangzhang on 2019-11-28
 * Version 1.0
 * Description:
 */
public class RxAdvancedOperator {

    private static final String TAG = "RxAdvancedOperator";
    private static final String JI = "奇数组";
    private static final String OU = "偶数组";


    public static void mainAdvancedOperator() {
//        repeatWhen();
    }


    private static void repeatWhen() {
        final boolean[] result = {true};
        Observable.timer(1, TimeUnit.SECONDS).subscribe(new Action1<Long>() {
            @Override
            public void call(Long aLong) {
                result[0] = false;
            }
        });
        String[] aaa = new String[]{"1", "2", "3"};
        Observable.from(aaa).repeatWhen(new Func1<Observable<? extends Void>, Observable<?>>() {
            @Override
            public Observable<?> call(Observable<? extends Void> observable) {

//                return Observable.timer(3, TimeUnit.SECONDS);

                /**
                 * take While 发射原始的Observable ， 直到某个指定的条件不成立， 它会立即停止发射原始Observable ， 并终止自己的Observable 。
                 */
                return observable.takeWhile(new Func1<Void, Boolean>() {
                    public Boolean call(Void aVoid) {
                        return result[0];
                    }
                });
            }
        }).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                Log.d(TAG, "call: " + s);
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                Log.d(TAG, "call: " + throwable.toString());
            }
        }, new Action0() {
            @Override
            public void call() {
                Log.d(TAG, "call: " + "onComponent");
            }
        });
    }

    private static void repeat() {
        String[] aaa = new String[]{"1", "2", "3"};
        Observable.from(aaa).repeat(1).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                Log.d(TAG, "call: " + s);
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                Log.e(TAG, "call: " + throwable.toString());
            }
        }, new Action0() {
            @Override
            public void call() {
                Log.d(TAG, "call: " + "onComplete");
            }
        });
    }

    private static void window() {
        Observable.range(1, 10).window(2).subscribe(new Action1<Observable<Integer>>() {
            @Override
            public void call(Observable<Integer> integerObservable) {
                Log.d(TAG, "call: integerObservable");
                integerObservable.subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        Log.d(TAG, "call: integer" + integer);
                    }
                });
            }
        });
    }

    private static void bufferAndBufferSkip() {
        Observable.range(1, 11).buffer(2, 3).subscribe(new Action1<List<Integer>>() {
            @Override
            public void call(List<Integer> integers) {
                Log.d(TAG, "call: " + integers);
            }
        });
    }

    private static void groupBy() {
        Observable.range(1, 8).groupBy(new Func1<Integer, String>() {
            @Override
            public String call(Integer integer) {
                return (integer % 2 == 0) ? OU : JI;
            }
        }).subscribe(new Action1<GroupedObservable<String, Integer>>() {
            @Override
            public void call(GroupedObservable<String, Integer> stringIntegerGroupedObservable) {
                /**
                 * 将组别进行回调，回调次数等于组别个数
                 */
                Log.d(TAG, "group name: " + stringIntegerGroupedObservable.getKey());
                if (stringIntegerGroupedObservable.getKey().equals(JI)) {
                    stringIntegerGroupedObservable.subscribe(new Action1<Integer>() {
                        @Override
                        public void call(Integer integer) {
                            Log.d(TAG, "奇数组: " + integer);
                        }
                    });
                }
            }
        });
    }

    private static void order() {
        Observable.from(new String[]{"aaa"}).doOnNext(new Action1<String>() {
            @Override
            public void call(String s) {
                Log.d(TAG, "call: doOnNext " + s);
            }
        }).doOnError(new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                Log.d(TAG, "call: doOnError " + throwable.toString());
            }
        }).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                Log.d(TAG, "call: onNext " + s);
                throw new NullPointerException();
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                Log.d(TAG, "call: onError " + throwable.toString());
            }
        });
    }

    private static void switchMap() {
        Observable.from(new String[]{"aaa", "bbb", "ccc"}).switchMap(new Func1<String, Observable<String>>() {
            @Override
            public Observable<String> call(String s) {
                return Observable.just(s);
            }
        }).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                Log.d(TAG, "call: " + s);
            }
        });
    }

    private static void flatMap() {
        Observable.from(new String[]{"aaa", "bbb", "ccc"}).flatMap(new Func1<String, Observable<String>>() {

            @Override
            public Observable<String> call(String s) {
                return Observable.just(s);
            }
        }).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {

            }
        });
    }

    private static void contractMap() {
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
