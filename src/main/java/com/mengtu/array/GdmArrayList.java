package com.mengtu.array;

public class GdmArrayList<E>  extends GdmAbstractList<E>{

    /**
     * 所有的元素
     */
    private E[] elements;

    private static final int DEFAULT_CAPACITY = 10;


    public GdmArrayList(int capacity){
        capacity = Math.max(capacity, DEFAULT_CAPACITY);
        elements = (E[]) new Object[capacity];
    }
    public GdmArrayList(){
        this(DEFAULT_CAPACITY);
    }

    @Override
    public boolean contains(E element) {
        return indexOf(element) != ELEMENT_NOT_FOUND;
    }

    @Override
    public E get(int index) {
        rangeCheck(index);
        return elements[index];
    }


    @Override
    public E set(int index,E element) {
        rangeCheck(index);
        E old = elements[index];
        elements[index] = element;
        return old;
    }

    @Override
    public void add(int index, E element) {
        rangeCheckForAdd(index);
        ensureCapacity(size+1);
        for (int i = size-1; i >= index; i--) {
            elements[i+1] = elements[i];
        }
        elements[index] = element;
        size++;
    }

    /**
     * 确保容量
     * @param capacity 容量大小
     */
    private void ensureCapacity(int capacity){
        int oldCapacity = elements.length;
        if (oldCapacity >= capacity) return;
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        E newElements[] = (E[]) new Object[newCapacity];
        if (size >= 0) System.arraycopy(elements, 0, newElements, 0, size);
        elements = newElements;
    }
    @Override
    public E remove(int index) {
        rangeCheck(index);
        E old = elements[index];
        for (int i = index + 1; i < size ; i++) {
            elements[i - 1] = elements[i];
        }
        elements[--size] = null;
        trim();
        return old;
    }

    /**
     * 数组缩容
     */
    private void trim() {
        int capacity = elements.length;
        //size大小大于容量的一半 或者容量小于等于默认容量 不进行缩容
        int newCapacity = (capacity >> 1);
        if (size >= newCapacity || capacity <= DEFAULT_CAPACITY ) return;
        //1.剩余容量很多
        E[] newElements = (E[]) new Object[newCapacity];
        System.arraycopy(elements,0,newElements,0,size);
        elements = newElements;
    }

    @Override
    public int indexOf(E element) {
        if (element == null){
            for (int i = 0; i < size; i++){
                if (elements[i] == null) return i;
            }
        }else {
            for (int i = 0; i < size; i++){
                if (element.equals(elements[i])) {
                    return i;
                }
            }
        }
        return ELEMENT_NOT_FOUND;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }
        size = 0;
        elements = (E[]) new Object[DEFAULT_CAPACITY];
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
