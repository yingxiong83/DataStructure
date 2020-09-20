package com.atguigu._08sort;

import java.util.Arrays;

/**
 * 冒泡排序
 * 8万条数据：10s
 *
 * 强制类型转换的优先级最高，所以如果要对某一表达式强制类型转换，先将其用小括号括起来
 */

public class _01BubbleSort {
    public static void main(String[] args) {
        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int) (Math.random() * 8000000);
        }
        long start = System.currentTimeMillis();

        bubbleSort(arr);//冒泡排序  11s

        long end = System.currentTimeMillis();
        System.out.println((end - start)/1000);//13
    }

    /**
     * BubbleSort：冒泡排序
     * @param arr
     */
    private static void bubbleSort(int[] arr) {
        int temp = 0;
        boolean flag = false;//false代表本次排序未发生交换，true代表本次排序有元素发生交换
        for (int i = 0; i < arr.length - 1; i++) {
            //每次排序之前把标志位置为false
            // 若在这次排序中有元素发生交换，则置为true，继续循环
            // 若无交换，则说明数组已经有序，flag为false，直接退出循环，避免浪费时间
            flag = false;
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    flag = true;
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
            //System.out.println("第" + (i + 1) + "次排序后：" + Arrays.toString(arr));
            if (!flag) {
                //此次排序无元素交换。退出循环
                break;
            }
        }
    }
}
