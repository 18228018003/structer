package com.mengtu.trie;

import java.util.HashMap;

/**
 * 字典树实现
 * @param <V>
 */
public class Trie<V> {
    private int size;
//    private Node<V> root = new Node<>();
    private Node<V> root;

    int size(){
        return size;
    }
    boolean isEmpty(){
        return size == 0;
    }
    void clear(){
        size = 0;
//        root.getChildren().clear();
        root = null;
    }
    public V get(String key){
        Node<V> node = node(key);
        return node != null && node.word ? node.value : null;
    }
    public boolean contains(String key){
        Node<V> node = node(key);
        return node != null && node.word;
    }
    public V add(String key,V value){
        keyCheck(key);
        if (root == null) root = new Node<>(null);
        Node<V> node = root;
        int len = key.length();
        for (int i = 0; i < len; i++) {
            char c = key.charAt(i);
            boolean emptyChildren = node.children == null;

            Node<V> childNode = emptyChildren ? null : node.children.get(c);
            if (childNode == null){
                childNode = new Node<>(node);
                childNode.character = c;
                node.children = emptyChildren ? new HashMap<>():node.children;
                node.children.put(c,childNode);
            }
            node = childNode;
        }
        if (node.word){ //已经存在这个单词
            V oldValue = node.value;
            node.value = value;
            return oldValue;
        }
        //新增一个单词
        node.word = true;
        node.value = value;
        size++;
        return null;
    }
    public V remove(String str){
        //找到最后一个节点
        Node<V> node = node(str);
        //如果不是单词结尾，不用做任何处理
        if (node == null || !node.word) return null;
        size--;
        V old = node.value;
        //如果还有子节点
        if (node.children != null && !node.children.isEmpty()){
            node.word = false;

            node.value = null;
            return old;
        }
        //如果没有子节点
        Node<V> parent = null;
        while ((parent = node.parent) != null){
            parent.children.remove(node.character);
            if (parent.word || !parent.children.isEmpty()) break;
            node = parent;

        }
        return old;
    }
    boolean startsWith(String prefix){
        keyCheck(prefix);
        Node<V> node = root;
        int len = prefix.length();
        for (int i = 0; i < len; i++) {
            if (node == null || node.children == null || node.children.isEmpty()) return false;
            char c = prefix.charAt(i);
            node = node.children.get(c);
        }
        return true;
    }
    private Node<V> node(String key){
        Node<V> node = root; //根节点
        int len = key.length();
        for (int i = 0; i < len; i++) {
            if (node == null || node.children == null || node.children.isEmpty()) return null;
            char c = key.charAt(i); //拿到首字符
            node = node.children.get(c);
        }
        return node;
    }
    private void keyCheck(String key){
        if (key == null || key.length() == 0){
            throw new IllegalArgumentException("key must not be null");
        }
    }
    private static class Node<V>{
        private Node<V> parent;
        private HashMap<Character,Node<V>> children;
        private V value;
        private boolean word; //是否为单词的结尾（是否为一个完整的单词）
        private Character character;
        public Node(Node<V> parent) {
            this.parent = parent;
        }
        //        public HashMap<Character, Node<V>> getChildren() {
//            return children == null ? (children = new HashMap<>()) : children;
//        }
    }
}
