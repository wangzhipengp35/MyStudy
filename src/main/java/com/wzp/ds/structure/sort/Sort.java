package com.wzp.ds.structure.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class Sort {



    public static void main(String[] args) {
        int[] nums = {-9,78,0,23,-567,70};
        Sort sort = new Sort();
//        int[] bubbleSort = sort.BubbleSort(nums, "ASC");
//        int[] selectSort = sort.selectSort(nums, "ASC");
//        int[] insertSort = sort.insertSort(nums, "DESC");
//        int[] sheelSort = sort.sheelSort(nums, "ASC");
        int[] quickSort = sort.quickSort(nums, 0, nums.length - 1);
        System.out.println(Arrays.toString(quickSort));


        int[] arr = new int[80000];
        for (int i = 0;i < 80000; i++){
            arr[i] = (int)(Math.random() * 8000000);
        }
        Date startDate = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        System.out.println("排序前的日期：" + simpleDateFormat.format(startDate));


//        int[] bubbleSort = sort.BubbleSort(arr, "DESC"); // 6s
//        int[] bubbleSort = sort.selectSort(arr, "DESC"); // 1s
//        int[] bubbleSort = sort.insertSort(arr, "DESC"); // 1s
//        int[] bubbleSort = sort.sheelSort(arr, "DESC"); // 3s


        Date endDate = new Date();
        System.out.println("排序后的日期：" + simpleDateFormat.format(endDate));
    }

    //时间复杂度O(n^2)
    public int[] BubbleSort(int[] nums,String operator) {
        if (nums.length <= 1){
            System.out.println("数字太小不需要排序");
            return nums;
        }

        boolean flag = false;

        for (int i = 0;i < nums.length;i++){
            for (int j = i+1; j < nums.length;j++){
                if (operator.equals("DESC")){
                    if (nums[j] > nums[i]){
                        flag = true;
                        transform(nums,i,j);
                    }
                }
                if (operator.equals("ASC")){
                    if (nums[j] < nums[i]){
                        flag = true;
                        transform(nums,i,j);
                    }
                }
            }
            if (!flag){
                break;
            }else {
                flag = false;
            }
        }
        return nums;
    }


    public int[] selectSort(int[] nums,String operator) {
        if (nums.length <= 1){
            System.out.println("数字太小不需要排序");
            return nums;
        }

        int temp = 0;
        int superIndex = 0;
        int superNums = 0;
        for (int i = 0;i < nums.length;i++){
            superNums = nums[i];
            superIndex = i;
            for (int j = i+1; j < nums.length;j++){
                if (operator.equals("DESC")){
                    if (superNums < nums[j]){
                        superNums = nums[j];
                        superIndex = j;
                    }
                }
                if (operator.equals("ASC")){
                    if (superNums > nums[j]){
                        superNums = nums[j];
                        superIndex = j;
                    }
                }
            }
            transform(nums,i,superIndex);
        }
        return nums;
    }


    public int[] insertSort(int[] nums,String operator) {
        if (nums.length <= 1){
            System.out.println("数字太小不需要排序");
            return nums;
        }

        int insertValue = 0;
        int insertIndex = 0;
        for (int i = 1;i < nums.length;i++){
            insertValue = nums[i];
            insertIndex = i - 1;

            if (operator.equals("DESC")){
                while (insertIndex >= 0 && insertValue > nums[insertIndex]){
                    nums[insertIndex + 1] = nums[insertIndex];
                    insertIndex--;
                }
                nums[insertIndex + 1] = insertValue;
            }
            if (operator.equals("ASC")){
                while (insertIndex >= 0 && insertValue < nums[insertIndex]){
                    nums[insertIndex + 1] = nums[insertIndex];
                    insertIndex--;
                }
                nums[insertIndex + 1] = insertValue;
            }
        }

        return nums;
    }


    public int[] sheelSort(int[] nums,String operator) {
        if (nums.length <= 1){
            System.out.println("数字太小不需要排序");
            return nums;
        }

        int insertValue = 0;
        int insertIndex = 0;
        //希尔分组 k代表了该次遍历要分几组
        for (int k = nums.length/2;k >= 1;k = k/2){
            //遍历每组 i代表了k次遍历的第几组
            for (int i = 0;i < k;i++){
                //组内插入排序
                for (int j = 1;j < nums.length / k; j++){
                        insertValue = nums[k*j + i];
                        insertIndex = k*(j-1) + i;

                        if (operator.equals("DESC")){
                            while (insertIndex >= 0 && insertIndex + k < nums.length && insertValue > nums[insertIndex]){
                                nums[insertIndex + k] = nums[insertIndex];
                                insertIndex -= k;
                            }
                            nums[insertIndex + k] = insertValue;
                        }
                        if (operator.equals("ASC")){
                            while (insertIndex >= 0 && insertIndex + k < nums.length && insertValue < nums[insertIndex]){
                                nums[insertIndex + k] = nums[insertIndex];
                                insertIndex -= k;
                            }
                            nums[insertIndex + k] = insertValue;
                        }
                }
            }

        }
        return nums;
    }

    public int[] quickSort(int[] nums,int left,int right) {
        if (nums.length <= 1){
            System.out.println("数字太小不需要排序");
            return nums;
        }
        int l = left;
        int r = right;
        int pivot = nums[(left + right) / 2];
        //while 循环的目的是让比pivot值小的放左边
        while (l < r){
            while (l <= nums.length - 1 && nums[l] < pivot){
                l++;
            }
            while (r >= 0 && nums[r] > pivot){
                r--;
            }
            if (l >= r) {
                break;
            }
            transform(nums,l,r);

            if (nums[l] == pivot) {
                r--;
            }

            if (nums[r] == pivot) {
                l++;
            }
        }

        if (l == r) {
            l++;
            r--;
        }
        if (left < r){
            quickSort(nums,left,r);
        }

        if (right > l){
            quickSort(nums,l,right);
        }
        return nums;
    }

    public static void transform(int[] array,int num1,int num2){
        int temp = 0;
        temp = array[num1];
        array[num1] = array[num2];
        array[num2] = temp;
    }


}
