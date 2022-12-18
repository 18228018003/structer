package com.mengtu.tree;

import java.util.Comparator;

/*二叉搜索树*/
@SuppressWarnings("uncheck")
public class BST<E> extends BinaryTree<E>{

    private final Comparator<E> comparator;
    public BST(Comparator<E> comparator){
        this.comparator = comparator;
    }
    public BST(){
        this(null);
    }



    /**
     * @param e1 比较的元素1
     * @param e2 比较的元素2
     * @return 返回值等于0 代表e1和e2相等 小于0 e1 < e2 大于0 e1 > e2
     */
    private int compare(E e1,E e2){
        if (comparator != null){
            return comparator.compare(e1,e2);
        }
        return ((Comparable<E>)e1).compareTo(e2);
    }

    private void elementNullCheck(E element) {
        if (element == null) throw new IllegalArgumentException("element must not be null");
    }

    /**
     * 删除节点
     * @param element 元素
     */
    public void remove(E element){
        remove(node(element));
    }

    /**
     * 根据元素查找节点
     * @param element 元素
     * @return 节点
     */
    private Node<E> node(E element) {
        Node<E> node = root;
        while (node != null){
            int cmp = compare(element,node.element);
            if (cmp == 0) return node;
            if (cmp > 0) {
                node = node.right;
            }else {
                node = node.left;
            }
        }
        return null;
    }
    /*不支持null元素*/
    public void add(E element){
        elementNullCheck(element);
        if (root == null){
            root = createNode(element,null);
            size++;
            afterAdd(root);
            return;
        }
        //添加的不是第一个节点
        Node<E> node = root;
        Node<E> parent = null;
        int cmp = 0;
        while (node != null){
            cmp = compare(element, node.element);
            //在继续向下找的过程中把parent 节点保存下来
            parent = node;
            if (cmp > 0){
                node = node.right;
            }else if (cmp < 0){
                node = node.left;
            }else { //相等
                node.element = element;
                return;
            }
        }
        Node<E> newNode = createNode(element,parent);
        if (cmp > 0){
            parent.right = newNode;
        }else {
            parent.left = newNode;
        }
        size++;
        afterAdd(newNode);
    }

    /**
     * 添加node之后的调整 该树不需要实现自平衡，留一个方法给子类实现自平衡相关
     * @param node 节点
     */
    protected void afterAdd(Node<E> node){

    }
    /**
     * 删除node之后的调整 该树不需要实现自平衡，留一个方法给子类实现自平衡相关
     * @param node 节点
     */
    protected void afterRemove(Node<E> node,Node<E> replacement){

    }
    private void remove(Node<E> node){
        if (node == null) return;
        size--;
        /*度为2的节点*/
        if (node.hasTwoChildren()){
            /*找到后继节点*/
            Node<E> successor = successor(node);
            /*用后继节点的值覆盖度为2节点的值*/
            node.element = successor.element;
            //删除后继节点
            node = successor;
        }
        /*删除node节点 (node节点的度必定为0 或者 1)*/
        Node<E> replacement = node.left != null ? node.left : node.right;
        //说明代替的子节点是度为1的
        if (replacement != null){
            //更改parent
            replacement.parent = node.parent;
            //更改parent的left、right的指向
            if (node.parent == null){//node是度为1的节点 并且是根节点
                root = replacement;
            }else if (node == node.parent.left){
                node.parent.left = replacement;
            }else{  // node == node.parent.right
                node.parent.right = replacement;
            }
            afterRemove(node,replacement);
        }else if (node.parent == null){//说明是叶子节点 并且为根节点
            root = null;
            afterRemove(node,null);
        }else {//说明是叶子节点 但不是根节点
            if (node == node.parent.left){
                node.parent.left = null;
            }else{
                node.parent.right = null;
            }
            afterRemove(node,null);
        }
    }
    public boolean contains(E element){
        return node(element) != null;
    }

}
