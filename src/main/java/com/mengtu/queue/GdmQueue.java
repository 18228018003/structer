package com.mengtu.queue;

import com.mengtu.array.GdmArrayList;
import com.mengtu.array.GdmLinkedList;

/**
 * 队列实现
 */
public class GdmQueue<E> {
    private GdmLinkedList<E> list = new GdmLinkedList<>();
    public int size(){
        return list.size();
    }

    public boolean isEmpty(){
        return list.isEmpty();
    }

    public void enQueue(E element){
        list.add(element);
    }

    public E deQueue(){
        return list.remove(0);
    }

    public E front(){
        return list.get(0);
    }

    public void clear(){
        list.clear();
    }
}

