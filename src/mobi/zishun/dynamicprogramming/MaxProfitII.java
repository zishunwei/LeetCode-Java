package mobi.zishun.dynamicprogramming;

/*
 * 122. 买卖股票的最佳时机 II
给定一个数组 prices ，其中 prices[i] 表示股票第 i 天的价格。

在每一天，你可能会决定购买和/或出售股票。你在任何时候 最多 只能持有 一股 股票。你也可以购买它，然后在 同一天 出售。
返回 你能获得的 最大 利润 。
* https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-ii/
 */
public class MaxProfitII {
    // 贪心
    // 因为可以购买它，然后在 同一天 出售，只要今天的价格比昨天多就将差价作为收益
    // 快进快出操作（只要有收益就今天买明天卖）
    public int maxProfit(int[] prices) {
        int res = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1]) {
                res += prices[i] - prices[i - 1];
            }
        }
        return res;
    }

    // dp
    public int maxProfitV2(int[] prices) {
        int n = prices.length;
        int[][] dp = new int[n][2];
        dp[0][1] = -prices[0];
        for (int i = 1; i < n; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]); // 当天不持股（继续不持股或当天卖出）
            dp[i][1] = Math.max(dp[i - 1][0] - prices[i], dp[i - 1][1]); // 当天持股（继续持股或当天买入）
        }
        return dp[n - 1][0];
    }

}
