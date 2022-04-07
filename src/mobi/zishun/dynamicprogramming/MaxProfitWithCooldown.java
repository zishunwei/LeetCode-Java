package mobi.zishun.dynamicprogramming;

/*
 * 309. 最佳买卖股票时机含冷冻期
给定一个整数数组 prices，其中第  prices[i] 表示第 i 天的股票价格 。
设计一个算法计算出最大利润。在满足以下约束条件下，你可以尽可能地完成更多的交易（多次买卖一支股票）:
卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)。
注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。

示例 1:
输入: prices = [1,2,3,0,2]
输出: 3
解释: 对应的交易状态为: [买入, 卖出, 冷冻期, 买入, 卖出]
示例 2:
输入: prices = [1]
输出: 0
提示：
1 <= prices.length <= 5000
0 <= prices[i] <= 1000
* https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/
 */
public class MaxProfitWithCooldown {
    // dp
    public int maxProfit(int[] prices) {
        int n = prices.length;
        int[][] dp = new int[n][3];
        dp[0][0] = -prices[0]; // 第一天持有股票
        for (int i = 1; i < n; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][2] - prices[i]); // 持有股票（如果i-1天是冷冻期-dp[i - 1][1]情况，i天不能卖出）
            dp[i][1] = dp[i - 1][0] + prices[i]; // 不持有股票，在冷冻期 (当天卖出，随即进入冷冻期，冷冻期影响到i+1天)
            dp[i][2] = Math.max(dp[i - 1][1], dp[i - 1][2]); // 不持有股票，不在冷冻期
        }
        return Math.max(dp[n - 1][1], dp[n - 1][2]);
    }


    // 回溯 - 超出时间限制
    public int maxProfitV2(int[] prices) {
        int n = prices.length;
        dfs(prices, n, 0, 0, false);
        return res;
    }

    private int res = 0;

    private void dfs(int[] prices, int n, int i, int temp, boolean hasStock) {
        if (i >= n) {
            if (temp > res) {
                res = temp;
            }
            return;
        }
        // 当天不操作
        dfs(prices, n, i + 1, temp, hasStock);
        if (!hasStock) { // 当天买入股票
            dfs(prices, n, i + 1, temp - prices[i], true);
        } else { // 当天卖出股票
            dfs(prices, n, i + 2, temp + prices[i], false);
        }
    }


}
