package com.inke.zcl.learnrxjava.view;

import android.content.Context;


import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public abstract class CustomFatherView extends FrameLayout {

    public static final String TAG = "ZCLZCL";
    protected View currentRootView;

    public CustomFatherView(@NonNull Context context) {
        super(context);
        initView(context);
    }

    public CustomFatherView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    private void initView(Context context) {
        if (getLayoutId() <= 0) {
            Log.d(TAG, "initView: layoutID 非法");
            return;
        }
        try {
            currentRootView = LayoutInflater.from(context).inflate(getLayoutId(), this);
            init();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @return 获取对应View的Id
     */
    protected abstract int getLayoutId();

    /**
     * 对View进行初始化
     */
    protected abstract void init();

    /**
     * view的点击事件处理函数
     *
     * @param v 注册到本函数的v
     */
    protected abstract void callOnClick(View v);

}
