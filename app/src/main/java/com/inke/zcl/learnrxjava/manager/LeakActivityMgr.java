package com.inke.zcl.learnrxjava.manager;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Create By chunliangzhang on 2019-06-25
 * Version 1.0
 * Description:
 */
public class LeakActivityMgr {

    private static final LeakActivityMgr outInstance = new LeakActivityMgr();
    private List<Activity> activityList = new ArrayList<>();

    private LeakActivityMgr() {
    }

    public static LeakActivityMgr getInstance() {
        return outInstance;
    }

    public void addActivity(Activity activity) {
        activityList.add(activity);
    }

    public void removeActivity(Activity activity) {
        activityList.remove(activity);
    }
}
