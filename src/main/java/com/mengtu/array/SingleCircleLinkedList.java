package com.mengtu.array;

/**
 * 单向循环链表
 * @param <E>
 */
public class SingleCircleLinkedList<E> extends GdmAbstractList<E> {
    private Node<E> first;
    private static class Node<E>{
        E element;
        Node<E> next;
        public Node(E element,Node<E> next){
            this.element = element;
            this.next = next;
        }
    }
    public SingleCircleLinkedList(){
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
        if (index == 0){
            Node<E> newFirst = new Node<>(element,first);
            //拿到最后一个节点 如果是第一次添加 把first当做last 自己指向自己
            Node<E> last = (size == 0) ? newFirst : node(size - 1);
            last.next = newFirst;
            first = newFirst;
        }else {
            Node<E> prev = node(index - 1);
            prev.next = new Node<>(element,prev.next);
        }
        size++;
    }

    @Override
    public E remove(int index) {
        rangeCheck(index);
        Node<E> node = first;
        if (index == 0){
            if (size == 1){
                first = null;
            }else {
                Node<E> last = node(size-1);
                first = first.next;
                last.next = first;
            }
        }else {
            /*拿到前一节点*/
            Node<E> prev = node(index - 1);
            node = prev.next;
            prev.next = node.next;
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
        /**/
        Node<E> node = first;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node;

    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node<E> node = first;
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
