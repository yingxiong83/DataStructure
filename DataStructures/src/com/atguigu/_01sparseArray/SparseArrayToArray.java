package com.atguigu._01sparseArray;

import java.io.*;

/**
 * 稀疏数组--》二维数组
 */
public class SparseArrayToArray {
    public static void main(String[] args) {
        BufferedReader br = null;
        try {
            //1.读取稀疏数组数据
            br = new BufferedReader(new FileReader("map.data"));

            //2.将稀疏数组还原成原数组
            String header = br.readLine();
            String[] data = header.split("\t");
            int array[][] = new int[Integer.parseInt(data[0])][Integer.parseInt(data[1])];
            for (int i = 1; i <= Integer.parseInt(data[2]); i++) {
                String body = br.readLine();
                String[] value = body.split("\t");
                array[Integer.parseInt(value[0])][Integer.parseInt(value[1])] = Integer.parseInt(value[2]);
            }

            //3，打印出原数组
            System.out.println("恢复后的数组：");
            for (int[] row : array) {
                for (int k : row) {
                    System.out.printf("%d\t", k);
                }
                System.out.println();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
