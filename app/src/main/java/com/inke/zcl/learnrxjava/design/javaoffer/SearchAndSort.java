package com.inke.zcl.learnrxjava.design.javaoffer;

import android.util.Log;

import java.util.Arrays;

/**
 * Create By chunliangzhang on 2020-04-19
 * Version 1.0
 * Description: 排序和查找，其中突出的是 二分查找、归并排序、快排
 *
 * 查找: 顺序查找；二分查找；哈希表查找；二叉排序树查找（Importent二分查找代码）
 *
 * 原文:哈希表最主要的有点事可以在O(1)的时间内查找某一元素，是效率最高的查找方式，其缺点是需要额外的空间来实现哈希表
 * (Ps:哈希表是怎么实现在O(1)的时间内实现查找某一元素呢)
 *
 * 排序: 各种排序算法的特点，能够从额外空间消耗，平均时间复杂度，最差时间复杂度等方面进行比较优缺点
 * （Imporent:实现快排）
 *
 */
public class SearchAndSort implements IQuestion {

    @Override
    public void main() {
        int[] ints = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        int result = binarySearch(ints, 7);
        Log.e(TAG, "main: " + result);
    }

    /**
     * 二分查找(问题在于将其进行递归操作之后，数组便不是原来的数组，对应的下标也不再是总数组的下标)
     */
    public int binarySearch(int[] ints, int tartget) {
        if (ints == null || ints.length == 0) {
            return -1;
        }
        int index = ints.length / 2;
        if (ints[index] == tartget) {
            return index;
        }
        int[] left = Arrays.copyOfRange(ints, 0, index);
        int[] right = Arrays.copyOfRange(ints, index + 1, ints.length);
        int leftResult = binarySearch(left, tartget);
        int rightResult = binarySearch(right, tartget);
        int result = -1;
        if (leftResult >= 0) {
            return leftResult;
        } else if (rightResult >= 0) {
            return rightResult;
        } else {
            return result;
        }
    }


}
