package com.atguigu._08sort;

import java.util.Arrays;

/**
 * 希尔排序
 * 80万条数据：
 *      交换式：213ms
 *      位移式：136ms
 */
public class _04ShellSort {
    public static void main(String[] args) {

        /*int[] arr = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
        shellSort1(arr);
        shellSort2(arr);*/


        int[] arr = new int[800000];
        for (int i = 0; i < 800000; i++) {
            arr[i] = (int) (Math.random() * 8000000);
        }


        long start = System.currentTimeMillis();
        //shellSort1(arr);//交换式希尔排序  20ms     缩小增量+交换
        shellSort2(arr);//位移式希尔排序   22ms    缩小增量+位移（插入）
        long end = System.currentTimeMillis();
        System.out.println((end-start));
    }

    /**
     * 位移式希尔排序
     *
     * @param arr
     */
    private static void shellSort2(int[] arr) {
        int count = 0;
        int length = arr.length;
        //缩小增量
        for (int gap = length / 2; gap > 0; gap /= 2) {
            //循环为每一个元素排序（每组的第一个元素作为基准点）
            for (int i = gap; i < length; i++) {
                //插入排序
                int j = i;
                int temp = arr[j];
                while ((j - gap) >= 0 && temp < arr[j - gap]) {
                    arr[j] = arr[j - gap];
                    j -= gap;
                }
                arr[j] = temp;
            }
            //System.out.println("第" + (++count) + "次结果为：" + Arrays.toString(arr));
        }
    }

    /**
     * 交换式希尔排序
     *
     * @param arr
     */
    private static void shellSort1(int[] arr) {
        int count = 0;
        int length = arr.length;
        int temp = 0;
        //缩小增量
        for (int gap = length / 2; gap > 0; gap /= 2) {
            //循环次数等于除去每组第一个的元素个数
            for (int i = gap; i < length; i++) {
                //排序时，将第i个元素之前的所有属于该组的元素进行排序，小的在前，大的在后
                for (int j = i; j >= gap; j -= gap) {
                    if (arr[j] < arr[j - gap]) {
                        temp = arr[j];
                        arr[j] = arr[j - gap];
                        arr[j - gap] = temp;
                    } else {
                        break;
                    }
                }
            }
            //System.out.println("第" + (++count) + "次结果：" + Arrays.toString(arr));
        }
    }


}

