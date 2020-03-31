package com.inke.zcl.learnrxjava.view.anim_view;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.inke.zcl.firstmode.Reflect.ReflectHelper;
import com.inke.zcl.learnrxjava.R;
import com.inke.zcl.learnrxjava.rx.RxAdvancedOperator;
import com.inke.zcl.learnrxjava.rx.RxOperator;
import com.inke.zcl.learnrxjava.rx.RxScheduler;
import com.inke.zcl.learnrxjava.view.CustomFatherView;
import com.zcl.jarlib.MyJarUtils;

/**
 * Create By chunliangzhang on 2019-09-29
 * Version 1.0
 * Description: 向上滑动的自定义View
 */
public class CustomViewFlipper extends CustomFatherView {
    private ViewFlipper viewFlipper;
    private TextView tv_view_flipper;
    private TextView view_flipper_canzhao;
    String[] viewFlipperString;
    private boolean switchText=true;
    private boolean switchCanzhao=true;

    public CustomViewFlipper(@NonNull Context context) {
        super(context);
    }

    public CustomViewFlipper(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.custom_view_flipper;
    }

    @Override
    protected void init() {
        viewFlipper = findViewById(R.id.viewFlipper);
        tv_view_flipper = findViewById(R.id.view_flipper);
        view_flipper_canzhao = findViewById(R.id.view_flipper_canzhao);
        viewFlipperString = new String[]{"1", "2", "3"};
        initData();
        tv_view_flipper.setOnClickListener(this::text);
        view_flipper_canzhao.setOnClickListener(this::onClickCanzhao);
    }




    private void onClickCanzhao(View view) {
        if (switchCanzhao) {
            tv_view_flipper.setAlpha(0);
            tv_view_flipper.setClickable(false);
            switchCanzhao = false;
        } else {
            tv_view_flipper.setClickable(true);
            tv_view_flipper.setAlpha(1);
            switchCanzhao = true;
        }
    }

    private void text(View view) {
        Toast.makeText(getContext(), "clickThis", Toast.LENGTH_SHORT).show();
//        MyJarUtils utils = new MyJarUtils();
////        RxScheduler.mainScheduler();
//        RxAdvancedOperator.mainAdvancedOperator();
        if (switchText) {
            ObjectAnimator yCountDown = ObjectAnimator.ofFloat(tv_view_flipper, TRANSLATION_Y, 0, -100);
            yCountDown.setDuration(200);
            yCountDown.start();
            switchText = false;
        } else {
            ObjectAnimator yCountDown = ObjectAnimator.ofFloat(tv_view_flipper, TRANSLATION_Y, 0, 100);
            yCountDown.setDuration(200);
            yCountDown.start();
            switchText = true;
        }
    }

    private void initData() {
        for (int i = 0; i < 3; i++) {
            View view = LayoutInflater.from(getContext()).inflate(R.layout.custom_view_flipper_item, null);
            TextView textView = view.findViewById(R.id.tv_camera);
            textView.setText(viewFlipperString[i]);
            viewFlipper.addView(view);

            /*view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("Guanggao", "点击了" + i);
                }
            });*/
        }
        viewFlipper.setFlipInterval(2000);
        viewFlipper.startFlipping();

    }



    @Override
    protected void callOnClick(View v) {

    }

    public interface IViewFlipperInitView{
        void initViewFlipperItem();
    }
}
