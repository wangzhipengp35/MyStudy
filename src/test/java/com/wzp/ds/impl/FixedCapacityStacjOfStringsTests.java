package com.wzp.ds.impl;

public class FixedCapacityStacjOfStringsTests {
    public static void main(String[] args) {
        FixedCapacityStackOfStrings fixedCapacityStackOfStrings = new FixedCapacityStackOfStrings(10);
        System.out.println("fixedCapacityStackOfStrings : size=" + fixedCapacityStackOfStrings.size() + ",isEmpty="+fixedCapacityStackOfStrings.isEmpty());

        fixedCapacityStackOfStrings.push("A");
        fixedCapacityStackOfStrings.push("Aaha");
        System.out.println("fixedCapacityStackOfStrings : size="+fixedCapacityStackOfStrings.size()+",isEmpty="+fixedCapacityStackOfStrings.isEmpty());

        System.out.println("poped="+fixedCapacityStackOfStrings.pop());
        System.out.println("fixedCapacityStackOfStrings : size="+fixedCapacityStackOfStrings.size()+",isEmpty="+fixedCapacityStackOfStrings.isEmpty());
    }
}
