package com.mengtu;

import com.mengtu.queue.GdmQueue;
import com.mengtu.stack.GdmStack;
import com.mengtu.tree.BinarySearchTree;
import com.mengtu.tree.printer.BinaryTrees;


public class Main {
    public static void main(String[] args) {
        testBinaryTree();
    }

    private static void testStack() {
        GdmStack<Integer> stack = new GdmStack<>();
        stack.push(11);
        stack.push(22);
        stack.push(33);
        stack.push(44);
        while (!stack.isEmpty()){
            System.out.println(stack.pop());
        }
    }

    static void testQueue(){
        GdmQueue<Integer> queue = new GdmQueue<>();
        queue.enQueue(11);
        queue.enQueue(22);
        queue.enQueue(33);
        queue.enQueue(44);
        queue.enQueue(55);

        while (!queue.isEmpty()){
            System.out.println(queue.deQueue());
        }
    }

    static void testBinaryTree(){
        Integer data[] = new Integer[]{
                7,4,9,2,5,8,11,3
        };
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        for (Integer datum : data) {
            bst.add(datum);
        }
        BinaryTrees.print(bst);
    }
}
