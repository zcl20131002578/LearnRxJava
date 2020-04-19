package com.inke.zcl.learnrxjava.design.javaoffer;

import java.util.Arrays;

/**
 * Create By chunliangzhang on 2020-04-17
 * Version 1.0
 * Description:
 * Definition for binary tree
 * public class BinaryTreeNode {
 * int val;
 * BinaryTreeNode left;
 * BinaryTreeNode right;
 * BinaryTreeNode(int x) { val = x; }
 * }
 */
public class QustionSix_ implements IQuestion {
    int[] preOrder;
    int[] inOrder;

    @Override
    public void main() {
        initintArray();
        reConstructBinaryTree(preOrder, inOrder);
    }

    private void initintArray() {
        preOrder = new int[]{3, 9, 20, 15, 7};
        inOrder = new int[]{9, 3, 15, 20, 7};
    }


    public TreeNode reConstructBinaryTree(int[] pre, int[] in) {
        if (pre == null || in == null || pre.length == 0 || in.length == 0) {
            return null;
        }
        if (pre.length != in.length) {
            return null;
        }
        // 1:构建根节点的值;在每一次递归的情况下，根据前序遍历和中序遍历构造出对应的根节点（包括节点的值和左右的节点）
        TreeNode treeNode = new TreeNode(pre[0]);

        int index = 0;
        for (int i = 0; i < in.length; i++) {
            if (in[i] == pre[0]) {
                index = i;
                break;
            }
        }
        //2:构造根节点的左子节点和右子节点;(根据根据左子树和右子树的前序遍历和中序遍历序列进行左子树和右子树根节点的构造)
        treeNode.left = reConstructBinaryTree(Arrays.copyOfRange(pre, 1, index + 1), Arrays.copyOfRange(in, 0, index));
        treeNode.right = reConstructBinaryTree(Arrays.copyOfRange(pre, index + 1, pre.length), Arrays.copyOfRange(in, index + 1, in.length));
        return treeNode;
    }

    private int findRootNodeIndexInOrder(int[] preOrder, int[] inOrder) {
        for (int i = 0; i < inOrder.length; i++) {
            if (inOrder[i] == preOrder[0]) {
                return i;
            }
        }
        return 0;
    }


}
