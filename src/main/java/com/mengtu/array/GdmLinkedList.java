package com.mengtu.array;


public class GdmLinkedList<E> extends GdmAbstractList<E> {

    private Node<E> first;
    private Node<E> last;
    private static class Node<E>{
        E element;
        Node<E> prev;
        Node<E> next;
        public Node(Node<E> prev,E element,Node<E> next){
            this.prev = prev;
            this.element = element;
            this.next = next;
        }
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
        if (index == size){
            Node<E> oldLast = last;
            last = new Node<>(oldLast,element,null);
            if (oldLast == null){
                first = last;
            }else {
                oldLast.next = last;
            }
//            last.prev.next = last;
        }else {
            /*index位置的元素即为新添加元素的next位置*/
            Node<E> next = node(index);
            /*prev就是新添加的上一个元素*/
            Node<E> prev = next.prev;
            /*新添加的节点*/
            Node<E> node = new Node<>(prev,element,next);
            if (prev == null){
                first = node;
            }else {
                prev.next = node;
            }
            next.prev = node;
        }
        size++;
    }

    @Override
    public E remove(int index) {
        rangeCheck(index);
        Node<E> node = node(index);
        Node<E> prev = node.prev;
        Node<E> next = node.next;

        if (prev == null){
            first = next;
        }else {
            prev.next = next;
        }
        if (next == null){
            last = prev;
        }else {
            next.prev = prev;
        }

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
        if (index < (size >> 1)){
            Node<E> node = first;
            for (int i = 0; i < index; i++) {
                node = node.next;
            }
            return node;
        }else {
            Node<E> node = last;
            for (int i = size - 1; i > index ; i--) {
                node = node.prev;
            }
            return node;
        }
    }

    @Override
    public String toString() {
        Node<E> node = first;
        StringBuilder sb = new StringBuilder();
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
