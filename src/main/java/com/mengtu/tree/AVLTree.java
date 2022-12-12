package com.mengtu.tree;

import java.util.Comparator;
import java.util.Map;

/**
 * 自平衡二叉搜索树
 * 先实现一颗AVL树
 */
public class AVLTree<E> extends BST<E>{
    public AVLTree(){
        this(null);
    }
    public AVLTree(Comparator<E> comparator){
        super(comparator);
    }

    @Override
    protected void afterAdd(Node<E> node) {
        while ((node = node.parent) != null){
            //判断node是否平衡
            if (isBalanced(node)){
                //更新高度
            }else {
                //恢复平衡
            }
        }
    }

    @Override
    protected Node<E> createNode(E element, Node<E> parent) {
        return new AVLNode<>(element,parent);
    }
    private boolean isBalanced(Node<E> node){
        return Math.abs(((AVLNode<E>)node).balanceFactor()) <= 1;
    }
    protected static class AVLNode<E> extends Node<E>{
        //树的高度
        int height;
        public AVLNode(E element, Node<E> parent) {
            super(element, parent);
        }
        public int balanceFactor(){
            int leftHeight = left == null ? 0 : ((AVLNode<E>)left).height;
            int rightHeight = right == null ? 0 : ((AVLNode<E>)right).height;
            return leftHeight - rightHeight;
        }
    }
}
