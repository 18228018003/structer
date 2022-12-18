package com.mengtu.tree;

import java.util.Comparator;

/**
 * 红黑树实现
 */
public class RBTree<E> extends BBST<E> {

    private static final boolean RED = false;
    private static final boolean BLACK = true;

    public RBTree(){
        this(null);
    }
    public RBTree(Comparator<E> comparator){
        super(comparator);
    }

    /*染色操作*/
    private Node<E> color(Node<E> node,boolean color){
        if (node == null) return null;
        ((RBNode<E>)node).color = color;
        return node;
    }
    /*把节点染成红色*/
    private Node<E> red(Node<E> node){
        return color(node,RED);
    }
    /*把节点染成黑色*/
    private Node<E> black(Node<E> node){
        return color(node,BLACK);
    }
    /*返回节点的颜色*/
    private boolean colorOf(Node<E> node){
        return node == null ? BLACK : ((RBNode<E>)node).color;
    }
    private boolean isBlack(Node<E> node){
        return colorOf(node) == BLACK;
    }
    private boolean isRED(Node<E> node){
        return colorOf(node) == RED;
    }

    @Override
    protected void afterAdd(Node<E> node) {
        Node<E> parent = node.parent;
        //如果添加的是根节点 或者上溢到了根节点
        if (parent == null) {
            black(node);
            return;
        }
        //如果父节点是黑色 直接返回
        if (isBlack(parent)) return;
        //uncle节点
        Node<E> uncle = parent.sibling();
        Node<E> grand = red(parent.parent);
        //叔父节点是红色
        if (isRED(uncle)){
            //将父节点染成黑色
            black(parent);
            //将叔父节点染成黑色
            black(uncle);
            //祖父节点向上合并 祖父节点染成红色当做新添加的节点
            afterAdd(grand);
            return;
        }
        //叔父节点不是红色
        if (parent.isLeftChild()){//L
            if (node.isLeftChild()){ //LL
                black(parent);
            }else {//LR
                black(node);
                rotateLeft(parent);
            }
            rotateRight(grand );
        }else { //R
            if (node.isLeftChild()){//RL
                black(node);
                rotateRight(parent);
            }else { //RR
                black(parent);
            }
            rotateLeft(grand);
        }
    }

    @Override
    protected Node<E> createNode(E element, Node<E> parent) {
        return new RBNode<>(element,parent);
    }

    @Override
    protected void afterRemove(Node<E> node,Node<E> replacement) {
        if (isRED(node)) return;
        if (isRED(replacement)){
            black(node);
            return;
        }
        Node<E> parent = node.parent;
        //删除的是根节点
        if (parent == null) return;

        //删除的是黑色叶子节点
        //判断被删除的node是左还是右
        boolean left = parent.left == null || node.isLeftChild();
        Node<E> sibling = left ? parent.right : parent.left;
        if (left){//被删除的节点在左边，兄弟节点右边
            if (isRED(sibling)){ //兄弟节点是红色
                black(sibling);
                red(parent);
                rotateLeft(parent);
                //跟换兄弟
                sibling = parent.right;
            }
            //兄弟节点没有一个红色子节点，父节点要向下和兄弟节点合并
            if (isBlack(sibling.left) && isBlack(sibling.right)){
                //父节点是否是黑色
                boolean parentIsBlack = isBlack(parent);
                red(sibling);
                black(parent);
                if (parentIsBlack){
                    afterRemove(parent,null);
                }
            }else {//兄弟节点至少有一个红色子节点
                //兄弟节点的左边是黑色，兄弟要先旋转
                if (isBlack(sibling.right)){
                    rotateRight(sibling);
                    sibling = parent.right;
                }
                color(sibling,colorOf(parent));
                black(sibling.right);
                black(parent);
                rotateLeft(parent);
            }
        }else{//被删除的节点在右边，兄弟节点左边
            if (isRED(sibling)){ //兄弟节点是红色
                black(sibling);
                red(parent);
                rotateRight(parent);
                //跟换兄弟
                sibling = parent.left;
            }
            //兄弟节点没有一个红色子节点，父节点要向下和兄弟节点合并
            if (isBlack(sibling.left) && isBlack(sibling.right)){
                //父节点是否是黑色
                boolean parentIsBlack = isBlack(parent);
                red(sibling);
                black(parent);
                if (parentIsBlack){
                    afterRemove(parent,null);
                }
            }else {//兄弟节点至少有一个红色子节点
                //兄弟节点的左边是黑色，兄弟要先旋转
                if (isBlack(sibling.left)){
                    rotateLeft(sibling);
                    sibling = parent.left;
                }
                color(sibling,colorOf(parent));
                black(sibling.left);
                black(parent);
                rotateRight(parent);
            }
        }

    }

    private static class RBNode<E> extends Node<E>{
        boolean color = RED;
        public RBNode(E element, Node<E> parent) {
            super(element, parent);
        }

    }
}
