package com.atguigu._04doubleLinkedList;

public class DoubleLinkedListDemo {
    public static void main(String[] args) {
        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
        HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode hero2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode hero3 = new HeroNode(3, "吴用", "智多星");
        HeroNode hero4 = new HeroNode(4, "林冲", "豹子头");

        doubleLinkedList.add(hero1);
        doubleLinkedList.add(hero2);
        doubleLinkedList.add(hero3);
        doubleLinkedList.add(hero4);
        doubleLinkedList.list();
        System.out.println("-----------------------------------------");
        doubleLinkedList.update(new HeroNode(4, "公孙胜", "入云龙"));
        doubleLinkedList.list();
        System.out.println("-----------------------------------------");
        doubleLinkedList.delete(2);
        doubleLinkedList.list();
        System.out.println("-----------------------------------------");
        doubleLinkedList.addByOrder(hero2);
        doubleLinkedList.list();
    }
}

class DoubleLinkedList {
    HeroNode header = new HeroNode(0, "", "");

    public void list() {
        if (header.next == null) {
            System.out.println("链表为空！");
        }
        HeroNode cur = header.next;
        while (true) {
            if (cur == null) {
                break;
            }
            System.out.println(cur);
            cur = cur.next;
        }
    }

    public void add(HeroNode newHeroNode) {
        HeroNode cur = header;
        while (true) {
            if (cur.next == null) {
                cur.next = newHeroNode;
                newHeroNode.pre = cur;
                break;
            }
            cur = cur.next;
        }
    }

    public void addByOrder(HeroNode newHeroNode) {
        HeroNode cur = header;
        while (true) {
            if (cur.next == null) {
                cur.next = newHeroNode;
                newHeroNode.pre = cur;
                break;
            }
            if (cur.next.no > newHeroNode.no) {
                HeroNode temp1 = cur.next;
                cur.next = newHeroNode;
                newHeroNode.next = temp1;
                temp1.pre = newHeroNode;
                newHeroNode.pre = cur;
                break;
            }
            cur = cur.next;
        }
    }

    public void update(HeroNode newHeroNode) {
        HeroNode cur = header.next;
        while (true) {
            if (cur == null) {
                System.out.println("不存在该人物" + newHeroNode);
                break;
            }
            if (cur.no == newHeroNode.no) {
                cur.pre.next = newHeroNode;
                newHeroNode.pre = cur.pre;
                if (cur.next != null) {
                    newHeroNode.next = cur.next;
                    cur.next.pre = newHeroNode;
                }
                break;
            }
            cur = cur.next;
        }
    }

    public void delete(int no) {
        HeroNode cur = header.next;
        while (true) {
            if (cur == null) {
                System.out.println("没有这个编号的英雄" + no);
                break;
            }
            if (cur.no == no) {
                cur.pre.next = cur.next;
                cur.next.pre = cur.pre;
                break;
            }
            cur = cur.next;
        }
    }
}

class HeroNode {
    public int no;
    public String name;
    public String nickName;
    public HeroNode pre;
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