package com.mengtu.queue;

import com.mengtu.heap.BinaryHeap;

import java.util.Comparator;

public class GdmPriorityQueue<E> {
    private BinaryHeap<E> heap = new BinaryHeap<>();
    public GdmPriorityQueue(Comparator<E> comparator) {
        heap = new BinaryHeap<>(comparator);
    }

    public GdmPriorityQueue() {
        this(null);
    }

    public int size() {
        return heap.size();
    }

    public boolean isEmpty() {
        return heap.isEmpty();
    }

    public void clear() {
        heap.clear();
    }

    public void offer(E element) {
        heap.add(element);
    }

    public E poll() {
        return heap.remove();
    }

    public E peek() {
        return heap.get();
    }
}
