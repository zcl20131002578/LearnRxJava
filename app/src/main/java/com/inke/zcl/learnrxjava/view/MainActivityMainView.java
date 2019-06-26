package com.inke.zcl.learnrxjava.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.inke.zcl.learnrxjava.R;

/**
 * 主界面的主入口管理类
 */
public class MainActivityMainView extends CustomFatherView {


    public MainActivityMainView(@NonNull Context context) {
        super(context);
    }

    public MainActivityMainView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main_view;
    }

    @Override
    protected void init() {

    }

    @Override
    protected void callOnClick(View v) {

    }


}
