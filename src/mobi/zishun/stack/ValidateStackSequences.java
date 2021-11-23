package mobi.zishun.stack;

import java.util.Deque;
import java.util.LinkedList;

public class ValidateStackSequences {
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        Deque<Integer> stack = new LinkedList<>();
        int i = 0;
        for (int num : pushed) {
            stack.addFirst(num);
            // 循环判断-1。栈为空；2。栈顶元素等于popped首个元素-出栈&&popped索引++
            while (!stack.isEmpty() && stack.peekFirst() == popped[i]) {
                stack.removeFirst();
                i++;
            }
        }
        return stack.isEmpty();
    }
}
