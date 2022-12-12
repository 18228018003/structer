package com.mengtu.tree;

import com.mengtu.tree.printer.BinaryTreeInfo;
import leetcode._226_ReverseTree;

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
    public Node<E> invertTree(Node<E> root){
        if (root == null) return null;
        Node<E> temp = root.left;
        root.left = invertTree(root.right);
        root.right = invertTree(temp);
        return root;
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
    private static class Node<E>{
        E element;
        Node<E> left;//左子节点
        Node<E> right;//右子节点
        Node<E> parent; //父节点

        public Node(E element,Node<E> parent){
            this.element = element;
            this.parent = parent;
        }

        public boolean isLeaf() {
            return left == null && right == null;
        }
        public boolean hasTwoChildren(){
            return left != null && right != null;
        }
    }
}
