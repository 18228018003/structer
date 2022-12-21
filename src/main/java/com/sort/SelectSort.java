package com.sort;

public class SelectSort extends Sort{
    @Override
    protected void sort() {
        for (int end = arr.length-1; end > 0; end--) {
            int maxIndex = 0;
            for (int begin = 1; begin <= end; begin++) {
//                if (arr[maxIndex] <= arr[begin]){
                if (cmp(maxIndex,begin) <= 0){
                    maxIndex = begin;
                }
            }
            swap(maxIndex,end);
        }
    }
}
