package com.mengtu.heap;

import java.util.Comparator;

public class BinaryHeap<E> extends AbstractHeap<E> implements Heap<E> {
    private E[] elements;
    private int size;
    private Comparator<E> comparator;
    private static final int DEFAULT_CAPACITY = 10;

    public BinaryHeap(E[] elements,Comparator<E> comparator) {
        super(comparator);
        if (elements == null || elements.length == 0){
            this.elements = (E[]) new Object[DEFAULT_CAPACITY];
        }else {
            size = elements.length;
            int cap = Math.max(elements.length,DEFAULT_CAPACITY);
            this.elements = (E[]) new Object[cap];
            System.arraycopy(elements, 0, this.elements, 0, elements.length);
            heapify();
        }
    }

    /*批量建堆*/
    private void heapify() {
        //自上而下的上滤
//        for (int i = 1; i < size; i++) {
//            siftUp(i);
//        }
        for (int i = (size >> 1) - 1; i >= 0 ; i--) {
            siftDown(i);
        }
    }

    public BinaryHeap(E[] elements) {
        this(elements,null);
    }
    public BinaryHeap(Comparator<E> comparator) {
        this(null,comparator);
    }

    public BinaryHeap() {
        this(null,null);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }
        size = 0;
    }

    @Override
    public void add(E element) {
        elementNotNullCheck(element);
        ensureCapacity(size+1);
        elements[size++] = element;
        siftUp(size-1);
    }

    /**
     * 让index位置的元素上滤
     * @param index
     */
    private void siftUp(int index){
        E e = elements[index];
        while (index > 0){
            int pIndex = (index - 1) / 2;
            E p = elements[pIndex];
            if (compare(e,p) <= 0) break;
            elements[index] = p;
            index = pIndex;
        }
        elements[index] = e;
    }
    private void siftDown(int index){
        E e = elements[index];
        //第一个叶子结点的索引 == 非叶子节点的数量
        int half = size >> 1;
        while (index < half){
            //默认为左子节点索引
            int childIndex = (index << 1) + 1;
            E child = elements[childIndex];
            //右子节点
            int rightIndex = childIndex + 1;
            //选出左右中较大的
            if (rightIndex < size && compare(elements[rightIndex],child) > 0){
                child = elements[childIndex = rightIndex];
            }
            if (compare(e,child) >= 0) break;
            elements[index] = child;
            index = childIndex;
        }
        elements[index] = e;
    }
    @Override
    public E get() {
        emptyCheck();
        return elements[0];
    }

    @Override
    public E remove() {
        emptyCheck();
        int lastIndex = --size;
        E root = elements[0];
        elements[0] = elements[lastIndex];
        elements[lastIndex] = null;
        siftDown(0);
        return root;
    }

    @Override
    public E repalce(E element) {
        elementNotNullCheck(element);

        E root = null;
        if (size == 0) {
            elements[0] = element;
            size++;
        } else {
            root = elements[0];
            elements[0] = element;
            siftDown(0);
        }
        return root;
    }

    private void emptyCheck(){
        if (size == 0){
            throw new IndexOutOfBoundsException("Heap is empty");
        }
    }
    private void elementNotNullCheck(E element){
        if (element == null){
            throw new IllegalArgumentException("element must not be null");
        }
    }
    private void ensureCapacity(int capacity){
        int oldCap = elements.length;
        if (oldCap >= capacity) return;

        int newCap = (oldCap >> 1) + oldCap;
        E[] newElements = (E[]) new Object[newCap];
        if (size >= 0) System.arraycopy(elements, 0, newElements, 0, size);
        elements = newElements;
    }
}
