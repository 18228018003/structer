package com.mengtu.tree;

import com.mengtu.tree.printer.BinaryTreeInfo;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

/*二叉搜索树*/
public class BinarySearchTree<E> implements BinaryTreeInfo {
    private int size; //元素数量
    private Comparator<E> comparator;
    public BinarySearchTree(Comparator<E> comparator){
        this.comparator = comparator;
    }
    public BinarySearchTree(){
        this(null);
    }

    private Node<E> root; //根节点
    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public void clear(){
        size = 0;
    }
    /*不支持null元素*/
    public void add(E element){
        elementNullCheck(element);
        if (root == null){
            root = new Node<>(element,null);
            size++;
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
        Node<E> newNode = new Node<>(element,parent);
        if (cmp > 0){
            parent.right = newNode;
        }else {
            parent.left = newNode;
        }
        size++;
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

    public void remove(E element){

    }

    public boolean contains(E element){
        return false;
    }

    /*前序遍历*/
    public void preorderTraversal(Node<E> node,Visitor<E> visitor){
        if (node==null || visitor.stop) return;
        visitor.stop = visitor.visit(node.element);
        preorderTraversal(node.left,visitor);
        preorderTraversal(node.right,visitor);
    }
    public void inOrder(Visitor<E> visitor){
        if (visitor == null) return;
        inorderTraversal(root,visitor);
    }

    /*中序遍历*/
    public void inorderTraversal(Node<E> node,Visitor<E> visitor){
        if (node == null || visitor.stop) return;
        inorderTraversal(node.left,visitor);
        if (visitor.stop) return;
        visitor.stop = visitor.visit(node.element);
        inorderTraversal(node.right,visitor);
    }

    public void preOrder(Visitor<E> visitor){
        if (visitor == null) return;
        preorderTraversal(root,visitor);
    }

    public void postOrder(Visitor<E> visitor){
        if (visitor == null) return;
        postorderTraversal(root,visitor);
    }
    /*后续遍历*/
    public void postorderTraversal(Node<E> node,Visitor<E> visitor){
        if (node == null || visitor.stop) return;
        inorderTraversal(node.left,visitor);
        inorderTraversal(node.right,visitor);
        if (visitor.stop) return;
        visitor.stop = visitor.visit(node.element);
    }
    public void levelOrder(Visitor<E> visitor){
        if (root == null || visitor == null) return;
        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()){
            Node<E> node = queue.poll();
            if (visitor.visit(node.element)) return;
            if (node.left != null){
                queue.offer(node.left);
            }
            if (node.right != null){
                queue.offer(node.right);
            }
        }
    }
    /*层序遍历*/
    public void levelTraversal(Node<E> node){
        if (node == null) return;
        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(node);
        while (!queue.isEmpty()){
            Node<E> poll = queue.poll();
            System.out.println(poll.element);
            if (poll.left != null){
                queue.offer(poll.left);
            }
            if (poll.right != null){
                queue.offer(poll.right);
            }
        }
    }

    @Override
    public Object root() {
        return root;
    }

    @Override
    public Object left(Object node) {
        return ((Node<E>)node).left;
    }

    @Override
    public Object right(Object node) {
        return ((Node<E>)node).right;
    }

    @Override
    public Object string(Object node) {
        return ((Node<E>)node).element;
    }
    private static abstract class Visitor<E>{

        /*停止标志位*/
        boolean stop;

        /**
         * @param element 元素
         * @return 如果返回true遍历停止
         */
        abstract boolean visit(E element);
    }
    private static class Node<E>{
        E element;
        Node<E> left;//左子节点
        Node<E> right;//右子节点
        Node<E> parent; //父节点

        public Node(E element,Node<E> parent){
            this.element = element;
            this.parent = parent;
        }
    }
}
