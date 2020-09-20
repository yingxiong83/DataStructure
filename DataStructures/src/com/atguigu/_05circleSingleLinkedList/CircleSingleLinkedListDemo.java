package com.atguigu._05circleSingleLinkedList;

public class CircleSingleLinkedListDemo {
    public static void main(String[] args) {
        CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
        circleSingleLinkedList.add(5);
        circleSingleLinkedList.show();
        System.out.println(circleSingleLinkedList.size());
        System.out.println("-----------------------------------");
        circleSingleLinkedList.solveJoseph(1, 2);
        circleSingleLinkedList.show();
    }
}

class CircleSingleLinkedList {
    private Boy first;//头指针，指向第一个环形链表的第一个元素

    public void add(int num) {
        if (num < 1) {
            System.out.println("输入参数有误");
            return;
        }
        Boy temp = null;
        for (int i = 1; i <= num; i++) {
            Boy boy = new Boy(i);
            if (i == 1) {
                first = boy;
            } else {
                temp.setNext(boy);
            }
            boy.setNext(first);
            temp = boy;
        }
    }

    public void show() {
        if (first == null) {
            System.out.println("环形链表为空！");
            return;
        }
        Boy temp = first;
        while (true) {
            System.out.println("男孩编号："+temp.getNo());
            temp = temp.getNext();
            if (temp == first) {
                break;
            }
        }
    }

    public void solveJoseph(int begin, int step) {
        if (begin > size()) {
            System.out.println("参数有误！");
        }
        Boy front = first;//通过下面的for循环使front指向开始的前一个
        for (int i = 1; i < (begin == 1 ? size() : begin); i++) {
            front = front.getNext();
        }
        while (true) {
            for (int i = 1; i < step; i++) {
                front = front.getNext();
            }
            System.out.println("取出的编号为：" + front.getNext().getNo());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (front == front.getNext()) {
                first = null;
                //建议带上，否则front指向front，发生内存泄漏，用不到垃圾回收器又收不了；
                //直接置front为null没用，因为该对象仍然会指向自己，只有让front.getNext（）不指向自己才行
                front.setNext(null);
                break;
            }
            front.setNext(front.getNext().getNext());
        }
    }

    public int size() {
        if (first == null) {
            return 0;
        }
        int i = 0;
        Boy cur = first;
        while (true) {
            i++;
            cur = cur.getNext();
            if (cur == first) {
                break;
            }
        }
        return i;
    }
}

class Boy {
    private int no;
    private Boy next;

    public Boy(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }
}

