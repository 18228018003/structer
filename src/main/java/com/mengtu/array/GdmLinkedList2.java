package com.mengtu.array;

/**
 * 增加一个虚拟头结点
 * 变为双向链表
 * @param <E>
 */
public class GdmLinkedList2<E> extends GdmAbstractList<E> {
    private Node<E> first;
    private static class Node<E>{
        E element;
        Node<E> next;
        public Node(E element,Node<E> next){
            this.element = element;
            this.next = next;
        }
    }
    public GdmLinkedList2(){
        /*虚拟头节点*/
        first = new Node<>(null,null);
    }
    @Override
    public boolean contains(E element) {
        return indexOf(element) != ELEMENT_NOT_FOUND;
    }

    @Override
    public E get(int index) {
        return node(index).element;
    }

    @Override
    public E set(int index, E element) {
        Node<E> node = node(index);
        E old = node.element;
        node.element = element;
        return old;
    }

    @Override
    public void add(int index, E element) {

        rangeCheckForAdd(index);
        //找到index位置的前一个元素
        Node<E> prev = index == 0 ? first : node(index - 1);
        /*创建一个新的Node 新Node的next为前一个节点的next*/
        prev.next = new Node<>(element,prev.next);

        size++;
    }

    @Override
    public E remove(int index) {
        rangeCheck(index);
        Node<E> prev = index == 0 ? first : node(index - 1);
        Node<E> node = prev.next;
        prev.next = node.next;
        size--;
        return node.element;
    }

    @Override
    public int indexOf(E element) {
        Node<E> node = first;
        if (element == null){
            for (int i = 0; i < size; i++) {
                if (node.element == element) return i;
                node = node.next;
            }
        }else {
            for (int i = 0; i < size; i++) {
                if (element.equals(node.element))
                    return i;
                node = node.next;
            }
        }
        return ELEMENT_NOT_FOUND;
    }

    @Override
    public void clear() {
        size = 0;
        first = null;
    }

    /**
     * 获取index位置对应的节点对象
     * @param index
     * @return
     */
    private Node<E> node(int index){
        rangeCheck(index);
        /**/
        Node<E> node = first.next;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node;

    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node<E> node = first.next;
        sb.append("size=").append(size).append(", [");
        for (int i = 0; i < size; i++) {
            if (i != 0){
                sb.append(" ,");
            }
            sb.append(node.element);
            node = node.next;
        }
        sb.append("]");
        return sb.toString();
    }
}
