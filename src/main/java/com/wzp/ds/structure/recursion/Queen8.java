package com.wzp.ds.structure.recursion;

public class Queen8 {

    int max = 8;
    int[] array = new int[max];
    static int nums = 0;
    public static void main(String[] args) {
        //测试一把

        Queen8 queen8 = new Queen8();
        queen8.check(0);
        System.out.printf("一共有%d种解法",nums);
    }

    //列递归
    private void check(int n){
        if (n == max){
            print();
            return;
        }

        for (int i = 0;i < max;i++){ //行遍历
            array[n] = i;
            if (judge(n)){ //不冲突
                //继续放着n+1个皇后
                check(n + 1);
            }
        }
    }

    //查看放置的Queen和之前的Queen是否有冲突
    private boolean judge(int n){
        for (int i = 0;i < n;i++){
            if (array[i] == array[n] || Math.abs(n-i) == Math.abs(array[n] - array[i])){
                return false;
            }
        }
        return true;
    }

    private void print(){
        for (int i = 0;i < max ;i++){
            System.out.print(array[i] + " ");
        }
        System.out.println();
        nums++;
    }
}
