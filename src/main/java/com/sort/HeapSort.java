package com.sort;

public class HeapSort<E extends Comparable<E>> extends Sort<E>{
    private int heapSize;
    @Override
    protected void sort() {
        heapSize = arr.length;
        //原地建堆
        for (int i = (heapSize >> 1); i >= 0; i--) {
            siftDown(i);
        }
        while (heapSize > 1){
            //交换堆顶元素和尾部元素
            swap(0,--heapSize);
            //对0位置进行siftDown(恢复堆的性质)
            siftDown(0);
        }
    }

    private void siftDown(int i) {
        E element = arr[i];
        //叶子结点等于总结点数量除2
        int half = heapSize >> 1;
        while (i < half){
            int childIndex = (i << 1) + 1;
            E child = arr[childIndex];
            int rightIndex = childIndex + 1;
            if (rightIndex < heapSize && cmp(childIndex,rightIndex) < 0){
                child = arr[childIndex = rightIndex];
            }
            if (cmp(element,child) >= 0) break;
            arr[i] = child;
            i = childIndex;
        }
        arr[i] = element;
    }
}
