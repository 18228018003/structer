package com.mengtu;

import com.mengtu.queue.GdmQueue;
import com.mengtu.stack.GdmStack;


public class Main {
    public static void main(String[] args) {

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
}
