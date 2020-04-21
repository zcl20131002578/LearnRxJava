package com.inke.zcl.learnrxjava.design.javaoffer;

import android.util.Log;

import java.util.Arrays;

/**
 * Create By chunliangzhang on 2020-04-21
 * Version 1.0
 * Description:
 */
public class SortAge implements IQuestion {

    @Override
    public void main() {
        int[] ages = new int[]{1, 3, 90, 44, 67, 95, 33, 55, 66, 44, 33, 22, 11, 24, 66, 7, 8};
        ages = sortAges(ages, ages.length);
        Log.e(TAG, "main: " + Arrays.toString(ages));
    }

    /**
     * 要求：
     * 1:时间复杂度为O(n)
     * 2:空间复杂度最大为O(n)
     *
     * @param ages
     * @param length
     * @return
     */
    private int[] sortAges(int[] ages, int length) {
        if (ages == null) {
            return null;
        }

        if (ages.length != length) {
            length = ages.length;
        }

        Log.e(TAG, "sortAges: length: " + length);
        int[] timesOfAge = new int[150];

        for (int age : ages) {
            if (age > 0 && age < 150) {
                timesOfAge[age]++;
            }
        }

        for (int j = 0, i = 0; j < timesOfAge.length; j++) {
            while (timesOfAge[j] > 0 && i < ages.length) {
                ages[i] = j;
                timesOfAge[j]--;
                i++;
            }
        }
        return ages;

    }
}
