package mobi.zishun.greedy;

/*
 * 1221. 分割平衡字符串
在一个 平衡字符串 中，'L' 和 'R' 字符的数量是相同的。
给你一个平衡字符串s，请你将它分割成尽可能多的平衡字符串。
注意：分割得到的每个字符串都必须是平衡字符串，且分割得到的平衡字符串是原平衡字符串的连续子串。
返回可以通过分割得到的平衡字符串的 最大数量 。
输入：s = "RLRRLLRLRL"
输出：4
解释：s 可以分割为 "RL"、"RRLL"、"RL"、"RL" ，每个子字符串中都包含相同数量的 'L' 和 'R' 。
链接：https://leetcode-cn.com/problems/split-a-string-in-balanced-strings
 */
public class BalancedStringSplit {
    public int balancedStringSplit(String s) {
        int res = 0;
        int d = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'R') {
                d++;
            } else {
                d--;
            }
            if (d == 0) {
                res++;
            }
        }
        return res;
    }

    public int balancedStringSplitV2(String s) {
        int res = 0;
        int r = 0;
        int l = 0;
        for (int i = 0; i < s.length(); i += 2) {
            if (s.charAt(i) == 'R') {
                r++;
            } else {
                l++;
            }
            if (s.charAt(i + 1) == 'R') {
                r++;
            } else {
                l++;
            }
            if (l == r) {
                res++;
            }
        }
        return res;
    }

}
