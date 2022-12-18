package com.mengtu.tree;

import java.util.Comparator;

public class BBST<E> extends BST<E> {
    public BBST(){
        this(null);
    }
    public BBST(Comparator<E> comparator){
        super(comparator);
    }
    /**
     * 左旋转
     * @param grand 传入的节点
     */
    protected void rotateLeft(Node<E> grand){
        //拿到节点的右节点
        Node<E> parent = grand.right;
        Node<E> child = parent.left;
        //进行旋转操作
        grand.right = child;
        parent.left = grand;
        afterRotate(grand, parent, child);
    }
    /*旋转之后的操作*/
    protected void afterRotate(Node<E> grand, Node<E> parent, Node<E> child) {
        //让parent成为子树的根节点
        parent.parent = grand.parent;
        if (grand.isLeftChild()){
            grand.parent.left = parent;
        }else if (grand.isRightChild()){
            grand.parent.right = parent;
        }else {//根节点
            root = parent;
        }
        //更新原来子树根节点的parent
        grand.parent = parent;
        if (child != null){
            child.parent = grand;
        }
    }

    /**
     * 旋转
     */
    protected void rotateRight(Node<E> grand){
        Node<E> parent = grand.left;
        Node<E> child = parent.right;
        parent.right = grand;
        grand.left = child;
        afterRotate(grand,parent,child);
    }
}
