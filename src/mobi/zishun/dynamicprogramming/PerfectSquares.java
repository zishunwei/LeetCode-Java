package mobi.zishun.dynamicprogramming;

import java.util.ArrayList;
import java.util.List;

/*
 * 279. 完全平方数
给你一个整数 n ，返回 和为 n 的完全平方数的最少数量 。
完全平方数 是一个整数，其值等于另一个整数的平方；
* 换句话说，其值等于一个整数自乘的积。例如，1、4、9 和 16 都是完全平方数，而 3 和 11 不是。

示例 1：
输入：n = 12
输出：3
解释：12 = 4 + 4 + 4
示例 2：
输入：n = 13
输出：2
解释：13 = 4 + 9
提示：
1 <= n <= 104
* https://leetcode-cn.com/problems/perfect-squares/
 */
public class PerfectSquares {
    // DP优化版本
    // dp过程中计算平方数
    public int numSquares(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 0;
        for (int i = 1; i <= n; i++) {
            int min = Integer.MAX_VALUE;
            int num = 1;
            while (num * num <= i) {
                min = Math.min(min, dp[i - num * num]);
                num++;
            }
            dp[i] = min + 1;
        }
        return dp[n];
    }

    // 初版 - 完全套用背包问题模版 - 代码冗余 - 复杂度高
    public int numSquaresV2(int n) {
        List<Integer> squares = new ArrayList<Integer>();
        int num = 1;
        while (num * num <= n) {
            squares.add(num * num);
            num++;
        }
        int[] dp = new int[n + 1];
        dp[0] = 0;
        for (int i = 1; i <= n; i++) {
            for (int square : squares) {
                if (i - square >= 0) {
                    if (dp[i] != 0) {
                        dp[i] = Math.min(dp[i], dp[i - square] + 1);
                    } else {
                        dp[i] = dp[i - square] + 1;
                    }
                }
            }
        }
        return dp[n];
    }

}
