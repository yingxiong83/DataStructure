package com.atguigu._02queue_array;

import java.util.Scanner;

/**
 * 使用数组模拟队列强化版：出对列的空间可重复利用
 *      两个指针，一个数组
 *      前指针指向队列第一个元素
 *      后指针指向队列最后一个元素的最后一个位置
 *      前后指针的初始值都为0
 *      空出数组的一个位置作为约定空间，此空间不可存储元素
 */
public class CircleQueueByArray {
    public static void main(String[] args) {
        CircleArrayQueue queue = new CircleArrayQueue(4);
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
                    System.out.println("请输入值：");
                    int value = scanner.nextInt();
                    queue.add(value);
                    break;
                case 'g':
                    System.out.println(queue.get());
                    break;
                case 'p':
                    System.out.println(queue.peek());
                    break;
                case 'e':
                    loop = false;
                    scanner.close();
                    break;
                default:
                    break;
            }
        }
    }
}

/**
 * 环形队列
 * front指向队列的头
 * rear指向队列尾的后一个元素
 * 注意：我设置了一个空元素，rear指向的位置始终为空
 */
class CircleArrayQueue {
    private int maxSize;
    private int front;//队列的头
    private int rear;//队列的尾+1
    private int[] queue;

    //初始化环形队列，头和尾指针默认全为0
    public CircleArrayQueue(int maxSize) {
        this.maxSize = maxSize;
        queue = new int[maxSize];
    }

    public boolean isFull() {
        return (rear + 1) % maxSize == front;
    }

    public boolean isEmpty() {
        return rear == front;
    }

    public void add(int value) {
        if (isFull()) {
            //throw new RuntimeException("队列已满。。。");
            System.out.println("队列已满。。。");
            return;
        }
        queue[rear] = value;
        rear = (rear + 1) % maxSize;
    }

    public int get() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空。。。");
        }
        int value = queue[front];
        front = (front + 1) % maxSize;
        return value;
    }

    public void showQueue() {
        if (isEmpty()) {
            //throw new RuntimeException("队列为空。。。");
            System.out.println("队列为空。。。");
            return;
        }
        for (int i=front;i<front+size();i++){
            System.out.printf("%d\t", queue[i % maxSize]);
        }
    }

    public int peek() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空。。。");
        }
        return queue[front];
    }


    public int size() {
        return (rear - front + maxSize) % maxSize;
    }
}
