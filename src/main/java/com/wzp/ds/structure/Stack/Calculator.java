package com.wzp.ds.structure.Stack;

public class Calculator {

    public static void main(String[] args) {
        String expression = "70+2*6-4"; //15
        //创建两个栈
        //一个是数值栈，一个是符号栈
        ArrayStack2 numStack = new ArrayStack2(10);
        ArrayStack2 operStack = new ArrayStack2(10);

        int index = 0;
        int num1 = 0;
        int num2 = 0;
        int oper = 0;
        int result = 0;
        char ch = ' ';//将每次扫描得到的char保存到ch
        String keepNum = "";
        while (true){
            //依次得到expression的每一个字符
            ch = expression.substring(index,index+1).charAt(0);
            if (operStack.isOper(ch)) {
                //如果是一个运算符
                //判断当前的符号栈是否为空
                if (!operStack.isEmpty()){
                    if (operStack.priority(ch) <= operStack.priority(operStack.peek())){
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        oper = operStack.pop();
                        result = numStack.cal(num1,num2,oper);
                        numStack.push(result);
                        operStack.push(ch);
                    }else {
                        operStack.push(ch);
                    }
                }else {
                    //如果为空直接入栈
                    operStack.push(ch);
                }
            } else {
                //当处理多位数时，不能发现是一个数就立即入栈，因为它可能是多位数

                //处理多位数
                keepNum += ch;
                //判断下一个字符是否是数组，如果是数字，则继续扫描，如果是运算符，则入栈
                if (index == expression.length() - 1){
                    numStack.push(Integer.parseInt(keepNum));
                } else {
                    if (operStack.isOper(expression.substring(index+1,index+2).charAt(0))) {
                        numStack.push(Integer.parseInt(keepNum));
                        keepNum = "";
                    }
                }
            }
            index++;
            if (index >= expression.length()){
                break;
            }
        }

        while (true){
            //如果符号栈为空，则计算完毕
            if (operStack.isEmpty()){
                break;
            }
            num1 = numStack.pop();
            num2 = numStack.pop();
            oper = operStack.pop();
            result = numStack.cal(num1,num2,oper);
            numStack.push(result);
        }
        System.out.printf("表达式 %s = %d", expression,numStack.pop());
    }
}

class ArrayStack2 {
    private int maxSize;//栈的大小
    private int[] stack;//数组，数组模拟栈，数据就放在该数组中
    private int top = -1;

    public ArrayStack2(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[maxSize];
    }

    //判断栈满
    public boolean isFull(){
        return top == maxSize - 1;
    }

    //栈空
    public boolean isEmpty(){
        return top == -1;
    }

    public void push(int value){
        if (isFull()){
            System.out.println("栈满");
            return;
        }
        top++;
        stack[top] = value;
    }

    public int pop(){
        if (isEmpty()){
            //跑出异常
            throw new RuntimeException("栈空，没有数据~");
        }
        int value = stack[top];
        top--;
        return value;
    }

    public void list(){
        if (isEmpty()){
            System.out.println("栈空，没有数据~~");
            return;
        }

        for (int i = top;i>=0;i--){
            System.out.printf("stack[%d]=%d\n",i,stack[i]);
        }
    }

    //返回运算符的优先级
    public int priority(int oper){
        if (oper == '*' || oper == '/'){
            return 1;
        } else if (oper == '+' || oper == '-'){
            return 0;
        }else{
            return -1;
        }
    }

    public boolean isOper(char val){
        return val == '+' ||  val == '-' || val == '*' || val == '/';
    }

    //计算方法
    public int cal(int num1, int num2, int oper){
        int res = 0;//res用于计算存放
        switch (oper){
            case '+':
                res = num1 + num2;
                break;
            case '-':
                res =  num2 - num1;
            break;
            case '*':
                res =  num1*num2;
            break;
            case '/':
                if (num1 != 0){
                    res =  num2 / num1;
                }else {
                    System.out.println("分母不能为0");
                }
            break;
            default:
                return 0;
        }
        return res;
    }

    public int peek(){
        return stack[top];
    }


}
