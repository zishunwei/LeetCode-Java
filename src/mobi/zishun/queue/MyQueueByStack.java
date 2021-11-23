package mobi.zishun.queue;

import java.util.Deque;
import java.util.LinkedList;

/*
232. 用栈实现队列
 */
public class MyQueueByStack {
    private Deque<Integer> inStack;
    private Deque<Integer> outStack;
    private int front; //队首元素

    public MyQueueByStack() {
        inStack = new LinkedList<>();
        outStack = new LinkedList<>();
    }

    public void push(int x) {
        if (inStack.isEmpty()) {
            front = x;
        }
        inStack.push(x);
    }

    public int pop() {
        if (outStack.isEmpty()) {
            while (!inStack.isEmpty()) {
                outStack.push(inStack.pop());
            }
        }
        return outStack.pop();
    }

    public int peek() {
        if (!outStack.isEmpty()) {
            // out stack不为空，说明上一次in2out过的元素还保留在outStack中，栈顶为最早入队列的元素
            return outStack.peek();
        }
        // outStack为空，队列有值的话全在inStack中
        return front;
    }

    public boolean empty() {
        return inStack.isEmpty() && outStack.isEmpty();
    }

}
