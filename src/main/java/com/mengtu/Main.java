package com.mengtu;

import com.mengtu.queue.GdmQueue;
import com.mengtu.stack.GdmStack;
import com.mengtu.tree.AVLTree;
import com.mengtu.tree.BST;
import com.mengtu.tree.printer.BinaryTrees;


public class Main {
    public static void main(String[] args) {
        testAVLTree();
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
                8,4,10,2,6,7,11,3,1,5,9
        };
        Integer data1[] = new Integer[]{
                7,4,9,2,5,8,10
        };
        BST<Integer> bst = new BST<>();
        for (Integer datum : data) {
            bst.add(datum);
        }
        System.out.println(bst.getTreeHeight(bst.getRoot()));
        System.out.println(bst.height());
        System.out.println( " ============= ");
        BinaryTrees.print(bst);
        System.out.println();
        System.out.println(" =========== ");
//        bst.levelOrder(new BinarySearchTree.Visitor<>() {
//            @Override
//            protected boolean visit(Integer element) {
//                if (element == 0) return true;
//                System.out.println(element);
//                return false;
//            }
//        });
        System.out.println(bst.complete() ? "是完全二叉树" : "不是完全二叉树");
    }

    static void testInvertTree(){
        Integer data[] = new Integer[]{
                7,4,9,2,5,8,10,11,23,51,45,67,131
        };
        BST<Integer> bst = new BST<>();
        for (Integer datum : data) {
            bst.add(datum);
        }
        BinaryTrees.println(bst);
        System.out.println( " ========================= ");
//        bst.invertTree(bst.getRoot());
        System.out.println( " ========================= ");
        BinaryTrees.print(bst);
    }

    static void testRemove() {
        Integer data[] = new Integer[]{
                7,4,9,2,5,8,10,11,23,51,45,67,131
        };
        BST<Integer> bst = new BST<>();
        for (Integer datum : data) {
            bst.add(datum);
        }
        BinaryTrees.println(bst);
//        bst.remove(131);
//        bst.remove(67);
//        bst.remove(45);
        bst.remove(51);
        bst.remove(23);
        bst.remove(11);
        bst.remove(10);
        BinaryTrees.println(bst);
    }

    static void testAVLTree(){
        Integer data[] = new Integer[]{
                13,14,15,12,11,17,16,8,9,1
        };
        AVLTree<Integer> avlTree = new AVLTree<>();
        for (Integer datum : data) {
            avlTree.add(datum);
        }
        BinaryTrees.println(avlTree);
    }
}
