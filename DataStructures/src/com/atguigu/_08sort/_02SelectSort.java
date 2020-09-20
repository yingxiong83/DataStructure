package com.atguigu._08sort;

import java.util.Arrays;

/**
 *选择排序：
 * 8万条数据：3s
 *
 *
 * 选择排序比冒泡排序快得多
 *      原因：选择排序只是让一个指针指向最小的（或最大的），一次排序后将此指针指向的元素和第一个元素（此次排序对象的第一个）交换
 *              冒泡排序每一次排序过程只要遇到下一个比现在小，就立即互相交换，指令更多
 *      虽然两者算法时间复杂度都为O(n^2),但要优先选择选择排序
 *
 *      选择排序重在选择，冒泡排序重在交换，故两者不同类
 */
public class _02SelectSort {
    public static void main(String[] args) {
        /*int[] arr = {-3, -4, 2, 10, 3, 5};

        selectSort(arr);

        System.out.println(Arrays.toString(arr));*/

        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int) (Math.random() * 8000000);
        }

        long start = System.currentTimeMillis();
        selectSort(arr);//选择排序  3s
        long end = System.currentTimeMillis();
        System.out.println((end - start) / 1000);


    }

    /**
     * 选择排序
     * @param arr
     */
    private static void selectSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            int temp = 0;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[minIndex] > arr[j]) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                temp = arr[i];
                arr[i] = arr[minIndex];
                arr[minIndex] = temp;
            }
        }
    }
}
