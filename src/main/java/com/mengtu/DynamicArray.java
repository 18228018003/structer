package com.mengtu;

public interface DynamicArray<E> {
    int size();//元素的数量
    boolean isEmpty(); //是否为空
    boolean contains(E element); //是否包含某个元素

    void add(E element); //添加元素
    E get(int index); //获取元素
    E set(int index,E element); //设置index位置元素的
    void  add(int index,E element); //往index位置添加元素
    E remove(int index); //删除index位置对应的元素
    int indexOf(E element); //查看元素的位置
    void clear();//清楚所有元素
}
