package mobi.zishun.greedy;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Objects;

/*
 * 402. 移掉 K 位数字
 * 给你一个以字符串表示的非负整数 num 和一个整数 k ，移除这个数中的 k 位数字，使得剩下的数字最小。
 * 请你以字符串形式返回这个最小的数字。
 */
public class RemoveKdigits {

    // 利用栈实现
    public String removeKdigits(String num, int k) {
        int n = num.length();
        Deque<Character> stack = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            char digit = num.charAt(i);
            while (!stack.isEmpty() && k > 0 && stack.getLast() > digit) {
                stack.removeLast();
                k--;
            }
            stack.addLast(digit);
        }
        // 已经遍历到底但还没有移除完全（k还不等于0）
        for (int i = 0; i < k; i++) {
            if (!stack.isEmpty()) {
                stack.removeLast();
            } else {
                break;
            }
        }
        // 合并结果字符串
        StringBuilder res = new StringBuilder();
        while (!stack.isEmpty()) {
            // 处理前缀为0的情况
            if (stack.getFirst() == '0' && res.length() == 0) {
                stack.removeFirst();
            } else {
                res.append(stack.removeFirst());
            }
        }
        // 处理删除完全的情况（example: "10", 2）
        if (res.length() == 0) {
            return "0";
        }
        return res.toString();
    }

    // 初版(每次都拼接)-效率较低
    public String removeKdigitsV2(String num, int k) {
        while (k > 0 && !Objects.equals(num, "0")) {
            if (num.length() == 1) {
                num = "0";
                break;
            }
            for (int i = 0; i < num.length(); i++) {
                if (i == num.length() - 1) {
                    num = num.substring(0, i);
                } else if (num.charAt(i) > num.charAt(i + 1)) {
                    num = num.substring(0, i) + num.substring(i + 1);
                    break;
                }
            }
            k--;
        }
        int i = 0;
        while (num.length() > i + 1 && Objects.equals(num.charAt(i), '0')) {
            i++;
        }
        return num.substring(i);
    }

    public static void main(String[] args) {
        RemoveKdigits method = new RemoveKdigits();
        System.out.println(method.removeKdigits("1234532219", 5));

        // 边界条件需考虑
        System.out.println(method.removeKdigits("10", 2));
        System.out.println(method.removeKdigits("100", 1));
        System.out.println(method.removeKdigits("10200", 1));
        System.out.println(method.removeKdigits("9", 1));
        System.out.println(method.removeKdigits("112", 1));

    }

}
