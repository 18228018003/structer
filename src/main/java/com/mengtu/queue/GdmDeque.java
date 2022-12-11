package com.mengtu.queue;

import com.mengtu.array.GdmLinkedList;

public class GdmDeque<E> {
    private GdmLinkedList<E> list = new GdmLinkedList<>();
    public int size(){
        return list.size();
    }

    public boolean isEmpty(){
        return list.isEmpty();
    }

    public void addLast(E element){
        list.add(element);
    }
    public E removeFront(){
        return list.remove(0);
    }
    public void addFirst(E element){
        list.add(0,element);
    }
    public void removeLast(){
        list.remove(list.size()-1);
    }
    /*获取队列头*/
    public E front(){
        return list.remove(0);
    }

    /*获取队列尾部*/
    public E rear(){
        return list.remove(list.size()-1);
    }
}
