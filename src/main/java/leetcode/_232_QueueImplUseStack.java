package leetcode;

import com.mengtu.stack.GdmStack;

/**
 * 用栈实现队列
 */
public class _232_QueueImplUseStack<E> {
    private final GdmStack<E> inStack = new GdmStack<>();
    private final GdmStack<E> outStack = new GdmStack<>();

    public void clear(){
        inStack.clear();
        outStack.clear();
    }

    public void offer(E element){
        inStack.push(element);
    }

    public E poll(){
        checkOutStack();
        return outStack.pop();
    }

    private void checkOutStack() {
        if (outStack.isEmpty()){
            while (!inStack.isEmpty()){
                outStack.push(inStack.pop());
            }
        }
    }

    public E peek(){
        checkOutStack();
        return outStack.top();
    }

    public boolean isEmpty(){
        return inStack.isEmpty() && outStack.isEmpty();
    }

}
