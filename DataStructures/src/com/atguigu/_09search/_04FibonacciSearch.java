package com.atguigu._09search;

import java.util.Arrays;

public class _04FibonacciSearch {

    private static int maxSize = 10;

    public static void main(String[] args) {
        int[] arr = {3, 7, 12, 45, 132, 346};
        int targetValue = 132;
        int targetIndex = fibonacciSearch(arr, targetValue);
    }

    /**
     * 斐波那契查找：重点是arr"扩容“至fibonacci[k]-1，每次循环操作的数组范围长度都为fibonacci[k]-1，往左k=k-1，往右k=k-2
     *      因为：
     *              fibonacci[k]=fibonacci[k-1] + fibonacci[k-2]
     *          ==> (fibonacci[k]-1) = (fibonacci[k-1]-1) + 1 + (fibonacci[k-2]-1)
     *          显而易见，需要保证每次的递归或循环操作的范围大小是一个斐波那契值-1，
     *                  中间的1就是mid索引指向的位置，我称之为斐波那契点
     * @param arr
     * @param targetValue
     * @return
     */
    private static int fibonacciSearch(int[] arr, int targetValue) {
        //获取数组长度
        int length = arr.length;
        //获取斐波那契数组
        int[] fibonacci = fibonacci(maxSize);
        //定义指向斐波那契数组的索引
        int k = 0;

        //找出第一个使得length<=fibonacci[k]-1的k
        while (length > (fibonacci[k] - 1)) {
            k++;
        }

        //将原数组“扩容”至fibonacci[k]-1的长度，填充值为arr[length-1]
        int[] temp = Arrays.copyOf(arr, fibonacci[k] - 1);
        for (int i = length; i < fibonacci[k] - 1; i++) {
            arr[i] = arr[length - 1];
        }

        //查找temp数组
        int left = 0;//初始值
        int right = temp.length - 1;//初始值
        int mid = 0;
        //循环查找
        while (left <= right) {
            //斐波那契点
            mid = left + fibonacci[k - 1] - 1;
            if (targetValue > temp[mid]) {
                //目标值大于斐波那契点的值，往右查找，left置成斐波那契点右边一点，范围大小变为fibonacci[k-2]-1，需置k=k-2
                left = mid + 1;
                k -= 2;
            } else if (targetValue < temp[mid]) {
                //目标值小于斐波那契的值，往左查找，right置成斐波那契点左边一点，范围大小变为fibonacci[k-1]-1,需置k=k-1
                right = mid - 1;
                k -= 1;
            } else {
                //找到，若mid大于了length-1，则说明索引位于length-1，否则就是mid
                if (mid >= length) {
                    return length - 1;
                } else {
                    return mid;
                }
            }
        }
        return -1;
    }

    private static int[] fibonacci(int maxSize) {
        int[] fibonacci = new int[maxSize];
        fibonacci[0] = 1;
        fibonacci[1] = 1;
        for (int i = 2; i < maxSize; i++) {
            fibonacci[i] = fibonacci[i - 1] + fibonacci[i - 2];
        }
        return fibonacci;
    }
}
