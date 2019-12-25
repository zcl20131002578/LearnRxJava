package com.inke.zcl.learnrxjava.view.anim_view.coldDown;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.inke.zcl.learnrxjava.R;
import com.inke.zcl.learnrxjava.view.CustomFatherView;

/**
 * Create By chunliangzhang on 2019-12-20
 * Version 1.0
 * Description:
 */
public class ColdDownTimerView extends CustomFatherView {

    private TextView countTimeTv;
    private ColdDownTimer coldDownTimer;

    public ColdDownTimerView(@NonNull Context context) {
        super(context);
    }

    public ColdDownTimerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.layout_cold_down_timer;
    }

    @Override
    protected void init() {
        countTimeTv = findViewById(R.id.countDownTime_tv);
        coldDownTimer = findViewById(R.id.coldDownTimer);
        coldDownTimer.setWaitHint("充能中...");// 冷却中提示
        coldDownTimer.setCountTime(10);// 总冷却时间
        coldDownTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                coldDownTimer.startCountdown();
            }
        });

        coldDownTimer.setOnCountDownTimeListener(new ColdDownTimer.CountDownTimeListener() {
            @Override
            public void getCurCountDownTime(int time) {
                if (time > 0) {
                    countTimeTv.setText(Utils.transferTime(time));
                    countTimeTv.setVisibility(View.VISIBLE);
                } else {
                    countTimeTv.setVisibility(View.GONE);
                }
            }

            @Override
            public void countDownFinish() {
                countTimeTv.setVisibility(View.GONE);
            }
        });
    }

    @Override
    protected void callOnClick(View v) {

    }
}
