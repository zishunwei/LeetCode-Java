package mobi.zishun.stack;

import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/*
 * 32. 最长有效括号 (栈版本，还有dp版本见dp包)
给你一个只包含 '('和 ')'的字符串，找出最长有效（格式正确且连续）括号子串的长度。
示例 1：
输入：s = ")(()))"
输出：4
解释：最长有效括号子串是 "(())"
链接：https://leetcode-cn.com/problems/longest-valid-parentheses
 */
public class LongestValidParentheses {
    // 单栈 - 存储索引 - 一次遍历 同时计算最大长度 - O(n)
    public int longestValidParentheses(String s) {
        int maxRes = 0;
        Deque<Integer> stack = new LinkedList<>();
        //这一步可以防止当第一个Character是')'的时候发生越界异常
        stack.add(-1);
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else {
                //如果是右括号则出栈，抵消stack中的'(' 或者 刷新之前的')'位置（更新最新的无法配对的索引记录）
                stack.pop();
                if (!stack.isEmpty()) {
                    //当前全部人数减去剩余无法配对的人数（单身）i - stack.peek()即为当前maxRes
                    maxRes = Math.max(maxRes, i - stack.peek());
                } else {
                    //但是如果栈是空的话还是得把（单身的）右括号放进来
                    stack.push(i);
                }
            }
        }
        return maxRes;
    }

    // 初版 - 双栈 - 存下有效括号索引 - 排序找到最大连续索引数 - 复杂度高 - 代码冗余
    public int longestValidParenthesesV2(String s) {
        int n = s.length();
        if (n == 0) {
            return 0;
        }
        Deque<Character> stack = new LinkedList<>();
        Deque<Integer> indexStack = new LinkedList<>();
        List<Integer> validIndexs = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            char cur = s.charAt(i);
            if (cur == '(') {
                stack.addLast(cur);
                indexStack.addLast(i);
            } else {
                if (!stack.isEmpty()) {
                    stack.removeLast();
                    validIndexs.add(i);
                    validIndexs.add(indexStack.removeLast());
                }
            }
        }
        Collections.sort(validIndexs);
        int res = 0;
        int start = 0;
        for (int i = 0; i < validIndexs.size(); i++) {
            if (i > 0 && validIndexs.get(i) - validIndexs.get(i - 1) != 1) {
                start = i;
            }
            res = Math.max(res, i - start + 1);
        }
        return res;
    }

    public static void main(String[] args) {
        LongestValidParentheses m = new LongestValidParentheses();
        System.out.println(m.longestValidParentheses(")(()))"));
    }


}
