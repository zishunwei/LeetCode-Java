package mobi.zishun.dynamicprogramming;

/*
 * 123. 买卖股票的最佳时机 III
给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。
设计一个算法来计算你所能获取的最大利润。你最多可以完成 两笔 交易。
注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。

示例 1:
输入：prices = [3,3,5,0,0,3,1,4]
输出：6
解释：在第 4 天（股票价格 = 0）的时候买入，在第 6 天（股票价格 = 3）的时候卖出，这笔交易所能获得利润 = 3-0 = 3 。
     随后，在第 7 天（股票价格 = 1）的时候买入，在第 8 天 （股票价格 = 4）的时候卖出，这笔交易所能获得利润 = 4-1 = 3 。
示例 2：
输入：prices = [1,2,3,4,5]
输出：4
解释：在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
     注意你不能在第 1 天和第 2 天接连购买股票，之后再将它们卖出。
     因为这样属于同时参与了多笔交易，你必须在再次购买前出售掉之前的股票。
提示：
1 <= prices.length <= 105
0 <= prices[i] <= 105
* https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-iii/
 */
public class MaxProfitIII {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        int[][] dp = new int[n][4];
        dp[0][0] = -prices[0];
        dp[0][2] = -prices[0];
        for (int i = 1; i < n; i++) {
            // 第一次交易持有
            // 继续持有或者当天买入
            dp[i][0] = Math.max(dp[i - 1][0], -prices[i]);
            // 第一次交易已卖出，空仓状态
            // 维持原有状态或者当天卖出
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] + prices[i]);
            // 第二次交易持有
            // 继续持有或者第一次交易结束后当天买入
            dp[i][2] = Math.max(dp[i - 1][2], dp[i - 1][1] - prices[i]);
            // 第二次交易已卖出，空仓状态
            // 维持原有状态或者当天卖出
            dp[i][3] = Math.max(dp[i - 1][3], dp[i - 1][2] + prices[i]);
        }
        // 此种状态由前三种状态比较取最大值迭代而来，一定是最大的
        return dp[n - 1][3];
    }

    // 滚动数组 - 空间复杂度O(1) - 状态定义同上
    public int maxProfitV2(int[] prices) {
        int n = prices.length;
        int state1 = -prices[0]; // 第一次交易持有
        int state2 = 0; // 第一次交易已卖出，空仓状态
        int state3 = -prices[0]; // 第二次交易持有
        int state4 = 0; // 第二次交易已卖出，空仓状态
        for (int i = 1; i < prices.length; i++) {
            // 注意先后顺序，被依赖项放在后面实现
            state4 = Math.max(state4, state3 + prices[i]);
            state3 = Math.max(state3, state2 - prices[i]);
            state2 = Math.max(state2, state1 + prices[i]);
            state1 = Math.max(state1, -prices[i]);
        }
        return state4;
    }

    public static void main(String[] args) {
        MaxProfitIII m = new MaxProfitIII();
        int[] prices = {1, 2, 3, 4, 5};
        System.out.println(m.maxProfit(prices));
    }

}
