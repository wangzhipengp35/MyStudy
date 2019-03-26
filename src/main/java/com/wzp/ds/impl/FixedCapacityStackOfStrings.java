package com.wzp.ds.impl;

import com.wzp.ds.Stack;

/*
 * String定容栈：固定容量的String类型栈
 */
public class FixedCapacityStackOfStrings implements Stack<String> {

    private String[] a; // stack entries

    private int N; // size

    public FixedCapacityStackOfStrings(int cap) {
        a = new String[cap];
    }

    public void push(String item) {
        a[N++] = item;
    }

    public String pop() {
        return a[--N];
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }
}
