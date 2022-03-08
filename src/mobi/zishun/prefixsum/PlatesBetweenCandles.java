package mobi.zishun.prefixsum;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/*
 * 2055. 蜡烛之间的盘子
给你一个长桌子，桌子上盘子和蜡烛排成一列。给你一个下标从 0 开始的字符串 s ，它只包含字符 '*' 和 '|' ，其中 '*' 表示一个 盘子 ，'|' 表示一支 蜡烛 。
同时给你一个下标从 0 开始的二维整数数组 queries ，其中 queries[i] = [lefti, righti] 表示 子字符串 s[lefti...righti] （包含左右端点的字符）。
对于每个查询，你需要找到 子字符串中 在 两支蜡烛之间 的盘子的 数目 。
如果一个盘子在 子字符串中 左边和右边 都 至少有一支蜡烛，那么这个盘子满足在 两支蜡烛之间 。
比方说，s = "||**||**|*" ，查询 [3, 8] ，表示的是子字符串 "*||**|" 。
子字符串中在两支蜡烛之间的盘子数目为 2 ，子字符串中右边两个盘子在它们左边和右边 都 至少有一支蜡烛。
请你返回一个整数数组 answer ，其中 answer[i] 是第 i 个查询的答案。
输入：s = "**|**|***|", queries = [[2,5],[5,9]]
输出：[2,3]
解释：
- queries[0] 有两个盘子在蜡烛之间。
- queries[1] 有三个盘子在蜡烛之间。
3 <= s.length <= 105
s只包含字符'*' 和'|'。
1 <= queries.length <= 105
queries[i].length == 2
0 <= lefti <= righti < s.length
链接：https://leetcode-cn.com/problems/plates-between-candles
 */
public class PlatesBetweenCandles {
    // 前缀和 - O(n+q)
    public int[] platesBetweenCandles(String s, int[][] queries) {
        // 预处理
        int n = s.length();
        // 盘子前缀和
        int[] preSum = new int[n];
        int sum = 0;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '*') {
                sum++;
            }
            preSum[i] = sum;
        }
        // 记录每个位置左边第一个蜡烛的位置
        int[] left = new int[n];
        int l = -1;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '|') {
                l = i;
            }
            left[i] = l;
        }
        // 记录每个位置右边第一个蜡烛的位置
        int[] right = new int[n];
        int r = -1;
        for (int i = n - 1; i >= 0; i--) {
            if (s.charAt(i) == '|') {
                r = i;
            }
            right[i] = r;
        }
        // 输出答案
        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int[] query = queries[i];
            // 查询左边界最近的右边的蜡烛
            int x = right[query[0]];
            // 查询右边界最近的左边的蜡烛
            int y = left[query[1]];
            // 满足条件的话 - 两个蜡烛索引对应的前缀和的差即为此次查询范围中盘子的数量
            if (x != -1 && y != -1 && x < y) {
                ans[i] = preSum[y] - preSum[x];
            }
        }
        return ans;
    }

    // 栈 + 遍历 - O(n*q) - 超出时间限制
    public int[] platesBetweenCandlesV2(String s, int[][] queries) {
        int n = queries.length;
        char[] chars = s.toCharArray();
        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            int[] query = queries[i];
            Deque<Integer> stack = new LinkedList<>();
            int curRes = 0;
            for (int j = query[0]; j <= query[1]; j++) {
                if (chars[j] == '|') {
                    if (!stack.isEmpty()) {
                        curRes += (j - stack.removeFirst() - 1);
                    }
                    stack.addFirst(j);
                }
            }
            res[i] = curRes;
        }
        return res;
    }

    public static void main(String[] args) {
        PlatesBetweenCandles m = new PlatesBetweenCandles();
        int[][] queries = {{1, 17}, {5, 9}};
        String s = "***|**|*****|**||**|*";
        System.out.println(Arrays.toString(m.platesBetweenCandles(s, queries)));
    }

}
