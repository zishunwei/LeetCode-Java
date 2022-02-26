package mobi.zishun.stack;

import java.util.Deque;
import java.util.LinkedList;

/*
 * 150. 逆波兰表达式求值
根据 逆波兰表示法，求表达式的值。
有效的算符包括+、-、*、/。每个运算对象可以是整数，也可以是另一个逆波兰表达式。
注意两个整数之间的除法只保留整数部分。
可以保证给定的逆波兰表达式总是有效的。换句话说，表达式总会得出有效数值且不存在除数为 0 的情况。
输入：tokens = ["2","1","+","3","*"]
输出：9
解释：该算式转化为常见的中缀算术表达式为：((2 + 1) * 3) = 9
链接：https://leetcode-cn.com/problems/evaluate-reverse-polish-notation
 */
public class EvaluateReversePolishNotation {
    public int evalRPN(String[] tokens) {
        Deque<Integer> stack = new LinkedList<>();
        for (String cur : tokens) {
            if (cur.equals("+") || cur.equals("-") || cur.equals("*") || cur.equals("/")) {
                int b = stack.removeFirst();
                int a = stack.removeFirst();
                if (cur.equals("+")) {
                    stack.addFirst(a + b);
                } else if (cur.equals("-")) {
                    stack.addFirst(a - b);
                } else if (cur.equals("*")) {
                    stack.addFirst(a * b);
                } else {
                    stack.addFirst(a / b);
                }
            } else {
                stack.addFirst(Integer.valueOf(cur));
            }
        }
        return stack.removeFirst();
    }

    public static void main(String[] args) {
        EvaluateReversePolishNotation m = new EvaluateReversePolishNotation();
        String[] tokens = {"2", "1", "+", "3", "*" };
        System.out.println(m.evalRPN(tokens));
    }

}
