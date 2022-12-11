package com.mengtu.queue;

/*循坏队列*/
public class CirCleQueue<E> {

    private int size;
    private int front;
    private E[] elements;
    private static final int DEFAULT_CAPACITY = 10;
    public CirCleQueue(int capacity){
        elements = (E[]) new Object[capacity];
    }
    public CirCleQueue(){
        elements = (E[]) new Object[DEFAULT_CAPACITY];
    }
    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public void enQueue(E element){
        ensureCapacity(size + 1);
        elements[index(size)] = element;
    }

    private void ensureCapacity(int i) {
        int oldCap = elements.length;
        if (oldCap >= i) return;

        int newCap = oldCap + (oldCap >> 1);
        E[] newEle = (E[]) new Object[newCap];
        for (int j = 0; j < size; j++) {
            newEle[j] = elements[index(j)];
        }
        elements = newEle;
        /*重置front*/
        front = 0;
    }
    private int index(int i){
        return (front + i) % elements.length;
    }

    public E deQueue(){
        E frontElement = elements[front];
        elements[front] = null;
        front = index(1);
        size--;
        return null;
    }

    public E front(){
        return elements[front];
    }

    public void clear(){
        size = 0;
        elements = null;
    }
}
