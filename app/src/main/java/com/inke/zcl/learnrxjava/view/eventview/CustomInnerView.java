package com.inke.zcl.learnrxjava.view.eventview;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.inke.zcl.learnrxjava.R;
import com.inke.zcl.learnrxjava.utils.ToastUtils;
import com.inke.zcl.learnrxjava.view.CustomFatherView;

/**
 * Create By chunliangzhang on 2019/4/28
 * Version 1.0
 * Description: 事件分发的子类
 */
public class CustomInnerView extends CustomFatherView {


    public CustomInnerView(@NonNull Context context) {
        super(context);
    }

    public CustomInnerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.custom_inner_view;
    }

    @Override
    protected void init() {
        // TODO: ZCL 2019/4/28 在merge里面设置背景色无效,设置ID无效
        setBackgroundColor(getResources().getColor(R.color.colorAccent));
        setOnClickListener(this::callOnClick);
    }

    @Override
    protected void callOnClick(View v) {
        Log.d(TAG, "callOnClick: CustomInnerView");
        ToastUtils.showToast(getContext(), "callOnClick: CustomInnerView");
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        boolean isDispatch = requestNotInterceptFromBottom(ev);
        Log.d(TAG, "CustomInnerView dispatchTouchEvent: isDispatch: " + isDispatch);
        return isDispatch;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean isIntercept = super.onInterceptTouchEvent(ev);
        Log.d(TAG, "CustomInnerView onInterceptTouchEvent: isIntercept: " + isIntercept);
        return isIntercept;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean isTouch = super.onTouchEvent(event);
        Log.d(TAG, "CustomInnerView onTouchEvent isTouch: " + isTouch);
        return isTouch;
    }


    private boolean requestNotInterceptFromBottom(MotionEvent ev) {
        //***********************************子类要求不拦截分发***********************************//
        int x = (int) ev.getX();
        int y = (int) ev.getY();
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                //ZCL 2019/4/28 这个函数调用的地方是？（这个地方设置会被清掉？）
                /**
                 * A:此处的时机是在父类接受到MotionEvent.ACTION_DOWN事件之后，父类的MotionEvent.ACTION_MOVE之前
                 * 因为这个FLAG每次在父类新接收到MotionEvent.ACTION_DOWN事件的时候都会清掉，所以设置这个FLAG对父类无效。
                 * 为了子类能要求父类不拦截对应的MotionEvent.ACTION_DOWN事件，要求父类不能拦截MotionEvent.ACTION_DOWN事件
                 * 此处的意义是拦截父类的MotionEvent.ACTION_MOVE
                 */
                getParent().requestDisallowInterceptTouchEvent(true);
                break;
            case MotionEvent.ACTION_MOVE:
                if (allowParentIntercept()) {
                    getParent().requestDisallowInterceptTouchEvent(false);
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

    /**
     * 允许父类拦截事件
     */
    public boolean allowParentIntercept() {
        return true;
    }

}
