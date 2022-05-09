package mobi.zishun.greedy;

/*
 * 942. 增减字符串匹配
由范围 [0,n] 内所有整数组成的 n + 1 个整数的排列序列可以表示为长度为 n 的字符串 s ，其中:
如果 perm[i] < perm[i + 1] ，那么 s[i] == 'I'
如果 perm[i] > perm[i + 1] ，那么 s[i] == 'D'
给定一个字符串 s ，重构排列 perm 并返回它。如果有多个有效排列perm，则返回其中 任何一个 。

示例 1：
输入：s = "IDID"
输出：[0,4,1,3,2]
示例 2：
输入：s = "III"
输出：[0,1,2,3]
示例 3：
输入：s = "DDI"
输出：[3,2,0,1]
提示：
1 <= s.length <= 105
s 只包含字符 "I" 或 "D"
* https://leetcode.cn/problems/di-string-match/
 */
public class DiStringMatch {
    public int[] diStringMatch(String s) {
        int n = s.length();
        int[] res = new int[n + 1];
        // min为没有使用过的数的最小值
        // max没有使用过的数的最大值
        int min = 0;
        int max = n;
        // 遍历字符串，如果是 I 说明当前的数比下一个数大，直接给当前位赋值max（一定满足条件）
        // 反之亦然
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == 'I') {
                res[i] = min;
                min++;
            } else {
                res[i] = max;
                max--;
            }
        }
        // 字符串遍历结束，此时只剩最后一个值，补齐（此时 min == max）
        res[n] = min;
        return res;
    }

}
