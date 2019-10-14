package com.inke.zcl.learnrxjava.view.anim_view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.inke.zcl.learnrxjava.R;
import com.inke.zcl.learnrxjava.view.CustomFatherView;

/**
 * Create By chunliangzhang on 2019-09-29
 * Version 1.0
 * Description: 向上滑动的自定义View
 */
public class viewFlipper extends CustomFatherView {

    public viewFlipper(@NonNull Context context) {
        super(context);
    }

    public viewFlipper(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.custom_view_flipper;
    }

    @Override
    protected void init() {
        ImageView iv_camera = findViewById(R.id.iv_camera);
        iv_camera.setImageDrawable(getContext().getResources().getDrawable(R.drawable.bank_camera));
    }

    @Override
    protected void callOnClick(View v) {

    }
}
