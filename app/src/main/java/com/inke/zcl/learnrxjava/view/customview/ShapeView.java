package com.inke.zcl.learnrxjava.view.customview;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.inke.zcl.learnrxjava.R;
import com.inke.zcl.learnrxjava.view.CustomFatherView;

import java.text.MessageFormat;

/**
 * Create By chunliangzhang on 2019-09-09
 * Version 1.0
 * Description:
 */
public class ShapeView extends CustomFatherView {

    private TextView tv_shape;
    private Handler handler;
    private boolean isFans = true;
    private TextView tv_top;
    private TextView tv_bottom;
    public static final int ANIM_DURATION = 300;
    private int fans_num;
    private int guard_num;


    public ShapeView(@NonNull Context context) {
        super(context);
    }

    public ShapeView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.shape_view_layout;
    }

    @Override
    protected void init() {
        tv_shape = findViewById(R.id.shape_text);
        tv_top = findViewById(R.id.tv_top);
        tv_bottom = findViewById(R.id.tv_bottom);
        tv_top.setText("真爱团人数**人");
        handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(new RotateTaskRunable(), 2000);
    }

    @Override
    protected void callOnClick(View v) {


    }

    public class RotateTaskRunable implements Runnable {

        @Override
        public void run() {
            isFans = !isFans;
            setTv_shape(isFans);
            doAnim();

        }
    }

    public void doAnim() {
        {
            int height = tv_top.getMeasuredHeight();
            PropertyValuesHolder alpha = PropertyValuesHolder.ofFloat("alpha", 1, 0.9f, 0.7f, 0);
            PropertyValuesHolder translateY = PropertyValuesHolder.ofFloat("translationY", 0, -height);
            PropertyValuesHolder rotate = PropertyValuesHolder.ofFloat("rotationX", 0, 90);
            ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(tv_top, alpha, translateY, rotate);
            animator.setDuration(ANIM_DURATION);
            animator.start();
        }
        {
            int height = tv_bottom.getMeasuredHeight();
            PropertyValuesHolder alpha = PropertyValuesHolder.ofFloat("alpha", 0.8f, 1);
            PropertyValuesHolder translateY = PropertyValuesHolder.ofFloat("translationY", height, 0);
            PropertyValuesHolder rotate = PropertyValuesHolder.ofFloat("rotationX", -90, 0);
            ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(tv_bottom, alpha, translateY, rotate);
            animator.setDuration(ANIM_DURATION);
            animator.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    //循环
                    handler.postDelayed(new RotateTaskRunable(), 2000);
                }
            });
            animator.start();
        }
    }

    public void setTv_shape(boolean isFans) {
        String fan = MessageFormat.format(getContext().getResources().getString(R.string.room_fans_num), fans_num);
        String guard = MessageFormat.format(getContext().getResources().getString(R.string.room_guard_num), guard_num);
        tv_top.setText(isFans ? fan : guard);
        tv_bottom.setText(isFans ? guard : fan);
    }
}
