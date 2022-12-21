package com.sort;

import com.sort.bean.Student;

import java.text.DecimalFormat;
import java.util.Arrays;

public abstract class Sort<E extends Comparable<E>> implements Comparable<Sort<E>>{
    protected E[] arr;

    private int cmpCount;
    private int swapCount;
    private long time;
    private DecimalFormat fmt = new DecimalFormat("#.00");

    public void sort(E[] arr){
        if (arr == null || arr.length < 2) return;
        this.arr = arr;
        long begin = System.currentTimeMillis();
        sort();
        time = System.currentTimeMillis() - begin;
    }

    protected abstract void sort();

    /**
     * 返回值 = 0 代表arr[i1] == arr[i2]
     * 返回值 < 0 代表arr[i1] < arr[i2]
     * 返回值 > 0 代表arr[i1] > arr[i2]
     */
    protected int cmp(int i1,int i2){
        cmpCount++;
        return arr[i1].compareTo(arr[i2]);
    }
    protected int cmp(E e1,E e2){
        cmpCount++;
        return e1.compareTo(e2);
    }
    protected void swap(int i1,int i2){
        swapCount++;
        E temp = arr[i1];
        arr[i1] = arr[i2];
        arr[i2] = temp;
    }
    private String numberString(int number){
        if (number < 10000) return ""+number;
        if (number < 100000000) return fmt.format((number / 10000.0)) + "万";
        return fmt.format((number / 100000000.0)) + "亿";
    }
    @Override
    public String toString() {
        String timeStr = "耗时：" + (time / 1000.0) + "s(" + time + "ms)";
        String compareStr = "比较：" + numberString(cmpCount);
        String swapStr = "交换：" + numberString(swapCount);
        String stableStr = "稳定性：" + isStable();
        return "【"+getClass().getSimpleName() + "】\n"
                + stableStr + " \t"
                + timeStr + "\t"
                + compareStr + "\t"
                + swapStr + "\n"
                + " ----------------------------------- ";
    }

    @Override
    public int compareTo(Sort<E> o) {
        int result = (int) (time-o.time);
        if (result != 0) return result;
        result = cmpCount - o.cmpCount;
        if (result != 0) return result;
        return swapCount - o.swapCount;
    }

    private boolean isStable() {
//        if (this instanceof RadixSort) return true;
//        if (this instanceof CountingSort) return true;
//        if (this instanceof ShellSort) return false;
        if (this instanceof SelectSort) return false;

        Student[] students = new Student[20];
        for (int i = 0; i < students.length; i++) {
            students[i] = new Student(i * 10, 10);
        }
        sort((E[]) students);
        for (int i = 1; i < students.length; i++) {
            int score = students[i].score;
            int prevScore = students[i - 1].score;
            if (score != prevScore + 10) return false;
        }
        return true;
    }
}
