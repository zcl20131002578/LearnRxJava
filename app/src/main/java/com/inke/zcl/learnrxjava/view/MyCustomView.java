package com.inke.zcl.learnrxjava.view;

import android.content.Context;
import android.os.Looper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.inke.zcl.learnrxjava.R;

import java.util.logging.Logger;


public class MyCustomView extends LinearLayout implements GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener {

    public static final String TAG = "MyCustomView";
    private GestureDetector gestureDetector;
    private TextView text_view;

    public MyCustomView(Context context) {
        super(context);
        init();
    }

    public MyCustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.my_custom_view, this);
        gestureDetector = new GestureDetector(getContext(), this);
        text_view = findViewById(R.id.text_view);
        text_view.setOnClickListener(this::callOnClick);
//        gestureDetector.setIsLongpressEnabled(false);

    }

    private void callOnClick(View view) {
        Log.d(TAG, "callOnClick: ");
        Log.d(TAG, "text_view:" + "scrolledX:" + text_view.getScrollX() + " scrolledY:" + text_view.getScrollY());
        Log.d(TAG, "text_view:" + "l:" + text_view.getLeft() + " r:" + text_view.getRight() + " t:" + text_view.getTop() + " b:" + text_view.getBottom());
        Log.d(TAG, "text_view:" + "x:" + text_view.getX() + " y: " + text_view.getY() + " translationX: " + text_view.getTranslationX() + " translationY:" + text_view.getTranslationY());
    }

    //**********************************接管onTouchEvent方法************************************//
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return gestureDetector.onTouchEvent(event);
//        gestureDetector.onTouchEvent(event);
//        return true;
    }

    //********************************GestureDetector的回调**************************************//
    @Override
    public boolean onDown(MotionEvent e) {
        Log.d(TAG, "onDown: ");
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {
        Log.d(TAG, "onShowPress: ");
    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        Log.d(TAG, "onSingleTapUp: ");
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        Log.d(TAG, "onScroll: ");
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {
        Log.d(TAG, "onLongPress: ");
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        Log.d(TAG, "onFling: ");
        return false;
    }

    //***********************************OnDoubleTapListener的监听回调***********************************//
    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
        Log.d(TAG, "onSingleTapConfirmed: ");
        return false;
    }

    @Override
    public boolean onDoubleTap(MotionEvent e) {
        Log.d(TAG, "onDoubleTap: ");
        return false;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {
        Log.d(TAG, "onDoubleTapEvent: ");
        return false;
    }


    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        Log.d(TAG, "//*****************************START*****************************************//");
        Log.d(TAG, "onScrollChanged: l:" + l + " t:" + t + " oldl:" + oldl + " oldt:" + oldt);
        Log.d(TAG, "scrolledX:" + getScrollX() + " scrolledY:" + getScrollY());
        Log.d(TAG, "l:" + getLeft() + " r:" + getRight() + " t:" + getTop() + " b:" + getBottom());
        Log.d(TAG, "x:" + getX() + " y: " + getY() + " translationX: " + getTranslationX() + " translationY:" + getTranslationY());
        Log.d(TAG, "//**********************************************************************//");
        Log.d(TAG, "text_view: vis: " + (text_view.getVisibility() == View.VISIBLE));
        Log.d(TAG, "text_view:" + "scrolledX:" + text_view.getScrollX() + " scrolledY:" + text_view.getScrollY());
        Log.d(TAG, "text_view:" + "l:" + text_view.getLeft() + " r:" + text_view.getRight() + " t:" + text_view.getTop() + " b:" + text_view.getBottom());
        Log.d(TAG, "text_view:" + "x:" + text_view.getX() + " y: " + text_view.getY() + " translationX: " + text_view.getTranslationX() + " translationY:" + text_view.getTranslationY());
        Log.d(TAG, "//*****************************END*****************************************//");
    }
}
