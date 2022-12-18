package com.mengtu.tree;

import com.mengtu.tree.printer.BinaryTreeInfo;

import java.util.LinkedList;
import java.util.Queue;

public class BinaryTree<E> implements BinaryTreeInfo {
    protected int size; //元素数量
    protected Node<E> root; //根节点

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public void clear(){
        root = null;
        size = 0;
    }

    public Node<E> invertTree(Node<E> root){
        if (root == null) return null;
        Node<E> temp = root.left;
        root.left = invertTree(root.right);
        root.right = invertTree(temp);
        return root;
    }
    /*前序遍历*/
    public void preorderTraversal(Node<E> node, BST.Visitor<E> visitor){
        if (node==null || visitor.stop) return;
        visitor.stop = visitor.visit(node.element);
        preorderTraversal(node.left,visitor);
        preorderTraversal(node.right,visitor);
    }
    public void inOrder(BST.Visitor<E> visitor){
        if (visitor == null) return;
        inorderTraversal(root,visitor);
    }

    /*中序遍历*/
    public void inorderTraversal(Node<E> node, BST.Visitor<E> visitor){
        if (node == null || visitor.stop) return;
        inorderTraversal(node.left,visitor);
        if (visitor.stop) return;
        visitor.stop = visitor.visit(node.element);
        inorderTraversal(node.right,visitor);
    }

    public void preOrder(BST.Visitor<E> visitor){
        if (visitor == null) return;
        preorderTraversal(root,visitor);
    }

    public void postOrder(BST.Visitor<E> visitor){
        if (visitor == null) return;
        postorderTraversal(root,visitor);
    }
    /*后续遍历*/
    public void postorderTraversal(Node<E> node, BST.Visitor<E> visitor){
        if (node == null || visitor.stop) return;
        inorderTraversal(node.left,visitor);
        inorderTraversal(node.right,visitor);
        if (visitor.stop) return;
        visitor.stop = visitor.visit(node.element);
    }
    public void levelOrder(BST.Visitor<E> visitor){
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
    protected static class Node<E>{
        E element;
        Node<E> left;//左子节点
        Node<E> right;//右子节点
        Node<E> parent; //父节点
        public Node(E element,Node<E> parent){

            this.element = element;
            this.parent = parent;
        }

        /*是否是叶子节点*/
        public boolean isLeaf() {
            return left == null && right == null;
        }
        public boolean hasTwoChildren(){
            return left != null && right != null;
        }

        public boolean isLeftChild(){
            return parent != null && this == parent.left;
        }

        public boolean isRightChild(){
            return parent != null && this == parent.right;
        }

        /*返回兄弟节点*/
        public Node<E> sibling(){
            if (isLeftChild()){
                return parent.right;
            }
            if (isRightChild()){
                return parent.left;
            }
            return null;
        }


    }

    /**
     * 给定一个节点，寻找他的前驱顶点
     * @param node 指定节点
     * @return 前驱节点
     */
    protected Node<E> precursor(Node<E> node){
        if (node == null) return null;
        /*前驱结点在左子树中 node.left.right.right*/
        Node<E> p = node.left;
        if (p != null){
            while (p.right != null){
                p = p.right;
            }
            return p;
        }
        /*从父节点中寻找*/
        while (node.parent != null && node == node.parent.left){
            node = node.parent;
        }
        return node.parent;
    }
    /**
     * 给定一个节点，寻找他的后继顶点
     * @param node 指定节点
     * @return 后继节点
     */
    protected Node<E> successor(Node<E> node){
        //后继节点在右子节点
        Node<E> s = node.right;
        if (s != null){
            //左子节点不为空 一直往左找下去
            while (s.left != null){
                s = s.left;
            }
            return s;
        }
        while (node.parent != null && node == node.parent.right){
            node = node.parent;
        }
        return node.parent;
    }
    public static abstract class Visitor<E>{

        /*停止标志位*/
        boolean stop;

        /**
         * @param element 元素
         * @return 如果返回true遍历停止
         */
        protected abstract boolean visit(E element);
    }
    /*递归方法求树的高度*/
    public int getTreeHeight(Node<E> node){
        if (node == null) return 0;
        return 1 + Math.max(getTreeHeight(node.left),getTreeHeight(node.right));
    }
    /*层序遍历求树高度*/
    public int height(){
        /*树高度*/
        int height = 0;
        /*每层元素的数量*/
        int levelSize = 1;
        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()){
            Node<E> node = queue.poll();
            levelSize--;
            if (node.left != null){
                queue.offer(node.left);
            }
            if (node.right != null){
                queue.offer(node.right);
            }
            if (levelSize == 0){
                height++;
                levelSize = queue.size();
            }
        }
        return height;
    }
    protected Node<E> createNode(E element,Node<E> parent){
        return new Node<>(element,parent);
    }
    /**
     * 判断一棵树是否是 完全二叉树
     * 完全 二叉树 满足几个前提条件：
     * 1.所有叶子节点都出现在 k 或者 k-1 层，而且从 1 到 k-1 层必须达到最大节点数
     * 2.第 k 层可以不是满的，但是第 k 层的所有节点必须集中在最左边。
     * 3.任何一个节点不能只有右子树没有左子树
     * 4.叶子节点出现在最后一层或者倒数第二层，不能再往上
     * @return Boolean
     */
    public boolean complete(){
        if (root == null) return true;
        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(root);
        //从当前位置开始以后的都必须是叶子节点的标志位
        boolean leaf = false;
        while (!queue.isEmpty()){
            Node<E> node = queue.poll();
            if (leaf && !node.isLeaf()) return false;
            if (node.left != null){
                queue.offer(node.left);
            }else if (node.right != null){//左子树为空 右子树不为空 直接返回false
                //node.left == null && node.right != null
                return false;
            }
            if (node.right != null){
                queue.offer(node.right);
            }else {//右子树为空 接下来的节点都必须是叶子节点
                //node.left == null && node.right == null
                //node.left != null && node.right == null
                leaf = true;
            }
        }
        return true;
    }

    @Override
    public Object root() {
        return root;
    }
    public Node<E> getRoot(){
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
}
