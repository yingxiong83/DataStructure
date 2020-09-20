package com.atguigu._06stack;

import java.util.Scanner;

public class _01StackByArray {
    public static void main(String[] args) {
        ArrayStack arrayStack = new ArrayStack(4);
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
                    arrayStack.show();
                    break;
                case "push":
                    System.out.println("请输入值：");
                    int pushValue = scanner.nextInt();
                    arrayStack.push(pushValue);
                    break;
                case "pop":
                    int popValue = arrayStack.pop();
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

class ArrayStack {
    private int maxsize;
    private int[] stack;
    private int top = -1;

    public ArrayStack(int maxsize) {
        this.maxsize = maxsize;
        stack = new int[maxsize];
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public boolean isFull() {
        return top == maxsize - 1;
    }

    public void push(int value) {
        if (isFull()) {
            System.out.println("栈满。。。");
            return;
        }
        stack[++top] = value;
    }

    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("栈空。。。");
        }
        return stack[top--];
    }

    public void show() {
        if (isEmpty()) {
            System.out.println("栈空。。。");
            return;
        }
        for (int i = top; i >= 0; i--) {
            System.out.printf("stack[%d]=%d\n", i, stack[i]);
        }
    }
}
