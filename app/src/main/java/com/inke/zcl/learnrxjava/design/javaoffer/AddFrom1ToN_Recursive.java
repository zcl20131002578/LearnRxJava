package com.inke.zcl.learnrxjava.design.javaoffer;

/**
 * Create By chunliangzhang on 2020-04-21
 * Version 1.0
 * Description:
 * Q:为什么递归的调用方式不如循环效率高
 * A:递归的调用是调用自身而函数的调用是有
 */
public class AddFrom1ToN_Recursive implements IQuestion {


    @Override
    public void main() {

    }



    /**
     * 递归
     * f(n)= f(n-1)+n (n>1)
     * f(n)= 0        (n<=0)
     */
    private int diGui(int n) {
        if (n <= 0) {
            return 0;
        }
        return diGui(n - 1) + n;


    }
}
