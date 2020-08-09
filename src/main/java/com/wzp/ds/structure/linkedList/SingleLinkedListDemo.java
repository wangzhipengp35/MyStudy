package com.wzp.ds.structure.linkedList;

import jdk.internal.org.objectweb.asm.util.CheckAnnotationAdapter;

import java.util.Stack;

public class SingleLinkedListDemo {

    public static void main(String[] args) {
        //进行测试
        //先创建一个节点
        HeroNode heroNode1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode heroNode2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode heroNode3 = new HeroNode(3, "吴用", "智多星");
        HeroNode heroNode4 = new HeroNode(4, "林冲", "豹子头");

//        SingleLinkedList singleLinkedList = new SingleLinkedList();
//        singleLinkedList.add(heroNode1);
//        singleLinkedList.add(heroNode2);
//        singleLinkedList.add(heroNode3);
//        singleLinkedList.add(heroNode4);


        SingleLinkedList singleLinkedList = new SingleLinkedList();
        singleLinkedList.addByOrder(heroNode1);
        singleLinkedList.addByOrder(heroNode4);
        singleLinkedList.addByOrder(heroNode3);
        singleLinkedList.addByOrder(heroNode2);
        singleLinkedList.addByOrder(heroNode3);

        HeroNode newHeroNode = new HeroNode(2, "小卢", "玉麒麟~~");
        singleLinkedList.list();
        singleLinkedList.update(newHeroNode);
//        singleLinkedList.delete(1);
//        singleLinkedList.list();
//
//        singleLinkedList.delete(3);
//        singleLinkedList.list();
//        System.out.println(SingleLinkedList.findLastNode(singleLinkedList.getHead(),3));
//        System.out.println(SingleLinkedList.getLength(singleLinkedList.getHead()));

        System.out.println("-----------------------------------------------------------");
//        SingleLinkedList.reverseList(singleLinkedList.getHead());
        SingleLinkedList.reversePrint(singleLinkedList.getHead());
//        singleLinkedList.list();
    }



}

//定义SingleLinkedList，管理我们的英雄
class SingleLinkedList {
    //先配置一个头节点，头节点不要动
    private HeroNode head = new HeroNode(0,"","");

    public HeroNode getHead() {
        return head;
    }

    //添加节点到单向链表
    //当不考虑编号顺序时，找到当前链表的最后一个节点
    //将最后这个节点的next 指向新的节点
    public void add(HeroNode heroNode){

        //因为head节点不能动，因为我们需要一个辅助便利temp
        HeroNode temp = head;

        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = heroNode;
    }


    public void addByOrder(HeroNode heroNode){

        //因为head节点不能动，因为我们需要一个辅助便利temp
        HeroNode temp = head;
        boolean flag = false; //标志添加的编号是否存在，默认为false

        while (true) {
            if (temp.next == null) {//说明temp已经在链表的最后
                break;
            }
            if (temp.next.no > heroNode.no){ //位置找到了，就在temp的后边插入
                break;
            } else if(temp.next.no == heroNode.no) { //说明希望添加的heroNode的编号已经存在
                flag = true;
                break;
            }
            temp = temp.next; //后移，遍历当前链表
        }
        if (flag){
            System.out.printf("准备插入的英雄的编号%d 已经存在了，不能插入\n",heroNode.no);
        } else {
            heroNode.next = temp.next;
            temp.next = heroNode;
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

        HeroNode temp = head.next;
        while (temp != null) {
            System.out.println(temp);
            temp = temp.next;
        }
    }


    public void update(HeroNode newheroNode){
        //先判断链表是否为空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        //找到需要修改的节点，根据no编号
        //定义一个辅助变量
        HeroNode temp = head.next;
        boolean flag = false; //表示是否找到该节点
        while (true){
            if (temp == null){
                break; //已经遍历完
            }
            if (temp.no == newheroNode.no){
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

        HeroNode temp = head;
        boolean flag = false;

        while (true){
            if (temp.next == null){
                break; //已经遍历完
            }
            if (temp.next.no == delNo){
                flag = true;
                break;
            }
            temp = temp.next;
        }

        if(flag){
            temp.next = temp.next.next;
        }
    }

    //获取单链表的节点个数
    public static int getLength(HeroNode head){
        //先判断链表是否为空
        if (head.next == null) {
            System.out.println("链表为空");
            return 0;
        }

        int lenght = 0;
        HeroNode temp = head.next;
        while (head.next != null){
            lenght++;
            head = head.next;
        }
        return lenght;

    }


    //查找单链表中的倒数第K个节点
    //1.编写一个方法，接收head节点，同时接收一个index
    //2.index表示是倒数第index个节点
    public static HeroNode findLastNode(HeroNode head, int index){
        //先判断链表是否为空
        if (head.next == null) {
            System.out.println("链表为空");
            return null;
        }

        //第一次遍历得到链表的节点个数
        int size = getLength(head);
        //第二次遍历 size - index 的位置，就是我们倒数的第K个节点
        //先做一个index的教研
        if (index <= 0 || index > size){
            return null;
        }

        //定义一个辅助变量
        HeroNode currentNode = head.next;
        for (int i = 0;i < size -index;i++){
            currentNode = currentNode.next;
        }
        return currentNode;
    }

    //将单链表进行反转
    public static void reverseList(HeroNode head){
        //先判断链表是否为空
        if (head.next != null && head.next.next != null) {
            HeroNode cur = head.next;
            HeroNode next  = null; //指向当前节点的下一个节点

            HeroNode reverseHead = new HeroNode(0,"","");

            while (cur != null){
                next = cur.next;
                cur.next = reverseHead.next;
                reverseHead.next =cur;
                cur = next;
            }
            head.next = reverseHead.next;
        }

    }

    public static void reversePrint(HeroNode head){
        //先判断链表是否为空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }

        Stack<HeroNode> stack = new Stack<>();
        HeroNode cur = head.next;

        while (cur != null){
            stack.push(cur);
            cur = cur.next;
        }

        while (stack.size() > 0 ){
            System.out.println(stack.pop());
        }

    }

}

//定义heroNode ,每个HeroNode对象就是一个节点
class HeroNode {
    public int no;
    public String name;
    public String nickName;
    public HeroNode next;

    //构造器
    public HeroNode(int no,String name,String nickName) {
        this.no = no;
        this.name = name;
        this.nickName = nickName;
    }

    //为了显示方便 ，重写toString方法
    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + nickName +
                '}';
    }


}
