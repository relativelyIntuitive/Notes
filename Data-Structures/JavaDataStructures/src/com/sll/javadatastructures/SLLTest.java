package com.sll.javadatastructures;

public class SLLTest {
    public static void main(String[] args) {
        SLL test = new SLL();

        test.add(3);
        test.add(4);
        test.add(10);
        test.add(5);
        test.add(15);
        test.add(2);
        test.remove();
        test.remove();
        test.printValues();
    }
}