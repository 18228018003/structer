package com.mengtu.array;

import com.mengtu.DynamicArray;

public class DynamicArrayImpl<E> implements DynamicArray<E> {

    /**
     * 元素的个数
     */
    private int size;

    /**
     * 所有的元素
     */
    private E[] elements;

    private static final int DEFAULT_CAPACITY = 10;
    private static final int ELEMENT_NOT_FOUND = -1;

    public DynamicArrayImpl(int capacity){
        capacity = Math.max(capacity, DEFAULT_CAPACITY);
        elements = (E[]) new Object[capacity];
    }
    public DynamicArrayImpl(){
        this(DEFAULT_CAPACITY);
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
    public boolean contains(E element) {
        return indexOf(element) != ELEMENT_NOT_FOUND;
    }

    @Override
    public void add(E element) {
        elements[size++] = element;
    }

    @Override
    public E get(int index) {
        checkIndex(index);
        return elements[index];
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size){
            throw new IndexOutOfBoundsException("Index:"+ index +" ,Size:"+size);
        }
    }

    @Override
    public E set(int index,E element) {
        checkIndex(index);
        E old = elements[index];
        elements[index] = element;
        return old;
    }

    @Override
    public void add(int index, E element) {

    }

    @Override
    public E remove(int index) {
        checkIndex(index);
        E old = elements[index];
        for (int i = index + 1; i < size ; i++) {
            elements[i - 1] = elements[i];
        }
        size--;
        return old;
    }

    @Override
    public int indexOf(E element) {
        for (int i = 0; i < size; i++)
            if (elements[i].equals(element)) return i;
        return ELEMENT_NOT_FOUND;
    }

    @Override
    public void clear() {
        size = 0;
        elements = null;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("size=").append(size).append(", [");
        for (int i = 0; i < size; i++) {
            if (i != 0){
                sb.append(" ,");
            }
            sb.append(elements[i]);
//            if (i != size - 1){
//                sb.append(",");
//            }
        }
        sb.append("]");
        return sb.toString();
    }
}
