package com.sort;

import com.sort.tools.Integers;


public class Main {
    public static void main(String[] args) {
        // 产生 20000 个数据,每个数据的范围是 1~10000
        Integer[] array = Integers.random(10000, 1, 10000);
        // 在这里面写要测试的代码
        testSorts(array,
                new HeapSort()
//                new BubbleSort(),// 冒泡排序
//                new SelectSort()
        );
    }
    static void testSorts(Integer[] array, Sort... sorts) {
        for (Sort sort : sorts) {
            sort.sort(Integers.copy(array));
            System.out.println(sort);
        }
//        Arrays.sort(sorts);
    }
}
