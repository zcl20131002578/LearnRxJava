package com.inke.zcl.learnrxjava.view.anim_view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.alibaba.android.arouter.launcher.ARouter;
import com.inke.zcl.learnrxjava.R;
import com.inke.zcl.learnrxjava.activity.RouterConstans;
import com.inke.zcl.learnrxjava.design.javaoffer.QuestionFive;
import com.inke.zcl.learnrxjava.design.javaoffer.QuestionFour;
import com.inke.zcl.learnrxjava.design.javaoffer.QuestionSix;
import com.inke.zcl.learnrxjava.view.CustomFatherView;

/**
 * Create By chunliangzhang on 2019-09-29
 * Version 1.0
 * Description: 向上滑动的自定义View
 */
public class CustomViewFlipper extends CustomFatherView {


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
        findViewById(R.id.setG).setOnClickListener(this::callOnClick);
    }

    private void initData() {
//        for (int i = 0; i < 3; i++) {
//            View view = LayoutInflater.from(getContext()).inflate(R.layout.custom_view_flipper_item, null);
//            TextView textView = view.findViewById(R.id.tv_camera);
//            textView.setText(viewFlipperString[i]);
//            viewFlipper.addView(view);
//
//            /*view.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Log.d("Guanggao", "点击了" + i);
//                }
//            });*/
//        }
//        viewFlipper.setFlipInterval(2000);
//        viewFlipper.startFlipping();

    }


    @Override
    protected void callOnClick(View v) {

    }
}
