package com.inke.zcl.learnrxjava.view.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.alibaba.android.arouter.launcher.ARouter;
import com.inke.zcl.learnrxjava.R;
import com.inke.zcl.learnrxjava.activity.RouterConstans;
import com.inke.zcl.learnrxjava.design.javaoffer.IQuestion;
import com.inke.zcl.learnrxjava.design.javaoffer.QuestionEight;
import com.inke.zcl.learnrxjava.view.CustomFatherView;

/**
 * Create By chunliangzhang on 2020-04-18
 * Version 1.0
 * Description:
 */
public class CustomTextView extends CustomFatherView {

    public CustomTextView(@NonNull Context context) {
        super(context);
    }

    public CustomTextView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.custom_feature_text_layout;
    }

    @Override
    protected void init() {
        findViewById(R.id.text_algorithm).setOnClickListener(this::callOnClick);
        findViewById(R.id.text_router).setOnClickListener(this::callOnClick);
    }

    @Override
    protected void callOnClick(View v) {
        switch (v.getId()) {
            case R.id.text_algorithm:
                textAlgorithm();
                break;
            case R.id.text_router:
                textAouter();
                break;
            default:
                break;
        }
    }

    private void textAlgorithm() {
        IQuestion question = new QuestionEight();
        question.main();
    }

    private void textAouter() {
        // 1. 应用内简单的跳转(通过URL跳转在'进阶用法'中)
//        ARouter.getInstance().build(RouterConstans.GradientProgressActivity).navigation();

        // 2. 跳转并携带参数
        ARouter.getInstance().build(RouterConstans.GradientProgressActivity)
                .withLong("key1", 666L)
                .withString("key3", "888")
                .navigation();
    }


}
