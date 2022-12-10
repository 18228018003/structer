package com.mengtu;

import com.mengtu.array.GdmArrayList;
import com.mengtu.array.GdmLinkedList;

public class Main {
    public static void main(String[] args) {
        GdmList<Integer> array = new GdmLinkedList<>();
        array.add(1);
        array.add(2);
        array.add(3);
        array.add(4);
        array.add(5);
        array.add(6);
        array.add(7);
        array.add(8);
        array.add(9);
        array.add(10);
        array.add(0,0);
        System.out.println(array);
    }
}
