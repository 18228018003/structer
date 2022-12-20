package com.mengtu.tree;

import com.mengtu.tree.printer.BinaryTrees;

public class Main {
    public static void main(String[] args) {
        test9();
    }
    static void test9() {
        Integer data[] = new Integer[] {
                7, 4, 9, 2, 1
        };

        BST<Integer> bst = new BST<>();
        for (int i = 0; i < data.length; i++) {
            bst.add(data[i]);
        }
        BinaryTrees.println(bst);

        bst.preOrder(new BinaryTree.Visitor<Integer>() {
            public boolean visit(Integer element) {
                System.out.print(element + " ");
                return element == 2;
            }
        });
        System.out.println();

//        bst.inorder(new Visitor<Integer>() {
//            public boolean visit(Integer element) {
//                System.out.print(element + " ");
//                return element == 4 ? true : false;
//            }
//        });
//        System.out.println();
//
//        bst.postorder(new Visitor<Integer>() {
//            public boolean visit(Integer element) {
//                System.out.print(element + " ");
//                return element == 4 ? true : false;
//            }
//        });
//        System.out.println();
//
//        bst.levelOrder(new Visitor<Integer>() {
//            public boolean visit(Integer element) {
//                System.out.print(element + " ");
//                return element == 9 ? true : false;
//            }
//        });
//        System.out.println();
    }
}
