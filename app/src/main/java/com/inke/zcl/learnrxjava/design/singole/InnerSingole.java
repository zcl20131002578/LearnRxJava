package com.inke.zcl.learnrxjava.design.singole;

/**
 * Create By chunliangzhang on 2020-03-31
 * Version 1.0
 * Description:
 */
public class InnerSingole {

    /**
     * 1: 加载一个类的时候内部类不会被同时加载，所以属于懒汉一类
     * 2: 类由JVM加载，只会加载一次
     */
    private InnerSingole() {

    }

    public static InnerSingole getIns() {
        return InnerSingoleHolder.ins;
    }

    private static class InnerSingoleHolder {
        private final static InnerSingole ins = new InnerSingole();
    }
}
