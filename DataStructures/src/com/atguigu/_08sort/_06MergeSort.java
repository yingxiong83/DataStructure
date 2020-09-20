package com.atguigu._08sort;

import java.util.Arrays;

/**
 * 归并排序
 * 测试：80万条数据，132ms
 */
public class _06MergeSort {
    public static void main(String[] args) {
        //int[] arr = {4, 2, 7, 10, 4, 1, 5, 19, 3, 1};

        int[] arr = new int[800000];
        for (int i = 0; i < 800000; i++) {
            arr[i] = (int) (Math.random() * 8000000);
        }
        long start = System.currentTimeMillis();
        mergeSort(arr, 0, arr.length - 1, new int[arr.length]);
        long end = System.currentTimeMillis();
        System.out.println(end - start);

        //System.out.println(Arrays.toString(arr));
    }

    /**
     * 递归分解+层层向上合并
     * @param arr
     * @param left
     * @param right
     * @param temp
     */
    private static void mergeSort(int[] arr, int left, int right, int[] temp) {
        if (left < right) {
            int mid = (right + left) / 2;
            mergeSort(arr, left, mid, temp);
            mergeSort(arr, mid + 1, right, temp);
            merge(arr, left, mid, right, temp);
        }
    }

    /**
     * 合并
     * @param arr
     * @param left
     * @param mid
     * @param right
     * @param temp
     */
    private static void merge(int[] arr, int left, int mid, int right, int[] temp) {
        int i = left;
        int j = mid + 1;
        int t = 0;

        //两个组成均有元素
        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) {
                temp[t] = arr[i];
                t++;
                i++;
            } else {
                temp[t] = arr[j];
                t++;
                j++;
            }
        }

        while (i <= mid) {
            temp[t] = arr[i];
            t++;
            i++;
        }
        while (j <= right) {
            temp[t] = arr[j];
            t++;
            j++;
        }

        t = 0;
        i = left;
        while (i <= right) {
            arr[i] = temp[t];
            i++;
            t++;
        }
    }
}


