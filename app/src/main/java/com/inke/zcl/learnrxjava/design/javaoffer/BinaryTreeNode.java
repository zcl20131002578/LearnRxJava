package com.inke.zcl.learnrxjava.design.javaoffer;

/**
 * Create By chunliangzhang on 2020-04-17
 * Version 1.0
 * Description:
 */
public class BinaryTreeNode {
    BinaryTreeNode leftNode;
    BinaryTreeNode rightNode;
    int element;

    public BinaryTreeNode( int element) {
        this.element = element;
    }


    public BinaryTreeNode getLeftNode() {
        return leftNode;
    }

    public void setLeftNode(BinaryTreeNode leftNode) {
        this.leftNode = leftNode;
    }

    public BinaryTreeNode getRightNode() {
        return rightNode;
    }

    public void setRightNode(BinaryTreeNode rightNode) {
        this.rightNode = rightNode;
    }

    public int getElement() {
        return element;
    }

    public void setElement(int element) {
        this.element = element;
    }
}
