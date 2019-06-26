package com.inke.zcl.learnrxjava.view.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.inke.zcl.learnrxjava.R;
import com.inke.zcl.learnrxjava.view.CustomFatherView;

public class CustomMove extends CustomFatherView {

    public static final String TAG = "CustomMove";
    float lastX = 0;
    float lastY = 0;
    private TextView move_me_text;

    public CustomMove(Context context) {
        super(context);
        init();
    }

    public CustomMove(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.custom_move;
    }

    @Override
    protected void init() {
        move_me_text = findViewById(R.id.move_me_text);
        move_me_text.setOnClickListener(this::callOnClick);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getRawX();
        float y = event.getRawY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                float moveTranslationX = x - lastX;
                float moveTranslationY = y - lastY;
                float currentTranslationX = getTranslationX() + moveTranslationX;
                float currentTranslationY = getTranslationY() + moveTranslationY;
                setTranslationX(currentTranslationX);
                setTranslationY(currentTranslationY);
                Log.d(TAG, "onTouchEvent: currentTranslationX: " + currentTranslationX
                        + " " + "currentTranslationY: " + currentTranslationY);
                break;

            case MotionEvent.ACTION_UP:
                break;
            default:
                break;
        }
        lastX = x;
        lastY = y;
        return true;
    }

    @Override
    protected void callOnClick(View v) {

    }

}
