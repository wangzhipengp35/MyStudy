package com.wzp.ds.impl;
import java.lang.*;

public class IterableResizingArrayStackTests {

    public static void main(String[] args) {

        IterableResizingArrayStack<Float> resizingArrayStack = new IterableResizingArrayStack<Float>();

        System.out.println("resizingArrayStack : size=" + resizingArrayStack.size() + ",isEmpty=" + resizingArrayStack.isEmpty());

        resizingArrayStack.push(100.011f);
        resizingArrayStack.push(202.022f);
        System.out.println("resizingArrayStack : size=" + resizingArrayStack.size() + ",isEmpty=" + resizingArrayStack.isEmpty());

        System.out.println("resizingArrayStack all items:");

        System.out.println("poped=" + resizingArrayStack.pop());
        System.out.println("resizingArrayStack : size=" + resizingArrayStack.size() + ",isEmpty=" + resizingArrayStack.isEmpty());

    }
}
