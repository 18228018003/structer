package com.mengtu.stack;

import com.mengtu.array.GdmArrayList;
import com.mengtu.array.GdmLinkedList;
import com.mengtu.array.GdmList;

public class GdmStack<E>{
    private GdmList<E> list = new GdmArrayList<>();

    public boolean isEmpty(){
        return list.isEmpty();
    }
    public void push(E element){
        list.add(element);
    }

    public E pop(){
        return list.remove(list.size()-1);
    }

    public E top(){
        return list.get(list.size()-1);
    }

    public void clear(){
        list.clear();
    }
}
