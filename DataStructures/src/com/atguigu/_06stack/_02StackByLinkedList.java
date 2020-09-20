package com.atguigu._06stack;

import java.util.Scanner;
import java.util.Stack;

public class _02StackByLinkedList {
    public static void main(String[] args) {
        LinkedListStack linkedListStack = new LinkedListStack();
        String key = "";
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        while (loop) {
            System.out.println("show:显示栈元素");
            System.out.println("exit:退出程序");
            System.out.println("push:压栈");
            System.out.println("pop:出栈");
            key = scanner.next();
            switch (key) {
                case "show":
                    linkedListStack.show();
                    break;
                case "push":
                    System.out.println("请输入值：");
                    int pushValue = scanner.nextInt();
                    Node node = new Node(pushValue);
                    linkedListStack.push(node);
                    break;
                case "pop":
                    int popValue = linkedListStack.pop().getValue();
                    System.out.println("栈顶元素为" + popValue);
                    break;
                case "exit":
                    loop = false;
                    break;
                default:
                    break;
            }
        }
    }
}

class LinkedListStack {
    private Node header = new Node(0);

    public boolean isEmpty() {
        return header.getNext() == null;
    }

    public void push(Node node) {
        Node temp = header;
        while (true) {
            if (temp.getNext() == null) {
                temp.setNext(node);
                break;
            }
            temp = temp.getNext();
        }
    }

    public Node pop() {
        if (isEmpty()) {
            throw new RuntimeException("栈空。。。");
        }
        Node front = header;
        while (true) {
            if (front.getNext().getNext() == null) {
                Node popNode = front.getNext();
                front.setNext(null);
                return popNode;
            }
            front = front.getNext();
        }
    }

    public void show() {
        if (isEmpty()) {
            System.out.println("栈空。。。");
            return;
        }
        Node cur = header.getNext();
        Stack<Node> nodes = new Stack<>();
        while (true) {
            if (cur == null) {
                break;
            }
            nodes.push(cur);
            cur = cur.getNext();
        }
        while (nodes.size() > 0) {
            System.out.println("栈元素为:" + nodes.pop().getValue());
        }
    }
}

class Node {
    private int value;
    private Node next;

    public Node(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }
}
