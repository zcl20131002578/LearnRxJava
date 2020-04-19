package com.inke.zcl.learnrxjava.design.javaoffer;

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

    /**
     * 查找出中轴（默认是最低位low）的在numbers数组排序后所在位置
     *
     * @param numbers 带查找数组
     * @param low     开始位置
     * @param high    结束位置
     * @return 中轴所在位置
     */
    public int getMiddle(int[] numbers, int low, int high) {
        int temp = numbers[low]; //数组的第一个作为中轴
        while (low < high) {
            while (low < high && numbers[high] >= temp) {
                high--;
            }
            numbers[low] = numbers[high];//比中轴小的记录移到低端
            while (low < high && numbers[low] <= temp) {
                low++;
            }
            numbers[high] = numbers[low]; //比中轴大的记录移到高端
        }
        numbers[low] = temp; //中轴记录到尾
        return low; // 返回中轴的位置
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
        if (low < high) {
            int middle = getMiddle(numbers, low, high); //将numbers数组进行一分为二
            quickSort(numbers, low, middle - 1);   //对低字段表进行递归排序
            quickSort(numbers, middle + 1, high); //对高字段表进行递归排序
        }
    }

    @Override
    public void main() {
        int[] ints = new int[]{54, 35, 48, 36, 27, 12, 44, 44, 8, 14, 26, 17, 28};
        quickSort(ints, ints.length);

    }
}
