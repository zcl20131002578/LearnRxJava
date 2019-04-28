package com.inke.zcl.learnrxjava.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Create By chunliangzhang on 2019/4/28
 * Version 1.0
 * Description:
 */
public class ToastUtils {

    public static void showToast(Context context, String toast) {
        Toast.makeText(context, toast, Toast.LENGTH_SHORT).show();
    }
}
