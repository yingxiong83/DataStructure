package com.atguigu._11tree._03threadedBinaryTree;

/**
 * 前序索引化二叉树练习
 */
public class _02PreOrderThreadedBinaryTree {
    public static void main(String[] args) {
        HeroNode root = new HeroNode(1, "宋江");
        HeroNode node1 = new HeroNode(3, "吴用");
        HeroNode node2 = new HeroNode(6, "卢俊义");
        HeroNode node3 = new HeroNode(8, "林冲");
        HeroNode node4 = new HeroNode(10, "戴院长");
        HeroNode node5 = new HeroNode(14, "武松");
        HeroNode node6 = new HeroNode(15, "大松");
        //root.setLeft(node1);
        //root.setRight(node2);
        //node1.setLeft(node3);
        //node1.setRight(node4);
        //node2.setLeft(node5);
        //node2.setRight(node6);

        PreOrderThreadedBinaryTree tree = new PreOrderThreadedBinaryTree(root);

        //System.out.println(node5.getLeft());
        //System.out.println(node5.getRight());

        tree.list();
    }
}

class PreOrderThreadedBinaryTree {
    private HeroNode root;

    private HeroNode pre;

    public PreOrderThreadedBinaryTree(HeroNode root) {
        this.root = root;
        this.preInOrderThreaded(root);
    }

    public void preInOrderThreaded(HeroNode root) {
        HeroNode node = root;
        if (node == null) {
            return;
        }
        //先索引化当前节点
        if (node.getLeft() == null) {
            node.setLeft(pre);
            node.setLeftStatus(1);
        }
        if (pre != null && pre.getRight() == null) {
            pre.setRight(node);
            pre.setRightStatus(1);
        }
        pre = node;

        //向左递归
        if (node.getLeftStatus() == 0) {
            preInOrderThreaded(node.getLeft());
        }

        //向右递归
        if (node.getRightStatus() == 0) {
            preInOrderThreaded(node.getRight());
        }
    }

    public void list() {
        if (root == null) {
            System.out.println("前序索引化二叉树为空！");
            return;
        }
        //打印出根节点
        System.out.println(root);
        HeroNode node = root;
        //循环遍历打印出除根节点以外的所有节点
        while (node.getRight() != null) {
            //找到第一个有后继节点的节点，并打印出此节点及其之前的节点（除根节点外）
            while (node.getRightStatus() == 0) {
                System.out.println(node.getLeft());
                node = node.getLeft();
            }
            //沿着后继节点的线索一直打印出所有后继节点
            while (node.getRightStatus() == 1) {
                System.out.println(node.getRight());
                node = node.getRight();
            }
            //node没有后继节点了，循环去找有后继节点的节点，并打印出再一次找到的有后继节点节点及之前节点（除根节点外）
        }
    }
}