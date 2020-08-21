package com.wzp.ds.structure.Stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import static com.wzp.ds.structure.Stack.Operation.getValue;

/**
 * @创建人 wangzhipeng
 * @创建时间 2020/8/18
 * @描述
 */
public class PolandNotation {

    public static void main(String[] args) {

        //完成将一个中缀表达式，转成后缀表达式的功能额
        //1. 1+((2+3)*4)-5   ===>  1 2 3 + 4 * + 5 -
        //2.因为直接对str进行操作，不方便，因此先将"1+((2+3)*4)-5" ==>中缀的表达式对应的list


        //3.将得到的中缀表达式对应的list =>后缀表达式对应的List

        String expression = "1+((2+3)*4)-5";


        List<String> list = parseSuffixExpressionList(toInfixExpressionList(expression));
        System.out.println(list);


        //先定义逆波兰表达式
        //(3+4)* 5-6 => 3 4 + 5 * 6 -

        // 4 * 5 - 8 + 60 + 8 / 2 => 76   =====>  4 5 * 8 - 60 + 8 2 / +

//        String suffixExpression = "3 4 + 5 * 6 -";
        String suffixExpression = "4 5 * 8 - 60 + 8 2 / +";

        //先将"3 4 + 5 * 6 - " 放到ArrayList中
        //将ArrayList传递一个方法，遍历ArrayList 配合栈 完成计算

        List<String> rpnList = getListString(suffixExpression);
//        System.out.println(rpnList);
//        System.out.println(calulate(rpnList));



    }

    //将中缀表达式转成对应的List
    public static List<String> toInfixExpressionList(String s){
        //定义一个List，存放中缀表达式对应的内容
        ArrayList<String> ls = new ArrayList<String>();
        int i = 0; //这是一个指针，用于遍历中缀表达式字符串
        String str; //用多位数的拼接
        char c = 0;//每遍历到一个字符，就放入到c
        do {
            //如果c是一个非数字，需要加入到ls里
            if ((c=s.charAt(i)) < 48 || (c=s.charAt(i)) > 57){
                ls.add("" + c);
                i++;
            }else {//如果是一个数，需要考虑多位数的问题
                str = "";
                while (i < s.length() && (c=s.charAt(i)) >= 48 && (c=s.charAt(i)) <= 57){
                    str += c;
                    i++;
                }
                ls.add(str);
            }
        }while (i < s.length());
        return ls;
    }

    // 将一个逆波兰表达式，一次将数据和运算符放入到ArrayList中

    public static List<String> getListString(String suffixExpression) {

        String[] split = suffixExpression.split(" ");
        ArrayList<String> list = new ArrayList<String>();
        for (String ele : split){
            list.add(ele);
        }
        return list;
    }

    public static int calulate(List<String> ls) {
        Stack<String> stack = new Stack<String>();

        for (String item : ls) {
            //使用正则表达式来取数
            if (item.matches("\\d+")){
                stack.push(item);
            } else {
                //pop出两个数，并且运算，再入栈
                int num1 = Integer.parseInt(stack.pop());
                int num2 = Integer.parseInt(stack.pop());
                int res = 0;
                if (item.equals("+")) {
                    res = num2 + num1;
                } else if (item.equals("-")) {
                    res = num2 - num1;
                } else if (item.equals("*")) {
                    res = num2 * num1;
                }  else if (item.equals("/")) {
                    res = num2 / num1;
                } else {
                    throw new RuntimeException("运算符有误");
                }
                stack.push("" +res);
            }
        }
        //最终留在stack中的数据是运算结果
        return Integer.parseInt(stack.pop());
    }

    public static List<String> parseSuffixExpressionList(List<String> arrayList){
        Stack<String> s1 = new Stack<String>(); //符号栈
        //因为S2这个栈，在整个转换过程中，没有pop操作，而且后面我们还需要逆序输出
        //因此比较麻烦，这里我们就不用栈，直接用arrayList
        ArrayList<String> s2 = new ArrayList<String>();
        //如果是一个数值，加入到s2
        for (String item : arrayList)
            if (item.matches("\\d+")) {
                s2.add(item);
            } else if (item.equals("(")) {
                s1.push(item);
            } else if (item.equals(")")) {
                while (!s1.peek().equals("(")){
                    s2.add(s1.pop());
                }
                s1.pop();//将（ 谈出s1栈，消除小括号
            } else {
                //当item的优先级小与等于栈顶运算符，将s1栈顶的运算符弹出并加入到s2中，再次转到4.1与s1中新的栈顶运算符相比较
                //缺少一个判断优先级的方法
                while (s1.size() != 0 && getValue(s1.peek()) >= Operation.getValue(item) ){
                    s2.add(s1.pop());
                }
                //还需要将item压入栈
                s1.push(item);
            }

            //将s1中剩余的运算符一次弹出并加入s2
        while (s1.size() != 0) {
            s2.add(s1.pop());
        }

        return s2;
    }
}

//编写一个类，Operation 可以返回一个运算符 对应的优先级
class Operation {
    private static int ADD = 1;
    private static int SUB = 1;
    private static int MUL = 2;
    private static int DIV = 2;

    //写一个方法，返回对应的优先级数字
    public static int getValue(String operation){
        int result = 0;
        if ("+".equals(operation)) {
            result = ADD;

        } else if ("-".equals(operation)) {
            result = SUB;

        } else if ("*".equals(operation)) {
            result = MUL;

        } else if ("/".equals(operation)) {
            result = DIV;

        } else {
            System.out.println("不存在该运算符");

        }
        return result;
    }

}