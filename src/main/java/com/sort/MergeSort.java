package com.sort;

public class MergeSort<E extends Comparable<E>> extends Sort<E>{
    private E[] leftArray;
    @Override
    protected void sort() {
        leftArray = (E[]) new Object[arr.length >> 1];
        sort(0,arr.length);
    }

    /**
     * 对[begin,end)范围内的数据进行归并排序
     */
    private void sort(int begin,int end){
        if ((end - begin) < 2) return;
        int mid = begin +((end - begin) >> 1);
        sort(begin,mid);
        sort(mid,end);
        merge(begin,mid,end);
    }

    /**
     * 将[begin,mid) 和 [mid,end)范围的序列和合并成一个有序序列
     */
    private void merge(int l, int mid, int r) {
        int li = 0,le = mid - l;
        int ri = mid,re = r;
        int ai = l;

        //备份左边数组
        for (int i = li; i < le; i++) {
            leftArray[i] = arr[l+i];
        }
        while (li < le){ //如果左边还未结束
            if (ri < re && cmp(arr[ri],leftArray[li]) < 0){
                arr[ai++] = arr[ri++];
            }else {
                arr[ai++] = leftArray[li++];
            }
        }
    }
}
