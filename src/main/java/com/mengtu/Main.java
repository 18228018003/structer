package com.mengtu;

import com.mengtu.queue.GdmQueue;
import com.mengtu.stack.GdmStack;
import com.mengtu.tree.BinarySearchTree;
import com.mengtu.tree.printer.BinaryTrees;


public class Main {
    public static void main(String[] args) {
        testInvertTree();
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
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
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
                7,4,9,2,5,8,10
        };
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        for (Integer datum : data) {
            bst.add(datum);
        }
        BinaryTrees.print(bst);
        System.out.println( " ========================= ");
        bst.invertTree(bst.getRoot());
        System.out.println( " ========================= ");
        BinaryTrees.print(bst);
    }
}
