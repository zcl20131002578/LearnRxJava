package com.inke.zcl.learnrxjava.design.singole;

/**
 * Create By chunliangzhang on 2020-03-19
 * Version 1.0
 * Description:
 */
public class HungrySingole {
    /*
    * 1:构造函数不对外开放，一般为private
    * 2:确保单例类对象在构造的时候不受多线程的影响
    * 3:通过一个静态方法返回单例对象
    * 4:
    */

    //饿汉式
    private static final HungrySingole outInstance = new HungrySingole();

    private HungrySingole() {
    }

    public static HungrySingole getInstance() {
        return outInstance;
    }

}
