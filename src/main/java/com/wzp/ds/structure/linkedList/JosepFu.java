package com.wzp.ds.structure.linkedList;

public class JosepFu {
    public static void main(String[] args) {
        CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
        circleSingleLinkedList.addBoy(5);
        circleSingleLinkedList.showBoy();
        System.out.println(circleSingleLinkedList.getLength(circleSingleLinkedList.getFirst()));

        //测试出圈
        circleSingleLinkedList.countBoy(1,2,5);

    }
}

class CircleSingleLinkedList {
    //创建一个first节点，当前没有编号
    private Boy first = new Boy(-1);

    public Boy getFirst() {
        return first;
    }

    public void addBoy(int nums){
        if (nums < 1){
            System.out.println("nums的值不正确 ");
            return;
        }
        Boy curBoy = null;
        for (int i = 1; i <= nums; i++){
            //根据编号创建小孩节点
            Boy boy = new Boy(i);
            if (i == 1){
                first = boy;
                first.setNext(first);
                curBoy = first;
            }
            curBoy.setNext(boy);
            boy.setNext(first);
            curBoy = boy;

        }
    }

    public void showBoy(){
        if (first == null){
            System.out.println("没有任何小孩！！");
        }
        //因为first不能动
        Boy curBoy = first;
        while (true){
            System.out.printf("小孩的编号为 ： %d \n",curBoy.getNo());
            if (curBoy.getNext() == first){
                break;
            }
            curBoy = curBoy.getNext();
        }
    }

    public int getLength(Boy first){
        if (first == null){
            return 0;
        }
        Boy curBoy = first;
        int length = 0;

        while (true){
            length++;
            if (curBoy.getNext() == first){
                break;
            }
            curBoy = curBoy.getNext();
        }
        return length;
    }

    public void countBoy(int startNo,int countNo,int nums){
        //先对数据进行校验
        if (first == null || startNo < 1 || startNo > nums){
            System.out.println("参数输入有误，请重新输入");
        }

        Boy helper = first;
        while (helper.getNext() != first) {
            helper = helper.getNext();
        }

        for (int j = 0;j < startNo - 1;j++){
            first = first.getNext();
            helper = helper.getNext();
        }

        while (helper != first) {
            //让first 和helper同时移动countNo - 1次
            for (int j = 0; j < countNo - 1; j++) {
                first = first.getNext();
                helper = helper.getNext();
            }
            //这是first指向的节点，就是要出圈的节点
            System.out.printf("小孩%d出圈\n", first.getNo());
            first = first.getNext();
            helper.setNext(first);
        }
        System.out.printf("最后留在圈中的小孩编号%d \n",first.getNo());


    }
}

//创建一个Boy
class Boy {
    private int no;//编号
    private Boy next; //指向下一个节点，默认null

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

    @Override
    public String toString() {
        return "Boy{" +
                "no=" + no +
                '}';
    }
}