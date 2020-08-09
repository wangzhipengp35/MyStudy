package com.wzp.ds.structure.linkedList;

public class DoubleLinkedListDemo {

    public static void main(String[] args) {
        //测试
        System.out.println("双向链表的测试");
        // 先创建节点
        DoubleHeroNode heroNode1 = new DoubleHeroNode(1, "宋江", "及时雨");
        DoubleHeroNode heroNode2 = new DoubleHeroNode(2, "卢俊义", "玉麒麟");
        DoubleHeroNode heroNode3 = new DoubleHeroNode(3, "吴用", "智多星");
        DoubleHeroNode heroNode4 = new DoubleHeroNode(4, "林冲", "豹子头");

        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
//        doubleLinkedList.add(heroNode1);
//        doubleLinkedList.add(heroNode2);
//        doubleLinkedList.add(heroNode3);
//        doubleLinkedList.add(heroNode4);

        doubleLinkedList.addByOrder(heroNode4);
        doubleLinkedList.addByOrder(heroNode3);
        doubleLinkedList.addByOrder(heroNode2);
        doubleLinkedList.addByOrder(heroNode1);


        DoubleHeroNode newHeroNode4 = new DoubleHeroNode(4, "林冲~~", "豹子头~~");
        doubleLinkedList.update(newHeroNode4);
        doubleLinkedList.list();

        System.out.println("----------------------------------");

        doubleLinkedList.delete(2);
        doubleLinkedList.list();

    }
}

class DoubleLinkedList{
    private DoubleHeroNode head = new DoubleHeroNode(0,"","");

    public DoubleHeroNode getHead() {
        return head;
    }

    public void add(DoubleHeroNode newHeroNode){

        //因为head节点不能动，因为我们需要一个辅助便利temp
        DoubleHeroNode temp = head;

        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = newHeroNode;
        newHeroNode.pre = temp;
    }

    public void addByOrder(DoubleHeroNode newHeroNode){

        //因为head节点不能动，因为我们需要一个辅助便利temp
        DoubleHeroNode temp = head;
        boolean flag = false; //标志添加的编号是否存在，默认为false

        while (true) {
            if (temp.next == null) {//说明temp已经在链表的最后
                break;
            }
            if (temp.next.no > newHeroNode.no){ //位置找到了，就在temp的后边插入
                break;
            } else if(temp.next.no == newHeroNode.no) { //说明希望添加的heroNode的编号已经存在
                flag = true;
                break;
            }
            temp = temp.next; //后移，遍历当前链表
        }
        if (flag){
            System.out.printf("准备插入的英雄的编号%d 已经存在了，不能插入\n",newHeroNode.no);
        } else {
            newHeroNode.next = temp.next;
            temp.next = newHeroNode;
            newHeroNode.pre = temp;
            if (newHeroNode.next != null){
                newHeroNode.next.pre = newHeroNode;
            }

        }
    }

    //显示链表[遍历]
    public void list(){
        //先判断链表是否为空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        //因为头节点不能动，所以我们需要一个temp变量来遍历

        DoubleHeroNode temp = head.next;
        while (temp != null) {
            System.out.println(temp);
            temp = temp.next;
        }
    }

    public void update(DoubleHeroNode newheroNode) {
        //先判断链表是否为空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        //找到需要修改的节点，根据no编号
        //定义一个辅助变量
        DoubleHeroNode temp = head.next;
        boolean flag = false; //表示是否找到该节点
        while (true) {
            if (temp == null) {
                break; //已经遍历完
            }
            if (temp.no == newheroNode.no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }

        //根据flag判断是否找到要修改的节点
        if (flag) {
            temp.name = newheroNode.name;
            temp.nickName = newheroNode.nickName;
        }else {
            System.out.printf("没有找到编号 %d 的节点，不能修改\n",newheroNode.no);
        }
    }

    public void delete(int delNo){
        //先判断链表是否为空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }

        DoubleHeroNode currentNode = head.next;
        boolean flag = false;

        while (true){
            if (currentNode == null){
                break; //已经遍历完
            }
            if (currentNode.no == delNo){
                flag = true;
                break;
            }
            currentNode = currentNode.next;
        }

        if(flag){
            currentNode.pre.next = currentNode.next;
            if (currentNode.next != null) {
                currentNode.next.pre = currentNode.pre;
            }
        }else {
            System.out.printf("要删除的节点:%d不存在",delNo);
        }
    }

}

class DoubleHeroNode {
    public int no;
    public String name;
    public String nickName;
    public DoubleHeroNode next;
    public DoubleHeroNode pre;

    public DoubleHeroNode(int no, String name, String nickName) {
        this.no = no;
        this.name = name;
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "DoubleHeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}