package com.atguigu._07recursion;

public class _01RecursionTest {
    public static void main(String[] args) {
        //test(10);
        System.out.println(factorial(3));
    }

    /*
        递归输出
     */
    public static void test(int n) {
        if (n > 1) {
            test(n - 1);
        }
        System.out.println(n);
    }

    /*
        阶乘
     */
    public static int factorial(int n) {
        if (n == 1) {
            return 1;
        }
        return n * factorial(n - 1);
    }
}
