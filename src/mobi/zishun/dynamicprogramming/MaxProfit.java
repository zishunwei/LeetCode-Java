package mobi.zishun.dynamicprogramming;
/*
 * 121. 买卖股票的最佳时机
给定一个数组 prices ，它的第 i 个元素 prices[i] 表示一支给定股票第 i 天的价格。
你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。设计一个算法来计算你所能获取的最大利润。
返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回 0 。
链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock
 */

public class MaxProfit {
    public int maxProfit(int[] prices) {
        int minPrice = Integer.MAX_VALUE;
        int res = 0;
        for (int price : prices) {
            if (price < minPrice) {
                minPrice = price;
            }
            if (res < price - minPrice) {
                res = price - minPrice;
            }
        }
        return res;
    }

    public int maxProfitV2(int[] prices) {
        int n = prices.length;
        if (n < 2) {
            return 0;
        }
        // dp[i][0] 下标为 i 这天结束的时候，不持股，手上拥有的现金数
        // dp[i][1] 下标为 i 这天结束的时候，持股，手上拥有的现金数
        int[][] dp = new int[n][2];
        // 初始化：不持股显然为 0，持股就需要减去第 1 天（下标为 0）的股价
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        for (int i = 1; i < n; i++) {
            // 今天结束为不持股状态
            // 昨天不持股，今天什么都不做；
            // 昨天持股，今天卖出股票（现金数增加）
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            // 今天结束为持股状态
            // 昨天持股，今天什么都不做（现金数与昨天一样）
            // 昨天不持股，今天买入股票（注意：只允许交易一次，因此手上的现金数就是当天的股价的相反数）
            dp[i][1] = Math.max(dp[i - 1][1], -prices[i]);
        }
        return dp[n - 1][0];
    }

    public static void main(String[] args) {
        MaxProfit m = new MaxProfit();
        int[] prices = {7, 1, 5, 3, 6, 4};
        System.out.println(m.maxProfitV2(prices));
    }

}