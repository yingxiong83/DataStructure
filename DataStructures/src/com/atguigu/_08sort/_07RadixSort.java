package com.atguigu._08sort;

import java.util.Arrays;

/**
 * 基数排序
 * 80万条数据：101ms
 */
public class _07RadixSort {
    public static void main(String[] args) {
        /*int[] arr = {34, 1, 4, 2, 456, 231, 34, 1};
        radixSort(arr);*/

        int[] arr = new int[80000000];
        for (int i = 0; i < 80000000; i++) {
            arr[i] = (int) (Math.random() * 8000000);
        }

        long start = System.currentTimeMillis();
        radixSort(arr);
        long end = System.currentTimeMillis();
        System.out.println(end - start);

    }

    private static void radixSort(int[] arr) {
        //定义10个桶，每一行代表一个桶
        int[][] buckets = new int[10][arr.length];
        //为每个桶定义一个指针，初始值为0
        int[] bucketPointers = new int[10];

        //先计算出数组的最大值有多少位，决定排序次数
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (max < arr[i]) {
                max = arr[i];
            }
        }
        int nBit = (max + "").length();//最大值的位数

        //根据个位、十位、百位、、、依次入桶排序
        for (int i = 0, n = 1; i < nBit; i++, n *= 10) {//n取1，10，100、、、
            //按位入桶
            for (int j = 0; j < arr.length; j++) {
                int xBit = arr[j] / n % 10;//计算出个位、十位、百位、、的值
                buckets[xBit][bucketPointers[xBit]] = arr[j];
                bucketPointers[xBit]++;
            }
            //按顺序出桶
            int arrIndex = 0;
            for (int j = 0; j < buckets.length; j++) {
                int bucketPointer = 0;
                while (bucketPointer < bucketPointers[j]) {
                    arr[arrIndex] = buckets[j][bucketPointer];
                    bucketPointer++;
                    arrIndex++;
                }
                //必须把bucketPointer[j]置0，否则下一次循环就会越界;即每一次循环都将桶指针重新置0
                bucketPointers[j] = 0;
            }

            //System.out.println(Arrays.toString(arr));
        }

    }
}
