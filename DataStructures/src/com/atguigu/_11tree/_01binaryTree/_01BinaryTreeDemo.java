package com.atguigu._11tree._01binaryTree;

/**
 * 二叉树遍历：真正执行遍历的我写在了英雄节点类内，可以使用this关键字
 *      前序遍历：先遍历根节点
 *      中序遍历：中间遍历根节点
 *      后序遍历：最后遍历根节点
 * 二叉树查找：真正执行遍历的我写在了二叉树类内，不可使用this关键字递归，需指定一个引用，为了方便调用，我写了一个重载
 *      前序查找
 *      中序查找
 *      后序查找
 * 二叉树删除：删除叶子节点或者子树
 *      在二叉树内完成root节点是否为空及是否要删除的就是root节点
 *      然后调用英雄节点内的删除方法，判断root的节点的左节点和右节点是否是要删除的节点，然后递归判断左右节点的左右节点，直至找到删除
 */
public class _01BinaryTreeDemo {
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
        /*
        System.out.println("前序遍历：");
        binaryTree.preOrder();
        System.out.println("中序遍历：");
        binaryTree.inOrder();
        System.out.println("后序遍历：");
        binaryTree.postOrder();
         */
        /*
        HeroNode heroNode = binaryTree.preOrderSearch(5);//前序查找
        System.out.println(heroNode);
        HeroNode heroNode1 = binaryTree.inOrderSearch(5);//中序查找
        System.out.println(heroNode1);
        HeroNode heroNode2 = binaryTree.postOrderSearch(5);//后序查找
        System.out.println(heroNode2);
         */
        System.out.println("前序遍历：");
        binaryTree.preOrder();
        binaryTree.delete(5);
        System.out.println("前序遍历：");
        binaryTree.preOrder();
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

    /**
     * 前序查找
     * @param no
     * @param node
     * @return
     */
    public HeroNode preOrderSearch(int no, HeroNode node) {
        System.out.println("-----------------");
        //1.与根节点比较
        if (no == node.getNo()) {
            return node;
        }
        HeroNode target = null;
        //2.向左递归查找左子树
        if (node.getLeft() != null) {
            target = preOrderSearch(no, node.getLeft());
        }
        //在左子树查到，直接返回
        if (target != null) {
            return target;
        }
        //3.向右递归查找右子树
        if (node.getRight() != null) {
            target = preOrderSearch(no, node.getRight());
        }
        return target;
    }
    //为了方便调用，重载一下
    public HeroNode preOrderSearch(int no) {
        return preOrderSearch(no, root);
    }

    //中序查找
    public HeroNode inOrderSearch(int no, HeroNode node) {
        HeroNode target = null;
        //1.查找左子树
        if (node.getLeft() != null) {
            target = inOrderSearch(no, node.getLeft());
        }
        //在左子树找到了，直接返回
        if (target != null) {
            return target;
        }
        //2.比较根节点，是则直接返回
        System.out.println("-----------------");
        if (no == node.getNo()) {
            return node;
        }
        //3.查找右子树
        if (node.getRight() != null) {
            target = inOrderSearch(no, node.getRight());
        }
        //直接返回，若找到了返回的是找到的HeroNode，若未找到，target仍是null
        return target;
    }

    //写个重载，方便调用
    public HeroNode inOrderSearch(int no) {
        return inOrderSearch(no, root);
    }

    //后序查找
    public HeroNode postOrderSearch(int no, HeroNode node) {
        HeroNode target = null;
        //1.查找左子树
        if (node.getLeft() != null) {
            target = postOrderSearch(no, node.getLeft());
        }
        if (target != null) {
            //找到了，直接返回
            return target;
        }
        //2.查找右子树
        if (node.getRight() != null) {
            target = postOrderSearch(no, node.getRight());
        }
        if (target != null) {
            //找到了，直接返回
            return target;
        }
        //3.对比根节点
        System.out.println("-----------------");
        if (no == node.getNo()) {
            return node;
        }
        return null;
    }

    //重载一下，方便调用
    public HeroNode postOrderSearch(int no) {
        return postOrderSearch(no, root);
    }

    /**
     * 删除节点
     * @param no
     */
    public void delete(int no) {
        if (root == null) {
            System.out.println("删除失败！");
        } else {
            if (no == root.getNo()) {
                root = null;
                System.out.println("删除成功！");
            } else {
                int success = root.delete(no);
                if (success == 1) {
                    System.out.println("删除成功！");
                } else {
                    System.out.println("删除失败！");
                }
            }
        }
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

    /**
     * 删除节点，如果是叶子节点直接删除，如果不是叶子节点，删除此子树
     * @param no
     * @return  0代表删除失败 1代表删除成功
     */
    public int delete(int no) {
        //判断左子节点是否是要删除的，是则直接删除，返回1
        if (this.getLeft() != null && no == this.getLeft().getNo()) {
            this.setLeft(null);
            return 1;
        }
        //判断右子节点是否是要删除的，是则直接删除，返回1
        if (this.getRight() != null && no == this.getRight().getNo()) {
            this.setRight(null);
            return 1;
        }
        //递归遍历左子树子树删除
        if (this.getLeft() != null) {
            this.getLeft().delete(no);
        }
        //递归遍历右子树删除
        if (this.getRight() != null) {
            this.getRight().delete(no);
        }
        //左子树和右子树未找到删除的节点
        return 0;
    }
}
