package com.inke.zcl.learnrxjava.view.window;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.inke.zcl.learnrxjava.R;
import com.inke.zcl.learnrxjava.view.CustomFatherView;

/**
 * Create By chunliangzhang on 2019/5/14
 * Version 1.0
 * Description:
 */
public class WindowManagerView extends CustomFatherView {

    WindowManager.LayoutParams layoutParams;
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
        Button button = new Button(getContext());
        button.setText("button");
        layoutParams = new WindowManager.LayoutParams();
    }

    @Override
    protected void callOnClick(View v) {

    }
}
