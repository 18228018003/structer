package com.sort;

import java.util.Arrays;

public class BubbleSort extends Sort{
    public static void main(String[] args) {
        int[] arr = {10,9,8,7,5,4,3,2,1};
    }

    private static void bubbleSort1(int[] arr) {
        for (int end = arr.length-1; end > 0; end--) {
            for (int begin = 1; begin <= end; begin++) {
                if (arr[begin] < arr[begin-1]){
                    int tmp = arr[begin];
                    arr[begin] = arr[begin-1];
                    arr[begin-1] = tmp;
                }
            }
        }
        System.out.println(Arrays.toString(arr));
    }

    private static void bubbleSort2(int[] arr) {
        for (int end = arr.length-1; end > 0; end--) {
            boolean sorted = true;
            for (int begin = 1; begin <= end; begin++) {
                if (arr[begin] < arr[begin-1]){
                    int tmp = arr[begin];
                    arr[begin] = arr[begin-1];
                    arr[begin-1] = tmp;
                    sorted = false;
                }
            }
            if (!sorted) break;
        }
        System.out.println(Arrays.toString(arr));
    }
    private static void bubbleSort3(int[] arr) {
        for (int end = arr.length-1; end > 0; end--) {
            int sortedIndex = 1;
            for (int begin = 1; begin <= end; begin++) {
                if (arr[begin] < arr[begin-1]){
                    int tmp = arr[begin];
                    arr[begin] = arr[begin-1];
                    arr[begin-1] = tmp;
                    sortedIndex = end;
                }
            }
            end = sortedIndex;
        }
        System.out.println(Arrays.toString(arr));
    }

    @Override
    protected void sort() {
        for (int end = arr.length-1; end > 0; end--) {
            for (int begin = 1; begin <= end; begin++) {
                if (cmp(begin,begin-1) < 0){
                    swap(begin,begin-1);
                }
            }
        }
    }
}
