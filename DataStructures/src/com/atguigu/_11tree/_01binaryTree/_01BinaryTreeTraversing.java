package com.atguigu._11tree._01binaryTree;

/**
 * 二叉树遍历
 *      前序遍历：先遍历根节点
 *      中序遍历：中间遍历根节点
 *      后序遍历：最后遍历根节点
 */
public class _01BinaryTreeTraversing {
    public static void main(String[] args) {
        HeroNode node1 = new HeroNode(1, "宋江");
        HeroNode node2 = new HeroNode(2, "吴用");
        HeroNode node3 = new HeroNode(3, "卢俊义");
        HeroNode node4 = new HeroNode(4, "林冲");
        HeroNode node5 = new HeroNode(5, "武松");
        node1.setLeft(node2);
        node1.setRight(node3);
        node3.setRight(node4);
        node3.setLeft(node5);
        BinaryTree binaryTree = new BinaryTree(node1);
        System.out.println("前序遍历：");
        binaryTree.preOrder();
        System.out.println("中序遍历：");
        binaryTree.inOrder();
        System.out.println("后序遍历：");
        binaryTree.postOrder();
    }
}


/**
 * 二叉树
 */
class BinaryTree {
    //根节点
    private HeroNode root;

    public BinaryTree(HeroNode root) {
        this.root = root;
    }

    public HeroNode getRoot() {
        return root;
    }

    public void setRoot(HeroNode root) {
        this.root = root;
    }

    /**
     * 前序遍历
     */
    public void preOrder() {
        if (root == null) {
            System.out.println("二叉树为空");
            return;
        }
        root.preOrder();
    }

    /**
     * 中序遍历
     */
    public void inOrder() {
        if (root == null) {
            System.out.println("二叉树为空");
            return;
        }
        root.inOrder();
    }

    /**
     * 后序遍历
     */
    public void postOrder() {
        if (root == null) {
            System.out.println("二叉树为空");
            return;
        }
        root.postOrder();
    }
}

/**
 * 英雄节点
 *      四个属性：编号、姓名、左子节点引用、右子节点引用
 *      方法：
 *          getter、setter、toString
 *          前序遍历方法preOrder
 *          中序遍历方法inOrder
 *          后序遍历方法postOrder
 *      为什么将遍历的方法写在节点的类里面：
 *          若将遍历的方法直接写在二叉树的类里面，递归遍历子树的时候必须记住传入子树的根节点，即遍历方法有一个参数
 *          而写在节点的类里面，可以直接用this关键字，省去了传参，遍历方法无参数，使得使用时体验更好，直接用二叉树对象调遍历方法即可
 */
class HeroNode {
    private int no;
    private String name;
    private HeroNode left;
    private HeroNode right;

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

    public HeroNode getRight() {
        return right;
    }

    public void setRight(HeroNode right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }

    /**
     * 前序遍历
     */
    public void preOrder() {
        //1.先输出根节点
        System.out.println(this);
        //2.遍历左子树
        if (this.left != null) {
            this.left.preOrder();
        }
        //3.遍历右子树
        if (this.right != null) {
            this.right.preOrder();
        }
    }

    /**
     * 中序遍历
     */
    public void inOrder() {
        //1.遍历左子树
        if (this.left != null) {
            this.left.inOrder();
        }
        //2.输出根节点
        System.out.println(this);
        //3.遍历右子树
        if (this.right != null) {
            this.right.inOrder();
        }
    }

    /**
     * 后序遍历
     */
    public void postOrder() {
        //1.遍历左子树
        if (this.left != null) {
            this.left.postOrder();
        }
        //2.遍历右子树
        if (this.right != null) {
            this.right.postOrder();
        }
        //3.输出根节点
        System.out.println(this);
    }
}
