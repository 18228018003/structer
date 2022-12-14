package com.mengtu.array;

/*双向循环链表*/
public class CircleLinkedList<E> extends GdmAbstractList<E> {

    private Node<E> first;
    private Node<E> last;
    private Node<E> current;
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
    public void reset(){
        current = first;
    }
    public E next(){
        if (current == null)return null;
        current = current.next;
        return current.element;
    }
    public E remove(){
        if (current == null) return null;
        Node<E> next = current.next;
        E element = remove(current);
        if (size == 0) {
            current = null;
        }else {
            current = next;
        }
        return element;
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
            last = new Node<>(oldLast,element,first);
            if (oldLast == null){//链表添加的第一个元素
                first = last;
                first.next = first;
                first.prev = first;
            }else {
                oldLast.next = last;
                first.prev = last;
            }
        }else {
            /*index位置的元素即为新添加元素的next位置*/
            Node<E> next = node(index);
            /*prev就是新添加的上一个元素*/
            Node<E> prev = next.prev;
            /*新添加的节点*/
            Node<E> node = new Node<>(prev,element,next);
            prev.next = node;
            next.prev = node;
            if (index == 0){
                first = node;
            }
        }
        size++;
    }

    private E remove(Node<E> node){
        if (size == 1){
            first = null;
            last = null;
            return node.element;
        }
        Node<E> prev = node.prev;
        Node<E> next = node.next;
        prev.next = next;
        next.prev = prev;
        if (node == first){
            first = next;
        }
        if (node == last){
            last = prev;
        }
        size--;
        return node.element;
    }
    @Override
    public E remove(int index) {
        rangeCheck(index);
        return remove(node(index));
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
