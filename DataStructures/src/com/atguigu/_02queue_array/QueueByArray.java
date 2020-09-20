package com.atguigu._02queue_array;

import java.util.Scanner;

/**
 * 使用数组模拟队列简化版：出队列的空间不能重复使用
 *      两个指针，一个数组
 *      前指针指向队列的第一个元素的前一个位置
 *      后指针指向队列的最后一个元素
 *      前指针和后指针的初始值均为-1
 */
public class QueueByArray {
    public static void main(String[] args) {
        ArrayQueue queue = new ArrayQueue(3);
        char key = ' ';
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        while (loop) {
            System.out.println("-----------------------------------------------------------");
            System.out.println("s(showQueue):显示队列");
            System.out.println("a(add):添加元素");
            System.out.println("g(get):获取元素");
            System.out.println("p(peek):窥视元素");
            System.out.println("e(exit):退出程序");
            key = scanner.next().charAt(0);
            switch (key) {
                case 's':
                    queue.showQueue();
                    break;
                case 'a':
                    System.out.print("请输入要添加的值：");
                    int value = scanner.nextInt();
                    queue.add(value);
                    break;
                case 'g':
                    System.out.println("获取的值为：" + queue.get());
                    break;
                case 'p':
                    System.out.println("窥视结果是：" + queue.peek());
                    break;
                case 'e':
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;
            }
        }
    }
}

/**
 * 普通队列：
 * front指向队列的头前面一个位置
 * rear指向队列的尾
 */
class ArrayQueue {
    private int maxSize;//队列的最大长度
    private int rear;//队列的尾
    private int front;//队列的头-1
    private int[] queue;

    //构造函数，初始化队列
    public ArrayQueue(int maxSize) {
        this.maxSize = maxSize;
        rear = -1;
        front = -1;
        queue = new int[maxSize];
    }

    public boolean isEmpty() {
        return rear == front;
    }

    public boolean isFull() {
        return rear == maxSize - 1;
    }

    public void add(int value) {
        if (isFull()) {
            System.out.println("队列已满。。。");
            return;
        }
        queue[++rear] = value;
    }

    public int get() {
        if (isEmpty()) {
            throw new RuntimeException("队列是空的。。。");
        }
        return queue[++front];
    }

    public void showQueue() {
        if (isEmpty()) {
            System.out.println("队列是空的。。。");
        }
        for (int i = front + 1; i < rear + 1; i++) {
            System.out.printf("queue[%d]=%d\n", i, queue[i]);
        }
    }

    public int peek() {
        if (isEmpty()) {
            throw new RuntimeException("队列是空的。。。");
        }
        return queue[front + 1];
    }
}
