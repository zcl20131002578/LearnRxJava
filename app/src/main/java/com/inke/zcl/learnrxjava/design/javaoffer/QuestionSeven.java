package com.inke.zcl.learnrxjava.design.javaoffer;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import java.util.Stack;

/**
 * Create By chunliangzhang on 2020-04-18
 * Version 1.0
 * Description:
 */
public class QuestionSeven implements IQuestion {

    private static final String TAG = "QuestionSeven";

    private Stack<Integer> stack1 = new Stack<>();//进
    private Stack<Integer> stack2 = new Stack<>();//出

    @Override
    public void main() {
        int[] strings = new int[]{1, 2, 3, 4, 5, 6};
        for (int s : strings) {
            push(s);
        }
        for (int i = 0; i < 4; i++) {
            Log.e(TAG, "main: " + pop());
        }
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                push(7);
                for (int i = 0; i < 3; i++) {
                    Log.e(TAG, "main: " + pop());
                }
            }
        }, 100);
    }


    /**
     * 在队列的尾部插入节点
     *
     * @param node
     */
    public void push(int node) {
        stack1.push(node);
    }

    /**
     * 在队列的头部删除节点
     *
     * @return
     */
    public int pop() {
        if (!stack2.isEmpty()) {
            return stack2.pop();
        } else {
            if (stack1.isEmpty()) {
                return Integer.MIN_VALUE;
            } else {
                while (!stack1.isEmpty()) {
                    stack2.push(stack1.pop());
                }
                return stack2.pop();
            }
        }
    }
}
