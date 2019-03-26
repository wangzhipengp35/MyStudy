package com.wzp.ds;

public interface Stack<Item> {
    /*
    * add an item
    */
    void push(Item item);

    /*
     * remove the most recently added item
     */
    Item pop();

    /*
     * is the stack empty?
     */
    boolean isEmpty();

    /*
     * number og items in the stack
     */
    int size();
}
