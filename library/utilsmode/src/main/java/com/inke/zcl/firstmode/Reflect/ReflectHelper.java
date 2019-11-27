package com.inke.zcl.firstmode.Reflect;

import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.widget.Toast;

import java.lang.reflect.Field;

/**
 * Create By chunliangzhang on 2019-11-27
 * Version 1.0
 * Description:
 */
public class ReflectHelper {


    public static void mainReflect(Context context) {
        Toast toast = Toast.makeText(context, "default", Toast.LENGTH_SHORT);
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.N_MR1) {
            try {
                Field tnField = Toast.class.getDeclaredField("mTN");
                tnField.setAccessible(true);
                Object mTN = tnField.get(toast);

                Field handlerField = mTN.getClass().getDeclaredField("mHandler");
                handlerField.setAccessible(true);
                Handler handlerOfTN = (Handler) handlerField.get(mTN);

                Handler safeHandler = new SafeHandler(handlerOfTN);
                handlerField.set(mTN, safeHandler);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
//        try {
//            Method method = Toast.class.getDeclaredMethod("handleShow", IBinder.class);
//        } catch (NoSuchMethodException | WindowManager.BadTokenException e) {
//            e.printStackTrace();
//        }
        toast.show();
    }
}
