package com.mengtu.map;

import com.mengtu.tree.BinaryTree;
import com.mengtu.tree.RBTree;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

public class TreeMap<K,V> implements GdmMap<K,V> {
    protected int size; //元素数量
    protected Node<K,V> root; //根节点

    private final Comparator<K> comparator;
    public TreeMap(Comparator<K> comparator){
        this.comparator = comparator;
    }
    public TreeMap(){
        this(null);
    }
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
    private void keyNotNullCheck(K key) {
        if (key == null) throw new IllegalArgumentException("key must not be null");
    }

    @Override
    public V put(K key, V value) {
        keyNotNullCheck(key);
        if (root == null){
            root = new Node<>(key,value,null);
            size++;
            afterPut(root);
            return null;
        }
        //添加的不是第一个节点
        Node<K,V> node = root;
        Node<K,V> parent = null;
        int cmp = 0;
        while (node != null){
            cmp = compare(key, node.key);
            //在继续向下找的过程中把parent 节点保存下来
            parent = node;
            if (cmp > 0){
                node = node.right;
            }else if (cmp < 0){
                node = node.left;
            }else { //相等
                V old = node.value;
                node.key = key;
                node.value = value;
                return old;
            }
        }
        Node<K,V> newNode = new Node<>(key,value,parent);
        if (cmp > 0){
            parent.right = newNode;
        }else {
            parent.left = newNode;
        }
        size++;
        afterPut(newNode);
        return null;
    }
    private int compare(K e1,K e2){
        if (comparator != null){
            return comparator.compare(e1,e2);
        }
        return ((Comparable<K>)e1).compareTo(e2);
    }
    private void afterPut(Node<K, V> node) {
        Node<K,V> parent = node.parent;
        //如果添加的是根节点 或者上溢到了根节点
        if (parent == null) {
            black(node);
            return;
        }
        //如果父节点是黑色 直接返回
        if (isBlack(parent)) return;
        //uncle节点
        Node<K,V> uncle = parent.sibling();
        Node<K,V> grand = red(parent.parent);
        //叔父节点是红色
        if (isRED(uncle)){
            //将父节点染成黑色
            black(parent);
            //将叔父节点染成黑色
            black(uncle);
            //祖父节点向上合并 祖父节点染成红色当做新添加的节点
            afterPut(grand);
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
    private Node<K,V> node(K key) {
        Node<K,V> node = root;
        while (node != null){
            int cmp = compare(key,node.key);
            if (cmp == 0) return node;
            if (cmp > 0) {
                node = node.right;
            }else {
                node = node.left;
            }
        }
        return null;
    }
    protected void rotateLeft(Node<K,V> grand){
        //拿到节点的右节点
        Node<K,V> parent = grand.right;
        Node<K,V> child = parent.left;
        //进行旋转操作
        grand.right = child;
        parent.left = grand;
        afterRotate(grand, parent, child);
    }
    /*旋转之后的操作*/
    protected void afterRotate(Node<K,V> grand, Node<K,V> parent, Node<K,V> child) {
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
    protected void rotateRight(Node<K,V> grand){
        Node<K,V> parent = grand.left;
        Node<K,V> child = parent.right;
        parent.right = grand;
        grand.left = child;
        afterRotate(grand,parent,child);
    }
    /*染色操作*/
    private Node<K,V> color(Node<K,V> node, boolean color){
        if (node == null) return null;
        node.color = color;
        return node;
    }
    /*把节点染成红色*/
    private Node<K,V> red(Node<K,V> node){
        return color(node,RED);
    }
    /*把节点染成黑色*/
    private Node<K,V> black(Node<K,V> node){
        return color(node,BLACK);
    }
    /*返回节点的颜色*/
    private boolean colorOf(Node<K,V> node){
        return node == null ? BLACK : node.color;
    }
    private boolean isBlack(Node<K,V> node){
        return colorOf(node) == BLACK;
    }
    private boolean isRED(Node<K,V> node){
        return colorOf(node) == RED;
    }
    @Override
    public V get(K key) {
        Node<K, V> node = node(key);
        return node != null ? node.value : null;
    }

    @Override
    public V remove(K key) {
        return remove(node(key));
    }
    /**
     * 给定一个节点，寻找他的前驱顶点
     * @param node 指定节点
     * @return 前驱节点
     */
    protected Node<K,V> precursor(Node<K,V> node){
        if (node == null) return null;
        /*前驱结点在左子树中 node.left.right.right*/
        Node<K,V> p = node.left;
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
    protected Node<K,V> successor(Node<K,V> node){
        //后继节点在右子节点
        Node<K,V> s = node.right;
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
    private V remove(Node<K,V> node) {
        if (node == null) return null;
        V oldValue = node.value;
        size--;
        /*度为2的节点*/
        if (node.hasTwoChildren()){
            /*找到后继节点*/
            Node<K,V> successor = successor(node);
            /*用后继节点的值覆盖度为2节点的值*/
            node.key = successor.key;
            node.value = successor.value;
            //删除后继节点
            node = successor;
        }
        /*删除node节点 (node节点的度必定为0 或者 1)*/
        Node<K,V> replacement = node.left != null ? node.left : node.right;
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
        return oldValue;
    }
    protected void afterRemove(Node<K,V> node, Node<K,V> replacement) {
        if (isRED(node)) return;
        if (isRED(replacement)){
            black(node);
            return;
        }
        Node<K,V> parent = node.parent;
        //删除的是根节点
        if (parent == null) return;

        //删除的是黑色叶子节点
        //判断被删除的node是左还是右
        boolean left = parent.left == null || node.isLeftChild();
        Node<K,V> sibling = left ? parent.right : parent.left;
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
    @Override
    public boolean containsKey(K key) {
        return node(key) != null;
    }
    @Override
    public boolean containsValue(V value) {
        if (root == null) return false;
        Queue<Node<K,V>> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()){
            Node<K, V> node = queue.poll();
            if (Objects.equals(node.value,value)) return true;
            if (node.left != null){
                queue.offer(node.left);
            }
            if (node.right != null){
                queue.offer(node.right);
            }
        }
        return false;
    }

    @Override
    public void traversal(Visitor<K, V> visitor) {

    }
    private static final boolean RED = false;
    private static final boolean BLACK = true;
    private static class Node<K,V>{
        K key;
        V value;
        boolean color = RED;
        Node<K,V> left;//左子节点
        Node<K,V> right;//右子节点
        Node<K,V> parent; //父节点
        public Node(K key,V value, Node<K,V> parent){
            this.key = key;
            this.value = value;
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
        public Node<K,V> sibling(){
            if (isLeftChild()){
                return parent.right;
            }
            if (isRightChild()){
                return parent.left;
            }
            return null;
        }
    }
}
