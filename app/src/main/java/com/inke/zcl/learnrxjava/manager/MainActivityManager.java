package com.inke.zcl.learnrxjava.manager;

/**
 * MainActivity的辅助管理类
 */
public class MainActivityManager {

    private static final MainActivityManager outInstance = new MainActivityManager();

    private MainActivityManager() {
    }

    public static MainActivityManager getInstance() {
        return outInstance;
    }


}
