package mobi.zishun.string;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/*
 * 821. 字符的最短距离
给你一个字符串 s 和一个字符 c ，且 c 是 s 中出现过的字符。
返回一个整数数组 answer ，其中 answer.length == s.length 且 answer[i] 是 s 中从下标 i 到离它 最近 的字符 c 的 距离 。
两个下标 i 和 j 之间的 距离 为 abs(i - j) ，其中 abs 是绝对值函数。

示例 1：
输入：s = "loveleetcode", c = "e"
输出：[3,2,1,0,1,0,0,1,2,2,1,0]
提示：
1 <= s.length <= 10^4
s[i] 和 c 均为小写英文字母
题目数据保证 c 在 s 中至少出现一次
* https://leetcode-cn.com/problems/shortest-distance-to-a-character/
 */
public class ShortestToChar {
    // 两次遍历
    // 问题可以转换成，对 ss 的每个下标 i，求
    // s[i] 到其左侧最近的字符 c 的距离
    // s[i] 到其右侧最近的字符 c 的距离
    public int[] shortestToChar(String s, char c) {
        int n = s.length();
        char[] chars = s.toCharArray();
        int[] res = new int[n];
        // s[i] 到其左侧最近的字符 c 的距离
        int index = -n; // 保证在i没有左侧c的时候，res[i]最大
        for (int i = 0; i < n; i++) {
            if (chars[i] == c) {
                index = i;
            }
            res[i] = i - index; // 保证在i没有左侧c的时候，res[i]最大
        }
        // s[i] 到其右侧最近的字符 c 的距离
        index = 2 * n; // 同上
        for (int i = n - 1; i >= 0; i--) {
            if (chars[i] == c) {
                index = i;
            }
            res[i] = Math.min(res[i], index - i);
        }
        return res;
    }

    // 初版 - 使用队列存储目标字符位置 - 再逐一计算 - 代码较复杂，不易理解
    public int[] shortestToCharV2(String s, char c) {
        int n = s.length();
        char[] chars = s.toCharArray();
        Queue<Integer> targetIndexs = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (chars[i] == c) {
                targetIndexs.offer(i);
            }
        }
        int[] res = new int[n];
        int cur = targetIndexs.remove();
        for (int i = 0; i < n; i++) {
            if (i <= cur) {
                res[i] = cur - i;
            } else if (targetIndexs.isEmpty()) {
                res[i] = i - cur;
            } else if (i < targetIndexs.element()) {
                res[i] = Math.min(i - cur, targetIndexs.element() - i);
            } else {
                cur = targetIndexs.remove();
            }
        }
        return res;
    }

    public static void main(String[] args) {
        ShortestToChar m = new ShortestToChar();
        System.out.println(Arrays.toString(m.shortestToChar("loveleetcode", 'e')));
    }

}
