package com.atguigu._11tree._03threadedBinaryTree;

/**
 * 中序线索化二叉树练习
 *
 * 个人总结：
 *      将一个普通二叉树中序索引化的步骤：递归之前，判断传入的节点是否为空，为空则返回上一级
 *          ①中序索引化root节点左子数（递归，每次传入要中序索引化左子树的根节点）
 *          ②索引化当前节点
 *
 *          ③中序索引化root节点右子树（递归，每次传入要中序索引化右子树的根节点
 *
 *      遍历一个中序索引化二叉树的步骤
 */
public class _01InOrderThreadedBinaryTree {
    public static void main(String[] args) {
        HeroNode root = new HeroNode(1, "宋江");
        HeroNode node1 = new HeroNode(3, "吴用");
        HeroNode node2 = new HeroNode(6, "卢俊义");
        HeroNode node3 = new HeroNode(8, "林冲");
        HeroNode node4 = new HeroNode(10, "戴院长");
        HeroNode node5 = new HeroNode(14, "武松");
        root.setLeft(node1);
        root.setRight(node2);
        node1.setLeft(node3);
        node1.setRight(node4);
        node2.setLeft(node5);

        InOrderThreadedBinaryTree tree = new InOrderThreadedBinaryTree(root);

        System.out.println(node3.getLeft());
        System.out.println(node3.getRight());

        //tree.list();
    }
}

/**
 * 中序线索化二叉树
 */
class InOrderThreadedBinaryTree {
    private HeroNode root;//线索化二叉树的根节点

    private HeroNode pre;//每一个节点的前驱节点

    public InOrderThreadedBinaryTree(HeroNode root) {
        //初始化该二叉树
        this.root = root;
        //中序线索化该二叉树，使其成为中序线索化二叉树
        this.inOrderThreaded(root);
    }

    /**
     * 中序线索化二叉树
     */
    public void inOrderThreaded(HeroNode node) {
        //节点为空，不需要线索化，返回上一级
        if (node == null) {
            return;
        }
        //开始线索化
        //1.线索化左子树
        inOrderThreaded(node.getLeft());
        //2.线索化当前节点左指针，线索化当前节点的前驱节点的右指针
        //如果当前节点的左指针为空，则将左指针指向其前驱节点，同时设置左指针状态为1，表示此指针是前驱指针
        if (node.getLeft() == null) {
            node.setLeft(pre);
            node.setLeftStatus(1);
        }
        //【有难度的一点】转换思维：如果该节点的前驱节点不为null同时该节点的前驱节点的右指针为空，设置该节点的前驱节点的右指针指向该节点，
        // 同时设置该节点前驱节点的右指针状态为1，表示此指针是后继指针
        if (pre != null && pre.getRight() == null) {
            pre.setRight(node);
            pre.setRightStatus(1);
        }
        //当前节点线索化完成，前驱节点设置为当前节点
        pre = node;
        //3.线索化右子树
        inOrderThreaded(node.getRight());
    }

    /**
     * 遍历中序索引化二叉树：可以使用循环了，不必递归，遍历速度变快了
     */
    public void list() {
        if (root == null) {
            System.out.println("此中序索引化二叉树为空！");
            return;
        }
        HeroNode temp = root;
        //找到第一个左指针状态为1的节点，此节点即为第一个要输出的节点
        while (temp != null) {
            while (temp.getLeftStatus() == 0) {
                temp = temp.getLeft();
            }
            //找到，输出
            System.out.println(temp);
            //循环输出该节点的所有后继节点
            while (temp.getRightStatus() == 1) {
                temp = temp.getRight();
                System.out.println(temp);
            }
            //temp的下一个节点为不再是后继节点
            temp = temp.getRight();
        }
    }
}