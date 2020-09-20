package com.atguigu._10hashtable;

import java.util.Scanner;

/**
 * 手写哈希表    数组+链表
 */
public class _01HashTableDemo {
    public static void main(String[] args) {
        HashTable hashTable = new HashTable(8);
        String key = "";
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;

        int id = -1;
        String name = "";
        Emp emp = null;

        while (loop) {
            System.out.println("------------------------------------------------------------------------------------");
            System.out.println("add:添加雇员");
            System.out.println("delete:删除雇员");
            System.out.println("update:修改雇员信息");
            System.out.println("find:查找雇员");
            System.out.println("list:显示所有雇员");
            System.out.println("exit:退出");
            key = scanner.next();
            switch (key) {
                case "add":
                    System.out.print("请输入雇员id：");
                    id = scanner.nextInt();
                    System.out.print("请输入雇员姓名：");
                    name = scanner.next();
                    emp = new Emp(id, name);
                    hashTable.add(emp);
                    break;
                case "delete":
                    System.out.print("请输入要删除的雇员id：");
                    id = scanner.nextInt();
                    hashTable.delete(id);
                    break;
                case "update":
                    System.out.print("请输入要修改的雇员id：");
                    id = scanner.nextInt();
                    System.out.print("请输入要修改的雇员姓名：");
                    name = scanner.next();
                    emp = new Emp(id, name);
                    hashTable.update(emp);
                    break;
                case "find":
                    System.out.print("请输入要查找的雇员id：");
                    id = scanner.nextInt();
                    hashTable.findById(id);
                    break;
                case "list":
                    hashTable.list();
                    break;
                case "exit":
                    scanner.close();
                    loop = false;
                    break;
                default:
                    System.out.println("输入有误！");
                    break;
            }
        }
    }
}

//哈希表
class HashTable {
    //数组，每一个元素是一个雇员链表
    private EmpLinkedList[] hashtable;
    //数组长度
    private int size;

    //哈希表构造函数
    public HashTable(int size) {
        //哈希表大小
        this.size = size;
        //初始化哈希表，
        //第一步，创建空哈希表
        this.hashtable = new EmpLinkedList[size];
        //第二步，为哈希表每一个元素创建一个链表
        for (int i = 0; i < size; i++) {
            this.hashtable[i] = new EmpLinkedList(i);
        }
    }

    /**
     * 添加雇员
     * @param emp
     */
    public void add(Emp emp) {
        //获取该雇员应该放在哈希表的哪个位置
        int no = hashFun(emp.getId());
        //添加
        int success = hashtable[no].add(emp);
        if (success == 1) {
            System.out.println("添加成功");
        } else {
            System.out.println("添加失败");
        }
    }

    /**
     * 删除雇员
     * @param id
     */
    public void delete(int id) {
        //获取此id雇员所在哈希表位置
        int no = hashFun(id);
        //删除
        int success = hashtable[no].delete(id);
        if (success == 1) {
            System.out.println("删除成功");
        } else {
            System.out.println("删除失败");
        }
    }

    /**
     * 更新雇员信息
     * @param emp
     */
    public void update(Emp emp) {
        //获取要更新的雇员所在哈希表的位置
        int no = hashFun(emp.getId());
        //更新
        int success = hashtable[no].update(emp);
        if (success == 1) {
            System.out.println("更新成功");
        } else {
            System.out.println("更新失败");
        }
    }

    /**
     * 根据id查找雇员信息
     * @param id
     */
    public void findById(int id) {
        //获取此id雇员所在哈希表位置
        int no = hashFun(id);
        //查找
        Emp emp = hashtable[no].findById(id);
        if (emp == null) {
            System.out.println("没有该雇员");
        } else {
            System.out.println(emp);
        }
    }

    /**
     * 显示所有的雇员
     */
    public void list() {
        for (int i = 0; i < hashtable.length; i++) {
            hashtable[i].list();
        }
    }

    /**
     * 散列函数：根据雇员id决定其在哪个链表
     * @param id
     * @return
     */
    public int hashFun(int id) {
        return id % size;
    }
}

//哈希表对应的每一个链表
//对于单向链表而言，增删都要基于前一个节点。改查基于当前节点即可
class EmpLinkedList {
    //链表头，存放一个雇员，相当于没有空头节点
    private Emp header;
    //标识链表在哈希表中的位置
    private int no;

    //构造函数，创建该链表时唯一标识该链表
    public EmpLinkedList(int no) {
        this.no = no;
    }

    /**
     * 添加雇员
     * @param emp
     * @return 0:添加失败   1:添加成功
     */
    public int add(Emp emp) {
        //链表为空，直接加入使header指向该雇主，返回1
        if (header == null) {
            header = emp;
            return 1;
        }
        //查看链表中是否已存在该id的雇员，存在则添加失败，返回0
        if (findById(emp.getId()) != null) {
            return 0;
        }
        //链表不为空，且不存在该雇员，添加到末尾元素的下一个位置
        Emp cur = header;
        //先找到末尾元素
        while (cur.next != null) {
            cur = cur.next;
        }
        //添加，返回1
        cur.next = emp;
        return 1;
    }

    /**
     * 遍历链表
     */
    public void list() {
        if (header == null) {
            System.out.println(no + "号链表为空");
            return;
        }
        System.out.print(no + "号链表元素为\t");
        Emp cur = header;
        while (cur != null) {
            System.out.print("==>" + cur + "\t");
            cur = cur.next;
        }
        System.out.println();
    }

    /**
     * 根据ID查找雇员
     * @param id
     * @return
     */
    public Emp findById(int id) {
        Emp cur = header;
        while (cur != null) {
            if (cur.getId() == id) {
                return cur;
            }
            cur = cur.next;
        }
        return null;
    }

    /**
     * 删除雇员
     * @param id
     * @return  0:删除失败  1:删除成功
     */
    public int delete(int id) {
        if (header == null) {
            return 0;
        }
        if (header.getId() == id) {
            header = null;
            return 1;
        }
        Emp cur = header;
        while (cur.next != null) {
            if (cur.next.getId() == id) {
                cur.next = cur.next.next;
                return 1;
            }
            cur = cur.next;
        }
        return 0;
    }

    /**
     * 更新雇员信息
     * @param emp
     * @return  0:更新失败  1:更新成功
     */
    public int update(Emp emp) {
        Emp cur = header;
        while (cur != null) {
            if (cur.getId() == emp.getId()) {
                cur.setName(emp.getName());
                return 1;
            }
            cur = cur.next;
        }
        return 0;
    }
}

//雇员
class Emp {
    private int id;
    private String name;

    public Emp next;//指向下一个雇员，默认为空

    public Emp(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "emp{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

