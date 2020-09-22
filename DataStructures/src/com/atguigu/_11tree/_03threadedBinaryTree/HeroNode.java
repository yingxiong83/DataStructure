package com.atguigu._11tree._03threadedBinaryTree;

/**
 * 英雄节点
 *      id、name、left、leftStatus、right、rightStatus属性
 *      getter、setter、toString方法（id、name）
 */
public class HeroNode {
    private int no;
    private String name;
    private HeroNode left;
    private int leftStatus;//表示左指针的状态，0代表左指针指向左子树，1代表左指针指向前驱节点
    private HeroNode right;
    private int rightStatus;//表示右指针的状态，0代表右指针指向右子树，1代表右指针指向后继节点

    public HeroNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HeroNode getLeft() {
        return left;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public int getLeftStatus() {
        return leftStatus;
    }

    public void setLeftStatus(int leftStatus) {
        this.leftStatus = leftStatus;
    }

    public HeroNode getRight() {
        return right;
    }

    public void setRight(HeroNode right) {
        this.right = right;
    }

    public int getRightStatus() {
        return rightStatus;
    }

    public void setRightStatus(int rightStatus) {
        this.rightStatus = rightStatus;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }
}

