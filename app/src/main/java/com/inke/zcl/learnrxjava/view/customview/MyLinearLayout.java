package com.inke.zcl.learnrxjava.view.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.inke.zcl.learnrxjava.R;
import com.inke.zcl.learnrxjava.view.CustomFatherView;

public class MyLinearLayout extends CustomFatherView {

    public MyLinearLayout(Context context) {
        super(context);
        init();
    }

    public MyLinearLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.my_linearlayout;
    }

    @Override
    protected void init() {

    }

    @Override
    protected void callOnClick(View v) {

    }

}
