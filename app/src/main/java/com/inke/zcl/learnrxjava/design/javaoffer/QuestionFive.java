package com.inke.zcl.learnrxjava.design.javaoffer;

import android.util.Log;

import java.util.Stack;

/**
 * Create By chunliangzhang on 2020-04-16
 * Version 1.0
 * Description: 从尾到头打印链表，
 */
public class QuestionFive {

    private static final String TAG = "QuestionFive";

    private static Node<String> firstNode, secondNode, thirdNode, fourNode, fiveNode;

    public static void main() {
        Node firstNode = initLinkList();
//        methodStack(firstNode);
        methodDiGui(firstNode);

    }


    public static void methodDiGui(Node firstNode) {
        if (firstNode == null) {
            return;
        }
//        if (firstNode.next != null) {
//            methodDiGui(firstNode.next);
//            Log.e(TAG, "methodDiGui: " + firstNode.element);
//        } else {
//            Log.e(TAG, "methodDiGui: " + firstNode.element);
//        }

        if (firstNode.next != null) {
            methodDiGui(firstNode.next);
        }
        Log.e(TAG, "methodDiGui: " + firstNode.element);

    }

    private static void methodStack(Node firstNode) {
        Stack<Node> stack = printMethodWithStack(firstNode);
        print(stack);
    }

    public static Node initLinkList() {
        fiveNode = new Node<>("five", null);
        fourNode = new Node<>("four", fiveNode);
        thirdNode = new Node<>("third", fourNode);
        secondNode = new Node<>("second", thirdNode);
        firstNode = new Node<>("first", secondNode);
        return firstNode;
    }

    public static Stack<Node> printMethodWithStack(Node firstNode) {
        if (firstNode == null) {
            return null;
        }
        Stack<Node> stack = new Stack<>();
        stack.push(firstNode);
        while (firstNode.next != null) {
            stack.push(firstNode.next);
            firstNode = firstNode.next;
        }
        return stack;
    }

    public static void print(Stack<Node> stack) {
        if (stack == null) {
            return;
        }
        while (!stack.isEmpty()) {
            Node node = stack.pop();
            if (node != null) {
                Log.e(TAG, "printMethodWithStack: " + node.element);
            }
        }
    }


    private static class Node<T> {
        public T element;
        public Node next;

        public Node(T element, Node next) {
            this.element = element;
            this.next = next;
        }
    }
}
