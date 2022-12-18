package com.mengtu.tree;

import java.util.Comparator;
import java.util.Map;

/**
 * 自平衡二叉搜索树
 * 先实现一颗AVL树
 * AVL 树是一种平衡二叉树，得名于其发明者的名字
 * （ Adelson-Velskii 以及 Landis）。（可见名字长的好处，命名都能多占一个字母出来）。
 * 平衡二叉树递归定义如下：
 *      左右子树的高度差小于等于 1。
 *      其每一个子树均为平衡二叉树。
 */
public class AVLTree<E> extends BBST<E>{
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
                updateHeight(node);
            }else {
                //恢复平衡
                rebalance(node);
                //整棵树恢复平衡
                break;
            }
        }
    }

    @Override
    protected void afterRotate(Node<E> grand, Node<E> parent, Node<E> child) {
        super.afterRotate(grand,parent,child);
        updateHeight(parent);
        updateHeight(grand);
    }

    @Override
    protected void afterRemove(Node<E> node,Node<E> replacement) {
        while ((node = node.parent) != null){
            //判断node是否平衡
            if (isBalanced(node)){
                //更新高度
                updateHeight(node);
            }else {
                //恢复平衡
                rebalance(node);
            }
        }
    }

    /**
     * 恢复平衡
     * @param node 高度最低的不平衡节点
     */
    private void rebalance(Node<E> node) {
        Node<E> parent = ((AVLNode<E>) node).tallerChild();
        Node<E> child = ((AVLNode<E>) parent).tallerChild();
        if (parent.isLeftChild()){//L
            if (child.isLeftChild()){ //LL
                rotateRight(node);
            }else {//LR
                rotateLeft(parent);
                rotateRight(node);
            }
        }else {//R
            if (child.isRightChild()){//RR
                rotateLeft(node);
            }else { //RL
                rotateRight(parent);
                rotateLeft(node);
            }
        }
    }


    @Override
    protected Node<E> createNode(E element, Node<E> parent) {
        return new AVLNode<>(element,parent);
    }

    /**
     * 计算树是否平衡
     *  平衡因子小于等于1即为平衡
     * @param node 节点
     * @return 布尔值
     */
    private boolean isBalanced(Node<E> node){
        return Math.abs(((AVLNode<E>)node).balanceFactor()) <= 1;
    }

    private void updateHeight(Node<E> node){
        ((AVLNode<E>) node).updateHeight();
    }
    protected static class AVLNode<E> extends Node<E>{
        //树的高度
        int height = 1;
        public AVLNode(E element, Node<E> parent) {
            super(element, parent);
        }
        /*平衡因子计算*/
        public int balanceFactor(){
            int leftHeight = left == null ? 0 : ((AVLNode<E>)left).height;
            int rightHeight = right == null ? 0 : ((AVLNode<E>)right).height;
            return leftHeight - rightHeight;
        }

        /*更新节点高度*/
        public void updateHeight() {
            int leftHeight = left == null ? 0 : ((AVLNode<E>)left).height;
            int rightHeight = right == null ? 0 : ((AVLNode<E>)right).height;
            height = 1 + Math.max(leftHeight,rightHeight);
        }

        /**
         * 返回左右子节点中较高的那一个节点
         */
        public Node<E> tallerChild(){
            int leftHeight = left == null ? 0 : ((AVLNode<E>)left).height;
            int rightHeight = right == null ? 0 : ((AVLNode<E>)right).height;
            if (leftHeight > rightHeight) return left;
            if (leftHeight < rightHeight) return right;
            //节点高度一样
            return isLeftChild() ? left : right;
        }
    }
}
