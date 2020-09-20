package com.atguigu._07recursion;

/**
 * 一层一层地插入皇后，第一层按照布局要求插入成功开始插入第二层；第二层成功开始插第三层。。。。。
 * 每一层的逻辑一样，直接for循环，循环次数为皇后的个数；参数为层号
 *      ①每一层的逻辑：
 *          首先判断是否达到最后一层的后一层（越界），如果是说明找到一个布局正确的解，直接打印出结果，返回上一级；
 *          否则，使用for循环遍历该层的第0列到第7列，每一列逻辑相同，如下
 *              ②每一列的逻辑：
 *                  先将皇后放于此列
 *                  判断放于此列是否满足布局要求，满足则开始插入下一层（递归调用）；不满足列指针++，重复每一列的逻辑
 *          8列遍历完成，回溯到上一层，列指针++，重复每一列的操作。。。。
 *
 *
 *套娃
 *
 *
 * 给出八皇后问题的所有解，统计其次数
 *      此次皇后标号从0开始
 *
 *      基本思路：
 *          第一步，需要将皇后放置于第一行，即遍历每一列，将皇后置于每一列，判断是否满足八皇后布局要求
 *          若符合，递归将皇后放置于第二行，即遍历第二行的每一列，将第二个皇后置于每一列，判断是否满足布局要求；若遍历完都不符合，回溯达调用它的方法
 *          若符合，递归将皇后放置于第三行
 *          。。。
 *          当行号超过最后一列时，打印出数组即为一解，此时方法结束，回溯到调用它的方法
 *          。。。
 */
public class _03EightQueensProblem {
    //定义皇后个数
    private static int max = 8;
    //定义数组，索引代表皇后所在行数，值代表皇后所在列数
    private static int[] arr = new int[max];
    //解的个数
    private static int count;

    public static void main(String[] args) {
        placeQueenX(0);
        System.out.println("解的个数：" + count);
    }

    /**
     * 放置第x个皇后，即在第x行放置皇后
     * @param x
     */
    private static void placeQueenX(int x) {
        if (x == max) {
            //皇后已正确放置完毕，打印出此次结果，回溯到上一级
            count++;
            print();
            return;
        }
        //扫面第x行的每一列，如果布局正确，则放置下一个皇后
        for (int i = 0; i < max; i++) {
            arr[x] = i;
            if (isCorrect(x)) {
                placeQueenX(x + 1);
            }
        }
    }

    /**
     * 判断当前皇后是否与之前的任一个皇后布局冲突（当前皇后的序号即为行号）
     * @param cur
     * @return
     */
    private static boolean isCorrect(int cur) {
        for (int i = 0; i < cur; i++) {//i<cur,保证了当前皇后和之前的皇后均不在同一行
            //如果当前皇后和前面某一皇后在同一列或者同一条线，当前皇后的位置不正确，返回false
            if (arr[i] == arr[cur] || Math.abs(cur - i) == Math.abs(arr[cur] - arr[i])) {
                return false;
            }
        }
        //当前皇后和之前的皇后均不在同一行、同一列、同一斜线，布局正确，返回true
        return true;
    }

    /**
     * 打印该解
     */
    private static void print() {
        for (int i : arr) {
            System.out.print(i + "\t");
        }
        System.out.println();
    }
}


