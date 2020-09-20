package com.atguigu._03singleLinkedList;

import java.util.Stack;

/**
 * 带有头节点的单向链表
 *
 * 解决单向链表的最有效的办法是画示意图，一步步演示，变量怎么存，需要移动哪些节点，怎么移动
 */
public class SingleLinkedListDemo {
    public static void main(String[] args) {
        HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode hero2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode hero3 = new HeroNode(3, "吴用", "智多星");
        //HeroNode hero3_1 = new HeroNode(3, "小吴用", "智多星");
        HeroNode hero4 = new HeroNode(4, "林冲", "豹子头");
        HeroNode hero5 = new HeroNode(5, "林冲", "豹子头");
        HeroNode hero6 = new HeroNode(6, "林冲", "豹子头");

        SingleLinkedList singleLinkedList1 = new SingleLinkedList();
        singleLinkedList1.addByOrder(hero1);
        singleLinkedList1.addByOrder(hero3);
        singleLinkedList1.addByOrder(hero5);
        SingleLinkedList singleLinkedList2 = new SingleLinkedList();
        singleLinkedList2.addByOrder(hero2);
        //singleLinkedList2.addByOrder(hero3_1);
        singleLinkedList2.addByOrder(hero4);
        singleLinkedList2.addByOrder(hero6);

        SingleLinkedList singleLinkedList = mergeInOrder(singleLinkedList1.getHeader(), singleLinkedList2.getHeader());
        singleLinkedList.list();

        System.out.println("--------------------------------------------------------");
        singleLinkedList1.list();
        System.out.println("----------------------------------------------------------");
        singleLinkedList2.list();

        //singleLinkedList.add(hero1);
        //singleLinkedList.add(hero2);
        //singleLinkedList.add(hero3);
        //singleLinkedList.add(hero4);

        /*singleLinkedList.addByOrder(hero1);
        singleLinkedList.addByOrder(hero3);
        singleLinkedList.addByOrder(hero4);
        singleLinkedList.addByOrder(hero2);
        singleLinkedList.addByOrder(hero5);
        singleLinkedList.addByOrder(hero6);

        singleLinkedList.list();

        System.out.println("-----------------------------------");
        reversePrint(singleLinkedList.getHeader());*/

        /*reverse(singleLinkedList.getHeader());

        System.out.println("------------------------------------");
        singleLinkedList.list();*/

        //singleLinkedList.list();


        //singleLinkedList.update(new HeroNode(5, "小宋", "及时与"));
        //System.out.println("============================================================");
        //singleLinkedList.delete(5);
        //singleLinkedList.list();

        /*
        System.out.println(sizeOfLinkedList(singleLinkedList.getHeader()));

        singleLinkedList.list();
        HeroNode lastNode = getLastNode(singleLinkedList.getHeader(), 5);
        System.out.println("倒数人物是：" + lastNode);*/


    }

    //合并两个链表，保证其有序
    public static SingleLinkedList mergeInOrder(HeroNode header1, HeroNode header2) {
        if (header1.next == null && header2.next == null) {
            return new SingleLinkedList(header1);
        } else if (header1.next == null && header2.next != null) {
            return new SingleLinkedList(header2);
        } else if (header1.next != null && header2.next == null) {
            return new SingleLinkedList(header1);
        }

        HeroNode front1 = header1;
        HeroNode front2 = header2;
        while (true) {
            if (front1.next == null && front2.next == null) {
                break;
            } else if (front1.next != null && front2.next == null) {
                break;
            } else if (front1.next == null && front2.next != null) {
                front1.next = front2.next;
                front2.next = null;
                break;
            } else {
                if (front1.next.no < front2.next.no) {
                    front1 = front1.next;
                } else if (front1.next.no > front2.next.no) {
                    HeroNode temp1 = front1.next;
                    front1.next = front2.next;
                    HeroNode temp2 = front2.next.next;
                    front2.next.next = temp1;
                    header2.next = temp2;
                    front1 = front1.next;
                } else {
                    header2.next = header2.next.next;
                    front2 = header2;
                }
            }
        }
        return new SingleLinkedList(header1);
    }

    //将单链表倒序输出
    public static void reversePrint(HeroNode header) {
        if (header.next == null) {
            System.out.println("链表为空！");
            return;
        }
        Stack<HeroNode> stack = new Stack<HeroNode>();
        HeroNode cur = header.next;
        while (cur != null) {
            stack.push(cur);
            cur = cur.next;
        }
        while (stack.size() > 0) {
            System.out.println(stack.pop());
        }
    }

    //将单链表进行反转
    /**
     * 具体过程自己画画流程图就懂了，以四个元素为例
     * @param header
     */
    public static void reverse(HeroNode header) {
        //如果链表为空或只有一个元素，不用反转
        if (header.next == null || header.next.next == null) {
            return;
        }
        HeroNode front = header.next;
        while (true) {
            if (front.next == null) {
                return;
            }
            HeroNode temp1 = header.next;
            header.next = front.next;
            HeroNode temp2 = front.next.next;
            front.next.next = temp1;
            front.next = temp2;
        }
    }


    //取链表的倒数第k个元素
    public static HeroNode getLastNode(HeroNode header, int k) {
        if (header.next == null) {
            return null;
        }
        int size = sizeOfLinkedList(header);
        if (k <= 0 || k > size) {
            return null;
        }
        HeroNode cur = header.next;
        for (int i = 0; i < (size - k); i++) {
            cur = cur.next;
        }
        return cur;
    }


    //计算单链表的有效元素个数，不算头节点
    public static int sizeOfLinkedList(HeroNode header) {
        if (header.next == null) {
            return 0;
        }
        HeroNode cur = header.next;
        int length = 0;
        while (true) {
            if (cur == null) {
                return length;
            }
            length++;
            cur = cur.next;
        }
    }
}

class SingleLinkedList {
    private HeroNode header = new HeroNode(0, "", "");

    public HeroNode getHeader() {
        return header;
    }

    public SingleLinkedList() {
    }

    public SingleLinkedList(HeroNode header) {
        this.header = header;
    }

    public void add(HeroNode heroNode) {
        HeroNode temp = header;
        while (true) {
            if (temp.next == null) {
                break;
            }
            if (temp.next == heroNode) {
                System.out.println("添加失败：人物已经存在" + heroNode);
                return;
            }
            temp = temp.next;
        }
        temp.next = heroNode;
    }

    public void addByOrder(HeroNode heroNode) {
        HeroNode temp = header;
        boolean flag = false;//判断元素是否重复
        while (true) {
            if (temp.next == null) {//到链表尾部了，跳出循环，插在尾部
                break;
            }
            if (temp.next.no > heroNode.no) {//应该插在temp和temp.next之间
                break;
            } else if (temp.next.no == heroNode.no) {//重复
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            System.out.println("添加失败：人物已经存在" + heroNode);
            return;
        }
        heroNode.next = temp.next;
        temp.next = heroNode;
    }

    public void update(HeroNode newHeroNode) {
        HeroNode temp = header;
        boolean flag = false;//false表示没找到
        while (true) {
            if (temp.next == null) {
                break;
            }
            if (temp.next.no == newHeroNode.no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            temp.next.name = newHeroNode.name;
            temp.next.nickName = newHeroNode.nickName;
        } else {
            System.out.println("修改失败：不存在该人物" + newHeroNode);
        }
    }

    public void delete(int no) {
        HeroNode temp = header;
        boolean flag = false;//是否找到自编号的人物
        while (true) {
            if (temp.next == null) {
                break;
            }
            if (temp.next.no == no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            temp.next = temp.next.next;
        } else {
            System.out.println("删除失败：该编号对应的人物不存在" + no);
        }
    }

    public void list() {
        if (header.next == null) {
            System.out.println("链表为空。。。");
            return;
        }
        HeroNode temp = header;
        while (temp.next != null) {
            System.out.println(temp.next);
            temp = temp.next;
        }
    }
}

class HeroNode {
    public int no;
    public String name;
    public String nickName;
    public HeroNode next;

    public HeroNode(int no, String name, String nickName) {
        this.no = no;
        this.name = name;
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}
