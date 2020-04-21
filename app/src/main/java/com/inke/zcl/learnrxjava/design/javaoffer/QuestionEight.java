package com.inke.zcl.learnrxjava.design.javaoffer;

import android.util.Log;

/**
 * Create By chunliangzhang on 2020-04-21
 * Version 1.0
 * Description:这个题目主要考察二分查找法，以及对应的异常边界case
 */
public class QuestionEight implements IQuestion {

    @Override
    public void main() {
        int[] nums = new int[]{3, 4, 5, 1, 2};
//        char[] nums = new char[]{1, 2, 3, 4, 5};
//        char[] nums = new char[]{1, 0, 1, 1, 1};
//        char[] nums = new char[]{1, 1, 1, 0, 1};

        int result = binarySearchMin(nums, nums.length);
        Log.e(TAG, "main: " + result);
    }


    public int minNumberInRotateArray(int[] array) {
        return binarySearchMin(array, array.length);
    }

    /**
     * 背景:这是一个有序的数组，将前面的部分数字放在数组的后面，形成一个新的数组（二逼的旋转数组）
     * 定义:把一个有序数组最开始的若干个元素搬到数组的末尾，该数组称为旋转数组。
     * 特殊case:
     * （1）当将数组开始的0个元素移动到末尾形成的数组。
     * （2）当两个指针指向的数字以及它们中间的数字三者相同的时候，我们无法判断中间的数字是属于前面的数组还是后面的数组，所以只能采用顺序查找的方式。『查找到中间的时候，顺序查找的范围会相对减小』
     * 目的:找出这个数组中最小的那个数字
     * 1:顺序查找法 时间O(n)
     * 2:二分查找法 O(log n)
     */

    private int binarySearchMin(int[] nums, int length) {
        if (nums == null) {
            return -1;
        }
        if (nums.length != length) {
            length = nums.length;
        }
        int startIndex = 0;
        int endIndex = length - 1;
        if (nums[startIndex] < nums[endIndex]) {
            return nums[startIndex];
        }
        int result;
        while (endIndex > startIndex && (endIndex - startIndex != 1)) {
            int mid = (startIndex + endIndex) / 2;
            if (nums[startIndex] < nums[mid]) {
                startIndex = mid;
            } else if (nums[mid] < nums[endIndex]) {
                endIndex = mid;
            } else if (nums[startIndex] == nums[mid] && nums[mid] > nums[endIndex]) {
                startIndex = mid;
            } else if (nums[endIndex] == nums[mid] && nums[mid] < nums[startIndex]) {
                endIndex = mid;
            } else {
                result = nums[startIndex];
                for (int i = startIndex + 1; i <= endIndex; i++) {
                    if (result > nums[i]) {
                        result = nums[i];
                    }
                }
                return result;
            }
        }
        result = nums[endIndex];
        return result;
    }


}
