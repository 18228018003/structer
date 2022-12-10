package com.mengtu;

import com.mengtu.array.DynamicArrayImpl;

public class Main {
    public static void main(String[] args) {
        DynamicArrayImpl<Integer> array = new DynamicArrayImpl<>();
        array.add(1);
        array.add(2);
        array.add(3);
        array.add(4);
        array.add(5);
        array.remove(0);
        System.out.println(array);
    }
}
