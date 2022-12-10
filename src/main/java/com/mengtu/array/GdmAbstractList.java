package com.mengtu.array;

import com.mengtu.GdmList;

public abstract class GdmAbstractList<E> implements GdmList<E> {

    /**
     * 元素的个数
     */
    protected int size;

    public boolean isEmpty(){
        return size == 0;
    }

    public void add(E element)
    {
        add(size,element);
    }
    public int size(){
        return size;
    }

    protected void rangeCheck(int index) {
        if (index < 0 || index >= size){
            outOfBounds(index);
        }
    }
    protected void rangeCheckForAdd(int index) {
        if (index < 0 || index > size){
            outOfBounds(index);
        }
    }
    private void outOfBounds(int index) {
        throw new IndexOutOfBoundsException("Index:"+ index +" ,Size:"+size);
    }

}
