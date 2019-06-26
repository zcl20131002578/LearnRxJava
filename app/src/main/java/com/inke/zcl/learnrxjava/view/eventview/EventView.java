package com.inke.zcl.learnrxjava.view.eventview;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.inke.zcl.learnrxjava.R;
import com.inke.zcl.learnrxjava.utils.ToastUtils;
import com.inke.zcl.learnrxjava.view.CustomFatherView;

/**
 * Create By chunliangzhang on 2019/1/9
 * Version 1.0
 * Description:
 */
public class EventView extends CustomFatherView {

    // TODO: ZCL 2019/4/28 父类onInterceptTouchEvent 返回true，但是并未触发随后的点击事件
    /**
     * Q:父类onInterceptTouchEvent 返回true，但是并未触发随后的点击事件
     * A:因为父类都没有拦截对应的点击事件,所以点击事件是由最底层的View消费,父类拦截了MOVE及以后事件,但是没有消费对应的DOWN事件，所以不算一个点击事件。子类消费了点击事件，但是未消费后续事件，所以也未触发点击事件
     */


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
        event_root_view = findViewById(R.id.event_root_view);//父类 蓝色
        event_center = findViewById(R.id.event_center);//中间 绿色
        event_top = findViewById(R.id.event_top);//最上层 灰色
        event_root_view.setOnClickListener(this::callOnClick);
        event_center.setOnClickListener(this::callOnClick);
        event_top.setOnClickListener(this::callOnClick);
        this.setOnClickListener(this::ClickRoot);
    }

    private void ClickRoot(View view) {
        Log.d(TAG, "callOnClick: ClickRoot");
        ToastUtils.showToast(getContext(), "callOnClick: EventView");
    }


    @Override
    protected void callOnClick(View v) {
        switch (v.getId()) {
            case R.id.event_root_view:
                Log.d(TAG, "callOnClick: event_root_view");
                ToastUtils.showToast(getContext(), "callOnClick: event_root_view");
                break;
            case R.id.event_center:
                Log.d(TAG, "callOnClick: event_center");
                ToastUtils.showToast(getContext(), "callOnClick: event_center");
                break;
            case R.id.event_top:
                Log.d(TAG, "callOnClick: event_top");
                ToastUtils.showToast(getContext(), "callOnClick: event_top");
                break;
            default:
                break;
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {//first
        boolean isDispatch = super.dispatchTouchEvent(ev);
        Log.d(TAG, "EventView dispatchTouchEvent: isDispatch: " + isDispatch);
        return isDispatch;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {// second
        boolean isIntercept = interceptFromTop(ev);
        Log.d(TAG, "EventView onInterceptTouchEvent: isIntercept: " + isIntercept);
        return isIntercept;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean isTouch = super.onTouchEvent(event);
        Log.d(TAG, "EventView onTouchEvent: isTouch: " + isTouch);
        return isTouch;
    }
    //**********************************************************************//

    /**
     * case1 :使用requestDisallowInterceptTouchEvent不允许父类拦截，但是父类在拦截里面依然拦截
     * 结果：父类拦截失效
     * case2 : 允许父类拦截，父类里面依然拦截
     * 结果：父类拦截生效
     */

    //**********************************************************************//

    /**
     * @return
     */
    public boolean ownNeedIntercept() {
        return true;
    }

    private boolean interceptFromTop(MotionEvent ev) {
        //***********************************父类拦截***********************************//
        boolean isIntercept = false;
        int x = (int) ev.getX();
        int y = (int) ev.getY();
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                //父类不能拦截，如果父类拦截了down事件，事件就完全无法传递到子类里面去
                isIntercept = false;
                break;
            case MotionEvent.ACTION_MOVE:
                isIntercept = ownNeedIntercept();
                break;
            case MotionEvent.ACTION_UP:
                isIntercept = false;
                // TODO: ZCL 2019/4/28
                /**
                 * 以下两种条件（在MotionEvent.ACTION_UP中isIntercept = true）
                 * Q1:为什么在这个地方MotionEvent.ACTION_UP，拦截了子类的MotionEvent.ACTION_UP事件，但是自身的点击事件也并未触发？
                 * Q2:为什么在此处添加了MotionEvent.ACTION_UP拦截了子类的事件，但是在不产生事件分发的空白区域，可以触发点击事件
                 */
                break;
            default:
                break;
        }
        mLastX = x;
        mLastY = y;
        return isIntercept;
    }


}
