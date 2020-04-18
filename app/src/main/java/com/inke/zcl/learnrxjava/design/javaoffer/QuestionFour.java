package com.inke.zcl.learnrxjava.design.javaoffer;

import android.util.Log;

import java.util.Arrays;

/**
 * Create By chunliangzhang on 2020-04-13
 * Version 1.0
 * Description:
 */
public class QuestionFour {

    private static final String TAG = "InsertEmptyInString";

    public static void text() {
        char[] st = {' ', 'a', 'b', 'c'};
        st = QuestionFour.replaceEmpty(st, 4);
        Log.e(TAG, "init: " + Arrays.toString(st));
    }

    public static char[] replaceEmpty(char[] string, int length) {
        /**
         * 提升鲁棒性
         */
        if (string == null) {
            return string;
        }

        if (string.length != length) {
            length = string.length;
        }

        int emptyNum = 0;
        for (char c : string) {
            if (c == ' ') {
                emptyNum++;
            }
        }
        if (emptyNum == 0) {
            return string;
        }
        int afterLength = length + emptyNum * 2;
        /**
         * 如何提升char的范围
         */
        char[] result = new char[afterLength];

        int afterIndex = afterLength - 1;
        int beforeIndex = length - 1;

        while (afterIndex >= beforeIndex && beforeIndex >= 0) {
            if (string[beforeIndex] == ' ') {
                result[afterIndex--] = '0';
                result[afterIndex--] = '2';
                result[afterIndex--] = '%';
                beforeIndex--;
            } else {
                result[afterIndex--] = string[beforeIndex--];
            }
        }
        return result;
    }

    public String replaceSpaceBuffer(StringBuffer str) {
        for (int k = 0; k < str.length(); k++) {
            char index = str.charAt(k);
            if (index == ' ') {
                str.replace(k, k + 1, "%20");
            }
        }

        return str.toString();
    }

    public String replaceSpaceBuilder(StringBuilder str) {
        for (int k = 0; k < str.length(); k++) {
            char index = str.charAt(k);
            if (index == ' ') {
                str.replace(k, k + 1, "%20");
            }
        }

        return str.toString();
    }
}
