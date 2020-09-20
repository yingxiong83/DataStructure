package com.atguigu._09search;

/**
 * 插值查找是对二分查找的优化
 *      当数据量大且元素值分布比较均匀时，使用自适应机制确定中间索引，效率更高
 */
public class _03InterpolationSearch {
    public static void main(String[] args) {
        int[] arr = new int[100];
        for (int i = 0; i < 100; i++) {
            arr[i] = i + 1;
        }

        int targetValue = 100;

        int targetIndex = interpolationSearch(arr, 0, arr.length - 1, targetValue);

        System.out.println(targetIndex);
    }

    /**
     * 插值查找，一个索引
     * @param arr
     * @param left
     * @param right
     * @param targetValue
     * @return
     */
    private static int interpolationSearch(int[] arr, int left, int right, int targetValue) {

        //递归退出机制left > right；保证索引不越界targetValue < arr[left] || targetValue > arr[right]
        if (left > right || targetValue < arr[left] || targetValue > arr[right]) {
            return -1;
        }

        //自适应机制确定中间索引
        int mid = left + (right - left) * (targetValue - arr[left]) / (arr[right] - arr[left]);
        if (targetValue > arr[mid]) {
            return interpolationSearch(arr, mid + 1, right, targetValue);
        } else if (targetValue < arr[mid]) {
            return interpolationSearch(arr, left, mid - 1, targetValue);
        } else {
            return mid;
        }
    }
}
