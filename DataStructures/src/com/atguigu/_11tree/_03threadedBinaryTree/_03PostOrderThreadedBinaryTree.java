package com.atguigu._11tree._03threadedBinaryTree;

import com.sun.jmx.snmp.SnmpNull;

/**
 * 后序索引化二叉树练习
 *      二叉树后序索引
 *      后序索引化二叉树遍历
 */
public class _03PostOrderThreadedBinaryTree {
    public static void main(String[] args) {
        HeroNode root = new HeroNode(1, "宋江");
        HeroNode node1 = new HeroNode(3, "吴用");
        HeroNode node2 = new HeroNode(6, "卢俊义");
        //HeroNode node3 = new HeroNode(8, "林冲");
        HeroNode node4 = new HeroNode(10, "戴院长");
        HeroNode node5 = new HeroNode(14, "武松");
        //HeroNode node6 = new HeroNode(15, "大松");
        root.setLeft(node1);
        root.setRight(node2);
        //node1.setLeft(node3);
        node1.setRight(node4);
        node2.setLeft(node5);
        //node2.setRight(node6);

        PostOrderThreadedBinaryTree tree = new PostOrderThreadedBinaryTree(root);

        //System.out.println(node4.getLeft());
        //System.out.println(node4.getRight());
        tree.list();
    }
}

class PostOrderThreadedBinaryTree {
    private HeroNode root;

    private HeroNode pre;

    public PostOrderThreadedBinaryTree(HeroNode root) {
        this.root = root;
        this.postOrderThreaded(root);
    }

    public void postOrderThreaded(HeroNode node) {
        if (node == null) {
            return;
        }
        postOrderThreaded(node.getLeft());
        postOrderThreaded(node.getRight());
        if (node.getLeft() == null) {
            node.setLeft(pre);
            node.setLeftStatus(1);
        }
        if (pre != null && pre.getRight() == null) {
            pre.setRight(node);
            pre.setRightStatus(1);
        }
        pre = node;
    }

    public void list() {
        if (root == null) {
            System.out.println("后序索引化二叉树为空！");
            return;
        }
        HeroNode node = root;
    }

}

/*
    do {
            while (node.getRightStatus() == 0) {
                node = node.getLeft();
            }
            System.out.println(node);
            while (node.getRightStatus() == 1) {
                System.out.println(node.getRight());
                node = node.getRight();
            }
        } while (node.getRight() != root);
 */