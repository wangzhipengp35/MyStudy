package com.wzp.ds.impl;

import com.wzp.ds.Stack;

public class ResizingArrayStack<Item> implements Stack<Item> {
    private Item[] a = (Item[]) new Object[1];

    private int N = 0;

    private void resize(int max){
        Item[] temp = (Item[]) new Object[max];
        for (int i = 0;i < N;i++){
            temp[i] = a[i];
        }
        a = temp;
    }

    public void push(Item item) {
        if (N == a.length){
            resize(2 * a.length);
        }
        a[N++] = item;
    }

    public Item pop() {
        Item item = a[--N];
        a[N] = null;
        if (N > 0 && N == a.length / 4) {
            resize(a.length / 2);
        }
        return item;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }
}
