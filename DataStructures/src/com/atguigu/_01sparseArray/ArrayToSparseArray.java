package com.atguigu._01sparseArray;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 二维数组--》稀疏数组
 */
public class ArrayToSparseArray {
    public static void main(String[] args) {
        //1.原始数组
        int array[][] = new int[11][11];//大小11*11的二维数组
        array[1][1] = 1;
        array[2][3] = 2;
        System.out.println("原数组为：");
        int num = 0;//计数器：记录值不为0的元素个数
        for (int[] row : array) {
            for (int data : row) {
                if (data != 0) {
                    num++;
                }
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }

        //2.转换为稀疏数组
        int sparseArray[][] = new int[num + 1][3];
        sparseArray[0][0] = 11;
        sparseArray[0][1] = 11;
        sparseArray[0][2] = num;
        int count = 1;
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if (array[i][j] != 0) {
                    sparseArray[count][0] = i;
                    sparseArray[count][1] = j;
                    sparseArray[count][2] = array[i][j];
                    count++;
                }
            }
        }
        System.out.println("转换为的稀疏数组为：");
        for (int[] row : sparseArray) {
            System.out.printf("%d\t%d\t%d\n", row[0], row[1], row[2]);
        }

        //3.输出到文件map.data
        FileWriter fw = null;
        try {
            fw = new FileWriter(new File("map.data"));
            for (int[] row : sparseArray) {
                fw.write(row[0] + "\t");
                fw.write(row[1] + "\t");
                fw.write(row[2] + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fw != null) {
                try {
                    fw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
