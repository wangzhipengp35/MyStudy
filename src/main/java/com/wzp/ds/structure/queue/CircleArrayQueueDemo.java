package com.wzp.ds.structure.queue;

import java.util.Scanner;

public class CircleArrayQueueDemo {

    public static void main(String[] args) {


        //测试一下
        //创建一个队列
        CircleArrayQueue circleArrayQueue = new CircleArrayQueue(4); //设置4，其队列的最大长度为3
        char key = ' '; //接收用户输入
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        //输出一个菜单
        while (loop) {
            System.out.println("s(show): 显示队列");
            System.out.println("e(exit): 推出程序");
            System.out.println("a(add): 添加数据到队列");
            System.out.println("g(get): 从队列取出数据");
            System.out.println("h(head): 查看队列头的数据");

            key = scanner.next().charAt(0);//接收第一个字符
            switch (key) {
                case 's' :
                    circleArrayQueue.showQueue();
                    break;
                case 'a' :
                    System.out.println("请输入一个int型的数值：");
                    circleArrayQueue.addQueue(scanner.nextInt());
                    break;
                case 'g' :
                    try {
                        int res = circleArrayQueue.getQueue();
                        System.out.printf("取出的数据是%d\n",res);
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h' :
                    try {
                        int res = circleArrayQueue.headQueue();
                        System.out.printf("队列头的数据是%d\n",res);
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e' : //退出
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;

            }

        }
    }

}

class CircleArrayQueue{
    private int maxSize;
    private int front; //队列头
    private int rear; //指向最后一个元素的后一个位置
    private int[] arr;

    public CircleArrayQueue(int arrayMaxSize) {
        maxSize = arrayMaxSize;
        arr = new int[maxSize];
        front = 0;
        rear = 0;
    }

    public boolean isFull(){
        return (rear + 1) % maxSize == front;
    }

    public boolean isEmpty(){
        return rear == front;
    }

    //添加数据到队列
    public void addQueue(int n) {
        //判断队列是否满
        if(isFull()) {
            System.out.println("队列满，不能加入数据");
            return;
        }
        arr[rear] = n;
        rear = (rear + 1) % maxSize;
    }

    //获取队列的数据，出队列
    public int getQueue() {
        //判断队列是否为空
        if(isEmpty()){
            //通过抛异常
            throw new RuntimeException("队列空，不能取数据");
        }
        //这里需要分析出front是指向队列的第一个元素
        //1.先把front对应的值保存到一个临时变量
        //2.将front后移
        //3.将临时保存的变量返回
        int value = arr[front];
        front = (front + 1) % maxSize;
        return value;
    }

    //显示队列所有数据
    public void showQueue(){
        if(isEmpty()){
            System.out.println("queue is null");
        }

        for (int i = front; i < front + size(); i++ ){
            System.out.printf("arr[%d]=%d\n",i % maxSize,arr[i % maxSize]);
        }
    }

    //求当前队列有效元素的个数
    public int size(){
        return (rear + maxSize - front) % maxSize;
    }

    //显示队列得头数据，注意不是取数据
    public int headQueue() {
        if(isEmpty()) {
            throw new RuntimeException("队列空的，没有数据~~");
        }
        return arr[front];
    }
}
