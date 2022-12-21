package com.sort;

public class InsertionSort<E extends Comparable<E>> extends Sort<E>{
    @Override
    protected void sort() {
//        for (int i = 1; i < arr.length; i++) {
//            int cur = i;
//            while (cur > 0 && cmp(cur,cur-1) < 0){
//                swap(cur,cur-1);
//                cur--;
//            }
//        }
        /**
         * 第二版优化 移动代替交换
         */
//        for (int i = 1; i < arr.length; i++) {
//            int cur = i;
//            E element = arr[cur];
//            while (cur > 0 && cmp(element,arr[cur-1]) < 0){
//                arr[cur] = arr[cur-1];
//                cur--;
//            }
//            arr[cur] = element;
//        }
        /**
         * 第三版优化 二分搜索优化
         */
        for (int i = 1; i < arr.length; i++) {
            int insertIndex = search(i);
            E element = arr[i];
            for (int j = i; j > insertIndex; j--) {
                arr[j] = arr[j-1];
            }
            arr[insertIndex] = element;
        }
    }

    /**
     * 利用二分搜索找到index位置元素的待插入位置
     * 已经排好序数组的区间范围是[0,index);
     */
    private int search(int index){
        E v = arr[index];
        int begin = 0;
        int end = index;
        while (begin < end){
            int mid = begin + ((end - begin) >> 1);
            if (v.compareTo(arr[mid]) < 0){
                end = mid;
            }else {
                begin = mid + 1;
            }
        }
        return begin;
    }
}
