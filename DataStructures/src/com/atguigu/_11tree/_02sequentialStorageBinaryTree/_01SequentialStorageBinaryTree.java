package com.atguigu._11tree._02sequentialStorageBinaryTree;

public class _01SequentialStorageBinaryTree {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        SequentialStorageBinaryTree tree = new SequentialStorageBinaryTree(arr);
        tree.preOrder();
        System.out.println("-----------------------------------------");
        tree.inOrder();
        System.out.println("-----------------------------------------");
        tree.postOrder();
    }
}

/**
 * 顺序存储二叉树，顺序存储的意思是元素存储的地址是连续的，故可用数组实现。用数组实现顺序存储二叉树有以下特点
 *      若当前节点的索引用n表示
 *      则当前节点的左子节点的索引为2*n+1
 *      当当前节点的右子节点的索引为2*n+2
 *      当前节点的父节点为（n-1）/2
 *
 *      编写前序遍历、中序遍历、后序遍历的函数
 */
class SequentialStorageBinaryTree {
    //存储树的节点
    private int[] arr;
    //构造函数，根据传入的数组构造二叉树
    public SequentialStorageBinaryTree(int[] arr) {
        this.arr = arr;
    }

    /**
     * 前序遍历
     */
    public void preOrder(int index) {
        //提到其重载方法中，因为此过程只需判断一次，如果放于此，递归调用每次都要判断，没有意义，浪费时间
        /*if (arr == null && arr.length == 0) {
            System.out.println("顺序存储二叉树为空！");
            return;
        }*/
        //1.输出当前节点
        System.out.println(arr[index]);
        //2.循环前序遍历左子树，输出每一个节点
        if ((2 * index + 1) < arr.length) {
            preOrder(2 * index + 1);
        }
        //3.循环前序遍历右子树，输出每一个节点
        if ((2 * index + 2) < arr.length) {
            preOrder(2 * index + 2);
        }
    }

    //为了方便调用重载一下，同时为了提高效率，将数组为空或长度为0的判断代码提到此处
    public void preOrder() {
        if (arr == null || arr.length == 0) {
            System.out.println("顺序存储二叉树为空！");
            return;
        }
        preOrder(0);
    }

    /**
     * 中序遍历和后序遍历都一样，我直接快速写完，不写注释了
     */
    public void inOrder() {
        if (arr == null || arr.length == 0) {
            System.out.println("顺序存储二叉树为空！");
            return;
        }
        inOrder(0);
    }

    public void inOrder(int index) {
        if ((index * 2 + 1) < arr.length) {
            inOrder(index * 2 + 1);
        }
        System.out.println(arr[index]);
        if ((index * 2 + 2) < arr.length) {
            inOrder(index * 2 + 2);
        }
    }

    public void postOrder() {
        if (arr == null || arr.length == 0) {
            System.out.println("顺序存储二叉树为空！");
            return;
        }
        //遍历此树
        postOrder(0);
    }

    public void postOrder(int index) {
        //先遍历左子树
        if ((index * 2 + 1) < arr.length) {
            postOrder(index * 2 + 1);
        }
        //遍历右子树
        if ((index * 2 + 2) < arr.length) {
            postOrder(index * 2 + 2);
        }
        //输出当前节点
        System.out.println(arr[index]);
    }
}

