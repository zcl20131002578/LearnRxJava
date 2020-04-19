package com.inke.zcl.learnrxjava.design.javaoffer;

/**
 * Create By chunliangzhang on 2020-04-17
 * Version 1.0
 * Description:
 */
public class TreeNode {
    TreeNode left;
    TreeNode right;
    int element;

    public TreeNode( int element) {
        this.element = element;
    }


    public TreeNode getLeft() {
        return left;
    }

    public void setLeft(TreeNode left) {
        this.left = left;
    }

    public TreeNode getRight() {
        return right;
    }

    public void setRight(TreeNode right) {
        this.right = right;
    }

    public int getElement() {
        return element;
    }

    public void setElement(int element) {
        this.element = element;
    }
}
