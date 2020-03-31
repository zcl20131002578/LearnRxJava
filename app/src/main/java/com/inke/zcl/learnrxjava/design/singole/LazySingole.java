package com.inke.zcl.learnrxjava.design.singole;

/**
 * Create By chunliangzhang on 2020-03-19
 * Version 1.0
 * Description:
 */
public class LazySingole {
    private static LazySingole lazySingole;

    private LazySingole() {

    }

    /*
     * 1:第一次调用的时候需要进行实例化
     * 2: 每次调用的时候都需要进行同步，造成不必要的开销
     */
    public static synchronized LazySingole getInstance() {
        if (lazySingole == null) {
            lazySingole = new LazySingole();
        }
        return lazySingole;
    }
}
