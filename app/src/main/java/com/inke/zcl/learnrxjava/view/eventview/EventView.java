package com.inke.zcl.learnrxjava.view.eventview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

import com.inke.zcl.learnrxjava.R;
import com.inke.zcl.learnrxjava.view.CustomFatherView;

/**
 * Create By chunliangzhang on 2019/1/9
 * Version 1.0
 * Description:
 */
public class EventView extends CustomFatherView {

    private FrameLayout event_root_view;
    private View event_center;
    private View event_top;
    private int mLastX;
    private int mLastY;

    public EventView(@NonNull Context context) {
        super(context);
    }

    public EventView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.event_view;
    }

    @Override
    protected void init() {
        event_root_view = findViewById(R.id.event_root_view);
        event_center = findViewById(R.id.event_center);
        event_top = findViewById(R.id.event_top);
        event_root_view.setOnClickListener(this::callOnClick);
        event_center.setOnClickListener(this::callOnClick);
        event_top.setOnClickListener(this::callOnClick);
    }

    @Override
    protected void callOnClick(View v) {
        switch (v.getId()) {
            case R.id.event_root_view:
                Log.d(TAG, "callOnClick: event_root_view");
                break;
            case R.id.event_center:
                Log.d(TAG, "callOnClick: event_center");
                break;
            case R.id.event_top:
                Log.d(TAG, "callOnClick: event_top");
                break;
            default:
                break;
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.d(TAG, "dispatchTouchEvent: ");
        return requestNotInterceptFromBottom(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.d(TAG, "onInterceptTouchEvent: ");
        return intercepteFromTop(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d(TAG, "onTouchEvent: ");
        return super.onTouchEvent(event);
    }

    public boolean ownNeedIntercept() {
        return true;
    }

    public boolean parentNeedIntercept() {
        return true;
    }


    private boolean intercepteFromTop(MotionEvent ev) {
        //***********************************父类拦截***********************************//
        boolean isIntercept = false;
        int x = (int) ev.getX();
        int y = (int) ev.getY();
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                isIntercept = false;
                break;
            case MotionEvent.ACTION_MOVE:
                isIntercept = ownNeedIntercept();
                break;
            case MotionEvent.ACTION_UP:
                isIntercept = false;
                break;
            default:
                break;
        }
        mLastX = x;
        mLastY = y;
        return isIntercept;
    }

    private boolean requestNotInterceptFromBottom(MotionEvent ev) {
        //***********************************子类要求不拦截分发***********************************//
        int x = (int) ev.getX();
        int y = (int) ev.getY();
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                //不需要父类拦截，如果父类拦截了down事件，子类就无法处理此事件
                getParent().requestDisallowInterceptTouchEvent(false);
                break;
            case MotionEvent.ACTION_MOVE:
                if (parentNeedIntercept()) {
                    getParent().requestDisallowInterceptTouchEvent(true);
                }
                break;
            case MotionEvent.ACTION_UP:
                //
                break;
            default:
                break;
        }
        return super.dispatchTouchEvent(ev);
    }
}
