package com.inke.zcl.learnrxjava.view.anim_view.coldDown;

import android.content.Context;

import java.text.DecimalFormat;

/**
 * Created by Administrator on 2018/3/9.
 */

public class Utils {
    public static String transferTime(int time) {
        DecimalFormat df = new DecimalFormat("#00");
        String string = "";
        if (time / 60 > 0) {
            string = String.format("%s:%s", df.format(time / 60),
                    df.format(time % 60));
        } else {
            string = String.format("%s:%s", df.format(0), df.format(time % 60));
        }
        return string;
    }

    /**
     * 获取运行时信息
     *
     * @return
     */
    public static String getFunctionName(Context context, String errorMsg) {
        StackTraceElement[] sts = Thread.currentThread().getStackTrace();
        if (sts == null) {
            return null;
        }
        for (StackTraceElement st : sts) {
            if (st.isNativeMethod()) {
                continue;
            }
            if (st.getClassName().equals(Thread.class.getName())) {
                continue;
            }
            if (st.getClassName().equals(context.getClass().getName())) {
                continue;
            }
            return "[ " + Thread.currentThread().getName() + "Thread: "
                    + st.getFileName() + ":" + st.getMethodName() + ":"
                    + st.getLineNumber() + ":" + errorMsg + " ]";
        }
        return null;
    }
}
