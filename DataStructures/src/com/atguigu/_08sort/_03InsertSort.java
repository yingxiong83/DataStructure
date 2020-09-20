package com.atguigu._08sort;

import java.util.Arrays;

/**
 * 插入排序
 *  8万条数据：506ms
 */
public class _03InsertSort {
    public static void main(String[] args) {
        /*int[] arr = {-44, 3, -39, -98, 4, 7,23,13,33,-8};

        insertSort(arr);

        System.out.println(Arrays.toString(arr));*/

        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int) (Math.random() * 8000000);
        }

        //System.out.println(Arrays.toString(arr));

        long start = System.currentTimeMillis();
        insertSort(arr);//插入排序  577ms
        long end = System.currentTimeMillis();
        System.out.println((end-start));

        //System.out.println(Arrays.toString(arr));

    }

    /**
     * 插入排序
     * @param arr
     */
    private static void insertSort(int[] arr) {
        int insertValue = 0;
        int insertIndex = 0;
        for (int i = 1; i < arr.length; i++) {
            insertValue = arr[i];
            insertIndex = i - 1;

            //找出要插入的索引位置
            while (insertIndex >= 0 && arr[insertIndex] > insertValue) {
                arr[insertIndex + 1] = arr[insertIndex];
                insertIndex--;
            }

            //必须加1
            arr[insertIndex + 1] = insertValue;
            //System.out.println(Arrays.toString(arr));
        }
    }
}