package com.atguigu._09search;

/**
 * 线性查找
 */
public class _01LinearSearch {
    public static void main(String[] args) {
        int[] arr = {3, 12, 323, 5, 5, -234, 5};
        int value = 5;
        int index = linearSearch(arr, value);
        if (index == -1) {
            System.out.println("未找到！");
        } else {
            System.out.println(value + "的第一个索引位置为" + index);
        }
    }

    /**
     * 线性查找，查到第一个索引位置即返回
     * @param arr
     * @param value
     * @return
     */
    private static int linearSearch(int[] arr, int value) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == value) {
                return i;
            }
        }
        return -1;
    }
}
