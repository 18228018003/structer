package com.mengtu.set;

import com.mengtu.tree.BinaryTree;
import com.mengtu.tree.RBTree;

import java.util.Set;

public class TreeSet<E> implements GdmSet<E> {
    private RBTree<E> tree = new RBTree<>();
    @Override
    public int size() {
        return tree.size();
    }

    @Override
    public boolean isEmpty() {
        return tree.isEmpty();
    }

    @Override
    public void clear() {
        tree.clear();
    }

    @Override
    public boolean contains(E element) {
        return tree.contains(element);
    }

    @Override
    public void add(E element) {
        tree.add(element);
    }

    @Override
    public void remove(E element) {
        tree.remove(element);
    }

    @Override
    public void traversal(Visitor<E> visitor) {
        tree.inOrder(new BinaryTree.Visitor<E>() {
            @Override
            protected boolean visit(E element) {
                return visitor.visit(element);
            }
        });
    }
}
