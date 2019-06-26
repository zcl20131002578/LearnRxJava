package com.inke.zcl.learnrxjava.view.customview;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.inke.zcl.learnrxjava.R;
import com.inke.zcl.learnrxjava.view.CustomFatherView;

import static android.os.Looper.getMainLooper;


/**
 * 自定义View测试的主View
 */
public class CustomMainEntrance extends CustomFatherView {

    private CustomLocaView customLocaView;
    private int mT = 50;
    private Handler handler;

    public CustomMainEntrance(@NonNull Context context) {
        super(context);
    }

    public CustomMainEntrance(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.custom_main_entrance;
    }

    @Override
    protected void init() {
        customLocaView = findViewById(R.id.my_custom_view);
        handler = new Handler(getMainLooper());

        findViewById(R.id.click).setOnClickListener(this::callOnClick);
        findViewById(R.id._click).setOnClickListener(this::callOnClick);
    }

    @Override
    protected void callOnClick(View v) {
        switch (v.getId()) {
            case R.id.click:

                break;
            case R.id._click:

                break;
            default:
                break;
        }
    }

    //********************************CustomView Start**************************************//
    private void startScrollCustomView() {
        Log.d(TAG, "onClick scrolledX:" + customLocaView.getScrollX() + " scrolledY:" + customLocaView.getScrollY());
        mT += 10;
        customLocaView.scrollBy(mT, 0);
        Log.d(TAG, "onClick scrolledX:" + customLocaView.getScrollX() + " scrolledY:" + customLocaView.getScrollY());
    }

    private void startCustomViewAnim() {
        customLocaView.currentLocaInfo();
        Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.tanslation_x);
        customLocaView.startAnimation(animation);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                customLocaView.currentLocaInfo();
            }
        }, 500);
    }
    //**********************************CustomView End************************************//
}
