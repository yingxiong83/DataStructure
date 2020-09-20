package com.atguigu._09search;

import java.util.ArrayList;
import java.util.List;

/**
 * 二分查找的数组首先要保证有序
 */
public class _02BinarySearch {
    public static void main(String[] args) {
        int[] arr = {-3, 4, 5, 7, 11, 25, 25, 25, 25, 25, 25, 25, 25, 25, 36};
        int targetValue = 25;
        //int targetIndex = binarySearch1(arr, 0, arr.length-1, targetValue);
        //System.out.println(targetIndex);
        List<Integer> targetIndexList = binarySearch2(arr, 0, arr.length - 1, targetValue);
        System.out.println(targetIndexList);
    }

    /**
     * 二分查找：将所有等于目标值的索引位置装在list集合里返回
     * @param arr
     * @param left
     * @param right
     * @param targetValue
     * @return
     */
    private static List<Integer> binarySearch2(int[] arr, int left, int right, int targetValue) {

        //未找到目标值
        if (left > right) {
            return null;
        }

        //中值索引
        int mid = (left + right) / 2;
        //目标值大于中值索引对应的值
        if (targetValue > arr[mid]) {
            //向右递归
            return binarySearch2(arr, mid + 1, right, targetValue);
        } else if (targetValue < arr[mid]) {
            //目标值小于中值索引对应的值，向左递归
            return binarySearch2(arr, left, mid - 1, targetValue);
        } else {
            //找到目标值，打包
            List<Integer> list = new ArrayList<>();
            int temp = mid - 1;
            while (temp >= 0 && arr[temp] == targetValue) {
                list.add(temp);
                temp--;
            }
            list.add(mid);
            temp = mid + 1;
            while (temp < arr.length && arr[temp] == targetValue) {
                list.add(temp);
                temp++;
            }
            return list;
        }
    }

    /**
     * 二分查找，找出数组中目标值对应的其中一个索引
     * @param arr   目标数组
     * @param left  左索引
     * @param right 右索引
     * @param targetValue   目标值
     * @return  目标索引
     */
    private static int binarySearch1(int[] arr, int left, int right, int targetValue) {

        if (left > right) {
            return -1;
        }
        int mid = (left + right) / 2;
        if (targetValue > arr[mid]) {
            return binarySearch1(arr, mid + 1, right, targetValue);
        } else if (targetValue < arr[mid]) {
            return binarySearch1(arr, left, mid - 1, targetValue);
        } else {
            return mid;
        }
    }


}
