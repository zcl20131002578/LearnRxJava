package com.inke.zcl.learnrxjava.design.javaoffer;

import android.util.Log;

/**
 * Create By chunliangzhang on 2020-04-17
 * Version 1.0
 * Description: 根据前序遍历和中序遍历 还原对应的二叉树
 */
public class QuestionSix {

    private static final String TAG = "QuestionSix";

    public static void main() {
        int[] preorder = new int[]{1, 2, 4, 7, 3, 5, 6, 8};
        int[] inorder = new int[]{4, 7, 2, 1, 5, 3, 8, 6};
        construct(preorder, inorder, 8);
    }

    public static void construct(int[] preorder, int[] inorder, int length) {


        /**
         * 鲁棒性 end
         */
        int rootElement = preorder[0];
        int rootIndex = 0;
        for (int i = 0; i < length; i++) {
            if (rootElement == inorder[i]) {
                break;
            } else {
                rootIndex++;
            }
        }
        // 根节点在中序遍历中的位置 rootIndex
        int left = rootIndex;// 左子树个数
        int right = length - left - 1;// 右子树个数
        int subLeftRoot = -1;
        int rightLeftRoot = -1;
        if (left > 0) {//表明有左子树
            subLeftRoot = preorder[1];
        }
        if (right > 0) {//表明有右子树
            rightLeftRoot = preorder[length - 1 - left];
        }
        Log.e(TAG, "construct: " + "left: " + left + " right: " + right + " subLeftRoot: " + subLeftRoot + " rightLeftRoot: " + rightLeftRoot);


//        BinaryTreeNode root = new BinaryTreeNode();


    }


}
