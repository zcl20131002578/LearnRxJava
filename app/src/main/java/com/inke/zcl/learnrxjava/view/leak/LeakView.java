package com.inke.zcl.learnrxjava.view.leak;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import com.inke.zcl.learnrxjava.R;
import com.inke.zcl.learnrxjava.activity.LeakActivity;
import com.inke.zcl.learnrxjava.utils.ContextCompat;
import com.inke.zcl.learnrxjava.view.CustomFatherView;

/**
 * Create By chunliangzhang on 2019-06-25
 * Version 1.0
 * Description: 测试内存泄漏View
 */
public class LeakView extends CustomFatherView {

    TextView leakText;

    public LeakView(@NonNull Context context) {
        super(context);
    }

    public LeakView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.custom_leak_layout;
    }

    @Override
    protected void init() {
        leakText = findViewById(R.id.text_leak);
        leakText.setOnClickListener(this::callOnClick);
    }

    @Override
    protected void callOnClick(View v) {
        switch (v.getId()) {
            case R.id.text_leak:
                Intent intent = new Intent(getContext(), LeakActivity.class);
                ContextCompat.startActivity(getContext(), intent);
                break;
            default:
                break;
        }
    }
}
