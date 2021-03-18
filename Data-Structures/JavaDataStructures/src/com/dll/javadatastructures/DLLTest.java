package com.dll.javadatastructures;

public class DLLTest {
    public static void main(String[] args) {
        DLL test = new DLL();
        Node n1 = new Node(10);
        Node n2 = new Node(20);
        Node n3 = new Node(50);
        Node n4 = new Node(60);
        Node n5 = new Node(80);
        Node n6 = new Node(100);
        
        test.push(n1);
        test.push(n2);
        test.push(n3);
        test.push(n4);
        test.push(n5);
        test.push(n6);
        
        test.printValuesForward();
        
    }
}