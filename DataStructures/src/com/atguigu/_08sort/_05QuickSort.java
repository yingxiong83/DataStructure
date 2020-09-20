package com.atguigu._08sort;

import java.util.Arrays;

/**
 * 快速排序
 * 80万条数据：148ms
 */
public class _05QuickSort {
    public static void main(String[] args) {
        /*int[] arr = {3, 4, 7, 2, 6, 1, 5, 34, 2, 5};
        quickSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));*/

        int[] arr = new int[800000];
        for (int i = 0; i < 800000; i++) {
            arr[i] = (int) (Math.random() * 8000000);
        }

        long start = System.currentTimeMillis();
        quickSort(arr, 0, arr.length - 1);  //快速排序  18ms
        long end = System.currentTimeMillis();
        System.out.println(end - start);
        //System.out.println(Arrays.toString(arr));

    }

    /**
     * 快速排序
     * 比较重要的一个细节：如果l和r已经重合了，就不能再执行l++或r--
     * @param arr
     * @param left
     * @param right
     */
    private static void quickSort(int[] arr, int left, int right) {
        //递归的结束条件
        if (left >= right) {
            return;
        }
        int l = left;
        int r = right;
        int pivot = arr[l];//基准
        //每轮快速排序
        while (l < r) {
            while (arr[r] >= pivot && l < r) {
                r--;
            }
            if (l < r) {
                arr[l] = arr[r];
                arr[r] = pivot;
                l++;
            }

            while (arr[l] <= pivot && l < r) {
                l++;
            }
            if (l < r) {
                arr[r] = arr[l];
                arr[l] = pivot;
                r--;
            }
        }

        quickSort(arr, left, l - 1);

        quickSort(arr, r + 1, right);
    }
}
