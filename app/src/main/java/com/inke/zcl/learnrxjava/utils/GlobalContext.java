package com.inke.zcl.learnrxjava.utils;

public class GlobalContext {

    private static final GlobalContext outInstance = new GlobalContext();

    private GlobalContext() {
    }

    public static GlobalContext getInstance() {
        return outInstance;
    }

}
