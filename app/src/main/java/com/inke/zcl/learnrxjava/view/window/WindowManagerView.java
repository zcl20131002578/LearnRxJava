package com.inke.zcl.learnrxjava.view.window;

import android.content.Context;
import android.graphics.PixelFormat;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.inke.zcl.learnrxjava.R;
import com.inke.zcl.learnrxjava.utils.AndroidUnit;
import com.inke.zcl.learnrxjava.utils.DisplayUtil;
import com.inke.zcl.learnrxjava.view.CustomFatherView;

/**
 * Create By chunliangzhang on 2019/5/14
 * Version 1.0
 * Description:
 */
public class WindowManagerView extends CustomFatherView {

    public static final String TAG = "WindowManagerView";
    WindowManager.LayoutParams layoutParams;
    private TextView textView;

    public WindowManagerView(@NonNull Context context) {
        super(context);
    }

    public WindowManagerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.custom_window_layout;
    }

    @Override
    protected void init() {
//        textView = findViewById(R.id.text);
//        textView.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                bottomRight();
//            }
//        });
//        Button button = new Button(getContext());
//        button.setText("一二三四五一二三四五");
//        layoutParams = new WindowManager.LayoutParams(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT, 0, 0, PixelFormat.TRANSPARENT);
//        layoutParams.setTitle("IconMan");
//        layoutParams.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
//                | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
//                | WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED;
//        layoutParams.gravity= Gravity.CENTER;
//        layoutParams.x=100;
//        layoutParams.y=300;
        int s1 = getScreenHeight(getContext(), true);
        int s2 = px2dip(getContext(), getScreenHeight(getContext()));
        Log.d(TAG, "init: s1:" + s1 + "  s2:" + s2);


    }


    public static int getScreenHeight(Context context, boolean isDp) {
        int screenHeight = 0;
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        if (wm != null) {
            Display defaultDisplay = wm.getDefaultDisplay();
            if (defaultDisplay != null) {
                defaultDisplay.getMetrics(dm);
            }
        }
        int height = dm.heightPixels;       // 屏幕高度（像素）

        if (!isDp) {
            return height;
        }

        float density = dm.density;         // 屏幕密度（0.75 / 1.0 / 1.5）
        if (density != 0) {
            screenHeight = (int) (height / density);// 屏幕高度(dp)
        }
        return screenHeight;
    }


    /**
     * 获取屏幕的高
     */
    public static int getScreenHeight(Context context) {
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        return dm.heightPixels;
    }

    /**
     * 将dip或dp值转换为px值，保证尺寸大小不变
     *
     * @param dipValue （DisplayMetrics类中属性density）
     * @return
     */
    public static int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    /**
     * 将px值转换为dip或dp值，保证尺寸大小不变
     *
     * @param pxValue （DisplayMetrics类中属性density）
     * @return
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }


    public void upperLeft() {
        Toast toast = Toast.makeText(getContext(), "Upper Left!", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.TOP | Gravity.START, 0, 0);
        toast.show();
    }

    public void upperRight() {
        Toast toast = Toast.makeText(getContext(), "Upper Right!", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.TOP | Gravity.END, 0, 0);
        toast.show();
    }

    public void bottomLeft() {
        Toast toast = Toast.makeText(getContext(), "Bottom Left!", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.BOTTOM | Gravity.START, 0, 0);
        toast.show();
    }

    public void bottomRight() {
        Toast toast = Toast.makeText(getContext(), "Bottom Right!", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.BOTTOM | Gravity.END, DisplayUtil.dip2px(getContext(), 0), DisplayUtil.dip2px(getContext(), 50));
        toast.show();
    }


    @Override
    protected void callOnClick(View v) {

    }
}
