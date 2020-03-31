package com.inke.zcl.learnrxjava.design.singole;

/**
 * Create By chunliangzhang on 2020-03-31
 * Version 1.0
 * Description:
 */
public class TwiceLockSingle {

    /**
     * volatile
     * 1: 可见性（指的是将该对象存放在主存，对于所有的线程都可见）
     * 2: 有序性 (编译器在编译的时候可能会对指令进行乱序执行)
     * {
     * 1: 分配堆上内存
     * 2: 在堆上生成对应类的对象
     * 3: 将地址赋值给单例对象
     * }
     */
    private static volatile TwiceLockSingle ins;

    private TwiceLockSingle() {

    }

    public static TwiceLockSingle getIns() {
        if (ins == null) {
            synchronized (TwiceLockSingle.class) {
                if (ins == null) {
                    ins = new TwiceLockSingle();
                }
            }
        }
        return ins;
    }
}
