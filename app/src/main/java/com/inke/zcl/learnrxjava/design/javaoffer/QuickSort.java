package com.inke.zcl.learnrxjava.design.javaoffer;

import android.util.Log;

/**
 * Create By chunliangzhang on 2020-04-19
 * Version 1.0
 * Description:
 */

public class QuickSort implements IQuestion {

    public int[] quickSort(int[] A, int n) {
        // write code here
        quickSort(A, 0, n - 1);
        return A;
    }

    public int getMiddleBySwap(int[] numbers, int low, int high) {
        int temp = numbers[low]; //数组的第一个作为中轴
        while (low < high) {
            while (low < high && numbers[high] >= temp) {
                high--;
            }
            swap(numbers, low, high);
            while (low < high && numbers[low] <= temp) {
                low++;
            }
            swap(numbers, low, high);
        }
        //经测验low在这样的限制条件下一定是等于high的，所以无论返回哪一个都是OK的
        return low;
    }

    public void swap(int[] arr, int index1, int index2) {
        int tmp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = tmp;
    }

    /**
     * @param numbers 待排序数组
     * @param low     开始位置
     * @param high    结束位置
     */
    public void quickSort(int[] numbers, int low, int high) {
        if (numbers.length == 0) {
            return;
        }
        // index 其实标识了一段待排的区间，如果low>high的时候，其实已经没必要进行排序了
        if (low > high) {
            return;
        }
        int middle = getMiddleBySwap(numbers, low, high); //将numbers数组进行一分为二
        //这里递归传入的数组都是整个的数组，但是从范围上对其进行了制约，符合递归的条件
        quickSort(numbers, low, middle - 1);   //对低字段表进行递归排序
        quickSort(numbers, middle + 1, high); //对高字段表进行递归排序
    }

    @Override
    public void main() {
        int[] ints = new int[]{54, 35, 48, 36, 27, 12, 44, 44, 8, 14, 26, 17, 28};
        ints = quickSort(ints, ints.length);
        for (int in : ints) {
            Log.e(TAG, "main: in: " + in);
        }
    }
}
