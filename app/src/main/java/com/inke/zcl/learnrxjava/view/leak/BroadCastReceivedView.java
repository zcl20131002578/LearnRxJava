package com.inke.zcl.learnrxjava.view.leak;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import android.content.IntentFilter;
import android.graphics.Color;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.BackgroundColorSpan;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.TextAppearanceSpan;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.inke.zcl.learnrxjava.R;
import com.inke.zcl.learnrxjava.activity.MainActivity;
import com.inke.zcl.learnrxjava.view.CustomFatherView;
import com.inke.zcl.learnrxjava.view.utils.VerticalCenterSpan;

/**
 * Create By chunliangzhang on 2019-06-25
 * Version 1.0
 * Description: 测试内存泄漏View
 */
public class BroadCastReceivedView extends CustomFatherView {

    TextView clickMe;
    public static final String INTENT_FILTER_CHANGE_ACTION = "intent_filter_change_action";
    private MyBroadCastReceived broadCastReceived;

    public BroadCastReceivedView(@NonNull Context context) {
        super(context);
    }

    public BroadCastReceivedView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.broadcast_received_view;
    }

    @SuppressLint("NewApi")
    @Override
    protected void init() {
        clickMe = findViewById(R.id.click_me);
        clickMe.setOnClickListener(this::callOnClick);

    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        broadCastReceived = new MyBroadCastReceived();
        // TODO: ZCL 2019-09-18 如果传入的 new IntentFilter()，则接收不到任何intent
        LocalBroadcastManager.getInstance(getContext()).registerReceiver(broadCastReceived, new IntentFilter());
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        LocalBroadcastManager.getInstance(getContext()).unregisterReceiver(broadCastReceived);
    }

    @Override
    protected void callOnClick(View v) {
        switch (v.getId()) {
            case R.id.click_me:
                Log.d(TAG, "callOnClick: text_leak");
                LocalBroadcastManager.getInstance(getContext()).sendBroadcast(new Intent(INTENT_FILTER_CHANGE_ACTION));
                LocalBroadcastManager.getInstance(getContext()).sendBroadcast(new Intent("ERROR"));
                LocalBroadcastManager.getInstance(getContext()).sendBroadcast(new Intent());
                break;
            default:
                break;
        }
    }

    private class MyBroadCastReceived extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d(TAG, "onReceive: " + intent.getAction());
            if (INTENT_FILTER_CHANGE_ACTION.equals(intent.getAction())) {
                Log.d(TAG, "onReceive: equalAction");
            }
        }
    }
}
