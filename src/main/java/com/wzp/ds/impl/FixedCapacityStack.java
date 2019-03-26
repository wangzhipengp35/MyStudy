package com.wzp.ds.impl;

import com.wzp.ds.Stack;

public class FixedCapacityStack<Item> implements Stack<Item> {

    private Item[] a;

    private  int N;

    public FixedCapacityStack(int cap) {
        a = (Item[]) new Object[cap];
    }

    public void push(Item item) {
        a[N++] = item;
    }

    public Item pop() {
        return a[--N];
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }
}
